package com.beom.spring_simpleboard.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDTO {

    private Long boardId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private int view;
}
