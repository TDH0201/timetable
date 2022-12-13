package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TimeTableRes {
	
	private String timeSlot;
	private List<TimetableClass> timeTableList = new ArrayList<>();
}
