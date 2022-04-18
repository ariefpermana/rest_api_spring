package com.ariefpermana.user_management.repositories;

import com.ariefpermana.user_management.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    public List<User> findByNameContaining(String name);

}