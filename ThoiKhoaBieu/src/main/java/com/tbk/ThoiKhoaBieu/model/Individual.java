package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import com.tbk.ThoiKhoaBieu.Const.Constan;

import lombok.Data;

@Data
public class Individual {

	private List<TimetableClass> chromosome = new ArrayList<>();
	private double fitness = -1;
	private Random random = new Random();
	private TeacherDto chuNhiem = new TeacherDto();
	private List<TeacherDto> chuNhiemList = new ArrayList<>();

	private Map<Long, Integer> soTiet = new HashMap<>();

	public Individual(int chromosomeSize) {

		for (int i = 0; i < chromosomeSize; i++) {
			this.chromosome.add(new TimetableClass());
		}
	}

	public Individual() {
	}
	
	public Individual(Timetable timetable) {

		for (ClassDto classDto : timetable.getClasses()) {

			List<SubjectDto> subjects = timetable.getSubjectByGrade(classDto.getGrade());

			SubjectDto monTV = new SubjectDto();

			SubjectDto chaoCo = new SubjectDto();

			SubjectDto sinhHoatLop = new SubjectDto();

			SubjectDto ngll = new SubjectDto();

			List<TeacherDto> teacherDtoList = timetable.getTeacherByClass(classDto.getGrade());

			List<TeacherDto> giaoVienChuNhiem = teacherDtoList.stream().filter(dto -> {
				return dto.getIsTeacherClass();
			}).collect(Collectors.toList());

			// set chu nhiem
			while (true) {
				int teacherRamdom = random.nextInt(giaoVienChuNhiem.size());
				if (!chuNhiemList.contains(giaoVienChuNhiem.get(teacherRamdom))) {
					this.chuNhiem = giaoVienChuNhiem.get(teacherRamdom);
					chuNhiemList.add(giaoVienChuNhiem.get(teacherRamdom));
					break;
				}

			}

			// set so tiet
			for (SubjectDto subject : subjects) {
				soTiet.put(subject.getId(), subject.getNumberTime());
				if (Constan.chao_Co.equals(subject.getName().trim())) {
					chaoCo.setId(subject.getId());
					chaoCo.setName(subject.getName());
				}

				if (Constan.NGLL.equals(subject.getName())) {
					ngll.setId(subject.getId());
					ngll.setName(subject.getName());
				}

				if (Constan.Shlop.equals(subject.getName())) {
					sinhHoatLop.setId(subject.getId());
					sinhHoatLop.setName(subject.getName());
				}

				if (Constan.tiengViet.equals(subject.getName().trim())) {
					monTV.setId(subject.getId());
					monTV.setName(subject.getName());
				}
			}

			int isTV1 = 1;
			int isTV2 = 1;
			int isTV3 = 1;
			for (TimeSlot timeSlot : timetable.getTimeslots()) {

				TimetableClass timetableClass = new TimetableClass();

				if (Constan.timeSlotCC.equals(timeSlot.getTimeslot())) {

					timetableClass.setSubjectName(chaoCo.getName());
					timetableClass.setSubJectsId(chaoCo.getId());
					timetableClass.setTimeSlot(timeSlot.getTimeslot());
					timetableClass.setTimSlotId(timeSlot.getTimeslotId());
					timetableClass.setTeacherId(chuNhiem.getId());
					timetableClass.setTeacherName(chuNhiem.getName());

					soTiet.remove(chaoCo.getId());

					timetableClass.setRoomName(classDto.getClassname());
					timetableClass.setRoomId(classDto.getId());
					chromosome.add(timetableClass);

					continue;
				}

				if (Constan.timeSlotNgll.equals(timeSlot.getTimeslot())) {
					timetableClass.setSubjectName(ngll.getName());
					timetableClass.setSubJectsId(ngll.getId());
					timetableClass.setTimeSlot(timeSlot.getTimeslot());
					timetableClass.setTimSlotId(timeSlot.getTimeslotId());
					timetableClass.setTeacherId(chuNhiem.getId());
					timetableClass.setTeacherName(chuNhiem.getName());

					soTiet.remove(ngll.getId());

					timetableClass.setRoomName(classDto.getClassname());
					timetableClass.setRoomId(classDto.getId());
					chromosome.add(timetableClass);

					continue;
				}

				if (timeSlot.getTimeslot().equals(Constan.timeSlotSinhHoatLop)) {
					timetableClass.setSubjectName(sinhHoatLop.getName());
					timetableClass.setSubJectsId(sinhHoatLop.getId());
					timetableClass.setTimeSlot(timeSlot.getTimeslot());
					timetableClass.setTimSlotId(timeSlot.getTimeslotId());
					timetableClass.setTeacherId(chuNhiem.getId());
					timetableClass.setTeacherName(chuNhiem.getName());

					soTiet.remove(sinhHoatLop.getId());

					timetableClass.setRoomName(classDto.getClassname());
					timetableClass.setRoomId(classDto.getId());
					chromosome.add(timetableClass);
					continue;
				}

				if (!timeSlot.getTimeslot().substring(0, 2).equals("T2")
						&& Constan.timeSlotTV.contains(timeSlot.getTimeslot().substring(3))) {
					if (soTiet.get(monTV.getId()) != null) {
						timetableClass.setSubjectName(monTV.getName());
						timetableClass.setSubJectsId(monTV.getId());
						timetableClass.setTimeSlot(timeSlot.getTimeslot());
						timetableClass.setTimSlotId(timeSlot.getTimeslotId());
						timetableClass.setTeacherId(chuNhiem.getId());
						timetableClass.setTeacherName(chuNhiem.getName());

						int numberTime = soTiet.get(monTV.getId()) - 1;
						soTiet.put(monTV.getId(), numberTime);

						timetableClass.setRoomName(classDto.getClassname());
						timetableClass.setRoomId(classDto.getId());
						chromosome.add(timetableClass);
						continue;
					}

				}

				if (timeSlot.getTimeslot().substring(3).equals("10:20 - 10:55")) {
					if (classDto.getClassname().charAt(0) == '1' || classDto.getClassname().charAt(0) == '2'
							|| classDto.getClassname().charAt(0) == '3') {

						timetableClass.setRoomName(classDto.getClassname());
						timetableClass.setRoomId(classDto.getId());
						timetableClass.setTimeSlot(timeSlot.getTimeslot());
						timetableClass.setTimSlotId(timeSlot.getTimeslotId());
						chromosome.add(timetableClass);
						continue;
					} else {
						if (timeSlot.getTimeslot().equals("T2 10:20 - 10:55")
								|| timeSlot.getTimeslot().equals("T4 10:20 - 10:55")
								|| timeSlot.getTimeslot().equals("T6 10:20 - 10:55")) {
							timetableClass.setRoomName(classDto.getClassname());
							timetableClass.setRoomId(classDto.getId());
							timetableClass.setTimeSlot(timeSlot.getTimeslot());
							timetableClass.setTimSlotId(timeSlot.getTimeslotId());
							chromosome.add(timetableClass);
							continue;
						} else {

						}
					}
				}

				if (!Constan.ngayNghi.contains(timeSlot.getTimeslot())) {
					// set subject
					while (true) {
						if (soTiet.size() > 1) {

							int radom1 = random.nextInt(subjects.size());
//							System.out.println("size to tiet: " + soTiet.size());
//							System.out.println("Mon: " + subjects.get(radom1).getName());

							if (!Objects.isNull(soTiet.get(subjects.get(radom1).getId()))
									&& !Constan.Shlop.equals(subjects.get(radom1).getName())) {
								if (soTiet.get(subjects.get(radom1).getId()) > 0) {

									if (Constan.tiengViet.equals(subjects.get(radom1).getName().trim())) {
										if (classDto.getClassname().charAt(0) == '1') {
											if (isTV1 <= 4) {
												timetableClass.setSubjectName(subjects.get(radom1).getName());
												timetableClass.setSubJectsId(subjects.get(radom1).getId());

												timetableClass.setTeacherId(chuNhiem.getId());
												timetableClass.setTeacherName(chuNhiem.getName());

												int numberTime = soTiet.get(subjects.get(radom1).getId()) - 1;
												soTiet.put(subjects.get(radom1).getId(), numberTime);

												isTV1++;

												break;
											}
										}

										if (classDto.getClassname().charAt(0) == '2') {
											if (isTV2 <= 2) {
												timetableClass.setSubjectName(subjects.get(radom1).getName());
												timetableClass.setSubJectsId(subjects.get(radom1).getId());

												timetableClass.setTeacherId(chuNhiem.getId());
												timetableClass.setTeacherName(chuNhiem.getName());

												int numberTime = soTiet.get(subjects.get(radom1).getId()) - 1;
												soTiet.put(subjects.get(radom1).getId(), numberTime);

												isTV2++;
												break;
											}
										}

										if (classDto.getClassname().charAt(0) == '3') {
											if (isTV3 <= 1) {
												timetableClass.setSubjectName(subjects.get(radom1).getName());
												timetableClass.setSubJectsId(subjects.get(radom1).getId());

												timetableClass.setTeacherId(chuNhiem.getId());
												timetableClass.setTeacherName(chuNhiem.getName());

												int numberTime = soTiet.get(subjects.get(radom1).getId()) - 1;
												soTiet.put(subjects.get(radom1).getId(), numberTime);

												isTV3++;
												break;
											}
										}

										continue;
									}

									timetableClass.setSubjectName(subjects.get(radom1).getName());
									timetableClass.setSubJectsId(subjects.get(radom1).getId());

									int numberTime = soTiet.get(subjects.get(radom1).getId()) - 1;
									soTiet.put(subjects.get(radom1).getId(), numberTime);

									// set teacher
									boolean isteacher = false;
									for (TeacherDto teacherDto : teacherDtoList) {
										if (!isteacher) {
											for (SubjectDto s : teacherDto.getSubjectIds()) {
												if (s.getName().equals(subjects.get(radom1).getName())) {
													if (Constan.monBatBuoc.contains(subjects.get(radom1).getName())) {
														timetableClass.setTeacherId(chuNhiem.getId());
														timetableClass.setTeacherName(chuNhiem.getName());
														isteacher = true;
														break;
													} else {
														timetableClass.setTeacherName(teacherDto.getName());
														timetableClass.setTeacherId(teacherDto.getId());
														isteacher = true;
														break;
													}
												}
											}
										}
									}

									if (Objects.nonNull(timetableClass.getTeacherName())
											&& Objects.nonNull(timetableClass.getSubjectName())) {
										break;
									}

								} else {
									soTiet.remove(subjects.get(radom1).getId());
									continue;
								}

							} else {
								continue;
							}
						} else {
							break;
						}
					}
				}

				// set timeSlot
				timetableClass.setRoomName(classDto.getClassname());
				timetableClass.setRoomId(classDto.getId());
				timetableClass.setTimeSlot(timeSlot.getTimeslot());
				timetableClass.setTimSlotId(timeSlot.getTimeslotId());
				chromosome.add(timetableClass);

			}
			chuNhiem = null;
		}
	}
}
