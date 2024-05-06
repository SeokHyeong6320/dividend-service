package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResult {

    private String ErrorCode;
    private String message;
}
