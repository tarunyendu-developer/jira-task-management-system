package com.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequestDto {

    private Long taskId;
    private Long userId;

    @NotBlank(message = "Comment cannot be empty")
    private String commentText;
}