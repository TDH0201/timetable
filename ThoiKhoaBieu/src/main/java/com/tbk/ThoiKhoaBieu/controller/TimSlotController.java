package com.tbk.ThoiKhoaBieu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.ThoiKhoaBieu.model.TimeSlot;
import com.tbk.ThoiKhoaBieu.service.ITimeSlotService;

@RestController
@RequestMapping("/timeSlot")
@CrossOrigin(maxAge = 3600)
public class TimSlotController {

	@Autowired
	ITimeSlotService timeSlotService;
	
	@GetMapping("")
	public List<TimeSlot> findAll(){
		return timeSlotService.findAll();
	}
	
	@PostMapping("/add")
	public TimeSlot save(@RequestBody TimeSlot timeSlot) {
		return timeSlotService.save(timeSlot);
	}
}
