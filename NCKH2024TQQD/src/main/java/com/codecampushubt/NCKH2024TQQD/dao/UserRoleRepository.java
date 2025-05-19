package com.codecampushubt.NCKH2024TQQD.dao;

import com.codecampushubt.NCKH2024TQQD.entity.UserRole;
import com.codecampushubt.NCKH2024TQQD.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository


public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    @Modifying
    @Transactional
    @Query("DELETE FROM UserRole ur WHERE ur.user.userID = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
