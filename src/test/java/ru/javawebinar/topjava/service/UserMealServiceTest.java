package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by MyMac on 23.03.16.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        MealTestData.init();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = MealTestData.userMeals.get(0);
        MealTestData.MATCHER.assertEquals(userMeal, service.get(userMeal.getId(), USER_ID));

    }
    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1,1);
    }

    @Test
    public void testDelete() throws Exception {

    }
    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1,1);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
         Collection<UserMeal > meals = service.getBetweenDates(LocalDate.of(2016,03,17),LocalDate.of(2016,03,19),USER_ID);
        Collection<UserMeal > mealsTset = MealTestData.userMeals.stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalDate(),LocalDate.of(2016,03,17),LocalDate.of(2016,03,19)))
                .collect(Collectors.toList());
        MealTestData.MATCHER.assertCollectionEquals(meals,mealsTset);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<UserMeal > meals = service.getBetweenDateTimes(LocalDateTime.parse("2016-03-19T08:00"),LocalDateTime.parse("2016-03-20T15:00"),USER_ID);
        Collection<UserMeal > mealsTest = MealTestData.userMeals.stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime(),LocalDateTime.parse("2016-03-19T08:00"),LocalDateTime.parse("2016-03-20T15:00")))
                .collect(Collectors.toList());
        MealTestData.MATCHER.assertCollectionEquals(meals,mealsTest);
    }

    @Test
    public void testGetAll() throws Exception {
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.userMeals,service.getAll(USER_ID));

    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal userMeal = MealTestData.userMeals.get(0);
        userMeal.setCalories(222);
        service.update(userMeal,100000);
        MealTestData.MATCHER.assertEquals(userMeal, service.update(userMeal, USER_ID));

    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        UserMeal userMeal = MealTestData.userMeals.get(0);
        userMeal.setCalories(222);
        service.update(userMeal,ADMIN_ID);

    }

    @Test
    public void testSave() throws Exception {
        UserMeal userMeal = new UserMeal(null, LocalDateTime.parse("2016-01-19T02:00:00"),"new lanch",700);
        service.save(userMeal,100000);
        MealTestData.userMeals.add(userMeal);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.userMeals, service.getAll(100000));
    }
}