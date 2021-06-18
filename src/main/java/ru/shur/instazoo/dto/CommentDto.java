package ru.shur.instazoo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDto {

    private Long id;
    @NotEmpty
    private String message;
    private String username;

}
