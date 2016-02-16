Continuous Monitoring: Nessus
=============================

We're using Nessus for our continuous monitoring. We host the Nessus server, which goes out and looks at fda.ninja on port 80 for known vulnerabilities. We can schedule the scan to run as often as we like, and it notifies the team of the scan's results via email.

We are monitored for vulnerabilities using the following plugins: 

**General**: A set of checks that gather information about the remote system, such as operating system and service identification, network connectivity, and more.

**CGI Abuses**: Checks for web-based CGI programs with publicly documented vulnerabilities. These checks include SQL injection, Local File Inclusion (LFI), Remote File Inclusion (RFI), Directory Traversal, and more. This family does not include checks for cross-site 
scripting.

**CGI Abuses : XSS**: Checks for web-based CGI programs with publicly documented cross-site scripting (XSS) vulnerabilities.

**Web Servers**: Plugins that check for vulnerabilities in web servers such as Apache HTTP Server, IBM Lotus Domino, Microsoft IIS, and many more.  

_NOTE:  These checks only test the web server software, not the web applications hosted on the server, which are covered by other aspects of our process._