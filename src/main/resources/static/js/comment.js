//댓글 수정 버튼 변수화
const commentUpdateBtn = document.querySelector("#comment-update-btn");
commentUpdateBtn.addEventListener("click", () => {

    //게시글과 댓글 내용에 담긴 값으로 객체 생성
    //id에 입력된 값 가져오기
    const data = {
        commentId: document.querySelector("#commentId").value,
        comment: document.querySelector("#comment").value
    }
    if(!data.comment || data.comment.trim() === ""){
        alert("댓글을 입력해주세요!");
        return false;
    }

    const cm_update_check = confirm("댓글을 수정하시겠습니까?");
    console.log("commentId " + data.commentId );
    console.log("comment " + data.comment );
    if(cm_update_check === true){
        $.ajax({
            type: "POST",
            url: "/comment/" + data.commentId,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function (){
            window.location.reload();
        });
    }
});