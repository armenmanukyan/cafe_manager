package com.myCafe.core.service.impl;

import com.myCafe.common.enums.UserRole;
import com.myCafe.core.converter.Converter;
import com.myCafe.core.dto.CafeTable;
import com.myCafe.core.dto.CafeUser;
import com.myCafe.core.service.TableService;
import com.myCafe.core.service.UserService;
import com.myCafe.dal.entities.TableEntity;
import com.myCafe.dal.exceptions.DuplicateEntityException;
import com.myCafe.dal.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    private Converter converter;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void addTable(CafeTable table) throws DuplicateEntityException {
        Assert.notNull(table, "Table should not be null");
        Assert.notNull(table.getNumber(), "Table number should not be null");
        boolean isTableAlreadyExists = tableRepository.findByNumber(table.getNumber()).isPresent();
        if (isTableAlreadyExists) {
            throw new DuplicateEntityException(String.format("Table with provided id already exists " +
                    "[%s]", table.getNumber()));
        }
        tableRepository.save(converter.toEntity(table));
    }

    @Override
    public void deleteTableById(Integer id) {
        Assert.notNull(id, "Id should not be null");
        tableRepository.deleteById(id);
    }

    @Override
    public List<CafeTable> getAll() {
        return tableRepository
                .findAll()
                .stream()
                .map(e -> converter.toModel(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<CafeTable> getTablesByUserId(Integer userId) {
        Assert.notNull(userId, "User id should not be null");
        return tableRepository
                .findAllByUserId(userId)
                .stream()
                .map(e -> converter.toModel(e))
                .collect(Collectors.toList());
    }

    @Override
    public CafeTable getTableById(Integer id) {

        Assert.notNull(id, "Table id should not be null");
        return converter.toModel(tableRepository.getOne(id));
    }

    @Override
    @Transactional
    public CafeTable assignTableToUser(Integer userId, Integer tableNumber) {
        Assert.notNull(userId, "User id should not be null");
        Assert.notNull(tableNumber, "Table number should not be null");
        CafeUser user = userService.getUserById(userId);
        CafeTable table = getTableByNumber(tableNumber);
        Assert.isTrue(user.getRole() != null && user.getRole().equals(UserRole.WAITER), "You can't assign table to user with role MANAGER");
        table.setUserId(userId);
        CafeTable storedTable = converter.toModel(tableRepository.saveAndFlush(converter.toEntity(table)));
        return storedTable;
    }

    @Override
    @Transactional
    public void unassignTableFromUser(Integer tableNumber) {
        Assert.notNull(tableNumber, "Table number should not be null");
        CafeTable table = getTableByNumber(tableNumber);
        table.setUserId(null);
        tableRepository.saveAndFlush(converter.toEntity(table));
    }

    @Override
    public CafeTable getTableByNumber(Integer tableNumber) {
        Assert.notNull(tableNumber, "Table number should not be null");
        TableEntity table = tableRepository.findByNumber(tableNumber).orElseThrow(() -> new RuntimeException("Table not found with provided id"));

        return converter.toModel(table);
    }
}


