{

    const joinFrmElem = document.querySelector('#join-frm');

    let idChkState = 2; //0: 아이디 사용 불가능, 1:아이디 사용가능, 2: 체크 안함

    const uidRegex = /^[a-z]+[a-z0-9]{4,9}$/g;
    const upwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
    const nmRegex = /^([가-힣]{2,15})$/;
    const birthRegex = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
    const phRegex = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
    const emailRegex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;


    const setIdChkMsg = (data) => {
        idChkState = data.result; //0 or 1

        const idChkMsgElem = joinFrmElem.querySelector('#id-chk-msg');
        switch(data.result) {
            case 0:
                idChkMsgElem.innerText = '이미 사용중인 아이디 입니다.';
                break;
            case 1:
                idChkMsgElem.innerText = '사용할 수 있는 아이디 입니다.';
                break;
        }
    };




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
                return false;
                //폰번호 오류시에만 화면이 넘어갑니다 이유모름.
            } else if (!birthRegex.test(birth)) {
                alert("생일은 - 없이 8자리 로 작성해주세요. ex)19990512");
                e.preventDefault();
            } else if (!emailRegex.test(email)) {
                alert("이메일 양식에 맞추어 입력하세요.");
                e.preventDefault();
            }else if(idChkState !== 1) {
                switch (idChkState) {
                    case 0:
                        alert('다른 아이디를 사용해 주세요!');
                        break;
                    case 2:
                        alert('아이디 중복 체크를 해주세요!');
                        break;
                }
                e.preventDefault();
            }
        });

        joinFrmElem.uid.addEventListener('keyup', () => {
            const idChkMsgElem = joinFrmElem.querySelector('#id-chk-msg');
            idChkMsgElem.innerText = '';
            idChkState = 2;
        });

        //아이디 중복 체크 버튼
        const idBtnChkElem = joinFrmElem.querySelector('#id-btn-chk');
        idBtnChkElem.addEventListener('click', () => {
            const idVal = joinFrmElem.uid.value;
            fetch(`/user/idChk/${idVal}`)
                .then(res => res.json())
                .then((data) => {
                    setIdChkMsg(data);
                }).catch((e)=> {
                console.log(e);
            });
        });

    }


}