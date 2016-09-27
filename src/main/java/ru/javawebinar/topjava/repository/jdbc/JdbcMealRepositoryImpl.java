package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {
    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("description", meal.getDescription())
                .addValue("date_time", meal.getDateTime())
                .addValue("calories", meal.getCalories())
                .addValue("user_id",userId)
                ;

        if (meal.isNew()){
            Number number = insertUser.executeAndReturnKey(map);
            meal.setId(number.intValue());
        }else{
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET description=:description, date_time=:date_time, calories=:calories " +
                            "WHERE id=:id  AND user_id=:user_id", map);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
       // namedParameterJdbcTemplate.query("DELETE FROM meals WHERE id=:id ",id);
                //namedParameterJdbcTemplate.update("DELETE FROM meals WHERE id=:id ",id)!=0;
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?",id,userId)!=0;
    }

    @Override
    public Meal get(int id, int userId) {
      //  namedParameterJdbcTemplate.query("SELECT * FROM meals WHERE id=:id AND user_id=:userId",ROW_MAPPER, id,userId);
        List<Meal> query = jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(query);
    }

    @Override
    public List<Meal> getAll(int userId) {
       // List<Meal> query = jdbcTemplate.query("SELECT * FROM meals WHERE user_id=?", ROW_MAPPER, userId);
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER,  userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return jdbcTemplate.query("SELECT * FROM meals " +
                "WHERE user_id=? AND date_time<=? AND meals.date_time>=? " +
                "ORDER BY date_time DESC",
                ROW_MAPPER,  userId,endDate,startDate);
    }
}
