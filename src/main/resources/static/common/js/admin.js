$(function(){
  //menu
  function checkCurrentGnb() {
    var _className = $('#wrap').attr('class'),
        _afterStr = _className ? _className.split(" ") : '',
        _gnbMenuLink = $('.nav-list > li');

    switch (_afterStr[0]) {
      case 'page01_01':
        _gnbMenuLink.eq(0).find('> a').addClass('active');
        _gnbMenuLink.eq(0).find('.depth2').show();
        _gnbMenuLink.eq(0).find('.depth2').find('li').eq(0).find('a').addClass('active');
        break;
      case 'page01_02':
        _gnbMenuLink.eq(0).find('> a').addClass('active');
        _gnbMenuLink.eq(0).find('.depth2').show();
        _gnbMenuLink.eq(0).find('.depth2').find('li').eq(1).find('a').addClass('active');
        break;
      case 'page02_01':
        _gnbMenuLink.eq(1).find('> a').addClass('active');
        _gnbMenuLink.eq(1).find('.depth2').show();
        _gnbMenuLink.eq(1).find('.depth2').find('li').eq(0).find('a').addClass('active');
        break;
      case 'page02_02':
        _gnbMenuLink.eq(1).find('> a').addClass('active');
        _gnbMenuLink.eq(1).find('.depth2').show();
        _gnbMenuLink.eq(1).find('.depth2').find('li').eq(1).find('a').addClass('active');
        break;
      case 'page02_03':
        _gnbMenuLink.eq(1).find('> a').addClass('active');
        _gnbMenuLink.eq(1).find('.depth2').show();
        _gnbMenuLink.eq(1).find('.depth2').find('li').eq(2).find('a').addClass('active');
        break;
      case 'page03_1':
        _gnbMenuLink.eq(2).find('> a').addClass('active');
        _gnbMenuLink.eq(2).find('.depth2').show();
        _gnbMenuLink.eq(2).find('.depth2').find('li').eq(0).find('a').addClass('active');
        break;
      case 'page03_2':
        _gnbMenuLink.eq(2).find('> a').addClass('active');
        _gnbMenuLink.eq(2).find('.depth2').show();
        _gnbMenuLink.eq(2).find('.depth2').find('li').eq(1).find('a').addClass('active');
        break;
      case 'page03_3':
        _gnbMenuLink.eq(2).find('> a').addClass('active');
        _gnbMenuLink.eq(2).find('.depth2').show();
        _gnbMenuLink.eq(2).find('.depth2').find('li').eq(2).find('a').addClass('active');
        break;
      case 'page03_4':
        _gnbMenuLink.eq(2).find('> a').addClass('active');
        _gnbMenuLink.eq(2).find('.depth2').show();
        _gnbMenuLink.eq(2).find('.depth2').find('li').eq(3).find('a').addClass('active');
        break;
      case 'page04':
        _gnbMenuLink.eq(3).addClass('active');
        break;
      case 'page05_1':
        _gnbMenuLink.eq(4).find('> a').addClass('active');
        _gnbMenuLink.eq(4).find('.depth2').show();
        _gnbMenuLink.eq(4).find('.depth2').find('li').eq(0).find('a').addClass('active');
        break;
      case 'page05_2':
        _gnbMenuLink.eq(4).find('> a').addClass('active');
        _gnbMenuLink.eq(4).find('.depth2').show();
        _gnbMenuLink.eq(4).find('.depth2').find('li').eq(1).find('a').addClass('active');
        break;
      case 'page05_3':
        _gnbMenuLink.eq(4).find('> a').addClass('active');
        _gnbMenuLink.eq(4).find('.depth2').show();
        _gnbMenuLink.eq(4).find('.depth2').find('li').eq(2).find('a').addClass('active');
        break;
      case 'page05_4':
        _gnbMenuLink.eq(4).find('> a').addClass('active');
        _gnbMenuLink.eq(4).find('.depth2').show();
        _gnbMenuLink.eq(4).find('.depth2').find('li').eq(3).find('a').addClass('active');
        break;
      case 'page05_5':
        _gnbMenuLink.eq(4).find('> a').addClass('active');
        _gnbMenuLink.eq(4).find('.depth2').show();
        _gnbMenuLink.eq(4).find('.depth2').find('li').eq(4).find('a').addClass('active');
        break;
      case 'page05_6':
        _gnbMenuLink.eq(4).find('> a').addClass('active');
        _gnbMenuLink.eq(4).find('.depth2').show();
        _gnbMenuLink.eq(4).find('.depth2').find('li').eq(5).find('a').addClass('active');
        break;
    }
  }


  function menuUI(){
    let menuWrap = $('.nav-list');
    let menu = $('.nav-list li');
    let menuBtn = $('.nav-list li a');

    menuBtn.on('mouseover', function(){
      let _this = $(this);
      let _thisMenu = $(this).parents('li');

      _thisMenu.siblings('li').find('> a').removeClass('active');
      _thisMenu.find('> a').addClass('active');
      menu.find('.depth2').hide();
      _thisMenu.find('.depth2').show();
      _this.addClass('active');
    });

    menuWrap.on('mouseleave', function(){
      menu.find('.depth2').hide();
      menuBtn.removeClass('active');
      checkCurrentGnb();
    })
  }
  $(document).ready(function(){
    checkCurrentGnb();
    menuUI();
  });

  // select
  let selectBtn = $('.select-btn');
  function selectUI(){
    let _this = $(this);
    let _cnt = $(this).next();

    if(!_this.hasClass('active')){
      _this.addClass('active');
      _cnt.stop().slideDown('fast');
    } else {
      _cnt.stop().slideUp('fast', function(){
        _this.removeClass('active');
      })
    }
  }
  selectBtn.on('click', selectUI);

  $(document).on("click", "#table-1 td", function() {
    let row = $("#table-1 tr");
    let _this = $(this);
    let _thisRow = _this.parent('tr');

    if(_this.attr('name') == 'nullRow') {
      return;
    }

    if(!_this.hasClass('btn-box')) {
      row.removeClass('on');
      _thisRow.addClass('on');
    } else {
      _thisRow.removeClass('on');
    }
  });

  $(document).on("click", ".table tr.on", function() {
    let row = $("#table-1 tr");
    let _this = $(this);

    row.removeClass('on');
    _this.removeClass('on');
  });

  $(".btn-box").on('click', function(e){
    e.preventDefault();

  });

  // popup 이벤트 on
  onPopEvent();

  // checkbox
  let chkAll = $('.allCheck');

  function checkFunc(){
    let _this = $(this);

    if(_this.prop('checked')){
      _this.parents('table').find('input[type=checkbox]').prop('checked', true);
    } else {
      _this.parents('table').find('input[type=checkbox]').prop('checked', false);
    }
  }

  chkAll.on('click', checkFunc)
})

// popup func
let _html = $('html , body');

function popFunc(){
  let _this = $(this);
  let popData = _this.data('pop');

  _html.css('overflow', 'hidden');
  $('.dim').fadeIn();

  $(".popup[data-pop='" + popData +"']").show();
}

function popClose(){
  let _this = $(this);
  $('.popup').hide();
  _html.css('overflow', 'overlay');
  $('.dim').fadeOut();
}

function onPopEvent() {
  let popBtn = $('.pop-btn');
  let closePop = $('.pop-close');

  popBtn.on('click', popFunc);
  closePop.on('click', popClose);
}

// 이하 퍼블리셔 작업 외

function logout() {
  fetch('/user/logout', {
    method: 'POST',
    body: null
  }).then((response) => response.json())
      .then((data) => {
        if(data.success == true) {
          location.href="/";
        }else {
          alert("로그아웃에 실패하였습니다.");
        }
      })
      .catch((error) => {
        // 에러 처리
        console.log(error);
      })
}

