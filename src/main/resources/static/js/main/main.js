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
    setTimeout(slideShow, 4000);   //함수를 4초마다 호출

}

$(document).ready(function(pointersecsion){
    var bg=['red','yellow','green','blue','aqua'];
    $('.img_box').css({
        backgroundColor:function(index){
            return bg[index];
        }
    })

    /*1.but_1을 클릭하면 img_wrap가 left:0
    2.but_2을 클릭하면 img_wrap가 left:-700
    3.but_3을 클릭하면 img_wrap가 left:-1400
    4.but_4을 클릭하면 img_wrap가 left:-2100
    5.but_5을 클릭하면 img_wrap가 left:-2800*/

    /*$('#but_1').click(function(){
        $('#img_wrap').animate({
            left:0
        });
    });

    $('#but_2').click(function(){
        $('#img_wrap').animate({
            left:-700
        });
    });

    $('#but_3').click(function(){
        $('#img_wrap').animate({
            left:-1400
        });
    });

    $('#but_4').click(function(){
        $('#img_wrap').animate({
            left:-2100
        });
    });

    $('#but_5').click(function(){
        $('#img_wrap').animate({
            left:-2800
        });
    });*/

    $('.but').click(function(){
        //클릭한 but의 순서값(index)을 저장 활용하기
        var i=$(this).index();

        /*alert(i);*/
        $('.but').css({
            backgroundColor:'black'
        });
        $(this).css({
            backgroundColor:'red'
        });
        /*this
        =>마우스 이벤트 적용되는 객체
        =>이미 선택되어진 객체를 다시 선택할때 자기자신을 의미*/

        $('#img_wrap').animate({
            left:-700*i
        });
    });



}); /*end*/
