package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.MATCHER_USER;

/**
 * Created by MyMac on 02.04.16.
 */
@ActiveProfiles({Profiles.POSTGRES,Profiles.DATAJPA})
public class DataJpaUserMealServiceTest extends UserMealServiceTest {

    @Test
    public void testGetWith() throws Exception {
        UserMeal actual = mealService.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER_USER.assertEquals(UserTestData.ADMIN, actual.getUser());
    }
}
