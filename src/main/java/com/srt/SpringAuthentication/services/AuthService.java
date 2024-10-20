package com.srt.SpringAuthentication.services;

import com.srt.SpringAuthentication.models.DTO.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
}
