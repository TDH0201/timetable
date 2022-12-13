package com.tbk.ThoiKhoaBieu.convert;

import org.springframework.stereotype.Component;

import com.tbk.ThoiKhoaBieu.entity.TimeSlotEntity;
import com.tbk.ThoiKhoaBieu.model.TimeSlot;

@Component
public class TimeSlotConvert {

	public TimeSlot toDto(TimeSlotEntity timeSlotEntity) {

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeslotId(timeSlotEntity.getId());
		timeSlot.setTimeslot(timeSlotEntity.getTimeslot());
		return timeSlot;
	}

	public TimeSlotEntity toEntity(TimeSlot timeSlot) {
		TimeSlotEntity timeSlotEntity = new TimeSlotEntity();
		timeSlotEntity.setTimeslot(timeSlot.getTimeslot());
		return timeSlotEntity;
	}
}
