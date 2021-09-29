package com.portfolio.main.response.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends CommonResult {
    private int rowSize;
    private List<String> fieldType;
    private List<String> fieldName;
    private List<T> list;
}