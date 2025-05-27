package com.unithon.team15_server.global.verification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {
    Optional<Email> findEmailByEmail(String email);
}
