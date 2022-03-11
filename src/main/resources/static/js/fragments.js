const searchFrmElem = document.querySelector('#search_frm');
const searchDivElem = document.querySelector('#submit_div');

searchDivElem.addEventListener('click',()=>{
    searchFrmElem.submit();
});