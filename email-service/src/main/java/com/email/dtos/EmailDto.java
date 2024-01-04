package com.email.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import jakarta.validation.constraints.Email;

@Data
public class EmailDto {

    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

}
