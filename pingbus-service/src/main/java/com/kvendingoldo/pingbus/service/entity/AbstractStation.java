package com.kvendingoldo.pingbus.service.entity;

import java.time.LocalTime;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 24.02.17
 */

public interface AbstractStation {

    void setId(int id);

    void setTTA(LocalTime tta);

    void setRating(int rating);

    int getId();

    LocalTime getTTA();

    int getRating();
}
