package com.jxt.jxtbank.role.repository;

import com.jxt.jxtbank.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    <Optional> Role findByName(String name);

}
