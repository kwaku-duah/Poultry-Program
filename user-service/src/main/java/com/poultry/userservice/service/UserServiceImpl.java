package com.poultry.userservice.service;


import com.poultry.userservice.dto.Oauth2RresponseDto;
import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.dto.UserResponseDto;
import com.poultry.userservice.entity.Role;
import com.poultry.userservice.entity.User;
import com.poultry.userservice.exception.DuplicateResourceException;
import com.poultry.userservice.exception.ResourceNotFoundException;
import com.poultry.userservice.mapper.UserMapper;
import com.poultry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    @Override
    public void addUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new DuplicateResourceException("Email " + userRequest.email() +  " already exists");
        }

        User user = userMapper.toEntity(userRequest);
        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.password());
        user.setPassword(encodedPassword);
        /*all new users added to the system assume role ROLE_USER, until
        upgrade, to protect the application against injection
        * */
        user.setRoles(EnumSet.of(Role.ROLE_USER));
        user.setOauthUser(false);
        userRepository.save(user);


    }


    public String generateRandom() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Transactional
    public Oauth2RresponseDto oauth2Signup(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String fullName = oAuth2User.getAttribute("name");

        System.out.println("Doing this is great, fantastic, >>>>" + fullName +  email);
        if (email == null) {
            throw new IllegalArgumentException("Email not provided by Google OAuth2");
        }


        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateResourceException("Email " + email + " already exists");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFullName(fullName != null ? fullName : "Unknown");
        newUser.setPassword(bCryptPasswordEncoder.encode(generateRandom()));
        newUser.setOauthUser(true);
        newUser.setRoles(EnumSet.of(Role.ROLE_USER));
        User savedUser = userRepository.save(newUser);

        System.out.println(newUser + ">>>>>>>>>>>>>>>>>>>>");
        return new Oauth2RresponseDto(savedUser.getEmail(), savedUser.getFullName());
    }



    @Transactional
    @Override
    public void addFarmer(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));

        if(!user.getRoles().contains(Role.ROLE_FARMER)) {
            user.setRoles(EnumSet.of(Role.ROLE_FARMER));
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void addVet(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));

        if(!user.getRoles().contains(Role.ROLE_VET)) {
            user.setRoles(EnumSet.of(Role.ROLE_VET));
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void addSupplier(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));

        if(!user.getRoles().contains(Role.ROLE_SUPPLIER)) {
            user.setRoles(EnumSet.of(Role.ROLE_SUPPLIER));
            userRepository.save(user);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDto> allUsers() {
        List<User> vets = userRepository.findAll().stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_VET))
                .toList();

        return vets.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());


    }
}
