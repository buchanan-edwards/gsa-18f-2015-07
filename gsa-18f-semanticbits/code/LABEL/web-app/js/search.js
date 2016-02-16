$(document).ready( function () {
    var term = $("#term").val();
    var oTable = $('#labelTable').dataTable({
        searching: false,
        processing: true,
        serverSide: true,
        sPaginationType: "simple_numbers",
        sDom: "<'row'<'col-md-6'i><'col-md-6'<'pull-right'p>>r>t<'row'<'col-md-6'><'col-md-6'>>",
        oLanguage: {
            oPaginate: {
                sPrevious: "<",
                sNext: ">"
            } ,
            sEmptyTable:"No results found. Please update your query."
        },
        ajax: {
            url: "/LABEL/search/textSearch",
            data: {term:term, page:function() { return $('#pageNumber').val();}},
            type: "POST"
        },
        columns: [
            {
                data: "labelDetails",
                "render" : function(data, type, r, meta) {
                    var api = new $.fn.dataTable.Api( meta.settings );
                    var currentPage = api.page();
                    var mterm = term ;
                    if(mterm.indexOf('#') >= 0) {
                        mterm = mterm.replace('#', "%23");
                    }
                    var content = '<a name="labelDetailsLink" class="labelDetails" href="details?id='+data.id+'&term='+mterm+'&page='+currentPage+'">'+data.title+'</a><p name="labelDescription" class="labelDescription">'+data.description+'</p>';
                    return content;
                }
            }
        ],
        "fnDrawCallback": function(oSettings) {
            $('body').animate({
                scrollTop: $("#labelTable_wrapper").offset().top
            }, 1000);
            if ($('#labelTable tbody tr td').html() == "No results found. Please update your query.") {
                $('.dataTables_paginate').hide();
            }
        },
        "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
            if(iTotal == 5000) {
              return "Showing "+iStart+"-"+iEnd+" of "+iTotal+"+ labels";
            }
            return "Showing "+iStart+"-"+iEnd+" of "+iTotal+" labels";
        },
        "initComplete": function (oSettings) {
            var pageNumber = $('#pageNumber').val();
            if($.isNumeric(pageNumber)){
                var oTable = this;
                oTable.fnPageChange(parseInt(pageNumber));
                $('#pageNumber').val('');
            }
        }
    });

    //To handle advance search
    $("#termText").autocomplete({
        autoFocus: true,
        source: function (request, response) {
            if(request.term.startsWith("#") && request.term.length > 1 && request.term.indexOf(':') == -1){
                $.ajax({
                    dataType:"JSON",
                    type:"GET",
                    data:{term:request.term},
                    url:"/LABEL/search/autocomplete",
                    success: function(data){
                        response(data);
                    },
                    error: function(xhr, status, error){

                    }
                });
            }
        },
        select: function(event, ui) {
            $("#termText").val("#"+ui.item.label+":");
            return false;
        }
    });
} );
