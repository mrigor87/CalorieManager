package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
/*          ('2015-05-30 10:00','Завтрак',500,100000),
            ('2015-05-30 13:00','Обед',1000,100000),
            ('2015-05-30 20:00','Ужин',500,100000),
            ('2015-05-31 10:00','Завтрак',1000,100000),
            ('2015-05-31 13:00','Обед',500,100000),
            ('2015-05-31 20:00','Ужин',510,100000),
            ('2015-06-01 14:00','Админ ланч',510,100001),
            ('2015-06-01 21:00','Админ ужин',1500,100001);*/


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

    public static Meal MEAL1=new Meal(START_SEQ+2,LocalDateTime.of(2015,5,30,10,00),"Завтрак",500);
    public static Meal MEAL2=new Meal(START_SEQ+3,LocalDateTime.of(2015,5,30,13,00),"Обед",1000);
    public static Meal MEAL3=new Meal(START_SEQ+4,LocalDateTime.of(2015,5,30,20,00),"Ужин",500);
    public static Meal MEAL4=new Meal(START_SEQ+5,LocalDateTime.of(2015,5,31,10,00),"Завтрак",1000);
    public static Meal MEAL5=new Meal(START_SEQ+6,LocalDateTime.of(2015,5,31,13,00),"Обед",500);
    public static Meal MEAL6=new Meal(START_SEQ+7,LocalDateTime.of(2015,5,31,20,00),"Ужин",510);

    public static Meal MEAL7=new Meal(START_SEQ+8,LocalDateTime.of(2015,6,1,14,00),"Админ ланч",510);
    public static Meal MEAL8=new Meal(START_SEQ+9,LocalDateTime.of(2015,6,1,21,00),"Админ ужин",1500);

    public static List<Meal>USER_MEALS=new ArrayList<>(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1));
    public static List<Meal>ADMIN_MEALS=new ArrayList<>(Arrays.asList(MEAL8,MEAL7));

    public static Meal NEW_MEAL=new Meal(LocalDateTime.of(2015,6,2,14,00),"Новая еда",666);

}
