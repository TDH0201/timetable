package com.tbk.ThoiKhoaBieu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tbk.ThoiKhoaBieu.entity.ClassEntity;

@Repository
public interface ClassRepository   extends JpaRepository<ClassEntity, Long>{
	//List<ClassEntity> findAll();
}
