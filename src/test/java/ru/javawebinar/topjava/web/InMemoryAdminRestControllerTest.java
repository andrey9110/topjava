package ru.javawebinar.topjava.web;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

@ContextConfiguration({"classpath:spring/spring-app.xml","classpath:spring/spring-db.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class InMemoryAdminRestControllerTest {
//    private static ConfigurableApplicationContext appCtx;
//    private static AdminRestController controller;

//    @BeforeClass
//    public static void beforeClass() {
//        appCtx = new ClassPathXmlApplicationContext("classpath:spring/spring-app.xml","classpath:spring/spring-db.xml");
//        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
//        controller = appCtx.getBean(AdminRestController.class);
//    }


    @Autowired
    private AdminRestController controller;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }
}