package com.drew.webnet.messenger.messenger.repository;

import com.drew.webnet.messenger.messenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
