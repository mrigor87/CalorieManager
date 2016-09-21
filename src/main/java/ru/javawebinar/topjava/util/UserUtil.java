package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Igor on 21.09.2016.
 */
public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(1,"User","email","password", Role.ROLE_USER),
            new User(1,"Admin","email","password", Role.ROLE_ADMIN)


    );
    public static User getUser(){
        return USERS.get(0);
    }
    public static User getAdmin(){
        return USERS.get(1);
    }
}
