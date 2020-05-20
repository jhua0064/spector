(function ($) {
    "use strict";

    /* ..............................................
       Loader
       ................................................. */
    $(window).on('load', function () {
        $('.preloader').fadeOut();
        $('#preloader').delay(550).fadeOut('slow');
        $('body').delay(450).css({
            'overflow': 'visible'
        });
    });

    /* ..............................................
       Fixed Menu
       ................................................. */

    $(window).on('scroll', function () {
        if ($(window).scrollTop() > 50) {
            $('.main-header').addClass('fixed-menu');
        } else {
            $('.main-header').removeClass('fixed-menu');
        }
    });

    /* ..............................................
       Gallery
       ................................................. */

    $('#slides-shop').superslides({
        inherit_width_from: '.cover-slides',
        inherit_height_from: '.cover-slides',
        play: 5000,
        animation: 'fade',
    });

    $(".cover-slides ul li").append("<div class='overlay-background'></div>");

    /* ..............................................
       Map Full
       ................................................. */

    $(document).ready(function () {
        $(window).on('scroll', function () {
            if ($(this).scrollTop() > 100) {
                $('#back-to-top').fadeIn();
            } else {
                $('#back-to-top').fadeOut();
            }
        });
        $('#back-to-top').click(function () {
            $("html, body").animate({
                scrollTop: 0
            }, 600);
            return false;
        });
    });

    /* ..............................................
       Special Menu
       ................................................. */


    var Container = $('.container');
    //store all filters
    var filters = {};

    function contactVaue(objArr) {
        var value = '';
        for (var prop in objArr) {
            value += objArr[prop];
        }
        return value;
    };

    Container.imagesLoaded(function () {
        var portfolio = $('.recommendation');
        portfolio.on('click', 'button', function (e) {
            $(this).addClass('active').siblings().removeClass('active');
            var btn = $(e.currentTarget);
            //get group key
            var btnGroup = btn.parents('.button-group');
            var filterGroup = btnGroup.attr('data-filter-group');
            //add filter
            filters[filterGroup] = btn.attr('data-filter');

            var filterValue = contactVaue(filters);
            $grid.isotope({
                filter: filterValue
            });
        });
        var $grid = $('.special-rec-list').isotope({
            itemSelector: '.special-grid'
        });
    });


    /* ..............................................
       BaguetteBox
       ................................................. */

    /*baguetteBox.run('.tz-gallery', {
        animation: 'fadeIn',
        noScrollbars: true
    });*/

    /* ..............................................
       Offer Box
       ................................................. */

    $('.offer-box').inewsticker({
        speed: 3000,
        effect: 'fade',
        dir: 'ltr',
        font_size: 13,
        color: '#ffffff',
        font_family: 'Montserrat, sans-serif',
        delay_after: 1000
    });

    /* ..............................................
       Tooltip
       ................................................. */

    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    /* ..............................................
       Owl Carousel Instagram Feed
       ................................................. */

    $('.main-instagram').owlCarousel({
        loop: true,
        margin: 0,
        dots: false,
        autoplay: true,
        autoplayTimeout: 2000,
        autoplayHoverPause: true,
        navText: ["<i class='fas fa-arrow-left'></i>", "<i class='fas fa-arrow-right'></i>"],
        responsive: {
            0: {
                items: 2,
                nav: true
            },
            600: {
                items: 3,
                nav: true
            },
            1000: {
                items: 5,
                nav: true,
                loop: true
            }
        }
    });

    /* ..............................................
       Featured Products
       ................................................. */


    $('.featured-products-box').owlCarousel({
        loop: true,
        margin: 15,
        dots: false,
        autoplay: true,
        autoplayTimeout: 3000,
        autoplayHoverPause: true,
        navText: ["<i class='fas fa-arrow-left'></i>", "<i class='fas fa-arrow-right'></i>"],
        responsive: {
            0: {
                items: 1,
                nav: true
            },
            600: {
                items: 3,
                nav: true
            },
            1000: {
                items: 4,
                nav: true,
                loop: true
            }
        }
    });

    /* ..............................................
       Scroll
       ................................................. */

    $(document).ready(function () {
        $(window).on('scroll', function () {
            if ($(this).scrollTop() > 100) {
                $('#back-to-top').fadeIn();
            } else {
                $('#back-to-top').fadeOut();
            }
        });
        $('#back-to-top').click(function () {
            $("html, body").animate({
                scrollTop: 0
            }, 600);
            return false;
        });
    });


    /* ..............................................
       Slider Range
       ................................................. */

    /*$(function() {
        $("#slider-range").slider({
            range: true,
            min: 0,
            max: 4000,
            values: [1000, 3000],
            slide: function(event, ui) {
                $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
            }
        });
        $("#amount").val("$" + $("#slider-range").slider("values", 0) +
            " - $" + $("#slider-range").slider("values", 1));
    });*/

    /* ..............................................
       NiceScroll
       ................................................. */

    /*$(".brand-box").niceScroll({
        cursorcolor: "#9b9b9c",
    });
    */

    $(function () {

        let wDiv = $('.weather');
        if (wDiv.length > 0) {
            showWeather();
        }

        function showWeather() {
            let localGeo = {"longitude": 144.9633, "latitude": -37.8184};
            /*if(navigator.geolocation){
                navigator.geolocation.getCurrentPosition((position) => {
                    localGeo.longitude = position.coords.longitude;
                    localGeo.latitude = position.coords.latitude;
                });
            }*/
            const icons = {
                'clear-day': "wi-day-sunny",
                'clear-night': "wi-night-clear",
                'rain': "wi-rain",
                'snow': "wi-snow",
                'sleet': "wi-sleet",
                'wind': "wi-strong-wind",
                'fog': "wi-day-fog",
                'cloudy': "wi-cloudy",
                'partly-cloudy-day': "wi-day-cloudy",
                'partly-cloudy-night': "wi-night-alt-cloudy",
                'default': 'wi-cloud'
            };
            const iconMap = new Map(Object.entries(icons));

            let weather = function (data) {
                let city = data['timezone'];

                $('.city span').text(city);

                let current = data['currently'];
                $('.current .icon p').text(current['summary']);
                $('.current .icon span').addClass(iconMap.get(current['icon']))
                $('.current .temp span').html(current['temperature'] + "&deg;");
                $('.current .wind .wind-val').text(current['windSpeed']);
                $('.current .wind .humid').text(current['humid']);
                $('.current .wind .pressure').text(current['pressure']);

                let daily = data['daily']['data'];
                let i = 2;
                $('.day').each(function () {
                    let datWeather = daily[i];
                    let date = new Date(Number(datWeather['time'] + '000')).toDateString().substring(0, 3);
                    $('h3', this).text(date);
                    $('span', this).addClass(iconMap.get(datWeather['icon']));
                    $('.temp', this).html(datWeather['temperatureLow'] + '&deg; - ' + datWeather['temperatureHigh'] + "&deg;")
                    i += 1
                })

            }
            if($('.breadcrumb .active').text() === "Pesticide"){
                weather = function (data) {
                    let current = data['currently'];
                    let wind = parseFloat(current['windSpeed']);
                    const windTxt1 = "The wind speed now is not suitable to spray pesticide!";
                    const  windTxt2 = "The wind speed looks good for pesticide.";
                    let temp = parseFloat(current['temperature']);
                    const tempTxt1 = "The temperature is too high! Do not spray pesticide!";
                    const tempTxt2 = "The temperature looks good for pesticide.";

                    let windTxt = ( wind > 3 && wind < 15) ? windTxt2 : windTxt1;
                    let tempTxt = (temp < 28 ) ? tempTxt2 : tempTxt1;

                    $('.current-wind').text(wind);
                    $('.wind-msg').text(windTxt);
                    $('.current-temp').text(temp);
                    $('.temp-msg').text(tempTxt);
                }
            }
            dealDataFromAjax("/weather", localGeo, weather);

        }

    })

    function dealDataFromAjax(url, dataParam, funcName) {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: url,
            data: JSON.stringify(dataParam),
            dataType: 'json',
            cache: false,
            timeout: 60000,
            success: function (data) {
                funcName(data);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }

}(jQuery));