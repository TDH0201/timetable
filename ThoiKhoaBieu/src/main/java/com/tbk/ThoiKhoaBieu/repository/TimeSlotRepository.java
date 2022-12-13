package com.tbk.ThoiKhoaBieu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tbk.ThoiKhoaBieu.entity.TimeSlotEntity;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT id,timeslot FROM timeslot ORDER BY id ASC ")
	List<TimeSlotEntity> findAllASCById();

}
