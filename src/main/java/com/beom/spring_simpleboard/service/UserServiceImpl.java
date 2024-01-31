package com.beom.spring_simpleboard.service;

import com.beom.spring_simpleboard.domain.User;
import com.beom.spring_simpleboard.dto.UserDTO;
import com.beom.spring_simpleboard.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final TestRepository testRepository;

    @Override
    public List<UserDTO> getUserList(Long id) {
        List<User> userList = testRepository.findAll();
        return null;
    }
}
