<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <asset:link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <g:external plugin="jquery-ui" dir="/jquery-ui/themes/ui-lightness/" file="jquery-ui-1.10.4.custom.min.css"/>
    <g:javascript plugin="jquery-ui" src="../jquery-ui/js/jquery-ui-1.10.4.custom.min.js"/>
    <g:javascript src='file-upload/vendor/jquery.ui.widget.js' />
    <g:javascript src='file-upload/jquery.iframe-transport.js' />
    <g:javascript src='file-upload/jquery.fileupload.js' />
    <g:javascript src='file-upload/jquery.fileupload-process.js' />
    <g:javascript src='file-upload/jquery.fileupload-audio.js' />
    <g:javascript src='file-upload/jquery.fileupload-video.js' />
    <g:javascript src='file-upload/jquery.fileupload-validate.js' />
    <g:layoutHead/>
</head>
<body>
<div class="center">
    <a class="logoLink" href="${createLink(uri: '/')}"><img class="logo" src="${resource(dir: "images", file: "LABEL-logo.svg")}"/></a>
</div>
<br/>
<br/>
<g:layoutBody />
<!-- Modal -->
<div class="modal fade" id="imageSearchModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Example barcode images</h4>
            </div>
            <div class="modal-body">
                <ul>
                    <li><a href="${resource(dir: 'images', file:'druglabels/Care One Cold Multi Symptom.png')}" download="Care One Cold Multi Symptom.png">Care One Cold Multi Symptom.png</a></li>
                    <li><a href="${resource(dir: 'images', file:'druglabels/ChildACCUDIAL.png')}" download="ChildACCUDIAL.png">ChildACCUDIAL.png</a></li>
                    <li><a href="${resource(dir: 'images', file:'druglabels/Junior Strength Pain Reliever Grape.png')}" download="Junior Strength Pain Reliever Grape.png">Junior Strength Pain Reliever Grape.png</a></li>
                    <li><a href="${resource(dir: 'images', file:'druglabels/Pain Reliever PM.png')}" download="Pain Reliever PM.png">Pain Reliever PM.png</a></li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="advancedSearchModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Example advanced searches</h4>
            </div>
            <div class="modal-body">
                <ul>
                    <li><a href="${createLink(controller: 'search', action: 'textSearchView', params: [term:'#id:2ba90e61-fe6b-487c-8fbc-847211595345'])}">#id:2ba90e61-fe6b-487c-8fbc-847211595345</a></li>
                    <li><a href="${createLink(controller: 'search', action: 'textSearchView', params: [term:'#generic_name:MELOXICAM'])}">#generic_name:MELOXICAM</a></li>
                    <li><a href="${createLink(controller: 'search', action: 'textSearchView', params: [term:'#openfda.upc:0070038610946'])}">#openfda.upc:0070038610946</a></li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="footer" role="contentinfo"></div>
</body>
</html>
