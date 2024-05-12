package com.project.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    SCRAPPER_CONNECTION_FAIL("couldn't connection to website"),

    TICKER_INACCURATE("inaccurate company ticker"),
    COMPANY_PARSING_FAIL("couldn't parsing company info"),
    MONTH_INACCURATE("unexpected Month enum value"),

    COMPANY_NOT_FOUND("couldn't find company in DB"),
    COMPANY_ALREADY_EXIST("This company already exist in DB"),

    USER_NOT_FOUND("couldn't find user in DB"),
    USER_ID_ALREADY_EXIST("This user id already exist in DB"),
    PASSWORD_NOT_MATCH("This is wrong password");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
