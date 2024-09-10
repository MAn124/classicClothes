package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.LoginRequest;
import com.ttma.classicClothes.dto.response.ResponseToken;
import com.ttma.classicClothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public ResponseToken authenticate(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        String accessToken = jwtService.generateToken(user);
        String freshToken = jwtService.generateRefreshToken(user);
        return ResponseToken.builder()
                .accessToken(accessToken)
                .refreshToken(freshToken)
                .id(user.getId())
                .build();
    }
}
