//수정 버튼 클릭시 제목과 내용 부분이 input 태그로 변경, 수정 버튼 사라지고 등록,취소 버튼 생성
const postUpdateBtn = document.querySelector("#post-update-btn");
postUpdateBtn.addEventListener("click", function() {
    //제목, 내용 변수화
    const titleElement = document.getElementById("title");
    const contentElement = document.getElementById("content");

    //버튼 변수화
    const updateSaveBtn = document.getElementById("update-save-btn");
    const updateCancelBtn = document.getElementById("update-cancel-btn");

    //게시글 제목, 내용 textArea 타입으로 변경
    replaceWithTextArea(titleElement);
    replaceWithTextArea(contentElement);

    //수정 버튼 클릭시 등록,취소 버튼만 보이게 변경
    postUpdateBtn.style.display = "none";
    updateSaveBtn.style.display = "inline-block";
    updateCancelBtn.style.display = "inline-block";
});

const updateSaveBtn = document.querySelector("#update-save-btn");
updateSaveBtn.addEventListener("click", () =>{

    //게시글 id, 제목, 내용이 담긴 값으로 객체 생성
    const data = {
        postId: document.querySelector("#postNum").textContent,
        title: document.querySelector("#title").value,
        content: document.querySelector("#content").value
    }
    if(!data.title || data.title.trim() === ""){
        alert("제목을 입력해주세요!");
        return false;
    }else if(!data.content || data.content.trim() === ""){
        alert("내용을 입력해주세요!");
        return false;
    }

    console.log("postId" + data.postId);
    console.log("title" + data.title);
    console.log("content" + data.content);

    const post_update_check = confirm("게시글을 수정하시겠습니까?");
    if(post_update_check === true) {
        $.ajax({
            type: "POST",
            url: "/post/edit/" + data.postId,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function () {
            window.location.reload();
        });
    }
});

//취소 버튼 클릭시 페이지 새로고침 (뒤로 가기)
const updateCancelBtn = document.getElementById("update-cancel-btn");
updateCancelBtn.addEventListener("click", function() {
    location.reload(); // 페이지 새로 고침
});

function replaceWithTextArea(element) {
    const textArea = document.createElement("textarea");
    textArea.type = "text";
    textArea.setAttribute("id", element.id); // 이전 요소의 id 속성을 복사
    textArea.setAttribute("name", element.getAttribute("name")); // 이전 요소의 name 속성을 복사
    textArea.value = element.textContent || element.innerText;
    element.parentNode.replaceChild(textArea, element);
}