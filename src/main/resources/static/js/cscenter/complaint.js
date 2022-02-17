{
    complaintElem = document.querySelector('.complaint_form');

    //문의글 제출하기 눌렀을 때
    complaintElem.addEventListener('submit', (e) => {
        const complaintCtntElem = complaintElem.querySelector('div.textarea[name="ctnt"]')
        const ctnt = complaintElem.com_ctnt.value;
        const email = complaintElem.com_email.value;
        console.log(ctnt);

        if(email.length ===0 ){
            alert('메일을 입력해주세요.');
            e.preventDefault();
        }
        else if(ctnt.length === 0){
            alert('문의 내용을 작성해주세요.');
            e.preventDefault();
        }
    })
}