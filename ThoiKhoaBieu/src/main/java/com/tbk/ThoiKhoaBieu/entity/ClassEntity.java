package com.tbk.ThoiKhoaBieu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "class")
public class ClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roomid", referencedColumnName = "id")
	private RoomEntity roomEntity;
	
	@ManyToMany(mappedBy = "classes", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TeacherEntity> teacherId;

	@Column(name="grade")
	private Integer grade;

	@Column(name="classname")
	private String classname;

	@Column(name="classSize")
	private String classSize;

	@Column(name="timeLearn")
	private Integer timeLearn;
}
