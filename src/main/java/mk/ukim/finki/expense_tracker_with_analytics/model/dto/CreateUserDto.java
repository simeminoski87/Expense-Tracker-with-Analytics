package mk.ukim.finki.expense_tracker_with_analytics.model.dto;

import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Role;

import java.time.LocalDateTime;

public record CreateUserDto(String username, String name, String email, String password, Role role, LocalDateTime createdAt) {
    public User toUser(){
        return new User(username,name,email,password,role,createdAt);
    }

    }
