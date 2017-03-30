package com.fluig.identity.swagger.api.model.exception;

import com.google.gson.JsonObject;

public class BaseDaoException extends BaseException {

    private static final long serialVersionUID = 1L;

    public static final ErrorCode GENERIC_PERSISTENCE_ERROR = new ErrorCode("SM_PERSISTENCE_0000", "An unknown persistence error occurred.");

    public BaseDaoException(String code, String description, String details, Throwable cause) {
        super(code, description, details, cause);
    }

    public BaseDaoException(String code, String description, Throwable cause) {
        this(code, description, null, cause);
    }

    public BaseDaoException(String code, String description, String details) {
        this(code, description, details, null);
    }

    public BaseDaoException(String code, String description) {
        this(code, description, null, null);
    }

    public BaseDaoException(ErrorCode error, String details, Throwable cause) {
        this(error.getCode(), error.getDescription(), details, cause);
    }

    public BaseDaoException(ErrorCode error, String details) {
        this(error.getCode(), error.getDescription(), details, null);
    }

    public BaseDaoException(ErrorCode error, Throwable cause) {
        this(error.getCode(), error.getDescription(), null, cause);
    }

    public BaseDaoException(ErrorCode error) {
        this(error.getCode(), error.getDescription(), null, null);
    }
}