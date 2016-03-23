package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);
    public static int USERMEAL_ID ;
    public static List<UserMeal> userMeals ;
    public static List<UserMeal> adminMeals ;

    public static void init(){
        USERMEAL_ID = START_SEQ +1;
        userMeals = Arrays.asList(
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-18T09:00:00"),"User breakfast #1",500),
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-19T12:00:00"),"User lunch #1",1000),
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-19T19:00:00"),"User dinner #1",500),
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-20T09:00:00"),"User breakfast #2",700),
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-20T12:00:00"),"User lunch #2",1000))
                .stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());


        adminMeals = Arrays.asList(
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-19T19:00:00"),"Admin dinner #1",500),
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-20T09:00:00"),"Admin breakfast #2",700),
                new UserMeal(++USERMEAL_ID,LocalDateTime.parse("2016-03-20T12:00:00"),"Admin lunch #3",1000))
                .stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());

    }


}
