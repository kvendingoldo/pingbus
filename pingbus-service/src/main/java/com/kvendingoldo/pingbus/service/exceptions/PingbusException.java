package com.kvendingoldo.pingbus.service.exceptions;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 24.02.17
 */

public class PingbusException extends RuntimeException {

    public PingbusException() {
    }

    public PingbusException(String msg) {
        super(msg);
    }
}
