package com.yanhuo.serviceucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterVo {
    private String nickname;

    private String mail;

    private String password;

    private String code;
}
