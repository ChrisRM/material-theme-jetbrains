$.when($.ready).then(() => {
  const jekyllApp = {

    setBodyClass(css) {
      const {classList} = document.getElementsByTagName('body')[0];
      classList.remove('oceanic', 'darker', 'lighter', 'palenight');
      classList.add('index', css);
    },
    setTheme(theme) {
      this.setBodyClass(theme.className);
    },
    showMenu() {
      $('.menu').toggleClass('active');
    },
    scrollSpyOn(wrapper, opt) {
      new window.ScrollSpy(wrapper, opt).init();
    },

    init() {
      // todo load from local storage
      this.setBodyClass('oceanic');

      $('.toc').pushpin({
        top: 284,
        offset: 64,
      });

      // custom scroll spy (is that necessary?)
      this.scrollSpyOn('.doc', {nav: '.toc a'});
    },
  };

  // init
  jekyllApp.init();

  window.jekyllApp = jekyllApp;

  //
  //   // sidebar toggling responsive
  //   $('.sidebar-toggle').click(function () {
  //     $('.sidebar').toggleClass('open');
  //   });
  //
  //   // Back to top link
  //   $('.backtotop').click(function () {
  //     $('body,html').animate({
  //       scrollTop: 0,
  //     }, 500);
  //     return false;
  //   });
  //
  //   // Responsive Menu
  //   $('.toggle-link').click(function () {
  //     $('.menu').toggleClass('active');
  //   });
  //
  //   // Search
  //   $('a[href="#search"]').click(function () {
  //     $('#search').addClass('open');
  //     $('#search input').focus();
  //     $('body').addClass('overflow');
  //   });
  //
  //   $('#search, #search button.close').on('click keyup', function (event) {
  //     if (event.target == this || event.target.className == 'close' || event.keyCode == 27) {
  //       $(this).removeClass('open');
  //       $('body').removeClass('overflow');
  //     }
  //   });
  //
  //   var cookieLayout = getCookie('switch-style');
  //   if (cookieLayout != '') {
  //     $('#switch-style').attr('href', '/css/' + cookieLayout + '.css');
  //   }
  //
  //   // Style Switch index layout
  //   $('.switch div').click(function () {
  //     var id = $(this).attr('id');
  //
  //     // adjust link here
  //     $('#switch-style').attr('href', 'css/' + id + '.css');
  //     setCookie('switch-style', id, 365);
  //   });
  //
  //   var cookieColor = getCookie('color-change');
  //   if (cookieColor != '') {
  //     $('#color-change').attr('href', 'css/main_' + cookieColor + '.css');
  //   }
  //
  //   // Style Switch color scheme
  //   $('.color-change img').click(function () {
  //     var id = $(this).attr('id');
  //
  //     // adjust link here
  //     $('#color-change').attr('href', 'css/main_' + id + '.css');
  //     setCookie('color-change', id, 365);
  //   });
  //
  //   if ($('.articles').find('div.wrapper').length != 0) {
  //     $('.switch').hide();
  //   } else {
  //     $('.switch').show();
  //   }
  // });
  //
  // $(window).scroll(function () {
  //   // scroll stuff
  // });
  //
  // function setCookie(cname, cvalue, exdays) {
  //   var d = new Date();
  //   d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  //   var expires = 'expires=' + d.toUTCString();
  //   document.cookie = cname + '=' + cvalue + '; ' + expires;
  // }
  //
  // function getCookie(cname) {
  //   var name = cname + '=';
  //   var ca = document.cookie.split(';');
  //   for (var i = 0; i < ca.length; i++) {
  //     var c = ca[i];
  //     while (c.charAt(0) == ' ') c = c.substring(1);
  //     if (c.indexOf(name) == 0) {
  //       return c.substring(name.length, c.length);
  //     }
  //   }
  //   return '';
  // }
  //
  // function checkCookie(cname) {
  //   var cookie = getCookie(cname);
  //   if (cookie != '') {
  //     alert('cookie is' + cookie);
  //   } else {
  //     alert('cookie unset');
  //   }
  // }
  //
  // // lightbox stuff
  // $(function () {
  //   var activityIndicatorOn  = function () {
  //         $('<div id="imagelightbox-loading"><div></div></div>').appendTo('body');
  //       },
  //       activityIndicatorOff = function () {
  //         $('#imagelightbox-loading').remove();
  //       },
  //
  //       // OVERLAY
  //       overlayOn            = function () {
  //         $('<div id="imagelightbox-overlay"></div>').appendTo('body');
  //       },
  //       overlayOff           = function () {
  //         $('#imagelightbox-overlay').remove();
  //       },
  //
  //       // CLOSE BUTTON
  //       closeButtonOn        = function (instance) {
  //         $('<button type="button" id="imagelightbox-close" title="Close"><i class="fa
  // fa-times-circle"></i></button>') .appendTo('body') .on('click touchend', function () { $(this).remove();
  // instance.quitImageLightbox(); return false; }); }, closeButtonOff       = function () {
  // $('#imagelightbox-close').remove(); },  // CAPTION captionOn            = function () { var description =
  // $('a[href="' + $('#imagelightbox').attr('src') + '"] img').attr('alt'); if (description.length > 0) { $('<div
  // id="imagelightbox-caption">' + description + '</div>').appendTo('body'); } }, captionOff           = function () {
  // $('#imagelightbox-caption').remove(); },  // NAVIGATION navigationOn         = function (instance, selector) { var
  // images = $(selector); if (images.length) { var nav = $('<div id="imagelightbox-nav"></div>'); for (var i = 0; i <
  // images.length; i++) nav.append('<button type="button"></button>'); nav.appendTo('body'); nav.on('click touchend',
  // function () { return false; }); var navItems = nav.find('button'); navItems.on('click touchend', function () { var
  // $this = $(this); if (images.eq($this.index()).attr('href') != $('#imagelightbox').attr('src')) {
  // instance.switchImageLightbox($this.index()); } navItems.removeClass('active');
  // navItems.eq($this.index()).addClass('active'); return false; }) .on('touchend', function () { return false; }); }
  // }, navigationUpdate     = function (selector) { var items = $('#imagelightbox-nav button');
  // items.removeClass('active'); items.eq($(selector).filter('[href="' + $('#imagelightbox').attr('src') +
  // '"]').index(selector)) .addClass('active'); }, navigationOff        = function () {
  // $('#imagelightbox-nav').remove(); },  // ARROWS arrowsOn             = function (instance, selector) { var $arrows
  // = $('<button type="button" class="imagelightbox-arrow imagelightbox-arrow-left"></button><button type="button"
  // class="imagelightbox-arrow imagelightbox-arrow-right"></button>'); $arrows.appendTo('body'); $arrows.on('click
  // touchend', function (e) { e.preventDefault(); var $this   = $(this), $target = $(selector + '[href="' +
  // $('#imagelightbox').attr('src') + '"]'), index   = $target.index(selector);  if
  // ($this.hasClass('imagelightbox-arrow-left')) { index = index - 1; if (!$(selector).eq(index).length) { index =
  // $(selector).length; } } else { index = index + 1; if (!$(selector).eq(index).length) { index = 0; } }
  // instance.switchImageLightbox(index); return false; }); }, arrowsOff            = function () {
  // $('.imagelightbox-arrow').remove(); };  //	ALL COMBINED var selectorF = 'a[data-imagelightbox="f"]';  var
  // instanceF = $(selectorF).imageLightbox({ onStart: function () { overlayOn(); closeButtonOn(instanceF);
  // arrowsOn(instanceF, selectorF); }, onEnd: function () { overlayOff(); captionOff(); closeButtonOff(); arrowsOff();
  // activityIndicatorOff(); }, onLoadStart: function () { captionOff(); activityIndicatorOn(); }, onLoadEnd: function
  // () { captionOn(); activityIndicatorOff(); $('.imagelightbox-arrow').css('display', 'block'); }, });
});

