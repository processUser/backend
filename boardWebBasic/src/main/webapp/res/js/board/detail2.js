var cmtListContainerElem = document.querySelector('#cmtListContainer')


//(댓글 수정) 취소 버튼 클릭 이벤트 연결
let cmtModContainerElem = document.querySelector('.cmtModContainer')
let btnCancelElem = cmtModContainerElem.querySelector('#btnCancel')
let cmtModFrmElem = cmtModContainerElem.querySelector('#cmtModFrm')

btnCancelElem.addEventListener('click',function () {
    cmtModContainerElem.style.display = 'none'
    var selectedTrElem = document.querySelector('.cmt_selected')
    selectedTrElem.classList.remove('cmt_selected');
})

var submitBtnElem = cmtModFrmElem.querySelector('input[type=submit]')
submitBtnElem.addEventListener('click', function (e) {
    e.preventDefault()

    var url = '/board/cmt?proc=upd'
    // 댓글 수정 : ctnt, icmt
    var param = {
        'icmt': cmtModFrmElem.icmt.value,
        'ctnt': cmtModFrmElem.ctnt.value
    }
    fetch(url, {
        'method' : 'POST',
        'headers': {
            'Content-Type': 'application/json'
        },
        'body': JSON.stringify(param)
    }).then(function (res) {
        return res.json()
    }).then(function (data) {
        console.log(data.result)
        console.log(cmtListContainerElem.firstChild)

        switch (data.result) {
            case 0: // 수정 실패
                alert('댓글 수정을 실패')
                break;
            case 1: // 수정 성공
                modCtnt(param.ctnt);
                var e = new Event('click')
                btnCancelElem.dispatchEvent(e) // 클릭 이벤트 주기
                break;
        }

        // cmtModContainerElem.style.display = 'none'
        // cmtListContainerElem.firstChild.remove()
        // getList()
    }).catch(function (err) {
        console.log(err)
    })
})
// 댓글 내용 수정 적용
function modCtnt(ctnt) {
    var selectedTrElem = document.querySelector('.cmt_selected')
    var tdCtntElem = selectedTrElem.children[0]
    tdCtntElem.innerText = ctnt
}

if(cmtListContainerElem) {
    function openModForm2(icmt, ctnt) { // 구조 분해 할당 사용함.

        cmtModContainerElem.style.display = 'flex'
        cmtModFrmElem.icmt.value = icmt
        cmtModFrmElem.ctnt.value = ctnt
    }
    // function openModForm({icmt, ctnt}) { // 구조 분해 할당 사용함.
    //
    //     cmtModContainerElem.style.display = 'flex'
    //     cmtModFrmElem.icmt.value = icmt
    //     cmtModFrmElem.ctnt.value = ctnt
    // }

    function getList() {
        var iboard = cmtListContainerElem.dataset.iboard;
        var url = '/board/cmt?iboard=' + iboard;
        console.log('url : ' + url)
        fetch(url).then(function (res) {
            return res.json()
        }).then(function (data) {
            console.log(data)
            displayCmt2(data);
        }).catch(function (err) {
            console.log(err)
        })
    }

    function displayCmt2(data){
        var tableElem = document.createElement('table');
        tableElem.innerHTML = `
            <tr>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>비고</th>
            </tr>
        ` // 템플릿 리터럴
        cmtListContainerElem.appendChild(tableElem)

        var loginUserPk = cmtListContainerElem.dataset.loginuserpk === '' ? 0 : Number(cmtListContainerElem.dataset.loginuserpk);

        console.log('loginUserPk : '+loginUserPk)

        data.forEach(function(item){
            var tr = document.createElement('tr');
            var ctnt = item.ctnt.replaceAll('<', '&lt').replaceAll('>','&gt');
            tr.innerHTML = `
                <td>${ctnt}</td>
                <td>${item.writerNm}</td>
                <td>${item.rdt}</td>
            `;
            tableElem.appendChild(tr)
            var lastTd = document.createElement('td');
            tr.appendChild(lastTd)

            if(item.writer === loginUserPk){
                var btnMod = document.createElement('button');
                btnMod.innerText='수정';
                btnMod.addEventListener('click', function (){
                    tr.classList.add('cmt_selected');
                    var ctnt = tr.children[0].innerText;
                    openModForm2(item.icmt, ctnt); // 구조 분해 할당
                    //openModForm(item); // 구조 분해 할당
                })
                var btnDel = document.createElement('button');
                btnDel.innerText='삭제';

                lastTd.appendChild(btnMod)
                lastTd.appendChild(btnDel)
            }
        })
    }

    function displayCmt(data){
        var tableElem = document.createElement('table');

        var tr = document.createElement('tr');
        var th1 = document.createElement('th');
        th1.innerText = '내용';
        var th2 = document.createElement('th');
        th2.innerText = '작성자';
        var th3 = document.createElement('th')
        th3.innerText = '작성일';
        var th4 = document.createElement('th');
        th4.innerText = '비고';

        tr.append(th1)
        tr.appendChild(th2)
        tr.appendChild(th3)
        tr.appendChild(th4)

        tableElem.appendChild(tr)
        cmtListContainerElem.appendChild(tableElem)
    }
    getList()
}