package ru.shur.instazoo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String username;
    private String bio;
}
