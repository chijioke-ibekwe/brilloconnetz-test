package dev.chijiokeibekwe.questionone.data;

import dev.chijiokeibekwe.questionone.annotation.Age;
import jakarta.validation.constraints.*;

public class UserData {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "Username cannot be less than 4 characters")
    private final String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be a valid email address")
    private final String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password cannot be less than 8 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*_-])(?=.*\\d).+$",
            message = "Password must have at least 1 uppercase character, 1 special character and 1 number")
    private final String password;

    @NotEmpty(message = "Date of birth cannot be empty")
    @Age(min = 16, message = "Age must be more than or equal to 16 years")
    private final String dateOfBirth;

    public UserData(String username, String email, String password, String dateOfBirth) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
