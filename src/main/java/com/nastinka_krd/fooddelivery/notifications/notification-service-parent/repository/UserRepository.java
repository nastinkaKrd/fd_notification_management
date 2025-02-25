package com.nastinka_krd.fooddelivery.notifications.repository;

import com.nastinka_krd.fooddelivery.notifications.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByIsSendingNotificationAllowed(Boolean isTrue);


    @Query("UPDATE User e SET e.isSendingNotificationAllowed = CASE WHEN e.isSendingNotificationAllowed = true THEN false ELSE true END WHERE e.email = :userEmail")
    void updateIsSendingNotificationAllowedColumn(@Param("userEmail") String userEmail);
}
