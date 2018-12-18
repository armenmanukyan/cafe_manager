package com.myCafe.core.service;

import com.myCafe.core.dto.CafeTable;
import com.myCafe.dal.exceptions.DuplicateEntityException;

import java.util.List;

public interface TableService {

    /**
     * Add table.
     *
     * @param table
     */
    public void addTable(CafeTable table) throws DuplicateEntityException;

    /**
     * Delete table by id.
     *
     * @param id
     */
    public void deleteTableById(Integer id);

    /**
     * Get all tables
     *
     * @return
     */
    public List<CafeTable> getAll();

    /**
     * Get table by waiter id
     *
     * @param userId
     * @return
     */
    public List<CafeTable> getTablesByUserId(Integer userId);

    /**
     * Get table by id
     *
     * @param id
     * @return
     */
    public CafeTable getTableById(Integer id);

    /**
     * Assigns only one waiter to only one table
     *
     * @param userId
     * @param tableNumber
     * @return Table
     */
    CafeTable assignTableToUser(final Integer userId, final Integer tableNumber);

    /**
     * Unassign table from user
     *
     * @param tableNumber
     */
    void unassignTableFromUser(final Integer tableNumber);

    /**
     * Get table by provided number
     *
     * @param tableNumber
     * @return
     */
    CafeTable getTableByNumber(final Integer tableNumber);

}
