package com.myCafe.core.service;

import com.myCafe.core.dto.CafeUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Save user into db.
     *
     * @param cafeUser
     */
    public CafeUser addUser(CafeUser cafeUser);

    /**
     * Updates user
     *
     * @return user
     */
    CafeUser updateUser(CafeUser user);

    /**
     * Removes user by id.
     *
     * @param id
     */
    public void deleteUserById(Integer id);

    /**
     * Get user for provided ID
     *
     * @param userId
     * @return User
     * @throws RuntimeException if user doesn't exist for provided id
     */
    CafeUser  getUserById(final Integer userId);

    /**
     * Get all users
     *
     * @return
     */
    public List<CafeUser> getAll();

    /**
     * Get users with role Waiter
     *
     * @return
     */
    public List<CafeUser> getWaiters();

    /**
     * Get manager
     *
     * @return manager
     */
    CafeUser getManager();

    /**
     * Get user by username.
     *
     * @param username
     * @return
     */
    public Optional<CafeUser> getUserByUsername(String username);


}
