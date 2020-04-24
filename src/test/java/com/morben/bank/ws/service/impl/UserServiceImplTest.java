package com.morben.bank.ws.service.impl;

import com.morben.bank.ws.exceptions.BankServiceException;
import com.morben.bank.ws.io.entity.UserEntity;
import com.morben.bank.ws.repository.UserRepository;
import com.morben.bank.ws.shared.Utils;

import static org.junit.jupiter.api.Assertions.*;

import com.morben.bank.ws.shared.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String userId = "dfdfd678478";
    String encryptedPassword = "sdfx76hhbh";
    UserEntity userEntity;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setNome("Michele Orben");
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword(this.encryptedPassword);
        userEntity.setEmail("test@test.com.br");
        userEntity.setEmailVerificationToken("sjhf7bhb");
    }

    @Test
    void createUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateUserId(anyInt())).thenReturn(userId);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        //Mockito.doNothing().when(amazonSES).verifyEmail(any(UserDTO.class));//exclude integration code from unit test;

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");
        userDTO.setNome("Michele Orben");
        userDTO.setPassword("123456");

        UserDTO storedUserDetails = userService.createUser(userDTO);
        assertNotNull(storedUserDetails);
        assertNotNull(storedUserDetails.getUserId());
        assertEquals(userEntity.getNome(), storedUserDetails.getNome());
        assertEquals(userEntity.getEmail(), storedUserDetails.getEmail());
        verify(this.bCryptPasswordEncoder, times(1)).encode("123456");
        verify(this.userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void getUserByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        UserDTO userDTO = userService.getUserByEmail("teste@teste.com");

        assertNotNull(userDTO);
        assertEquals("Michele Orben", userDTO.getNome());
        assertEquals("test@test.com.br", userDTO.getEmail());
        assertEquals(encryptedPassword, userDTO.getEncryptedPassword());

    }

    @Test
    final void testGetUser_UsernameNotFoundException() {

        when(userRepository.findByEmail(anyString())).thenReturn(null);

        //if (userEntity == null) throw new UsernameNotFoundException("User with email: " + email + " not found.");

        assertThrows(UsernameNotFoundException.class,
                ()->{
                    userService.getUserByEmail("teste@teste.com");
                });

    }

    @Test
    final void testCreateUser_CreateUserServiceException() {

        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");
        userDTO.setNome("Michele Orben");
        userDTO.setPassword("123456");

        assertThrows(BankServiceException.class,
                ()->{
                    userService.createUser(userDTO);
                });

    }
}