<powershell>
Start-Transcript -Path 'c:\vagrant-transcript.log' -Force
Set-StrictMode -Version Latest
Set-ExecutionPolicy Unrestricted

$log = 'c:\vagrant.log'
$now = Get-Date
Add-Content $log -value $now

$systemPath = [Environment]::GetFolderPath([Environment+SpecialFolder]::System)
$sysNative = [IO.Path]::Combine($env:windir, "sysnative")
#http://blogs.msdn.com/b/david.wang/archive/2006/03/26/howto-detect-process-bitness.aspx
$Is32Bit = (($Env:PROCESSOR_ARCHITECTURE -eq 'x86') -and ($Env:PROCESSOR_ARCHITEW6432 -eq $null))
Add-Content $log -value "Is 32-bit [$Is32Bit]"

#http://msdn.microsoft.com/en-us/library/ms724358.aspx
$coreEditions = @(0x0c,0x27,0x0e,0x29,0x2a,0x0d,0x28,0x1d)
$IsCore = $coreEditions -contains (Get-WmiObject -Query "Select OperatingSystemSKU from Win32_OperatingSystem" | Select -ExpandProperty OperatingSystemSKU)
Add-Content $log -value "Is Core [$IsCore]"

# move to home, PS is incredibly complex :)
cd $Env:USERPROFILE
Set-Location -Path $Env:USERPROFILE
[Environment]::CurrentDirectory=(Get-Location -PSProvider FileSystem).ProviderPath

$client = new-object System.Net.WebClient

#check winrm id, if it's not valid and LocalAccountTokenFilterPolicy isn't established, do it
$id = &winrm id
if (($id -eq $null) -and (Get-ItemProperty -Path HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System -name LocalAccountTokenFilterPolicy -ErrorAction SilentlyContinue) -eq $null)
{
    New-ItemProperty -Path HKLM:\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System -name LocalAccountTokenFilterPolicy -value 1 -propertyType dword
    Add-Content $log -value "Added LocalAccountTokenFilterPolicy since winrm id could not be executed"
}

#enable powershell servermanager cmdlets (only for 2008 r2 + above)
if ($IsCore)
{
    DISM /Online /Enable-Feature /FeatureName:MicrosoftWindowsPowerShell /FeatureName:ServerManager-PSH-Cmdlets /FeatureName:BestPractices-PSH-Cmdlets
    Add-Content $log -value "Enabled ServerManager and BestPractices Cmdlets"

    #enable .NET flavors - on server core only -- errors on regular 2008
    DISM /Online /Enable-Feature /FeatureName:NetFx2-ServerCore /FeatureName:NetFx2-ServerCore-WOW64 /FeatureName:NetFx3-ServerCore /FeatureName:NetFx3-ServerCore-WOW64
    Add-Content $log -value "Enabled .NET frameworks 2 and 3 for x86 and x64"
}

# Disable UAC
New-ItemProperty -Path HKLM:Software\Microsoft\Windows\CurrentVersion\Policies\System -Name EnableLUA -PropertyType DWord -Value 0 -Force | Out-Null
Add-Content $log -value "User Access Control (UAC) has been disabled."

# Disable Complex Passwords
# Reference: http://vlasenko.org/2011/04/27/removing-password-complexity-requirements-from-windows-server-2008-core/
$seccfg = [IO.Path]::GetTempFileName()
secedit /export /cfg $seccfg
(Get-Content $seccfg) | Foreach-Object {$_ -replace "PasswordComplexity\s*=\s*1", "PasswordComplexity=0"} | Set-Content $seccfg
secedit /configure /db $env:windir\security\new.sdb /cfg $seccfg /areas SECURITYPOLICY
del $seccfg
Add-Content $log -value "Complex Passwords have been disabled."

# Disable IE ESC
Set-ItemProperty -Path "HKLM:\SOFTWARE\Microsoft\Active Setup\Installed Components\{A509B1A7-37EF-4b3f-8CFC-4F3A74704073}" -Name "IsInstalled" -Value 0 | Out-Null
Set-ItemProperty -Path "HKLM:\SOFTWARE\Microsoft\Active Setup\Installed Components\{A509B1A8-37EF-4b3f-8CFC-4F3A74704073}" -Name "IsInstalled" -Value 0 | Out-Null
Stop-Process -Name Explorer | Out-Null
Add-Content $log -value "IE Enhanced Security Configuration (ESC) has been disabled."

# Set up Internet Explorer Driver: https://code.google.com/p/selenium/wiki/InternetExplorerDriver
If (!(Test-Path "HKLM:\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE")) {
  New-Item -Path "HKLM:\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE"
}
New-ItemProperty -Path "HKLM:\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE" -Name "iexplore.exe" -PropertyType DWord -Value 0 -Force -ErrorAction continue 
Add-Content $log -value "Added FEATURE_BFCACHE"


# http://www.howtogeek.com/howto/windows-vista/enable-or-disable-uac-from-the-windows-vista-command-line/
# $env:windir\System32\reg.exe ADD HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System /v EnableLUA /t REG_DWORD /d 0 /f
# http://stackoverflow.com/a/9573204/20971
New-ItemProperty -Path HKLM:Software\Microsoft\Windows\CurrentVersion\policies\system -Name EnableLUA -PropertyType DWord -Value 0 -Force
Add-Content $log -value "Turned off UAC"

# Disable Shutdown Tracker on Windows 2008/2012 Servers (except Core).
# Step 3: Disable the shutdown tracker
# Reference: http://www.askvg.com/how-to-disable-remove-annoying-shutdown-event-tracker-in-windows-server-2003-2008/
If (!(Test-Path "HKLM:\SOFTWARE\Policies\Microsoft\Windows NT\Reliability")) {
  New-Item -Path "HKLM:\SOFTWARE\Policies\Microsoft\Windows NT\Reliability"
}
New-ItemProperty -Path "HKLM:\SOFTWARE\Policies\Microsoft\Windows NT\Reliability" -Name "ShutdownReasonOn" -PropertyType DWord -Value 0 -Force -ErrorAction continue 
New-ItemProperty -Path "HKLM:\SOFTWARE\Policies\Microsoft\Windows NT\Reliability" -Name "ShutdownReasonUI" -PropertyType DWord -Value 0 -Force -ErrorAction continue 
Set-ItemProperty -Path "HKLM:\SOFTWARE\Policies\Microsoft\Windows NT\Reliability" -Name "ShutdownReasonOn" -Value 0
Set-ItemProperty -Path "HKLM:\SOFTWARE\Policies\Microsoft\Windows NT\Reliability" -Name "ShutdownReasonUI" -Value 0
Add-Content $log -value "Shutdown Tracker has been disabled."

# Disable Automatic Updates
# Reference: http://www.benmorris.me/2012/05/1st-test-blog-post.html
$AutoUpdate = (New-Object -com "Microsoft.Update.AutoUpdate").Settings
$AutoUpdate.NotificationLevel = 1
$AutoUpdate.Save()
Add-Content $log -value "Windows Update has been disabled."

# Enable and configure WinRM (see below)
&winrm quickconfig `-q
&winrm set winrm/config/client/auth '@{Basic="true"}'
&winrm set winrm/config/service/auth '@{Basic="true"}'
&winrm set winrm/config/winrs '@{MaxMemoryPerShellMB="512"}'
&winrm set winrm/config '@{MaxTimeoutms="1800000"}'
&winrm set winrm/config/service '@{AllowUnencrypted="true"}'
Add-Content $log -value "Ran quickconfig for winrm"
Set-Service winrm -startuptype "auto"
Add-Content $log -value "Started winrm"
&netsh firewall set portopening TCP 5985 "Port 5985: winrm"
Add-Content $log -value "Ran firewall config to allow incoming winrm/tcp:5985"

&netsh firewall set portopening tcp 445 smb enable
Add-Content $log -value "Ran firewall config to allow incoming smb/tcp:445"

#run SMRemoting script to enable event log management, etc - available only on R2
$remotingScript = [IO.Path]::Combine($systemPath, 'Configure-SMRemoting.ps1')
if (-not (Test-Path $remotingScript)) { $remotingScript = [IO.Path]::Combine($sysNative, 'Configure-SMRemoting.ps1') }
Add-Content $log -value "Found Remoting Script: [$(Test-Path $remotingScript)] at $remotingScript"
if (Test-Path $remotingScript)
{
    . $remotingScript -force -enable
    Add-Content $log -value 'Ran Configure-SMRemoting.ps1'
}

#chocolatey - standard one line installer doesn't work on Core b/c Shell.Application can't unzip
iex $client.DownloadString('https://chocolatey.org/install.ps1')
#SetX Path "${Env:Path};%systemdrive%\chocolatey\bin" /m
#$env:Path += ';%systemdrive%\chocolatey\bin'
Add-Content $log -value "Installed Chocolatey"

#change admin password
net user Administrator AWS_WIN_PASSWORD
Add-Content $log -value "Changed Administrator password"

Add-Content $log -value "Restarting"
$now = Get-Date
Add-Content $log -value $now

Restart-Computer -force
</powershell>

