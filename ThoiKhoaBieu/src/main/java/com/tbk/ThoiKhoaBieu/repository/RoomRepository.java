package com.tbk.ThoiKhoaBieu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tbk.ThoiKhoaBieu.entity.RoomEntity;

@Repository
public interface RoomRepository  extends JpaRepository<RoomEntity, Long>  {
	
}
