package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.User;
import com.beom.spring_simpleboard.dto.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> getUserList(Long id);
}
