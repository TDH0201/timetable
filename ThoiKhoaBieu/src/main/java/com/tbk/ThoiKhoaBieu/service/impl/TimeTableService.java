package com.tbk.ThoiKhoaBieu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ThoiKhoaBieu.entity.TimeTableEntity;
import com.tbk.ThoiKhoaBieu.repository.TimeTableRepository;
import com.tbk.ThoiKhoaBieu.service.ITimeTableService;

@Service
public class TimeTableService implements ITimeTableService {

	@Autowired
	TimeTableRepository timeTableRepository;
	
	@Override
	public void deleteAll() {
		timeTableRepository.deleteAll();
	}

	@Override
	public List<TimeTableEntity> saveAll(List<TimeTableEntity>timetableList) {
		return timeTableRepository.saveAll(timetableList);
	}

}
