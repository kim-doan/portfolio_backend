package com.portfolio.main.response.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

    private boolean success;

    private String code;

    private String msg;
}
