package com.tbk.ThoiKhoaBieu.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;
import com.tbk.ThoiKhoaBieu.model.RoomDto;
import com.tbk.ThoiKhoaBieu.service.IRoomService;

@RestController()
@RequestMapping("/room")
public class RoomController {

	@Autowired
	IRoomService roomService;
	
	@GetMapping("")
	public List<RoomDto> getAllTeacher() {
		return roomService.findAll();
	}

	@PostMapping("/add")
	public RoomDto addRoom(@RequestBody RoomDto roomDto) {

		if (!Objects.isNull(roomDto)) {
			return roomService.save(roomDto);
		}
		return null;
	}
}
