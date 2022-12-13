package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GATimeTableRes {
	private List<TimeTableRes> timeTableList = new ArrayList<>();
	private List<Double> fitness = new ArrayList<>();
	
	public GATimeTableRes() {
		
	}
	
	public GATimeTableRes(List<TimeTableRes> timeTableList, List<Double> fitness) {
		super();
		this.timeTableList = timeTableList;
		this.fitness = fitness;
	}
}
