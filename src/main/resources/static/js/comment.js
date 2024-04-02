//댓글 작성 버튼
function commentWrite(){

    //게시글의 id, 회원 정보 id, 댓글이 담긴 값으로 객체 생성
    const data = {
        postId: document.querySelector("#postId").value,
        userId: document.querySelector("#userId").value,
        comment: document.querySelector("#write-comment").value
    }
    console.log("postId" + data.postId);
    console.log("userId" + data.userId);
    console.log("comment" + data.comment);
    if(!data.comment || data.comment.trim() === ""){
        alert("댓글을 입력해주세요!");
        return false;
    }
    $.ajax({
        type: "POST",
        url: "/commentWrite/" + data.postId,
        dataType: "JSON",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data)
    }).done(function () {
        window.location.reload();
    });

}

//각 댓글마다 고유 id 부여
let currentCommentId;
document.addEventListener('click', function(event) {
    const target = event.target;
    if (target.classList.contains('comment-update-btn')) {
        // 클릭된 버튼이 속한 댓글의 ID 가져오기
        const commentId = target.closest('.comment').id.split('_')[1];
        currentCommentId = commentId;
        // 이제 commentId를 사용하여 해당 댓글에 대한 작업을 수행할 수 있습니다.
    }
});
//댓글 내용 -> textArea 변환
function commentUpdate(event) {
    // 클릭된 버튼의 부모 요소에서 해당 댓글의 요소들을 가져오기
    const commentContainer = event.target.closest('.comment');
    const commentUpdateBtn = commentContainer.querySelector(".comment-update-btn");
    const commentUpdateSaveBtn = commentContainer.querySelector(".comment-update-save-btn");
    const commentDeleteBtn = commentContainer.querySelector(".comment-delete-btn");
    const updateCancelBtn = commentContainer.querySelector(".comment-update-cancel-btn");


    //버튼을 누른 댓글에 해당 하는 댓글 내용을 가져옴
    const comment = commentContainer.querySelector(".comment-content");
    // currentComment = commentContainer.querySelector(".comment-content");
    console.log(comment);

    //댓글 textArea 타입으로 변경
    function replaceWithTextAreaComment(element) {
        const textArea = document.createElement("textarea");
        textArea.type = "text";
        textArea.setAttribute("class", element.className); // 이전 요소의 클래스를 복사
        textArea.setAttribute("name", element.getAttribute("name")); // 이전 요소의 name 속성을 복사
        textArea.value = element.textContent || element.innerText;
        element.parentNode.replaceChild(textArea, element);
    }
    replaceWithTextAreaComment(comment);

    //수정 버튼 클릭시 등록,취소 버튼만 보이게 변경
    commentUpdateBtn.style.display = "none";
    commentUpdateSaveBtn.style.display = "inline-block";
    commentDeleteBtn.style.display = "inline-block";
    updateCancelBtn.style.display = "inline-block";
}

function commentUpdateSave(){
    const data = {
        commentId: currentCommentId,
        // comment: document.querySelector(".comment-content").value
        comment: event.target.closest('.comment').querySelector(".comment-content").value
    }
    console.log("commentId    " + data.commentId);
    console.log("comment   " + data.comment);
    if (!data.comment || data.comment.trim() === "") {
        alert("댓글을 입력해주세요!");
        return false;
    }
    const cm_update_check = confirm("댓글을 수정하시겠습니까?");

    if (cm_update_check === true) {
        $.ajax({
            type: "POST",
            url: "/commentUpdate/" + data.commentId,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function (response) {
            window.location.reload();
        });
    }
}

function commentDelete(){
    const data = {
        commentId: currentCommentId
    }
    console.log(data.commentId);
    const cm_delete_check = confirm("댓글을 삭제하시겠습니까?");
    console.log("commentId", data.commentId);
    if (cm_delete_check === true) {
        $.ajax({
            type: "DELETE",
            url: "/commentDelete/" + data.commentId,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function () {
            window.location.reload();
        })
    }
}

function updateCancel(){
    //취소 버튼 클릭시 페이지 새로고침 (뒤로 가기)
    window.location.reload();
}