$(function () {

    /* toggle default text in input field */

    $('input').data('holder', $('input').attr('placeholder'));
    $('input').focusin(function () {
        $(this).attr('placeholder', '');
    });
    $('input').focusout(function () {
        $(this).attr('placeholder', $(this).data('holder'));
    });

    /* instantiate jquery tabs */

    $("#tabs").tabs({ heightStyle: "auto" }, { active: 0 });

    /* initialize testimonial-slider */
    $('.testimonial-slider').bxSlider({
        auto: true,
        speed: 1200,
        pause: 8000,
        mode: 'fade',
        pager: false,
        controls: false
    });

})