function isDelCmt(iboard,icmt) {
    console.log(icmt)
    if(confirm('댓글을 삭제 하시겠습니까?')){
        location.href = '/board/cmt/del?iboard='+iboard+'&icmt='+icmt;
    }
}
let cmtModContainerElem = document.querySelector('.cmtModContainer')
let btnCancelElem = cmtModContainerElem.querySelector('#btnCancel')
btnCancelElem.addEventListener('click',function () {
    cmtModContainerElem.style.display = 'none'
})
function openModForm(icmt, ctnt) {
    if(cmtModContainerElem){
        cmtModContainerElem.style.display = 'flex'
        let cmtModFrmElem = cmtModContainerElem.querySelector('#cmtModFrm')
        cmtModFrmElem.icmt.value = icmt
        cmtModFrmElem.ctnt.value = ctnt
    }
}