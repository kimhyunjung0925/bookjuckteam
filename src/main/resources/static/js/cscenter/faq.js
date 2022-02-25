(function (){
    'use strict';

    //faq리스트 정보 가져오기
    const getFaqList = () => {
        myFetch.get(`/ajax/cscenter/faq`, list=>{
            makeFaqRecordList(list);
        });
    }

    //faq레코드
    const makeFaqRecordList = list => {
        const faqMenuElem = document.querySelector('#faq_menu');
        const faqtableElem = document.querySelector('#faqboardtable');
        //각 분류마다 엘레먼트 만들어줌
        const cate1 = faqMenuElem.querySelector('.faqcategory_1');
        const cate2 = faqMenuElem.querySelector('.faqcategory_2');
        const cate3 = faqMenuElem.querySelector('.faqcategory_3');
        const cate4 = faqMenuElem.querySelector('.faqcategory_4');

        cate1.addEventListener("click", (e) => {

        })


    }

    //카테고리안에 아이템마다 레코드 만들어주는 함수
    const categoryrecord = (item, faqtableElem) => {
        faqtableElem.innerHTML = `
                <h5 id="faq_title" class="fw-bold pointer">${item.faq_title}</h5>
                <pre>${item.faq_ctnt}</pre>
                <hr>
        `;

    }

    getFaqList();

})();





//
// (function (){
//     'use strict';
//
//     const cscenterElem = document.querySelector('.cscenter');
//     const faqElem = cscenterElem.querySelector('.faq');
//
//     //faq리스트 정보 가져오기
//     const getFaqList = () => {
//         myFetch.get(`/ajax/cscenter/faq`, list=>{
//             makeFaqRecordList(list);
//         });
//     }
//
//     //faq레코드
//     const makeFaqRecordList = list => {
//         const faqListElem = faqElem.querySelector('.faqboard');
//
//         for(var i = 0; i<9; i++){
//             const divElem = document.createElement('div');
//             faqListElem.appendChild(divElem);
//
//             divElem.className = " px-2 col  align-items-start";
//             divElem.innerHTML = `
//                 <strong class="head back_color_grey2">${list[i].faq_title}</strong>
//                 <div class="body m-t-10" style="display: none">
//                     <pre>${list[i].faq_ctnt}</pre>
//                     <div class="color_grey font_size_small">원하는 답변을 얻지 못하셨나요 ? [1:1상담]을 이용해주세요.</div>
//                 </div>
//
//                 <hr>
//             `;
//
//         }
//         const head = document.getElementsByClassName("head");
//
//         for (var j = 0; j < head.length; j++) {
//             head[j].addEventListener("click", function() {//클릭이벤트를 추가한다.
//                 this.classList.toggle("active");// 클래스를 추가하거나 삭제함.
//                 var body = this.nextElementSibling; //현재 아코디언의 다음노트를 가져온다.
//
//                 if (body.style.display === "none") { //출력모드가 블럭인지 none인지 체크한다.
//                     body.style.display = "block"; }
//                 else {
//                     body.style.display = "none";
//                 }
//             });
//         }
//
//
//     }
//     getFaqList();
//
// })();