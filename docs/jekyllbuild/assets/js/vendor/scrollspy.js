(function () {
  function ScrollSpy(wrapper, opt) {

    this.doc = document;
    this.wrapper = (typeof wrapper === 'string') ? this.doc.querySelector(wrapper) : wrapper;
    this.nav = this.doc.querySelectorAll(opt.nav);

    this.contents = [];
    this.win = window;

    this.winH = this.win.innerHeight;

    this.className = opt.className || 'active';

    this.callback = opt.callback || function () {

    };
  }

  ScrollSpy.prototype.init = function () {
    this.contents = this.getContents();
    this.attachEvent();
  };

  ScrollSpy.prototype.getContents = function () {
    var targetList = [];

    for (var i = 0, max = this.nav.length; i < max; i++) {
      var nav = this.nav[i];
      var href = nav.href;

      var id = href.split('#')[1];
      var elementById = this.doc.getElementById(id);

      targetList.push(elementById);
    }

    return targetList;
  };

  ScrollSpy.prototype.addClickListener = function (nav) {
    $(nav).on('click', function (e) {
      // This sets the hash
      var target = this.hash;

      // Prevent default anchor click behavior
      e.preventDefault();

      // The grabs the height of my header
      var navOffset = 74;

      // Animate The Scroll
      $('html, body').animate({
        scrollTop: $(this.hash).offset().top - navOffset,
      }, 600, function () {

        // Adds hash to end of URL
        return window.history.pushState(null, null, target);

      });
    });
  };

  ScrollSpy.prototype.attachEvent = function () {
    this.win.addEventListener('load', (function () {
      this.spy(this.callback);
    }).bind(this));

    var scrollingTimer;

    this.win.addEventListener('scroll', (function () {
      if (scrollingTimer) {
        clearTimeout(scrollingTimer);
      }

      var _this = this;

      scrollingTimer = setTimeout(function () {
        _this.spy(_this.callback);
      }, 10);
    }).bind(this));

    var resizingTimer;

    this.win.addEventListener('resize', (function () {
      if (resizingTimer) {
        clearTimeout(resizingTimer);
      }

      var _this = this;

      resizingTimer = setTimeout(function () {
        _this.spy(_this.callback);
      }, 10);
    }).bind(this));
  };

  ScrollSpy.prototype.spy = function (cb) {
    var elems = this.getElemsViewState();

    this.markNav(elems);

    if (typeof cb === 'function') {
      cb(elems);
    }
  };

  ScrollSpy.prototype.getElemsViewState = function () {
    var elemsInView    = [],
        elemsOutView   = [],
        viewStatusList = [];

    for (var i = 0, max = this.contents.length; i < max; i++) {
      var currentContent = this.contents[i],
          isInView       = this.isInView(currentContent);

      if (isInView) {
        elemsInView.push(currentContent);
      }
      viewStatusList.push(isInView);
    }

    return {
      inView: elemsInView,
      outView: elemsOutView,
      viewStatusList: viewStatusList,
    };
  };

  ScrollSpy.prototype.isInView = function (el) {
    var winH         = this.winH,
        scrollTop    = this.doc.documentElement.scrollTop || this.doc.body.scrollTop,
        scrollBottom = scrollTop + winH,
        rect         = el.getBoundingClientRect(),
        elTop        = rect.top + scrollTop,
        elBottom     = elTop + el.offsetHeight;

    return (elBottom > scrollTop) && (elTop < scrollBottom);
  };

  ScrollSpy.prototype.markNav = function (elems) {
    var navItems        = this.nav,
        isAlreadyMarked = false;

    this.marked = -1;
    var oldMarked = this.marked;

    for (var i = 0, max = navItems.length; i < max; i++) {
      if (elems.viewStatusList[i] && !isAlreadyMarked) {
        isAlreadyMarked = true;
        this.marked = i;
      }
    }

    if (oldMarked !== this.marked) {
      $(navItems).removeClass(this.className);
    }
    if (this.marked > -1){
      $(navItems[this.marked]).addClass(this.className);
    }

  };

  window.ScrollSpy = ScrollSpy;

})();
