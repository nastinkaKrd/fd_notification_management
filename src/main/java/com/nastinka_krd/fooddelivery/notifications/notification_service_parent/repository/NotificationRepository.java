package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.repository;

import com.nastinka_krd.fooddelivery.notifications.notification_service_parent.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByPlanningDate(LocalDate date);
}
