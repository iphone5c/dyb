package com.dyb.platforms.fixfunds.services.utils.core.exception;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2016/3/1.
 */
public class DybRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 6549836270325498175L;
    private int exceptionCode = DybExceptionCode.UNDEFINED_EXCEPTION;
    private String message;

    /**
     * 默认构造方法
     */
    public DybRuntimeException() {
        super();
    }

    /**
     * 构建与指定详细信息，异常原因，抑制启用或禁用，并写入启用或禁用堆栈跟踪。
     *
     * @param message            详细信息
     * @param cause              原因
     * @param enableSuppression  启用或禁用挂起
     * @param writableStackTrace 是否写跟踪堆栈
     * @param exceptionCode      异常代码
     */
    public DybRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                               int exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构建指定详细信息的新异常。
     *
     * @param message       详细信息
     * @param exceptionCode 异常代码
     */
    public DybRuntimeException(String message, int exceptionCode) {
        super(message);
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构建指定原因的新异常
     *
     * @param cause 原因
     */
    public DybRuntimeException(Throwable cause, int exceptionCode) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    /**
     * 构建指定详细信息和原因的新异常
     *
     * @param message       详细信息
     * @param cause         原因
     * @param exceptionCode 异常代码
     */
    public DybRuntimeException(String message, Throwable cause, int exceptionCode) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }


    /**
     * 构建指定详细信息的新异常。
     *
     * @param message 详细信息
     */
    public DybRuntimeException(String message) {
        super(message);
        this.message = message;
        this.exceptionCode = DybExceptionCode.UNDEFINED_EXCEPTION;
    }

    /**
     * 构建指定原因的新异常
     *
     * @param cause 原因
     */
    public DybRuntimeException(Throwable cause) {
        super(cause);
        this.exceptionCode = DybExceptionCode.UNDEFINED_EXCEPTION;
    }

    /**
     * 构建指定详细信息和原因的新异常
     *
     * @param message 详细信息
     * @param cause   原因
     */
    public DybRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.exceptionCode = DybExceptionCode.UNDEFINED_EXCEPTION;
    }

    /**
     * 构建与指定详细信息，异常原因，启用或禁用挂起，并写入启用或禁用跟踪堆栈。
     *
     * @param message            详细信息
     * @param cause              原因
     * @param enableSuppression  启用或禁用挂起
     * @param writableStackTrace 是否写跟踪堆栈
     */
    public DybRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = DybExceptionCode.UNDEFINED_EXCEPTION;
    }

    /**
     * 获取异常代码
     *
     * @return exceptionCode 异常代码
     */
    public int getExceptionCode() {
        return exceptionCode;
    }

    /**
     * 设置异常代码
     *
     * @param exceptionCode 要设置的异常代码
     */
    public void setExceptionCode(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    @Override
    public String getMessage() {
        String result = null;
        if (StringUtils.isBlank(message) && getCause() != null && getCause() instanceof InvocationTargetException) {
            Throwable targetEx = ((InvocationTargetException) getCause()).getTargetException();
            if (targetEx != null)
                result = targetEx.getMessage();
        }
        if (result == null)
            result = super.getMessage();
        return result;
    }
}
