
package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;


    public UserMeal save(UserMeal userMeal, int userId) {
        return ExceptionUtil.check(repository.save(userMeal, userId),userMeal.getId());
    }


    public void delete(int userId, int mealId)  throws NotFoundException {
        ExceptionUtil.check(repository.delete(userId,mealId), mealId);
    }


    public UserMeal get(int userId, int mealId) throws NotFoundException {
        return ExceptionUtil.check(repository.get(userId,mealId), mealId);
    }


    public List<UserMeal> getAll(int userId) throws NotFoundException{
        return ExceptionUtil.check(repository.getAll(userId),userId);
    }

    @Override
    public List<UserMeal> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, int userId) {
        return ExceptionUtil.check(repository.getFiltered(fromDate,toDate,fromTime,toTime,userId),userId);
    }
}