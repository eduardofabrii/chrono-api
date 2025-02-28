package com.chrono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chrono.domain.releasetime.ReleaseTime;
import java.util.List;
import java.util.Optional;

import com.chrono.domain.activity.Activity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.chrono.domain.user.User;

public interface ReleaseTimeRepository extends JpaRepository<ReleaseTime, Long>{
    public Optional<ReleaseTime> findById(Integer id);
    public List<ReleaseTime> findByActivity(Activity activity);
    public List<ReleaseTime> findByDescription(String description);
    public List<ReleaseTime> findByEndDate(LocalDate endDate);
    public List<ReleaseTime> findByRegisterDate(LocalDateTime registerDate);
    public List<ReleaseTime> findByStartDate(LocalDate startDate);
    public List<ReleaseTime> findByUser(User user);

    @Query("SELECT rt FROM ReleaseTime rt WHERE rt.user.id = ?1")
    List<ReleaseTime> findByUserId(Integer userId);
}