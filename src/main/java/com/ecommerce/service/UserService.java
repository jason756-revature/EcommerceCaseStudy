package com.ecommerce.service;

import com.ecommerce.dtos.RegisterRequest;
import com.ecommerce.dtos.UserResponse;
import com.ecommerce.exceptions.InvalidUserInputException;
import com.ecommerce.exceptions.ResourcePersistanceException;
import com.ecommerce.model.User;
import com.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        return userRepository.checkEmail(email);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email){
        if(userRepository.checkEmail(email).isPresent())
            throw new InvalidUserInputException("Email: " + email + " is already registered please try again. Or Log in with email & password.");
        return true;
    }

    @Transactional
    public UserResponse registerUser(RegisterRequest registerRequest) throws InvalidUserInputException, ResourcePersistanceException {
        User newUser = new User(registerRequest);
        isEmailAvailable(newUser.getEmail());
        return new UserResponse(userRepository.save(newUser));
    }



}
