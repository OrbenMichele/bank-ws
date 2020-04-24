package com.morben.bank.ws.service;

import com.morben.bank.ws.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO user);

    UserDTO getUserByEmail(String email);
}
