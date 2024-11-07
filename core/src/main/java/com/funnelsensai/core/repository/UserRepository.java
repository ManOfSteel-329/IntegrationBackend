package com.funnelsensai.core.repository;

import com.funnelsensai.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
