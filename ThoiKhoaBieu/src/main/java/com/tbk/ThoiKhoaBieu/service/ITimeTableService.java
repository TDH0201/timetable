package com.tbk.ThoiKhoaBieu.service;

import java.util.List;

import com.tbk.ThoiKhoaBieu.entity.TimeTableEntity;

public interface ITimeTableService {
	void deleteAll();
	List<TimeTableEntity> saveAll(List<TimeTableEntity>timetableList);
}
