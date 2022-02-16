(function (){
    'use strict';

    function textLengthOverCut(ctnt, len, lastTxt) {
        if (len == "" || len == null) { // 기본값
            len = 90;
        }
        if (lastTxt == "" || lastTxt == null) { // 기본값
            lastTxt = "...";
        }
        if (ctnt.length > len) {
            ctnt = ctnt.substr(0, len) + lastTxt;
        }
        return ctnt;
    }

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

        list.forEach(item => {
            const divElem = document.createElement('div');
            faqListElem.appendChild(divElem);
            //글자 수 제한
            let cutctnt = textLengthOverCut(item.faq_ctnt);
            divElem.className = "col  align-items-start";
            divElem.innerHTML = `
                <h5 class="fw-bold">${item.faq_title}</h5>
                <p>${cutctnt}</p>
                <a href="#" class="btn btn-outline-secondary">자세히 보기</a>
            `;
        })
    }

    getFaqList();

})();