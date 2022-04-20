package com.ariefpermana.user_management.repositories;

import com.ariefpermana.user_management.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}