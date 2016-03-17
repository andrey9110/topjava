package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(UserMeal userMeal, int userId);

    void delete(int userId, int mealId);

    UserMeal get(int userId, int mealId);

    List<UserMeal> getAll(int userId);

    List<UserMeal> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime,int userId);
}
