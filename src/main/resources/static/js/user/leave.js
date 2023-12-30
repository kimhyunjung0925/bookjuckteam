{

    // form 태그
    const infoElem = document.querySelector("#info-frm");

    // if(form 태그) 안에서 발생하는 이벤트들
    if (infoElem) {
        const emailBtnElem = document.querySelector("#email_btn");
        const emailTextElem = document.querySelector("#email_text");
        const emailDivElem = document.querySelector("#email_div");
        let emailName = document.querySelector("#email_name");
        let emailNameTemp;

        let resendKey; //이메일 재전송을 위한

        // email 변경하기 버튼 클릭시 이벤트
        emailBtnElem.addEventListener("click", () => {
            if (confirm("인증번호를 발송 하시겠습니까?")) {
                makeInputText(emailTextElem.value);
                const param = {
                    currentEmail : emailTextElem.value
                }

                myFetch.post(`/sendEmail`, data => {
                    console.log(data);
                    matchKey(data.result);
                    resend();
                },param)
            }
        });

        let keyElem;
        let btnElem;

        // 변경하기 button 클릭 => 인증하기 Text로 변경
        const makeInputText = (emailName) => {
            emailDivElem.innerHTML = null;
            emailDivElem.innerHTML =
                `<div class='flex-direc-column w-100'><input type='text' readonly id="putedMail" name='email' class='text_box m-b-5 back_color_grey4' value=${emailName} >` +
                `<div class='flex w-100 '><input type='text' id="keyElem" class='text_box m-r-10'> ` +
                `<input type='button' value='재전송' id="resendKey" class='btn m-r-5'> ` +
                `<input type='button' value='인증완료' class='btn btnElem' ></div></div>`;
            resendKey = document.querySelector('#resendKey');
            emailNameTemp = document.querySelector('#putedMail');

        };


        function matchKey(data){

            let suckey = document.querySelector('#sucKey'); //이메일 인증 실패,성공 알림 innertext
            btnElem = document.querySelector('.btnElem');
            btnElem.addEventListener('click', () => {
                keyElem = document.querySelector('#keyElem');
                if(Number(keyElem.value) === data){
                    suckey.innerHTML="이메일 인증 성공하였어요.";
                } else {
                    console.log(keyElem.value === data)
                    suckey.innerHTML="이메일 인증 실패하였어요. 다시 입력해주세요.";
                }
            })


        }

    }






}