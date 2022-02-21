(function (){
    'use strict';
    //글자 수 제한하는 함

    const faqElem = document.querySelector('.faq');

    //faq리스트 정보 가져오기
    const getFaqList = () => {
        myFetch.get(`/ajax/cscenter/faq`, list=>{
            makeFaqRecordList(list);
        });
    }

    //faq레코드
    const makeFaqRecordList = list => {
        const faqListElem = faqElem.querySelector('.faqboard');

        //각 분류마다 엘레먼트 만들어줌
        const cate1 = faqListElem.querySelector('.faqcategory_1');
        const cate2 = faqListElem.querySelector('.faqcategory_2');
        const cate3 = faqListElem.querySelector('.faqcategory_3');
        const cate4 = faqListElem.querySelector('.faqcategory_4');

        list.forEach(item => {
            const divElem = document.createElement('div');
            switch(item.faq_cate){
                case 1:
                    cate1.appendChild(divElem);
                    categoryrecord(item,divElem);
                break;
                case 2:
                    cate2.appendChild(divElem);
                    categoryrecord(item,divElem);
                    break;
                case 3:
                    cate3.appendChild(divElem);
                    categoryrecord(item, divElem);
                    break;
                case 4:
                    cate4.appendChild(divElem);
                    categoryrecord(item, divElem);
                    break;
            }

            const titleElem = divElem.querySelector('#faq_title');
            titleElem.addEventListener('click', e => {
                location.href = `/cscenter/faq/faqdetail?ifaq=${item.ifaq}`;
            });
        })
    }

    //카테고리안에 아이템마다 레코드 만들어주는 함수
    const categoryrecord = (item, divElem) => {
        divElem.className = "px-2 col  align-items-start";
        divElem.innerHTML = `
                <h5 id="faq_title" class="fw-bold pointer">${item.faq_title}</h5>
                <p>${item.faq_ctnt}</p>
                <a href="/cscenter/faq/faqdetail?ifaq=${item.ifaq}" class="btn btn-outline-secondary">자세히 보기</a>
            `;
    }

    getFaqList();

})();