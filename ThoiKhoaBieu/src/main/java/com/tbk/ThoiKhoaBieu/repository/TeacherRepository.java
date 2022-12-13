package com.tbk.ThoiKhoaBieu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;

@Repository
public interface  TeacherRepository extends JpaRepository<TeacherEntity, Long> {
	
	List<TeacherEntity> findAll();
	
	Optional <TeacherEntity> findById(Long id);
}
