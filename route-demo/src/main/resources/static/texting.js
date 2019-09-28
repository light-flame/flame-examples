$(document).ready(function () {

    $('html').keyup(function (e) {

        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            $('.line').last().removeClass('line-active');
            $('.line-wrapper').first().clone().appendTo('.code-wrapper').show();
            return;
        }
        if (keycode == '8') {
            const txt = $('.line-active').last().text();
            if (txt.length < 3){
                return;
            }
            $('.line-active').last().text(txt.substr(0, txt.length-1));
            return;
        }

        const char = String.fromCharCode(event.which).toLowerCase();
        $('.line-active').last().append(document.createTextNode(char));
    });


});