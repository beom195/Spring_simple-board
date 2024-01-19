package com.beom.spring_simpleboard.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDTO {

    private Long commentId;
    private Long userId;
    private Long boardId;
    private String comment;
    private LocalDateTime cm_Crate_Date;
    private LocalDateTime cm_Update_Date;
}
