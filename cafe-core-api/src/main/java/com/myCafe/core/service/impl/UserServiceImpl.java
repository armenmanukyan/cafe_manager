package com.myCafe.core.service.impl;

import com.myCafe.common.enums.UserRole;
import com.myCafe.core.converter.Converter;
import com.myCafe.core.dto.CafeUser;
import com.myCafe.core.service.helper.ServiceHelper;
import com.myCafe.core.service.UserService;
import com.myCafe.dal.entities.UserEntity;
import com.myCafe.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceHelper implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Converter converter;

    @Override
    @Transactional
    public CafeUser addUser(CafeUser cafeUser) {
        validateUserOnSave(cafeUser);
        if (cafeUser.getRole().equals(UserRole.MANAGER) && getManager() != null) {
            throw new RuntimeException("User with role manager already exists");
        }
        CafeUser storedUser = converter.toModel(userRepository.saveAndFlush(converter.toEntity(cafeUser)));
        return storedUser;
    }

    @Override
    @Transactional
    public CafeUser updateUser(CafeUser user) {
        validateUserOnSave(user);
        CafeUser updatedUser = converter.toModel(userRepository.saveAndFlush(converter.toEntity(user)));
        return updatedUser;
    }

    @Override
    @Transactional
    public void deleteUserById(Integer userId) {
        Assert.isTrue(userId != null, "Id should not be null");
        userRepository.deleteById(userId);
    }

    @Override
    public CafeUser getUserById(Integer userId) {
        Assert.isTrue(userId != null, "Id should not be null");
        UserEntity cafeUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with provided id was not found"));
        return converter.toModel(cafeUser);
    }

    @Override
    public List<CafeUser> getAll() {
        return userRepository.findAll().stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
    }

    @Override
    public List<CafeUser> getWaiters() {
        return userRepository.findAllByRole(UserRole.WAITER).stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
    }

    @Override
    public CafeUser getManager() {
        List<CafeUser> userList = userRepository.findAllByRole(UserRole.MANAGER).stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userList)&& userList.size() > 1) throw new RuntimeException("More than one user with role Manager exists");
        return CollectionUtils.isEmpty(userList) ? null:userList.get(0);
    }

    @Override
    public Optional<CafeUser> getUserByUsername(String username) {
        Assert.hasText(username, "Username should not be null");
        Optional<UserEntity> userEntity = userRepository.findByUserName(username);
        return userEntity.isPresent() ? Optional.of(converter.toModel(userEntity.get())):null;
    }

}
