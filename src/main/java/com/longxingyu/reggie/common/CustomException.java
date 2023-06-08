package com.longxingyu.reggie.common;

/**
 * {@code @Create:}  2023-06-07  21 : 30
 * {@code @Author:} Cherry
 * {@code @ToUser:} Be Happy EveryDay
 * ------------------------------------
 * {@code @note:}
 */

@SuppressWarnings({"all"})

/**
 * 自定义业务异常
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
