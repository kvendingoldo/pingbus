package com.kvendingoldo.pingbus.service.entity;

import java.time.LocalTime;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 12.02.17
 */

public class Station implements AbstractStation {

    private int id;
    private LocalTime tta;
    private int rating;

    public Station() {
        id = 0;
        tta = LocalTime.of(0, 0, 0);
        rating = 0;
    }

    public Station(int id, LocalTime tta, int rating) {
        this.id = id;
        this.tta = tta;
        this.rating = rating;
    }

    public Station(LocalTime tta, int rating) {
        this.tta = tta;
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTTA(LocalTime tta) {
        this.tta = tta;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public LocalTime getTTA() {
        return tta;
    }

    public int getRating() {
        return rating;
    }
}
