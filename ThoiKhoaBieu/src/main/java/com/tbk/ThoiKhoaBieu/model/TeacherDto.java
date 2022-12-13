package com.tbk.ThoiKhoaBieu.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TeacherDto {
	
	private Long id;

	private String name;

	private String phone;

	private String address;

	private LocalDate birthDay;

	private List<SubjectDto> subjectIds = new ArrayList<>();
	
	private Boolean isTeacherClass;
}
