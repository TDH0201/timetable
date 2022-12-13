package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import com.tbk.ThoiKhoaBieu.Const.Constan;

import lombok.Data;

@Data
public class Timetable {
	private List<RoomDto> rooms = new ArrayList<>();
	private List<TeacherDto> teachers = new ArrayList<>();
	private List<SubjectDto> subjectDtos = new ArrayList<>();
	private List<ClassDto> classes = new ArrayList<>();
	private List<TimeSlot> timeslots = new ArrayList<>();
	private List<TimetableClass> timeTableList = new ArrayList<>();

	private int numClasses = 0;

	public Timetable() {
	}

	public Timetable(Timetable timetableClone) {
		this.rooms = timetableClone.getRooms();
		this.teachers = timetableClone.getTeachers();
		this.subjectDtos = timetableClone.getSubjectDtos();
		this.classes = timetableClone.getClasses();
		this.timeslots = timetableClone.getTimeslots();
		this.timeTableList = timetableClone.getTimeTableList();
	}

	public List<SubjectDto> getSubjectByGrade(Integer grade) {

		List<SubjectDto> subjects = new ArrayList<>();
		for (SubjectDto subjectDto : this.subjectDtos) {
			if (subjectDto.getGrade().equals(String.valueOf(grade))) {
				subjects.add(subjectDto);
			}
		}

		return subjects;
	}

	public List<TeacherDto> getTeacherByClass(Integer grade) {

		List<TeacherDto> teacherList = new ArrayList<>();
		for (TeacherDto teacher : this.teachers) {
			if (teacher.getSubjectIds().size() > 0) {
				for (SubjectDto s : teacher.getSubjectIds()) {
					if (s.getGrade().equals(String.valueOf(grade))) {
						teacherList.add(teacher);
					}
				}
			}
		}

		return teacherList;
	}

	public List<TimetableClass> getTimeTableByClass(String classes) {
		List<TimetableClass> timeTable = new ArrayList<>();
		for (TimetableClass timetableClass : this.getTimeTableList()) {
			if (timetableClass.getRoomName().equals(classes)) {
				timeTable.add(timetableClass);
			}
		}
		return timeTable;
	}

	public void createClasses(Individual individual) {
		this.timeTableList = individual.getChromosome();
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int calcClashes() {
		int clashes = 0;
		
		int countClass = 0;
		for (ClassDto classes : this.getClasses()) {

			List<TimetableClass> timTableClass = this.getTimeTableByClass(classes.getClassname());
			

			for (TimetableClass timetableClass1 : timTableClass) {
				int count = 0;
				if (Objects.nonNull(timetableClass1.getTeacherName())
						&& !Constan.monBatBuoc.contains(timetableClass1.getSubjectName().trim())) {
					for (TimetableClass timetableClass2 : this.getTimeTableList()) {
						if (Objects.nonNull(timetableClass2.getTeacherName())
								&& !Constan.monBatBuoc.contains(timetableClass2.getSubjectName().trim())) {
							if (timetableClass1.getTeacherName().equals(timetableClass2.getTeacherName())
									&& timetableClass1.getTimeSlot().equals(timetableClass2.getTimeSlot())
									&& timetableClass1.getSubjectName().equals(timetableClass2.getSubjectName())
									&& !timetableClass1.getRoomName().equals(timetableClass2.getRoomName())) {
								clashes++;
								count++;
								break;
							}
						}
					}
					if (count == 0) {
						timetableClass1.setIsCheck(false);
					} else {
						timetableClass1.setIsCheck(true);
					}
				}
			}
		}
		return clashes;
	}

	public List<TimetableClass> setGiaoVienChuNhiem() {
		List<TimetableClass> timeTableClone = new ArrayList<>();
		return timeTableClone;
	}
}