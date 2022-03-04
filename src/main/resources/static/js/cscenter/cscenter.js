(function (){
    'use strict';

    const noticeElem = document.querySelector('.noticeboardtable');
    const tbodyElem = noticeElem.querySelector('table tbody');

    //notice리스트 정보 가져오기
    const getNoticeList = () => {
        myFetch.get(`/ajax/cscenter/notice`, list=>{
            makenoticeRecordList(list);
        });
    }

    //notice레코드
    const makenoticeRecordList = list => {
        list.forEach(item => {
            const trElem = document.createElement('tr');
            tbodyElem.appendChild(trElem);
            trElem.innerHTML = `
                <td>${item.inotice}</td>
                <td>[${item.notice_type}] ${item.notice_title}</td>
                <td>${item.notice_rdt}</td>
                `;
            trElem.addEventListener('click', e => {
                location.href = `/cscenter/notice/item?inotice=${item.inotice}`;
            });
        });
    }


    getNoticeList();

})();
