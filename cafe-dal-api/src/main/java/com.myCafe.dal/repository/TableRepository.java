package com.myCafe.dal.repository;

import com.myCafe.dal.entities.OrderEntity;
import com.myCafe.dal.entities.TableEntity;
import com.myCafe.dal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<TableEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE \"WaiterTables\" SET \"WaiterId\" =:waiterId  WHERE \"WaiterTables\".\"tableId\"=:tableId", nativeQuery = true)
    public void updateTable(@Param("waiterId") Integer waiterId, @Param("tableId") Integer tableId);

    List<TableEntity> findAllByUserId(Integer userId);

    Optional<TableEntity> findByNumber(Integer number);
}
