

function joinChk() {
    //form 객체 주소값.
    var frm = document.querySelector('#frm');
    if(frm.uid.value.length < 5){
        alert('아이디를 5글자 이상 작성해 주세요.');
        return false;
    } else if(frm.upw.value.length < 5) {
        alert('비밀번호를 5자 이상 작성해 주세요');
        return false;
    } else if (frm.upw.value !== frm.reupw.value){
        alert('비밀번호를 확인해 주세요')
        return false
    } else if (frm.nm.value.length > 5) {
        //이름은 5글자 초과 되어있다면 이름을 확인 해주세요. alert 띄우고 전송안되게..
        alert('이름을 확인해주세요')
        return false
    }
    return true
}