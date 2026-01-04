package com.pk.platform.securitylib.repository;

import com.pk.platform.securitylib.entity.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserIdentityRepository extends JpaRepository<UserIdentity, UUID> {
    Optional<UserIdentity> findByName(String userName);
}
