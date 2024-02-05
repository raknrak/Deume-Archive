
// search dropdown
function searchFunction() {
    document.getElementById("searchDropdown").classList.toggle("show");
}

window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown_content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}

//modal
if (typeof $.fn.modal !== 'function') {

    (function ($) {
        $.fn.modal = function (options) {
            var defaults = {
                show: false,
                overlayDrop: true
            }
            var options = $.extend(defaults, options);
            var $btnOpen = $('[data-target="modal"]');
            var modal = this;
            if (options.overlayDrop == true) {
                if ($('body').find('.modal_backdrop').length == 0) {
                    $('body').prepend('<div class="modal_backdrop"></div>');
                }
                var overlay = $('.modal_backdrop');
            }
            function modalShow() {
                $('body').addClass('ovh');
                modal.show();
                overlay.show();
                var windowH = $(window).height();
                var modalDialog = modal.find('.modal_content');
                var modalDialogH = modalDialog.height();
                var modalDialogHfix = windowH / 2 - modalDialogH/2 - 40;
                if (modalDialogHfix < 0) {
                    modalDialogHfix = 30;
                }
                modalDialog.css('margin-top', modalDialogHfix);
                $(window).resize(function () {
                    var windowH = $(window).height();
                    var modalDialog = modal.find('.modal_content');
                    var modalDialogH = modalDialog.height();
                    var modalDialogHfix = windowH / 2 - modalDialogH /2 - 40;
                    if (modalDialogHfix < 0) {
                        modalDialogHfix = 30;
                    }
                    modalDialog.css('margin-top', modalDialogHfix);
                });
            }
            if (options.show == true) {
                modalShow();
            }
        }
    })(jQuery);
    $(function () {
        $('[data-target]').click(function () {
            var target = $(this).attr('data-target');
            $('.modal_backdrop').show();
            $("#" + target).modal({ show: true });
            return false;
        });
        $('[data-dismiss="modal"]').click(function () {
            $('body').removeClass('ovh');
            $('.modal_backdrop').hide();
            $('.modal').hide();
            return false;
        });
        $('.modal_content').click(function (event) {
            event.stopPropagation();
        });

        (function ($) {
            $.fn.modal = function (options) {
                var defaults = {
                    show: false,
                    overlayDrop: true
                }
                var options = $.extend(defaults, options);
                var $btnOpen = $('[data-target="modal"]');
                var modal = this;
                if (options.overlayDrop == true) {
                    if ($('body').find('.modal_backdrop').length == 0) {
                        $('body').prepend('<div class="modal_backdrop"></div>');
                    }
                    var overlay = $('.modal_backdrop');
                }
                function modalShow() {
                    $('body').addClass('ovh');
                    modal.show();
                    overlay.show();
                    var windowH = $(window).height();
                    var modalDialog = modal.find('.modal_content');
                    var modalDialogH = modalDialog.height();
                    var modalDialogHfix = windowH / 2 - modalDialogH/2 - 40;
                    if (modalDialogHfix < 0) {
                        modalDialogHfix = 30;
                    }
                    modalDialog.css('margin-top', modalDialogHfix);
                    $(window).resize(function () {
                        var windowH = $(window).height();
                        var modalDialog = modal.find('.modal_content');
                        var modalDialogH = modalDialog.height();
                        var modalDialogHfix = windowH / 2 - modalDialogH /2 - 40;
                        if (modalDialogHfix < 0) {
                            modalDialogHfix = 30;
                        }
                        modalDialog.css('margin-top', modalDialogHfix);
                    });
                }
                if (options.show == true) {
                    modalShow();
                }
            }
        })(jQuery);
        $(function () {
            $('[data-target]').click(function () {
                var target = $(this).attr('data-target');
                $('.modal_backdrop').show();
                $("#" + target).modal({ show: true });
                return false;
            });
            $('[data-dismiss="modal"]').click(function () {
                $('body').removeClass('ovh');
                $('.modal_backdrop').hide();
                $('.modal').hide();
                return false;
            });
            $('.modal_content').click(function (event) {
                event.stopPropagation();
            });

            $('.modal').each(function(){
                if(!$(this).hasClass("click_dimm_none")) {
                    $(this).click(function () {
                        if ($(this).attr('aria-hidden') != 'false') {
                            $('body').removeClass('ovh');
                            $('.modal_backdrop').hide();
                            $('.modal').hide();
                        }
                        return false;
                    });
                } else {
                    $('.modal').click(function (event) {
                        event.stopPropagation();
                    });
                }
            })

        });

    });

}


$(document).ready(function(){


    // 미리보기
    var idx = 0;
    $('.gallery_content > li').on('click', function(e){
        e.preventDefault();
        var me = $(this).index();

        idx = me;

        $('.gallery_content > li').removeClass('active');
        $('.content_box').addClass('preview_show');
        $('.preview_content').show();
        $(this).addClass('active');
        viewItem(idx);
    });
    $(".hover_content .check_box, .like_btn, .ico_btn li").on('click', function(e){
        e.stopPropagation();
    });
    $('.arrow .next').on('click', function(e){
        e.preventDefault();
        idx++ ;
        if (idx > $(".gallery_content > li").length - 1){
            idx = $(".gallery_content > li").length - 1;
        }
        Indigate(idx);
        viewItem(idx);
    });
    $('.arrow .prev').on('click', function(e){
        e.preventDefault();
        idx-- ;
        if (idx < 0){
            idx = 0;
        }
        Indigate(idx);
        viewItem(idx);
    });
    $('.top_btns .close').on('click', function(e){
        $('.preview_content').hide();
        $('.gallery_content > li').removeClass('active');
        $('.content_box').removeClass('preview_show');

    });

});

function Indigate(idx){
    var idx=idx;
    $('.gallery_content > li').removeClass('active');
    $('.gallery_content > li').eq(idx).addClass('active');
}
function viewItem(idx){
    var idx=idx;
    var num = idx + 1;
    $(".previw_img img").remove();

    $(".previw_img").prepend('<img src="../images/gallery_img/img_' + num + '.png" alt="검색결과 이미지 ' + num + '">');

    $(".previw_img").prepend('<img src="../images/gallery_img/img_' + num + '.png" alt="검색결과 이미지 ' + num + '">');

}

// 이미지 체크박스 클릭 시
$('.hover_content input').on('click', function(e){
    e.stopPropagation();
    $(this).parents().toggleClass('on');
});

// 스크랩 저장 버튼 클릭 시
$('.scrap_save_btn').on('click', function(e){
    e.preventDefault();
    $('.popup_alarm.save').show(200);
    $('.popup_alarm.save').fadeOut(1500);
});

// 새 폴더 만들기 클릭 시
$('.folder_plus').on('click', function(e){
    e.preventDefault();
    if ($('.folder_plus').hasClass('off')) {
        $(this).removeClass('off');
        $('.make_folder').removeClass('on')
    } else {
        $(this).addClass('off');
        $('.make_folder').addClass('on')
    }
});
$('.make_folder > .btn').on('click', function(e){
    e.preventDefault();
    $('.folder_plus').removeClass('off');
    $('.make_folder').removeClass('on');
    $('.popup_alarm.folder').show(200);
    $('.popup_alarm.folder').fadeOut(1500);
});


// 스크랩 폴더 선택 시

$('.scrap_check_box input').on('click', function(e){
    e.stopPropagation();
    $(this).parent().toggleClass('on');
});
$('.none_preview .gallery_content > li').on('click',function(e){
    e.preventDefault();
    $(this).removeClass('active');
});

// top btn
var btnTop = $('.btn_top');
btnTop.click(function(){
    $('html,body').animate( { scrollTop:0 },'slow' );
    return false;
});
// scroll시 top button 위치 제어
$(window).on('scroll', function (){
    var nDocH = $(document).height()
    var nWinH = $(window).height()
    var nMaxH = nDocH - nWinH
    var scrollTop = $(window).scrollTop()
    var footerH = $(".footer_wrap").height() // footer 높이값 구하는 함수
    if (scrollTop > 50) {
        btnTop.css({'opacity' : 1})
    } else {
        btnTop.css({'opacity' : 0})
    }
    if (scrollTop > nMaxH - 272 + 20) {
        btnTop.css({'bottom' : scrollTop - (nMaxH - footerH - 20)})

        // $(window).resize(function(){
        //     if (window.innerWidth < 1200) {
        //         btnTop.css({'bottom' : scrollTop - (nMaxH - footerH - 85)})
        //     }
        // }).resize();
    } else {
        btnTop.css({'bottom' : '45px'})
        $(window).resize(function(){
            if (window.innerWidth < 1200) {
                btnTop.css({'bottom' : '75px'})
            }
        }).resize();
    }
})

// 검색창 포커스 최근 검색 리스트 노출
$('.search_input_wrap .search_input').focus(function () {
    if (true) {
        $(this).next().addClass('active');
        $(this).focusout(function () {
            if (true && $(this).val().length >= 1) {
                $(this).next().removeClass('active');
            }
            else if (true && $(this).val().length == 0) {
                $(this).next().removeClass('active');
            }
        });
    }
});

// 상단 탭메뉴에 active
$('.tab_btn li').on('click', function(e){
    e.preventDefault();
    if($('.tab_btn li').hasClass('active')) {
        $('.tab_btn li').removeClass('active');
        $(this).addClass('active');
    }
});

// 정렬하기 버튼 클릭 시
$('.array_btn, .more_btn').on('click',function(e){
    e.preventDefault();
    $(this).siblings('.array_list').fadeIn(50);
});
$('.array_list a').on('click',function(e){
    e.preventDefault();
    $('.array_list').fadeOut(50);
});
// 외부영역 클릭시 팝업 닫기
$(document).mouseup(function (e){
    if($(".array_list").has(e.target).length === 0){
        $(".array_list").hide();
    }
});

// 찜버튼 on/off
$('.like_btn').on('click',function(e){
    e.preventDefault();
    $(this).toggleClass('on');
});