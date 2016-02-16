// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self
//= require bootstrap

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}


$(document).ready( function () {

    $("#uploadBarCodeButton").on('click', function(event) {
        $("#uploadBarCode, #searchText").show();
        $("#uploadBarCodeButton, #searchButton, #termText, #advancedSearchHelpText").hide();
        $("#helpLink").attr('data-target', '#imageSearchModal');
        event.preventDefault();
    });

    $("#searchText").on('click', function(event) {
        $("#termText, #uploadBarCodeButton, #searchButton, #advancedSearchHelpText").show();
        $("#uploadBarCode, #searchText").hide();
        $("#helpLink").attr('data-target', '#advancedSearchModal');
        //Remove two <br> elements before error span
        $(".text-danger").prev().remove()
        $(".text-danger").prev().remove()
        //Remove error span
        $(".text-danger").remove();
        event.preventDefault();
    });

    $(".nav li").on("click", function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });
});
