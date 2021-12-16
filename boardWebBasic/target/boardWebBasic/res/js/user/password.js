let frmElam = document.querySelector('#frm');
let submitBtnElem = document.querySelector('#submitBtn')

submitBtnElem.addEventListener('click', function () {
    if(frmElam){

        console.log(frmElam.changedUpw.value)
        console.log(frmElam.changedUpwConfirm.value)


        if(frmElam.upw.value.length < 5){
            alert("현재 비밀번호 확인")
        }else if(frmElam.changedUpw.value.length < 5){
            alert("변경 비밀번호 확인")
        }else if(frmElam.changedUpwConfirm.value !== frmElam.changedUpw.value){
            alert("비밀번호 불 일 치")
        }else {
            frmElam.submit();
        }
    }

})