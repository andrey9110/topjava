package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by andrey9110 on 11.03.2016.
 */

public class InMemoryUserRepositoryImpl implements UserRepository{
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(1,"user","user@user.com","password", Role.ROLE_USER));
        save(new User(2,"admin","admin@admin.com","password", Role.ROLE_ADMIN));
    }

    public static Comparator<User> COMPARE_NAME = (o1, o2) -> o1.getName().compareTo(o2.getName());

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) !=null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return getAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>(repository.values());
        Collections.sort(result,COMPARE_NAME);
        return result;
    }
}