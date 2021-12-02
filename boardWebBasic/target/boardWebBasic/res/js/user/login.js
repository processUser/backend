var frm = document.querySelector('#frm');
if(frm) {
    frm.addEventListener('submit', function (e) {
        // 아이디가 5글자 미만 혹은 20글자 초과 되면 " 아이디는 5~20글자 입니다."
        if(frm.uid.value.length < 5 || frm.uid.value.length>20){
            alert('아이디는 5~20글자 입니다.')
            e.preventDefault()
            return;
        }

        // 비밀번호는 5글자 미만 일 때 "비밀번호를 확인 해주 세요."
        if(frm.upw.value.length < 5){
            alert('비밀번호를 확인 해주 세요.')
            e.preventDefault()
            return;
        }
    });
}
let btn=document.querySelector('#btn')

btn.addEventListener('click', function () {
    if(frm.upw.type === 'password'){
        frm.upw.type = 'text'
        btn.value = '비밀번호 감추기'
    }else if(frm.upw.type === 'text'){
        frm.upw.type = 'password'
        btn.value = '비밀번호 보이기'
    }
    console.log(frm.text)
})

