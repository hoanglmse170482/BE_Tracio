package com.tracio.Tracio.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String status;
}
