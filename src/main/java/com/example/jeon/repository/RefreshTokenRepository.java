package com.example.jeon.repository;

import com.example.jeon.dto.Member;
import com.example.jeon.dto.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByMember(Member member);
}
