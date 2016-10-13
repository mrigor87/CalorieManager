package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Igor on 13.10.2016.
 */
@ActiveProfiles({Profiles.JPA,Profiles.ACTIVE_DB})
public class JPAUserServiceTest extends AbstractUserServiceTest {
}
