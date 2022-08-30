package com.yansen.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class LoginResponse<T> {
    private Map<String, String> data = new HashMap<>();
}
