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
// const commentWriteBtn = document.querySelector("#comment-write-btn");
// commentWriteBtn.addEventListener("click", () =>{
//
//     //게시글의 id, 회원 정보 id, 댓글이 담긴 값으로 객체 생성
//     const data = {
//         postId: document.querySelector("#postId").value,
//         userId: document.querySelector("#userId").value,
//         comment: document.querySelector("#write-comment").value
//     }
//     if(!data.comment || data.comment.trim() === ""){
//         alert("댓글을 입력해주세요!");
//         return false;
//     }
//     console.log("postId" + data.postId);
//     console.log("userId" + data.userId);
//     console.log("comment" + data.comment);
//     $.ajax({
//         type: "POST",
//         url: "/commentWrite/" + data.postId,
//         dataType: "JSON",
//         contentType: "application/json; charset=utf-8",
//         data: JSON.stringify(data)
//     }).done(function () {
//         window.location.reload();
//     });
// });

function commentUpdate(){
    //게시글과 댓글 내용에 담긴 값으로 객체 생성
    //id에 입력된 값 가져오기
    const data = {
        commentId: document.querySelector(".commentId").value,
        comment: document.querySelector(".update-comment").value
    }
    console.log(data.commentId);
    if (!data.comment || data.comment.trim() === "") {
        alert("댓글을 입력해주세요!");
        return false;
    }
    const cm_update_check = confirm("댓글을 수정하시겠습니까?");
    console.log("commentId " + data.commentId);
    console.log("comment" + data.comment);
    if (cm_update_check === true) {
        $.ajax({
            type: "POST",
            url: "/commentUpdate/" + data.commentId,
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function () {
            window.location.reload();
        });
    }
}
// //댓글 수정 버튼
// const commentUpdateBtn = document.querySelector(".comment-update-btn");
// if(commentUpdateBtn) {
//     commentUpdateBtn.addEventListener("click", () => {
//         console.log("댓글 수정 버튼");
//         //게시글과 댓글 내용에 담긴 값으로 객체 생성
//         //id에 입력된 값 가져오기
//         const data = {
//             commentId: document.querySelector("#commentId").value,
//             comment: document.querySelector("#update-comment").value
//         }
//         if (!data.comment || data.comment.trim() === "") {
//             alert("댓글을 입력해주세요!");
//             return false;
//         }
//
//         const cm_update_check = confirm("댓글을 수정하시겠습니까?");
//         console.log("commentId " + data.commentId);
//         console.log("comment" + data.comment);
//         if (cm_update_check === true) {
//             $.ajax({
//                 type: "POST",
//                 url: "/commentUpdate/" + data.commentId,
//                 dataType: "JSON",
//                 contentType: "application/json; charset=utf-8",
//                 data: JSON.stringify(data)
//             }).done(function () {
//                 window.location.reload();
//             });
//         }
//     });
// }

function commentDelete(){
    const data = {
        commentId: document.getElementById("commentId").value
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

// //댓글 삭제 버튼
// const commentDeleteBtn = document.getElementBycl("comment-delete-btn");
// if(commentDeleteBtn) {
//     commentDeleteBtn.addEventListener("click", () => {
//         const data = {
//             commentId: document.getElementById("commentId").value
//         }
//         const cm_delete_check = confirm("댓글을 삭제하시겠습니까?");
//         console.log("commentId", data.commentId);
//         if (cm_delete_check === true) {
//             $.ajax({
//                 type: "DELETE",
//                 url: "/commentDelete/" + data.commentId,
//                 dataType: "JSON",
//                 contentType: "application/json; charset=utf-8",
//                 data: JSON.stringify(data)
//             }).done(function () {
//                 window.location.reload();
//             })
//         }
//     });
// }