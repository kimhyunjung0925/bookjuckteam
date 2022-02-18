{
    const infoElem = document.querySelector('#info-frm');
    const emailBtnElem = document.querySelector('#email_btn');
    const emailTextElem = document.querySelector('#email_text');

    emailBtnElem.addEventListener('click',()=>{
        emailTextElem.removeAttribute('readonly');
        emailBtnElem.value = "인증하기";

        emailBtnElem.addEventListener('click',()=> {
            if(confirm('인증번호를 발송 하시겠습니까?')) {

            }
        });
    });

    const makeInputText = () => {

    }









}

