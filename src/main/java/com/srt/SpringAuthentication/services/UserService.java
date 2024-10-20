package com.srt.SpringAuthentication.services;

import com.srt.SpringAuthentication.exceptions.UserNotFoundException;
import com.srt.SpringAuthentication.models.User;
import com.srt.SpringAuthentication.repositories.UserRepository;
import com.srt.SpringAuthentication.utils.AuthenticationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser() {
        String username = AuthenticationUtils.getUserDetails().getUsername();
        Optional<User> user = userRepository.getUserByUsername(username);
        return user.orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Transactional
    public String deleteUser() {
        String username = AuthenticationUtils.getUserDetails().getUsername();
        userRepository.deleteByUsername(username);
        AuthenticationUtils.clearAuthentication();
        return "user successfully deleted";
    }

    public String changeFirstName(String newFirstName) {
        String username = AuthenticationUtils.getUserDetails().getUsername();
        userRepository.updateFirstNameByUsername(newFirstName, username);
        return "first name is updated";
    }

    public String changeLastName(String newLastName) {
        String username = AuthenticationUtils.getUserDetails().getUsername();
        userRepository.updateLastNameByUsername(newLastName, username);
        return "last name is updated";
    }
}
