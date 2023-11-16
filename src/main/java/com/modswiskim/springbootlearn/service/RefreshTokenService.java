package com.modswiskim.springbootlearn.service;

import com.modswiskim.springbootlearn.domain.RefreshToken;
import com.modswiskim.springbootlearn.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToekn) {
        return refreshTokenRepository.findByRefreshToken(refreshToekn)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
