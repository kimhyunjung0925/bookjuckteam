{
    const joinFrmElem = document.querySelector('#join-frm');


    let idChkState = 2; //0: 아이디 사용 불가능, 1:아이디 사용가능, 2: 체크 안함

    const uidRegex = /^[a-z]+[a-z0-9]{4,9}$/g;
    const upwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
    const nmRegex = /^([가-힣]{2,15})$/;
    const birthRegex = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
    const phRegex = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
    const emailRegex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;


    if (joinFrmElem) {
        joinFrmElem.addEventListener('submit', (e) => {
            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const upwChk = joinFrmElem.querySelector('#upw-chk').value;
            const nm = joinFrmElem.nm.value;

            const ph = joinFrmElem.ph.value;
            const birth = joinFrmElem.birth.value;
            const email = joinFrmElem.email.value;

            console.log(uid);
            console.log(upw);
            console.log(upwChk);
            console.log(nm);
            console.log(ph);
            console.log(birth);
            console.log(email);


            if (!uidRegex.test(uid)) {
                alert("아이디는 영소문자조합의 5-10자로 작성해주세요.");
                e.preventDefault();
            } else if (!upwRegex.test(upw)) {
                alert("비밀번호는 영소문자,대문자/숫자/특수문자 조합 8~15자리로 작성해주세요.");
                e.preventDefault();
            } else if (upw !== upwChk) {
                alert('비밀번호와 체크 비밀번호를 확인해 주세요.');
                e.preventDefault();
            } else if (!nmRegex.test(nm)) {
                alert("이름은 한글조합으로 2~5글자로 작성해주세요.");
                e.preventDefault();
            } else if (!phRegex.test(ph)) {
                alert("번호는 - 없이 입력해주세요.");
                e.preventDefault();
            } else if (!birthRegex.test(birth)) {
                alert("생일은 - 없이 8자리 로 작성해주세요. ex)19990512");
                e.preventDefault();
            } else if (!emailRegex.test(email)) {
                alert("이메일 양식에 맞추어 입력하세요.");
                e.preventDefault();
            }
        });
        // 아이디 중복체크 일단 뺌
    }
}