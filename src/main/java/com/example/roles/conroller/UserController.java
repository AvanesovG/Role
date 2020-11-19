package com.example.roles.conroller;

import com.example.roles.dto.ResultDTO;
import com.example.roles.dto.UserDTO;
import com.example.roles.service.UserService;
import com.example.roles.validator.UserDtoValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserDtoValidator userDtoValidator;
    private final UserService userService;

    public UserController(UserDtoValidator userDtoValidator, UserService userService) {
        this.userDtoValidator = userDtoValidator;
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userDtoValidator);
    }

    @GetMapping(path = "/{login}")
    public UserDTO getUserByLogin(@PathVariable String login) {
        return userService.get(login);
    }

    @GetMapping
    public List<UserDTO> getAllUser() {
        return userService.getAll();
    }

    @DeleteMapping(path = "/{login}")
    public void deleteUser(@PathVariable String login) {
        userService.delete(login);
    }

    @PutMapping
    public ResultDTO updateUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultDTO.successFalse(bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::toString)
                    .collect(Collectors.toList()));
        }
        userService.update(userDTO);
        return ResultDTO.successTrue();
    }


    @PostMapping
    public ResultDTO addUser(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultDTO.successFalse(bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::toString)
                    .collect(Collectors.toList()));
        }
        userService.add(userDTO);
        return ResultDTO.successTrue();
    }
}
