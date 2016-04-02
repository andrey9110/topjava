package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

/**
 * Created by MyMac on 02.04.16.
 */
@ActiveProfiles({Profiles.POSTGRES,Profiles.DATAJPA})
public class DataJpaUserMealServiceTest extends UserMealServiceTest {
}
