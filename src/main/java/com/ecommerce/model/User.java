package com.ecommerce.model;

import com.ecommerce.dtos.RegisterRequest;
import com.ecommerce.dtos.UserResponse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;




    public  User(RegisterRequest registerRequest) {
        this.email = registerRequest.getEmail();
        this.password = registerRequest.getPassword();
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();
    }

    public User(UserResponse userResponse) {
        this.email = userResponse.getEmail();
        this.firstName = userResponse.getFirstName();
        this.lastName = userResponse.getLastName();
    }

}
