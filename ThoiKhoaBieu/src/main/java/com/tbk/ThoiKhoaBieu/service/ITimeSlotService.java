package com.tbk.ThoiKhoaBieu.service;

import java.util.List;

import com.tbk.ThoiKhoaBieu.model.TimeSlot;

public interface ITimeSlotService {

	List<TimeSlot> findAll();
	TimeSlot save(TimeSlot timeSlot);
}
