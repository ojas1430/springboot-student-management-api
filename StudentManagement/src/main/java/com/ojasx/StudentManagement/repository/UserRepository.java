package com.ojasx.StudentManagement.repository;

import com.ojasx.StudentManagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

    boolean existsByUsername(String username);
}
