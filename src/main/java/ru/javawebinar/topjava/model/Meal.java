package ru.javawebinar.topjava.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
//@NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM UserMeal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),

@NamedQueries({
//        @NamedQuery(name = Meal.GET_ALL, query = "SELECT m FROM Meal m LEFT JOIN FETCH  m.user u WHERE u.id=?1 ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.GET_ALL, query = "SELECT m FROM Meal m  WHERE m.user.id=?1 ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.GET_DETWEEN,
                query = "SELECT m FROM Meal m " +
                        "WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id=?1 AND m.user.id=?2"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=?1 AND m.user.id=?2"),
        @NamedQuery(name = Meal.UPDATE,
                query = "UPDATE Meal m " +
                        "SET  m.dateTime=:date_time,m.calories=:calories, m.description=:description " +
                        "WHERE m.id=:id AND m.user.id=:userId"),
        //   @NamedQuery(name = Meal.SAVE,query = ""),
})
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {
    public static final String GET_ALL = "Get.All";
    public static final String GET_DETWEEN = "Get.Between";
    public static final String GET = "Get";
    public static final String DELETE = "Delete";
    public static final String UPDATE = "Add";

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    private String description;


    @Column(name = "calories", nullable = false)
    @Range(min = 10, max = 5000)
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
