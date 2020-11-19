package com.example.roles.service;

import com.example.roles.convertor.UserConvertor;
import com.example.roles.dto.UserDTO;
import com.example.roles.model.Role;
import com.example.roles.model.User;
import com.example.roles.model.UserRole;
import com.example.roles.repository.RoleRepository;
import com.example.roles.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConvertor userConvertor;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConvertor = UserConvertor.INSTANCE;
    }

    @Override
    public void add(UserDTO userDTO) {

        User user = userRepository.save(userConvertor.toEntity(userDTO));
        if (userDTO.getUserRole() == null || userDTO.getUserRole().isEmpty()) {
            return;
        }
        Set<Role> roles = roleRepository.findAllByNameIn(userDTO.getUserRole());
        Set<UserRole> userRoles = new HashSet<>(roles.size());
        for (Role role : roles) {
            userRoles.add(new UserRole(user, role));
        }
        user.setUserRoleSet(userRoles);
        userRepository.save(user);
    }

    @Override
    public void delete(String login) {
        userRepository.deleteById(login);
    }

    @Override
    public void update(UserDTO userDTO) {
        User newUser = userConvertor.toEntity(userDTO);
        userRepository.findById(userDTO.getLogin()).ifPresent(oldUser -> {
            oldUser.setName(newUser.getName());
            oldUser.setPassword(newUser.getPassword());
            Set<Role> roles = roleRepository.findAllByNameIn(userDTO.getUserRole());
            oldUser.getUserRoleSet().clear();
            for (Role role : roles) {
                oldUser.getUserRoleSet().add(new UserRole(oldUser, role));
            }
        });
    }

    @Override
    public UserDTO get(String login) {
        return userRepository.findUserByLoginWithRoles(login).map(user -> userConvertor.toDTO(user))
                .orElseThrow(() -> new IllegalStateException("User with login not found"));
    }

    @Override
    public List<UserDTO> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userConvertor::toDTO)
                .collect(Collectors.toList());
    }
}
