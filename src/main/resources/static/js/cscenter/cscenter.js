(function (){
    'use strict';

    const cscenterElem = document.querySelector('.cscenter');
    const noticeElem = cscenterElem.querySelector('.notice');
    const faqElem = cscenterElem.querySelector('.faq');

    //faq리스트 정보 가져오기
    const getFaqList = () => {
        myFetch.get(`/ajax/cscenter/faq`, list=>{
            makeFaqRecordList(list);
        });
    }

    //faq레코드
    const makeFaqRecordList = list => {
        const faqListElem = faqElem.querySelector('.faqboard');
        faqListElem.innerHTML = null;

        for(var i = 0; i<9; i++){
            const divElem = document.createElement('div');
            faqListElem.appendChild(divElem);
            //글자 수 제한
            let cutctnt = textLengthOverCut(list[i].faq_ctnt);
            divElem.className = "px-2 col  align-items-start";
            divElem.innerHTML = `
                    <h5 id="faq_title" class="fw-bold pointer">${list[i].faq_title}</h5>
                    <p>${cutctnt}</p>
                    <a href="/cscenter/faq/faqdetail?ifaq=${list[i].ifaq}" class="btn btn-outline-secondary">자세히 보기</a>
                `;
            const titleElem = divElem.querySelector('#faq_title');
            titleElem.addEventListener('click', e => {
                location.href = `/cscenter/faq/faqdetail?ifaq=${list[i].ifaq}`;
            });


        }
    }

    getFaqList();

})();