package com.tbk.ThoiKhoaBieu.model;

import java.util.List;

import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;

import lombok.Data;

@Data
public class SubjectDto {

	private Long id;
	private String name;
	private String grade;
	private Integer numberTime;
	private List<TeacherEntity> teachIds;

}
