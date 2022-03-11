var menuDivElem = document.getElementsByClassName('menuDiv');

menuDivElem[0].classList.add("clicked");

function menuClick(event) {
    console.log(event.target);
    console.log(event.target.classList);

    if (event.target.classList[1] === "clicked") {
        event.target.classList.remove("clicked");
    } else {
        for (var i = 0; i < menuDivElem.length; i++) {
            menuDivElem[i].classList.remove("clicked");
        }
        event.target.classList.add("clicked");

    }
}

function init() {
    for (var i = 0; i < menuDivElem.length; i++) {
        menuDivElem[i].addEventListener("click", menuClick);
    }
}

init();




var index = 0;   //이미지에 접근하는 인덱스
window.onload = function(){
    slideShow();
}

function slideShow() {
    var i;
    var x = document.getElementsByClassName("banner_inner");  //slide1에 대한 dom 참조
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";   //처음에 전부 display를 none으로 한다.
    }
    index++;
    if (index > x.length) {
        index = 1;  //인덱스가 초과되면 1로 변경
    }
    x[index-1].style.display = "block";  //해당 인덱스는 block으로
    setTimeout(slideShow, 2000);   //함수를 4초마다 호출

}


const domesticBtnElem = document.querySelector('.domesticBtn');
const foreignBtnElem = document.querySelector('.foreignBtn');
const newBtnElem = document.querySelector('.newBtn');
const stedyBtnElem = document.querySelector('.stedyBtn');

// domesticBtnElem.addEventListener('click', (e)=>{
//     bookElem.css('-webkit-animation-name','wrapmove');
// });
wrapmove(0);

domesticBtnElem.addEventListener('click', ()=>{
    var btn = 0;
    wrapmove(btn);
});

foreignBtnElem.addEventListener('click', ()=>{
    var btn = 1;
    wrapmove(btn);
});

newBtnElem.addEventListener('click', ()=>{
    var btn = 2;
    wrapmove(btn);
});

stedyBtnElem.addEventListener('click', ()=>{
    var btn = 3;
    wrapmove(btn);
});

function wrapmove(btn) {
    var i;
    var x = document.getElementsByClassName("wrapsecsion");  //slide1에 대한 dom 참조
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    x[btn].style.display = "block";  //해당 인덱스는 block으로
}

