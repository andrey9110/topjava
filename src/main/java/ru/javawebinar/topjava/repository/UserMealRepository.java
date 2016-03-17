package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {

    UserMeal save(UserMeal userMeal, int userId);

    boolean delete(int userId, int mealId);

    UserMeal get(int userId, int mealId);

    List<UserMeal> getAll(int userId);

    List<UserMeal> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime,int userId);
}