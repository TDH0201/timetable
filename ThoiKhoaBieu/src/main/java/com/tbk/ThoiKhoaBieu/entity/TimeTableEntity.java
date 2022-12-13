package com.tbk.ThoiKhoaBieu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "timetable")
public class TimeTableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "teacherid", referencedColumnName = "id")
	private TeacherEntity teacherId;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "classid", referencedColumnName = "id")
	private ClassEntity classId;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "subjectid", referencedColumnName = "id")
	private SubjectEntity subjectId;

	@Column(name = "day")
	private String day;

	@Column(name = "time")
	private String time;
}
