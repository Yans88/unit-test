package com.yansen.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseData<T> {
    private List<String> messages = new ArrayList<>();
    private T payload;
}
