package com.myCafe.dal.repository;

import com.myCafe.common.enums.UserRole;
import com.myCafe.dal.entities.OrderEntity;
import com.myCafe.dal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Transient
    @Modifying
    @Query(value = "INSERT INTO \"WaiterTables\"(\"TableId\", \"WaiterId\") VALUES (:tableId, :waiterId)", nativeQuery = true)
    void assignTableToWaiter(@Param("tableId") Integer tableId, @Param("waiterId") Integer weaiterId);

    List<UserEntity> findAllByRole(UserRole role);

    Optional<UserEntity> findByUserName(String userName);
}
