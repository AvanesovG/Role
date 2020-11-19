package com.example.roles.validator;

import com.example.roles.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Service
public class UserDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        if (userDTO.getLogin() == null) {
            errors.rejectValue("login", "Empty login input");
        }

        if (userDTO.getName() == null) {
            errors.rejectValue("name", "Empty login input");
        }

        if (!Pattern.matches("((?=.*[0-9])(?=.*[A-Z]))", userDTO.getPassword())) {
            errors.rejectValue("password", "Password must contain one upper registry letter and at least one number");
        }
    }
}
