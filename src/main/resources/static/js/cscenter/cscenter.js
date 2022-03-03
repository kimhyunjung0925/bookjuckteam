(function (){
    'use strict';

    const noticeElem = document.querySelector('#faqboardtable');

    //faq리스트 정보 가져오기
    const getFaqList = () => {
        myFetch.get(`/ajax/cscenter/faq`, list=>{
            makeFaqRecordList(list);
        });
    }

    //faq레코드
    const makeFaqRecordList = list => {

        //기본으로 카테고리1 애들이 뜨도록 설정
        categoryrecord(list,faqTableElem,1);

        //카테고리별로 클릭 했을 때 마다 레코드 지우고 다시 생성
        cate1.addEventListener("click", (e) => {
            faqTableElem.innerHTML= null;
            categoryrecord(list, faqTableElem,1);
        });

        cate2.addEventListener("click", (e) => {
            faqTableElem.innerHTML= null;
            categoryrecord(list, faqTableElem,2);
        });

        cate3.addEventListener("click", (e) => {
            faqTableElem.innerHTML= null;
            categoryrecord(list, faqTableElem,3);
        });

        cate4.addEventListener("click", (e) => {
            faqTableElem.innerHTML= null;
            categoryrecord(list, faqTableElem,4);
        });
    }



    //faqboardtable에 맞는 카테고리별로 레코드 만들어주는 메소드
    const categoryrecord = (list, faqTableElem, catenum) => {
        list.forEach(item => {
            if (item.faq_cate === catenum) { //카테고리 분기를 만들어준다
                //faqboardtable 안에 div하나 만들어줌
                const divElem = document.createElement('div');
                faqTableElem.appendChild(divElem);
                divElem.innerHTML = `
                     <!--맨마지막이나 맨 앞에 hr 없애고 싶음. 그리고 안에 스크롤 생기는거 없애는 방법-->
                    <h6 id="faq_title" class="head fw-bold pointer">${item.faq_title}</h6>
                    <div class="body m-t-10 m-l-20">
                        <pre class="faqctnt">${item.faq_ctnt}</pre>
                        <div class="color_grey font_size_small m-b-10">원하는 답변을 얻지 못하셨나요 ? [1:1상담]을 이용해주세요.</div>
                    </div>
                    <hr class="m-0 p-0">
                `;

            }
        });

    }

    getFaqList();

})();
