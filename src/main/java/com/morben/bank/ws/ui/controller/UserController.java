package com.morben.bank.ws.ui.controller;

import com.morben.bank.ws.exceptions.BankServiceException;
import com.morben.bank.ws.service.UserService;
import com.morben.bank.ws.shared.dto.UserDTO;
import com.morben.bank.ws.ui.model.request.UserDetailsRequestModel;
import com.morben.bank.ws.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(
            path = "/{email}",
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest getUser(@PathVariable String email) {

        UserDTO userDTO = userService.getUserByEmail(email);

        if (userDTO == null)
            throw new BankServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        UserRest returnValue = modelMapper.map(userDTO, UserRest.class);

        return returnValue;

    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetalhes) throws Exception {


        if (userDetalhes.getNome() == null || userDetalhes.getNome().isEmpty() ||
                userDetalhes.getEmail() == null || userDetalhes.getEmail().isEmpty() ||
                userDetalhes.getPassword() == null || userDetalhes.getPassword().isEmpty() )
            throw new BankServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(userDetalhes, UserDTO.class);

        UserDTO createdUser = userService.createUser(userDTO);

        return modelMapper.map(createdUser, UserRest.class);
    }
}