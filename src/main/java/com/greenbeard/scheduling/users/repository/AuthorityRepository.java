package com.greenbeard.scheduling.users.repository;

import com.greenbeard.scheduling.users.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
