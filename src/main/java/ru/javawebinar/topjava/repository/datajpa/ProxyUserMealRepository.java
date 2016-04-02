package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by MyMac on 01.04.16.
 */
//@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal,Integer> {

    @Override
    @Transactional
    UserMeal save(UserMeal meal);

    @Transactional
    @Modifying
    @Query(name = UserMeal.DELETE)
    int delete(@Param("id") int id,@Param("userId") int userId);


    @Query(name = UserMeal.GET)
    UserMeal get(@Param("id") int id,@Param("userId") int userId);

    @Query(name = UserMeal.ALL_SORTED)
    List<UserMeal> getAll(@Param("userId") int userId);

    @Query(name = UserMeal.GET_BETWEEN)
    List<UserMeal> getBetween(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("userId") int userId);

    @Transactional
    @Query("SELECT m FROM UserMeal m JOIN FETCH m.user WHERE m.id=:id AND m.user.id=:userId")
    UserMeal getWithUser(@Param("id") int id,@Param("userId") int userId);


}
