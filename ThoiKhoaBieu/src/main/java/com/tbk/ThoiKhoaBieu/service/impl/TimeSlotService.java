package com.tbk.ThoiKhoaBieu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ThoiKhoaBieu.convert.TimeSlotConvert;
import com.tbk.ThoiKhoaBieu.model.TimeSlot;
import com.tbk.ThoiKhoaBieu.repository.TimeSlotRepository;
import com.tbk.ThoiKhoaBieu.service.ITimeSlotService;

@Service
public class TimeSlotService implements ITimeSlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Autowired
	TimeSlotConvert timeSlotConvert;

	@Override
	public List<TimeSlot> findAll() {

		return timeSlotRepository.findAllASCById().stream().map(dto -> {
			return timeSlotConvert.toDto(dto);
		}).collect(Collectors.toList());
	}

	@Override
	public TimeSlot save(TimeSlot timeSlot) {
		return timeSlotConvert.toDto(timeSlotRepository.save(timeSlotConvert.toEntity(timeSlot)));
	}

}
