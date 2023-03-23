package com.revature.services;

import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUserRequest;
import com.revature.dtos.UserResponse;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;
    private User user1;
    private RegisterRequest registerRequest;
    private UpdateUserRequest updateUserRequest;


    @BeforeEach
    public void setUp() {
        user1 = new User(1, "test@gmail.com", "Password123!", "Test", "User", false, true, "user1Token");
        registerRequest = new RegisterRequest("new@gmail.com", "Password123!", "New", "User");
        updateUserRequest = new UpdateUserRequest("Updated", "New", "update@gmail.com", "!Test1234");

    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        registerRequest = null;
        updateUserRequest = null;
    }

    @Test
    @DisplayName("Find User By Credentials Test-Positive")
    public void findByCredentialsPositiveTest() {
        when(userRepository.findByEmailAndPassword(user1.getEmail(), user1.getPassword())).thenReturn(Optional.of(user1));

        Optional<User> savedUser = userService.findByCredentials("test@gmail.com", "Password123!");

        verify(userRepository, times(1)).findByEmailAndPassword(anyString(),anyString());
        assertNotNull(savedUser);
    }

    @Test
    @DisplayName("Find User By Credentials Test-Negative")
    public void findByCredentialsNegativeTest() {
        when(userRepository.findByEmailAndPassword(user1.getEmail(), "wrongPassword")).thenReturn(null);

        Optional<User> savedUser = userService.findByCredentials("test@gmail.com", "wrongPassword");

        verify(userRepository, times(1)).findByEmailAndPassword(anyString(),anyString());
        assertNull(savedUser);
    }

    @Test
    @DisplayName("Find User By Email Test-Positive")
    public void findByEmailPositiveTest() {
        when(userRepository.checkEmail(user1.getEmail())).thenReturn(Optional.of(user1));

        Optional<User> savedUser = userService.findByEmail(user1.getEmail());

        verify(userRepository, times(1)).checkEmail(anyString());
        assertNotNull(savedUser);
    }

    @Test
    @DisplayName("Find User By Email Test-Negative")
    public void findByEmailNegativeTest() {
        when(userRepository.checkEmail("wrong@gmail.com")).thenReturn(null);

        Optional<User> savedUser = userService.findByEmail("wrong@gmail.com");

        verify(userRepository, times(1)).checkEmail(anyString());
        assertNull(savedUser);
    }



    @DisplayName("Save user Test")
    @Test
    public void saveUserTest() {
        when(userRepository.save(user1)).thenReturn(user1);

        User savedUser = userService.save(user1);

        assertNotNull(savedUser);
        verify(userRepository, times(1)).save(any());
    }

    @DisplayName("Email Availability Test-Positive")
    @Test
    public void isEmailAvailablePositiveTest() {
        boolean emailAvailable = userService.isEmailAvailable("available@gmail.com");
        verify(userRepository, times(1)).checkEmail(any());
        assertTrue(emailAvailable);
    }
    @DisplayName("Email Availability Test-Negative")
    @Test
    public void isEmailAvailableNegativeTest() {
        when(userRepository.checkEmail(user1.getEmail())).thenReturn(Optional.of(user1));

        boolean emailNotAvailable = false;
        try{
            userService.isEmailAvailable(user1.getEmail());
        } catch (InvalidUserInputException e){
            emailNotAvailable = true;
        }
        verify(userRepository, times(1)).checkEmail(any());
        assertTrue(emailNotAvailable);
    }

    @Test
    @DisplayName("Register User Test-Positive")
    public void registerUserPositiveTest() {

        when(userRepository.save(any())).thenReturn(user1);
        userService.registerUser(registerRequest);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Register User Test-Negative")
    public void registerUserNegativeTest() {
        RegisterRequest invalidRequest = new RegisterRequest("invalidEmail", "invalidPassword", "", "");
        boolean nullUser = false;
        try{
            userService.registerUser(invalidRequest);
        } catch(NullPointerException e){
            nullUser = true;
        }
        assertTrue(nullUser);
    }


    @Test
    @DisplayName("Find User By Id Test-Positive")
    public void findUserByIdTest() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        User foundUser = userService.findUserById(user1.getId());
        verify(userRepository, times(1)).findById(anyInt());
        assertNotNull(foundUser);
    }

    @Test
    @DisplayName("Find User By Id Test-Negative")
    public void findUserByIdNegativeTest() {
        when(userRepository.findById(999)).thenThrow(ResourceNotFoundException.class);
        boolean notFound = false;
        try{
            userService.findUserById(999);
        } catch (ResourceNotFoundException e){
            notFound = true;
        }
        verify(userRepository, times(1)).findById(anyInt());
        assertTrue(notFound);
    }


}