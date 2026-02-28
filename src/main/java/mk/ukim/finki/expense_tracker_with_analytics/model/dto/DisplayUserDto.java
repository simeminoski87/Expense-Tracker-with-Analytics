package mk.ukim.finki.expense_tracker_with_analytics.model.dto;

import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Role;

import java.time.LocalDateTime;

public record DisplayUserDto(String username, String name, String email, String password, Role role, LocalDateTime createdAt) {
    public static DisplayUserDto from(User user){
        return new DisplayUserDto(user.getUsername(), user.getName(), user.getEmail(),
                user.getPassword(), user.getRole(),user.getCreatedAt());
    }
    public User toUser(){
        return new User(username,name,email,password,role,createdAt);
    }
}
