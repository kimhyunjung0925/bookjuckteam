{
    const frmElem = document.querySelector('#password-frm');
    if(frmElem) {
        frmElem.addEventListener('submit', (e)=> {
            const currentupwVal = frmElem.currentupw.value;
            const upwVal = frmElem.upw.value;
            const confirmupwVal = frmElem.confirmupw.value;

            if(currentupwVal.length === 0) {
                alert('현재 비밀번호를 작성해 주세요.');
                e.preventDefault();
            } else if(upwVal.length === 0) {
                alert('변경 비밀번호를 작성해 주세요.');
                e.preventDefault();
            } else if(upwVal !== confirmupwVal) {
                alert('변경 비밀번호와 확인 비밀번호가 다릅니다.');
                e.preventDefault();
            } else if(regex.isWrongWith('upw', currentupwVal)) {
                alert(`현재 비밀번호가 ${regex.msg.upw}인지 확인해 주세요.`);
                e.preventDefault();
            } else if(regex.isWrongWith('upw', upwVal)) {
                alert(`변경 비밀번호가 ${regex.msg.upw}인지 확인해 주세요.`);
                e.preventDefault();
            }
        });
    }
}