package com.fluig.identity.swagger.service.model;

import com.fluig.identity.swagger.api.model.exception.BaseException;
import com.fluig.identity.swagger.api.model.exception.ErrorCode;

public class ApiException extends BaseException {

    private static final long serialVersionUID = 1L;

    public static final ErrorCode API_INSERT_ERROR = new ErrorCode("SM_API_0001", "Error to persist API.");
    public static final ErrorCode API_REMOVE_ERROR = new ErrorCode("SM_API_0002", "Error to remove API.");
    public static final ErrorCode API_NOT_FOUND = new ErrorCode("SM_API_0003", "API not found.");

    public ApiException(String code, String description, String details, Throwable cause) {
        super(code, description, details, cause);
    }

    public ApiException(String code, String description, Throwable cause) {
        this(code, description, null, cause);
    }

    public ApiException(String code, String description, String details) {
        this(code, description, details, null);
    }

    public ApiException(String code, String description) {
        this(code, description, null, null);
    }

    public ApiException(ErrorCode error, String details, Throwable cause) {
        this(error.getCode(), error.getDescription(), details, cause);
    }

    public ApiException(ErrorCode error, String details) {
        this(error.getCode(), error.getDescription(), details, null);
    }

    public ApiException(ErrorCode error, Throwable cause) {
        this(error.getCode(), error.getDescription(), null, cause);
    }

    public ApiException(ErrorCode error) {
        this(error.getCode(), error.getDescription(), null, null);
    }
}