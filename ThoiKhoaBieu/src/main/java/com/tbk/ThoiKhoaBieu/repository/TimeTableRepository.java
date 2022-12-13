package com.tbk.ThoiKhoaBieu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tbk.ThoiKhoaBieu.entity.TimeTableEntity;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTableEntity, Long>{

}
