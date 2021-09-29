package com.portfolio.main.response.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private int rowSize;
    private List<String> fieldType;
    private List<String> fieldName;
    private T data;
}
