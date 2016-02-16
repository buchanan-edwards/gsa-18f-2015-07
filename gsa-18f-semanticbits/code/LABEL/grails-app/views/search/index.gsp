<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
    <title><g:message code="home.page.title"/></title>
    <meta name="layout" content="main"/>
    <g:javascript src="search.js"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <g:form controller="search" action="textSearchView" method="GET">
                <div>
                    <input type="text" class="searchBox" name="term" placeholder="${message(code:"search.prompt.text")}" id="termText" size="35" autocomplete="off"/>
                    <span id="uploadBarCode" style="display: none">
                        &nbsp;
                        <span class="btn btn-primary btn-sm fileinput-button" id="overrideFile">
                            <span>Upload</span>
                            <!-- The file input field used as target for the file upload widget -->
                            <input id="fileupload" type="file" name="files[]" accept="image/*;capture=camera">
                            <!-- The container for the uploaded files -->

                        </span>
                        <span id="filesUpdate" class="files"></span>
                        <div id="progress" class="progress" style="display: none">
                            <div class="progress-bar"></div>
                        </div>
                    </span>
                    <input type="submit" id="searchButton" value="${message(code:"search.button.label")}" class="btn btn-primary"/>
                    <span title="${message(code: 'advancedSearch.help.text')}">
                        <a data-toggle="modal" data-target="#advancedSearchModal" id="helpLink"><i class="fa fa-question-circle"></i></a>
                    </span>
                    <br/>
                    <div class="hr-center-heading"><span>OR</span></div>
                    <br/>
                    <button class="btn btn-primary" id="uploadBarCodeButton" ><g:message code="upload.barcode.link.text" /></button>
                    <button class="btn btn-primary" id="searchText" style="display: none"><g:message code="search.term.link.text" /></button>

                </div>
            </g:form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        'use strict';
        // Change this to the location of your server-side upload handler:
        var url = 'search/processBarCodeImage';
        var jqXHR = null

        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            acceptFileTypes: /(\.|\/)(jpg|jpeg|gif|png)$/i,
            done: function (e, data) {
                $('#overrideFile').hide();
                $.each(data.result, function (index, file) {
                    $("#filesUpdate").html("");
                    $('#progress').hide();
                    $('#termText').val("#openfda.upc:" + data.result.code);
                    $('#termText').show();
                    $('#searchButton').click();
                });
            },
            progressall: function (e, data) {
                $('#progress').show();
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                        'width',
                        progress + '%'
                );
            },
            error: function (e, data) {
                $('#progress').hide();
                $("#filesUpdate").html("");
                $("#filesUpdate")
                        .append("<br>")
                        .append("<br>")
                        .append($('<span class="text-danger"/>').text("Unable to decode barcode. Please upload an alternate image or manually type the barcode into the search field like this: #openfda.upc:0123456789"));
            }
        }).on('fileuploadprocessalways', function (e, data) {
                    var index = data.index,
                            file = data.files[index]
                    if (file.error) {
                        if(file.error == "TYPE_NOT_ALLOWED") {
                            file.error = "File type not supported. Please select a [jpg|jpeg|gif|png] file.";
                        }
                        $("#filesUpdate").html("");
                        $("#filesUpdate")
                                .append("<br>")
                                .append("<br>")
                                .append($('<span class="text-danger"/>').text(file.error));
                    }
                })
    });
</script>
</body>
</html>
