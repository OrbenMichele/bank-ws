package com.morben.bank.ws.service.impl;

import com.morben.bank.ws.exceptions.BankServiceException;
import com.morben.bank.ws.io.entity.UserEntity;
import com.morben.bank.ws.repository.UserRepository;
import com.morben.bank.ws.service.UserService;
import com.morben.bank.ws.shared.Utils;
import com.morben.bank.ws.shared.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;


	public UserDTO createUser(UserDTO user) {


		if (userRepository.findByEmail(user.getEmail()) != null) throw new BankServiceException("Registro já existe!");

		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);


		String publicUserId = utils.generateUserId(20);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDTO returnValue =modelMapper.map(storedUserDetails, UserDTO.class);

		//amazonSES.verifyEmail(returnValue);

		return returnValue;

	}

	@Override
	public UserDTO getUserByEmail(String email) {

		UserEntity userEntity = this.userRepository.findByEmail(email);

		if (userEntity == null) throw new UsernameNotFoundException("User with email: " + email + " not found.");

		UserDTO user = new UserDTO();
		BeanUtils.copyProperties(userEntity, user);
		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null) throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());

	}
}

