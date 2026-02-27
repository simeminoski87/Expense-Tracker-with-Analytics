package mk.ukim.finki.expense_tracker_with_analytics.service.domain.impl;

import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Role;
import mk.ukim.finki.expense_tracker_with_analytics.service.domain.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public User register(String username, String name, String email, String password, Role role) {
        //TODO
        return null;
    }

    @Override
    public User login(String username, String password) {
        //TODO
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        //TODO
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        //TODO
        return null;
    }
}