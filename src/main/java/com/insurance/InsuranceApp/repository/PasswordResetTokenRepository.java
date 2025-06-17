package com.insurance.InsuranceApp.repository;
import com.insurance.InsuranceApp.model.PasswordResetToken; // Updated import
import com.insurance.InsuranceApp.model.User; // Updated import

import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE) // <-- ESSENTIAL FOR CONCURRENCY SAFETY
    Optional<PasswordResetToken> findByUser(User user);

    Optional<PasswordResetToken> findByToken(String token);
    @Transactional
    void deleteByUser(User user);
}
