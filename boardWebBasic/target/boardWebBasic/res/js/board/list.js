function moveToDetail(iboard){
    console.log('iboard : ' + iboard)
    location.href='/board/detail?iboard='+iboard
}

let searchFrmElem = document.querySelector('#searchFrm');
if(searchFrmElem) {
    searchFrmElem.rowCnt.addEventListener('change', function () {
        searchFrmElem.submit();
    })
}