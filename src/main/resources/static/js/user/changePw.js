{
    const frmElem = document.querySelector('#password-frm');
    if(frmElem) {
        const msg1Elem = frmElem.querySelector("#pw_msg1");
        const msg2Elem = frmElem.querySelector("#pw_msg2");
        const msg3Elem = frmElem.querySelector("#pw_msg3");
        const msg4Elem = frmElem.querySelector("#pw_msg4");
        frmElem.addEventListener('submit', (e)=> {
            const currentupwVal = frmElem.currentupw.value;
            const upwVal = frmElem.upw.value;
            const confirmupwVal = frmElem.confirmupw.value;

            if(currentupwVal.length === 0) {
                msg1Elem.innerText = '현재 비밀번호를 작성해 주세요.'
                // alert('현재 비밀번호를 작성해 주세요.');
                e.preventDefault();
            } else if(upwVal.length === 0) {
                msg2Elem.innerText = '변경 비밀번호를 작성해 주세요.'
                // alert('변경 비밀번호를 작성해 주세요.');
                e.preventDefault();
            } else if(upwVal !== confirmupwVal) {
                msg3Elem.innerText = '변경 비밀번호와 확인 비밀번호가 다릅니다.'
                // alert('변경 비밀번호와 확인 비밀번호가 다릅니다.');
                e.preventDefault();
            }  else if(regex.isWrongWith('upw', upwVal)) {
                msg4Elem.innerText = `변경 비밀번호가 ${regex.msg.upw}인지 확인해 주세요.`
                // alert(`변경 비밀번호가 ${regex.msg.upw}인지 확인해 주세요.`);
                e.preventDefault();
            }
        });

        frmElem.currentupw.addEventListener('keyup' , ()=>{
            msg1Elem.innerText = '';
        });

        frmElem.upw.addEventListener('keyup' , ()=>{
            msg2Elem.innerText = '';
        });
        frmElem.upw.addEventListener('keyup' , ()=>{
            msg4Elem.innerText = '';
        });
        frmElem.confirmupw.addEventListener('keyup' , ()=>{
            msg3Elem.innerText = '';
        });
    }
}