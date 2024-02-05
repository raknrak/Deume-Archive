function setCookie(name, value, exp) {
    const date = new Date();
    date.setTime(date.getTime() + exp * 24 * 60 * 60 * 1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

function getCookie(name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value? value[2] : null;
}

function deleteCookie(name) {
    // 만료일을 과거로 변경하여 쿠키 삭제
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;path=/;';
}