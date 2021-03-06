package com.example.roles.repository;

import com.example.roles.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Set<Role> findAllByNameIn(List<String> roleList);
}
