package com.springdoc.repository;

import com.springdoc.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springdoc.model.Role;

import java.util.List;

/**
 * Created by alucard on 8/4/17.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByRole(Authority auth);
}
