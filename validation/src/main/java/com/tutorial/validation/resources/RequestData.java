package com.tutorial.validation.resources;

import com.tutorial.validation.core.validation.rules.StrongPassword;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public record RequestData(
        @NotNull
        @Length(min = 2, max = 10, message = "username should be 2 to 30 size ")
        String username,

        @NotNull
        @Length(min = 6, max = 10, message = "password should be 6 to 18 size ")
        @StrongPassword
        String password

) {

}
