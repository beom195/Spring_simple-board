function postWrite() {

    const data = {
        title: document.querySelector("#title").value, content: document.querySelector("#content").value
    }
    if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
        alert("제목 또는 내용을 입력해주세요!");
        return false;
    } else {
        $.ajax({
            type: "POST",
            url: "/post/write",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function () {
            alert("등록 완료");
            window.location.href = "/";
        });
    }
}

//수정 버튼 클릭시 제목과 내용 부분이 input 태그로 변경, 수정 버튼 사라지고 등록,취소 버튼 생성
function postUpdate() {
    //버튼 변수화
    const postUpdateBtn = document.getElementById("post-update-btn");
    const updateSaveBtn = document.getElementById("update-save-btn");
    const updateCancelBtn = document.getElementById("update-cancel-btn");
    //제목, 내용 변수화
    const titleElement = document.getElementById("title");
    const contentElement = document.getElementById("content");



    //게시글 제목, 내용 textArea 타입으로 변경
    replaceWithTextArea(titleElement);
    replaceWithTextArea(contentElement);

    //수정 버튼 클릭시 등록,취소 버튼만 보이게 변경
    postUpdateBtn.style.display = "none";
    updateSaveBtn.style.display = "inline-block";
    updateCancelBtn.style.display = "inline-block";
    // });
}

//수정한 게시글 저장하기
function postUpdateSave() {

    //게시글 id, 제목, 내용이 담긴 값으로 객체 생성
    const data = {
        postId: document.querySelector("#postNum").textContent,
        title: document.querySelector("#title").value,
        content: document.querySelector("#content").value
    }
    console.log("postId" + data.postId);
    console.log("title" + data.title);
    console.log("content" + data.content);
    if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
        alert("제목 또는 내용을 입력해주세요!");
        return false;
    } else {
        $.ajax({
            type: "POST",
            url: "/post/edit/" + data.postId,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function () {
            alert("수정완료");
            window.location.reload();
        });
    }
}
function postDelete(){

    const data = {
        postId: document.querySelector("#postNum").textContent
    }
    console.log("postId ---->" + data.postId);
    const post_delete_chk = confirm("게시글을 삭제하시겠습니까?");
    if(post_delete_chk){
        $.ajax({
            type: "DELETE",
            url: "/post/delete/" + data.postId,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function() {
            alert("삭제 완료!");
            location.href = "/";
        })
    }
}
//게시글 수정 취소
function postUpdateCancel() {
    //취소 버튼 클릭시 페이지 새로고침 (뒤로 가기)
    window.location.reload();
}

//게시글 수정시 title, content textarea 타입으로 변환
function replaceWithTextArea(element) {
    const textArea = document.createElement("textarea");
    textArea.type = "text";
    textArea.setAttribute("id", element.id); // 이전 요소의 id 속성을 복사
    textArea.setAttribute("name", element.getAttribute("name")); // 이전 요소의 name 속성을 복사
    textArea.value = element.textContent || element.innerText;
    element.parentNode.replaceChild(textArea, element);
}