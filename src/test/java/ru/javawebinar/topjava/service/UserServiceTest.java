package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData.*;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL2;
import static ru.javawebinar.topjava.UserTestData.*;


abstract public class UserServiceTest extends DbTest{

    @Test
    public void testSave() throws Exception {
        TestUser tu = new TestUser(null, "New", "new@gmail.com", "newPass", 1555, false, Collections.singleton(Role.ROLE_USER));
        User created = userService.save(tu.asUser());
        tu.setId(created.getId());
        MATCHER_USER.assertCollectionEquals(Arrays.asList(ADMIN, tu, USER), userService.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        userService.save(new TestUser("Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER).asUser());
    }

    @Test
    public void testDelete() throws Exception {
        userService.delete(USER_ID);
        MATCHER_USER.assertCollectionEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        userService.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = userService.get(USER_ID);
        MATCHER_USER.assertEquals(USER, user);
    }

    @Test
    public void testGetWithUserMeals() throws Exception {
        User user = userService.getWithUserMeal(USER_ID);
//        System.out.println(USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(  MEAL1, MEAL2, MEAL3, MEAL4, MEAL5,MEAL6), user.getMeals());
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        userService.get(1);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = userService.getByEmail("user@yandex.ru");
        MATCHER_USER.assertEquals(USER, user);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = userService.getAll();
        MATCHER_USER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void testUpdate() throws Exception {
        TestUser updated = new TestUser(USER);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        userService.update(updated.asUser());
        MATCHER_USER.assertEquals(updated, userService.get(USER_ID));
    }
}