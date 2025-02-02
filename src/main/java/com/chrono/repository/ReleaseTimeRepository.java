package com.chrono.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrono.domain.releasetime.ReleaseTime;
import java.util.List;
import java.util.Optional;

import com.chrono.domain.activity.Activity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.chrono.domain.user.User;

public interface ReleaseTimeRepository extends JpaRepository<ReleaseTime, Long>{
    Optional<ReleaseTime> findById(Integer id);
    List<ReleaseTime> findByActivity(Activity activity);
    List<ReleaseTime> findByDescription(String description);
    List<ReleaseTime> findByEndDate(LocalDate endDate);
    List<ReleaseTime> findByRegisterDate(LocalDateTime registerDate);
    List<ReleaseTime> findByStartDate(LocalDate startDate);
    List<ReleaseTime> findByUser(User user);
}