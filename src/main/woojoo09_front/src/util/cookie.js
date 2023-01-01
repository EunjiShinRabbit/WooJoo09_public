const setCookie = (name, value) => {
  const date = new Date();
  date.setMinutes(date.getMinutes() + 60);
  document.cookie = name + '=' + encodeURIComponent(value) + ';expires=' + date.toUTCString() + ';';
};

const getCookie = (name) => {
  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value ? decodeURIComponent(value[2]) : null;
};

const deleteCookie = (name) => {
  document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}

export {setCookie, getCookie, deleteCookie}