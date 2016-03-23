package ru.javawebinar.topjava;

import java.time.LocalDateTime;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");
        String s= "2016-03-19T19:00:00";
        LocalDateTime l = LocalDateTime.parse(s);
        System.out.println(l.getHour());
    }
}
