package com.ecommerce.dtos;

import com.ecommerce.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private int userId;
    private String email;
    private String firstName;
    private String lastName;

    public UserResponse (User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

}
