{
    // form 태그
    const infoElem = document.querySelector("#info-frm");

    // if(form 태그) 안에서 발생하는 이벤트들
    if (infoElem) {
        const emailBtnElem = document.querySelector("#email_btn");
        const emailTextElem = document.querySelector("#email_text");
        const emailDivElem = document.querySelector("#email_div");

        // email 변경하기 버튼 클릭시 이벤트
        emailBtnElem.addEventListener("click", () => {
            emailTextElem.removeAttribute("readonly");
            changeText(); // Text 변환 이벤트 함수
        });

        // 변경하기 button 클릭시 Text 변환 => 이벤트 발생
        function changeText() {
            const emailbtn = (emailBtnElem.value = "인증하기");
            if (emailbtn) {
                emailBtnElem.addEventListener("click", checkMsg);
            }
        }

        // 인증하기 button 클릭시 event
        function checkMsg() {
            if (confirm("인증번호를 발송 하시겠습니까?")) {
                makeInputText();
            }
        }

        // 변경하기 button 클릭 => 인증하기 Text로 변경
        const makeInputText = () => {
            emailDivElem.innerHTML = null;
            emailDivElem.innerHTML =
                "<div class='flex-direc-column w-100'><input type='text' readonly class='text_box m-b-5 back_color_grey4' th:value='' >" +
                "<div class='flex w-100 '><input type='text' class='text_box m-r-10'> " +
                "<input type='button' value='재전송' class='btn m-r-5'> " +
                "<input type='button' value='인증하기' class='btn'></div></div>";
        };
    }
}