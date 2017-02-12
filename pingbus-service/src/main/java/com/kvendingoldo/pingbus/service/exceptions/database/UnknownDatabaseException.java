package com.kvendingoldo.pingbus.service.exceptions.database;

import com.kvendingoldo.pingbus.service.exceptions.PingbusException;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 24.02.17
 */

public class UnknownDatabaseException extends PingbusException {

    public UnknownDatabaseException() {
    }

    public UnknownDatabaseException(String msg) {
        super(msg);
    }
}
