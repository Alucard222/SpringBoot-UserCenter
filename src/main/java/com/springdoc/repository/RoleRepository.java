package com.springdoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springdoc.model.Role;

/**
 * Created by alucard on 8/4/17.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
