package com.ecommerce.services;

import com.ecommerce.dtos.RegisterRequest;
import com.ecommerce.model.User;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;
    private User user1;
    private RegisterRequest registerRequest;


    @BeforeEach
    public void setup() {
        user1 = new User(1, "Valid", "Valid", "Valid", "Valid");
        registerRequest = new RegisterRequest("Valid", "Valid", "Valid", "Valid");
    }


    @AfterEach
    public void teardown(){
        user1 = null;
        registerRequest = null;
    }

    @Test
    @DisplayName("Test should pass when registering user with valid input data")
    public void testRegisterUser_givenValidInput() {

        //Use thenThrows to test for errors and exceptions thrown during method execution
        when(userRepository.save(any())).thenReturn(user1);
        userService.registerUser(registerRequest);
        verify(userRepository, times(1)).save(any());
    }


    @DisplayName("JUnit test for findByCredentials method")
    @Test
    public void givenUserEmailAndPassword_whenGetUserEmailAndPassword_thenReturnUserObject() {
        // given
        given(userRepository.findByEmailAndPassword(user1.getEmail(), user1.getPassword())).willReturn(Optional.of(user1));

        // when
        Optional<User> savedUser = userService.findByCredentials("Valid", "Valid");

        // then
        assertThat(savedUser).isNotNull();

    }

}
