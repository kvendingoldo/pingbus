package com.kvendingoldo.pingbus.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 23.02.17
 */

@ComponentScan
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class, args);
    }
}
