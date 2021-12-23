let tableWarp = document.querySelector('.tableWarp');
let tableTag = document.createElement('table');

let searchFrm = document.querySelector('#searchFrm')
let searchTypeElem = searchFrm.searchType;
let searchTextElem = searchFrm.searchText

let searchSubmitBtn = searchFrm.querySelector('input[type=submit]')
console.log("searchSubmitBtn : " + searchSubmitBtn)

let rowCntElem = searchFrm.rowCnt;

let searchType = searchTypeElem.options[searchTypeElem.selectedIndex].value
let searchText = '';
let pageNum = 1;
let rowCntNum = rowCntElem.options[rowCntElem.selectedIndex].value;
let thArr = ['글번호', '제목', '작성자', '조회수', '작성일'];

// 테이블 구조 생성
function createTable() {
    let trTag = document.createElement('tr');
    tableWarp.append(tableTag)
    tableTag.append(trTag)
    thArr.forEach((item) => {
        let th = document.createElement('th');
        th.innerText = item;
        trTag.append(th)
    });
}

function createTd(data) {
    data.forEach((item) => {
        let trTag = document.createElement('tr');
        let imgTag = item.profileImg == null ? `<img src="/res/img/DefaultAccountTile.png" class="circular--img circular--size40" alt=""/>` : `<img src="/res/img/profile/${item.writer}/${item.profileImg}" class="circular--img circular--size40" alt=""/>`;
        //TODO td 값 뿌리는 방법 고민 필요.
        trTag.innerHTML = `
                <td>${item.iboard}</td>
                <td>${item.title}</td>
                <td>${imgTag}${item.writerNm}</td>
                <td>${item.hit}</td>
                <td>${item.rdt}</td>
            `;

        tableTag.append(trTag)
        trTag.addEventListener('click',() => {
            location.href = '/board/detail2?iboard='+item.iboard
        })
    })

}


// 게시판 리스트 data 조회
function selectList() {
    let url = '/board/listData'
    fetch(url).then((res)=>{
        return res.json();
    }).then((data) => {
        // console.log(data)
        // console.log(data.length)
        createTd(data.list)
        createPagingBtn(data.maxPageNum)
    }).catch((err) => {
        console.error(err);
    })
}

// 글 검색 기능

function search() {
    searchTypeElem.addEventListener('change',() => {
        // console.log(searchTypeElem.options[searchTypeElem.selectedIndex].value);
        searchType = searchTypeElem.options[searchTypeElem.selectedIndex].value
        console.log("searchType : " + searchType)
    })
    searchTextElem.addEventListener('blur', () =>{
        searchText = searchTextElem.value
        console.log(searchTextElem.value)
    })
    rowCntElem.addEventListener('change',() => {
        // console.log(searchTypeElem.options[searchTypeElem.selectedIndex].value);
        rowCntNum = rowCntElem.options[rowCntElem.selectedIndex].value
        console.log("rowCntNum : " + rowCntNum)
        pagingSearch()
    })
    searchSubmitBtn.addEventListener('click',(e) =>{
        e.preventDefault()
        pagingSearch()
    })

}

// 페이징 태그 구현
function createPagingBtn(maxPageNum) {
    let divTag = document.createElement('div');
    divTag.id = 'pagingBtnList'
    divTag.style.display = 'inline-block'
    tableWarp.append(divTag)

    for(let i = 1; i <= maxPageNum; i++){
        let spanTag = document.createElement('span');
        spanTag.innerText = i
        spanTag.style.display = 'inline-block'
        spanTag.style.marginLeft = '20px'
        spanTag.style.padding = '20px'

        divTag.append(spanTag)
    }
    clickNum()
}
//페이징 요소 값 가져오기
function clickNum(){
    let pagingBtnList = document.querySelector('#pagingBtnList');

    pagingBtnList.addEventListener('click',(e) =>{
        console.log(e.target.textContent)
        pageNum = e.target.textContent;
        pagingSearch()
    })
}

// 페이징&값 검색 처리
function pagingSearch(){
    let pagingBtnList = document.querySelector('#pagingBtnList');
    // 몇 개의 값을 보여줄거냐, 몇번째 페이지 인가
    let url = '/board/listData?page='+pageNum+"&rowCnt="+rowCntNum+"&searchType="+searchType+"&searchText="+searchText

    fetch(url).then((res)=>{
        return res.json();
    }).then((data) => {
        let trList = document.querySelectorAll('tr')
        for(let i = 1; i < trList.length; i++){
            trList[i].remove()
        }
        console.log(trList)
        createTd(data.list)
        pagingBtnList.remove()
        createPagingBtn(data.maxPageNum)

    }).catch((err) => {
        console.error(err);
    })
}

window.onload = () => {
    createTable()
    selectList()
    search()

}

