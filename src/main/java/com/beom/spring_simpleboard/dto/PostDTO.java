package com.beom.spring_simpleboard.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDTO {

    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private int view;
    private UserDTO user;
}
