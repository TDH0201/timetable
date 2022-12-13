package com.tbk.ThoiKhoaBieu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "subject")
public class SubjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(mappedBy = "subjectIds", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TeacherEntity> teachIds;

	@Column(name = "name")
	private String name;

	@Column(name = "grade")
	private String grade;

	@Column(name = "numberTime")
	private Integer numberTime;
}
