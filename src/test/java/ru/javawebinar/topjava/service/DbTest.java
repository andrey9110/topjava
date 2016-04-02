package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by MyMac on 02.04.16.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class DbTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserMealService mealService;
    @Before
    public void setUp() throws Exception {
        userService.evictCache();
    }
}
