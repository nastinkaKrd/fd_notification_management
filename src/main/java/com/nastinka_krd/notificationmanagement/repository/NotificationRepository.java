package com.nastinka_krd.notificationmanagement.repository;

import com.nastinka_krd.notificationmanagement.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByPlanningDate(Date date);
}
