package com.tbk.ThoiKhoaBieu.controller.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.ThoiKhoaBieu.Const.Constan;
import com.tbk.ThoiKhoaBieu.entity.ClassEntity;
import com.tbk.ThoiKhoaBieu.entity.SubjectEntity;
import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;
import com.tbk.ThoiKhoaBieu.entity.TimeTableEntity;
import com.tbk.ThoiKhoaBieu.model.ClassDto;
import com.tbk.ThoiKhoaBieu.model.GATimeTableRes;
import com.tbk.ThoiKhoaBieu.model.GeneticAlgorithm;
import com.tbk.ThoiKhoaBieu.model.Population;
import com.tbk.ThoiKhoaBieu.model.TeacherDto;
import com.tbk.ThoiKhoaBieu.model.TimeSlot;
import com.tbk.ThoiKhoaBieu.model.TimeTableRes;
import com.tbk.ThoiKhoaBieu.model.Timetable;
import com.tbk.ThoiKhoaBieu.model.TimetableClass;
import com.tbk.ThoiKhoaBieu.service.IClassService;
import com.tbk.ThoiKhoaBieu.service.IRoomService;
import com.tbk.ThoiKhoaBieu.service.ISubjectService;
import com.tbk.ThoiKhoaBieu.service.ITeacherService;
import com.tbk.ThoiKhoaBieu.service.ITimeSlotService;
import com.tbk.ThoiKhoaBieu.service.ITimeTableService;

@RestController
@RequestMapping("/ga")
@CrossOrigin(maxAge = 3600)
public class TimetableGAController {

	@Autowired
	IClassService classService;

	@Autowired
	IRoomService roomService;

	@Autowired
	ITeacherService teacherService;

	@Autowired
	ISubjectService subjectsService;

	@Autowired
	ITimeSlotService timeSlotService;

	@Autowired
	ITimeTableService timeTableService;

	private Timetable timetable = new Timetable();

	private GeneticAlgorithm ga;

	private Population population;

	@GetMapping("/init")
	public List<TimeTableRes> initalTimetable() {

		timetable.setClasses(classService.findAll());
		timetable.setRooms(roomService.findAll());
		timetable.setTeachers(teacherService.findAll());
		timetable.setSubjectDtos(subjectsService.findAll());
		timetable.setTimeslots(timeSlotService.findAll());

		ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

		this.population = ga.initPopulation(this.timetable);
		ga.evalPopulation(population, this.timetable);
		this.timetable.createClasses(population.getPopulation().get(0));

		List<TimetableClass> timetableClassList = new ArrayList<>(this.timetable.getTimeTableList());
		Collections.sort(timetableClassList, new Comparator<TimetableClass>() {

			@Override
			public int compare(TimetableClass o1, TimetableClass o2) {
				Integer time1 = Integer.parseInt(o1.getTimeSlot().substring(1, 2).trim());
				Integer time2 = Integer.parseInt(o2.getTimeSlot().substring(1, 2).trim());
				if (time1 < time2)
					return -1;
				else if (time1 > time2)
					return 1;
				else {
					return o1.getTimeSlot().substring(2).trim().compareTo(o2.getTimeSlot().substring(2).trim());
				}
			}

		});

		List<TimeTableRes> timetableResList = new ArrayList<>();
		int count = 0;
		for (TimeSlot timeSlot : this.timetable.getTimeslots()) {
			TimeTableRes timeTableRes = new TimeTableRes();
			timeTableRes.setTimeSlot(timeSlot.getTimeslot());
			List<TimetableClass> list = new ArrayList<>();
			for (int i = count; i < timetableClassList.size(); i++) {
				if (timeSlot.getTimeslot().equals(timetableClassList.get(i).getTimeSlot())) {
					list.add(timetableClassList.get(i));

				} else {
					count = i;
					timeTableRes.setTimeTableList(list);
					timetableResList.add(timeTableRes);
					break;
				}

				if (i == timetableClassList.size() - 1) {
					timeTableRes.setTimeTableList(list);
					timetableResList.add(timeTableRes);
				}

			}
		}

		return timetableResList;
	}

	@GetMapping("/timtable")
	public GATimeTableRes getTimeTable() {

		GATimeTableRes gATimeTableRes = new GATimeTableRes();

		ga.evalPopulation(population, this.timetable);
		int generation = 1;
		List<Double> fitnessList = new ArrayList<>();
		// start loop
		while (ga.isTerminationConditionMet(generation, 1000) && !ga.isTerminationConditionMet(population)) {

			Double fitness = population.getFittest(0).getFitness();

			fitnessList.add((double) Math.round(fitness * 1000) / 1000);

			// print fitness
			System.out.println("G" + generation + " Best fitness: " + fitness);

			// Apply crossover
			population = ga.crossoverPopulation(population);

			// Apply mutation
			population = ga.mutatePopulation(population, timetable);

			ga.evalPopulation(population, this.timetable);

			generation++;

		}
		Double fitness = population.getFittest(0).getFitness();
		System.out.println("G" + generation + " Best fitness: " + fitness);
		fitnessList.add(fitness);
		this.timetable.createClasses(population.getPopulation().get(0));
		List<TimetableClass> timetableClassList = new ArrayList<>(this.timetable.getTimeTableList());

		// set chu nhiem
		TeacherDto chuNhiem = new TeacherDto();
		List<TeacherDto> chuNhiemList = new ArrayList<>();
		for (ClassDto classDto : this.timetable.getClasses()) {
			List<TeacherDto> teacherDtoList = timetable.getTeacherByClass(classDto.getGrade());

			List<TeacherDto> giaoVienChuNhiem = teacherDtoList.stream().filter(dto -> {
				return dto.getIsTeacherClass();
			}).collect(Collectors.toList());

			Random random = new Random();
			while (true) {
				int teacherRamdom = random.nextInt(giaoVienChuNhiem.size());
				if (!chuNhiemList.contains(giaoVienChuNhiem.get(teacherRamdom))) {
					chuNhiem = giaoVienChuNhiem.get(teacherRamdom);
					chuNhiemList.add(giaoVienChuNhiem.get(teacherRamdom));
					break;
				}

			}
			for (int k = 0; k < timetableClassList.size(); k++) {
				if (classDto.getClassname().equals(timetableClassList.get(k).getRoomName())) {
					if (Objects.nonNull(timetableClassList.get(k).getSubjectName())) {
						if (Constan.monBatBuoc.contains(timetableClassList.get(k).getSubjectName().trim())) {
							timetableClassList.get(k).setTeacherName(chuNhiem.getName());
						}
					}
				}
			}
		}

		Collections.sort(timetableClassList, new Comparator<TimetableClass>() {

			@Override
			public int compare(TimetableClass o1, TimetableClass o2) {
				Integer time1 = Integer.parseInt(o1.getTimeSlot().substring(1, 2).trim());
				Integer time2 = Integer.parseInt(o2.getTimeSlot().substring(1, 2).trim());
				if (time1 < time2)
					return -1;
				else if (time1 > time2)
					return 1;
				else {
					return o1.getTimeSlot().substring(2).trim().compareTo(o2.getTimeSlot().substring(2).trim());
				}
			}

		});

		List<TimeTableRes> timetableResList = new ArrayList<>();
		int count = 0;
		for (TimeSlot timeSlot : this.timetable.getTimeslots()) {
			TimeTableRes timeTableRes = new TimeTableRes();
			timeTableRes.setTimeSlot(timeSlot.getTimeslot());
			List<TimetableClass> list = new ArrayList<>();
			for (int i = count; i < timetableClassList.size(); i++) {
				if (timeSlot.getTimeslot().equals(timetableClassList.get(i).getTimeSlot())) {
					timetableClassList.get(i).setIsCheck(false);
					list.add(timetableClassList.get(i));

				} else {
					count = i;
					timeTableRes.setTimeTableList(list);
					timetableResList.add(timeTableRes);
					break;
				}

				if (i == timetableClassList.size() - 1) {
					timeTableRes.setTimeTableList(list);
					timetableResList.add(timeTableRes);
				}

			}
		}

		gATimeTableRes.getTimeTableList().addAll(timetableResList);
		gATimeTableRes.getFitness().addAll(fitnessList);

		return gATimeTableRes;
	}

	@PostMapping("/save")
	public Boolean newTimetable(@RequestBody GATimeTableRes gATimeTableRes) {
		System.out.println(gATimeTableRes);
		List<TimeTableEntity> timeTableEntityList = new ArrayList<>();
		for (TimeTableRes timetableRes : gATimeTableRes.getTimeTableList()) {
			for (TimetableClass timetableClass : timetableRes.getTimeTableList()) {
				if (Objects.nonNull(timetableClass.getSubjectName())) {
					TimeTableEntity timeTableEntity = new TimeTableEntity();

					Optional<TeacherEntity> teacherEntity = teacherService.findById(timetableClass.getTeacherId());
					timeTableEntity.setTeacherId(teacherEntity.get());

					ClassEntity classEntity = new ClassEntity();
					classEntity.setId(timetableClass.getRoomId());
					timeTableEntity.setClassId(classEntity);

					SubjectEntity subjectEntity = new SubjectEntity();
					subjectEntity.setId(timetableClass.getSubJectsId());
					timeTableEntity.setSubjectId(subjectEntity);

					timeTableEntity.setTime(timetableClass.getTimeSlot());

					timeTableEntityList.add(timeTableEntity);
				}
			}
		}

		timeTableService.deleteAll();
		List<TimeTableEntity> timeTableAddRes = timeTableService.saveAll(timeTableEntityList);
		if (timeTableAddRes.size() != timeTableEntityList.size()) {
			return false;
		}
		return true;
	}
}
