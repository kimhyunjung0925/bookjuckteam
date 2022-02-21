// {
//     // form 태그
//     const infoElem = document.querySelector("#info-frm");
//
//     // if(form 태그) 안에서 발생하는 이벤트들
//     if (infoElem) {
//         const emailBtnElem = document.querySelector("#email_btn");
//         const emailTextElem = document.querySelector("#email_text");
//         const emailDivElem = document.querySelector("#email_div");
//
//         // email 변경하기 버튼 클릭시 이벤트
//         emailBtnElem.addEventListener("click", () => {
//             emailTextElem.removeAttribute("readonly");
//             changeText(); // Text 변환 이벤트 함수
//         });
//
//         // 변경하기 button 클릭시 Text 변환 => 이벤트 발생
//         function changeText() {
//             const emailbtn = (emailBtnElem.value = "인증하기");
//             if (emailbtn) {
//                 emailBtnElem.addEventListener("click", checkMsg);
//             }
//
//
//         }
//
//
//         // 인증하기 button 클릭시 event
//         function checkMsg() {
//             const emailTextElem = document.querySelector("#email_text");
//             if (confirm("인증번호를 발송 하시겠습니까?")) {
//                 makeInputText();
//                 fetch(`/sendMail`,{
//                     method: "POST",
//                     headers: {
//                         "Content-Type": "application/json",
//                     },
//                     body: JSON.stringify({
//                         email:emailTextElem.value
//                     }),
//
//                 })
//                     .then(res => res.json())
//                     .then(data=>{
//                         console.log(data);
//                     })
//             }
//         }
//
//         // 변경하기 button 클릭 => 인증하기 Text로 변경
//         const makeInputText = () => {
//             emailDivElem.innerHTML = null;
//             emailDivElem.innerHTML =
//                 "<div class='flex-direc-column w-100'><input type='text' readonly class='text_box m-b-5 back_color_grey4' th:value='' >" +
//                 "<div class='flex w-100 '><input type='text' class='text_box m-r-10'> " +
//                 "<input type='button' value='재전송' class='btn m-r-5'> " +
//                 "<input type='button' value='인증하기' class='btn'></div></div>";
//         };
//     }
// }

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
            changeText(); // Text 변환 이벤트 함수
        });

        // 변경하기 button 클릭시 Text 변환 => 이벤트 발생
        function changeText() {
            emailDivElem.innerHTML = null;
            emailDivElem.innerHTML = "<input type='text' name='currentEmail' class='text_box m-r-10' id='email_name'>    " +
                " <input type='button' value='인증하기' class='btn' id='email_btn2'>"
            const emailBtnElem2 = document.querySelector("#email_btn2");

            emailBtnElem2.addEventListener("click", function checkMsg() {
                const emailName = document.querySelector("#email_name");
                console.log(emailName.value);
                if (confirm("인증번호를 발송 하시겠습니까?")) {
                    makeInputText(emailName.value);
                }
            });
        }
        // 인증하기 button 클릭시 이메일 인증번호 전송!!!!


        // 변경하기 button 클릭 => 인증하기 Text로 변경
        const makeInputText = (emailName) => {
            emailDivElem.innerHTML = null;
            emailDivElem.innerHTML =
                `<div class='flex-direc-column w-100'><input type='text' readonly class='text_box m-b-5 back_color_grey4' value=${emailName} >` +
                `<div class='flex w-100 '><input type='text' class='text_box m-r-10'> ` +
                `<input type='button' value='재전송' class='btn m-r-5'> ` +
                `<input type='button' value='인증완료' class='btn' ></div></div>`;
        };
    }
}