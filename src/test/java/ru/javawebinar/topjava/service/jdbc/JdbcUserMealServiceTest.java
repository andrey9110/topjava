package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

/**
 * Created by MyMac on 02.04.16.
 */
@ActiveProfiles({Profiles.POSTGRES,Profiles.JDBC})
public class JdbcUserMealServiceTest extends UserMealServiceTest{

}
