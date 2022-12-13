package com.tbk.ThoiKhoaBieu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.ThoiKhoaBieu.model.ClassDto;
import com.tbk.ThoiKhoaBieu.service.IClassService;

@RestController
@RequestMapping("/class")
@CrossOrigin(maxAge = 3600)
public class ClassController {

	@Autowired
	IClassService classService;

	@GetMapping("")
	public List<ClassDto> getAllTeacher() {
		return classService.findAll();
	}

	@PostMapping("/add")
	public ClassDto addRoom(@RequestBody ClassDto classDto) {
		return classService.save(classDto);
	}
}
