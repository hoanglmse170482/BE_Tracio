package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class SignInResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Integer userId;
}
