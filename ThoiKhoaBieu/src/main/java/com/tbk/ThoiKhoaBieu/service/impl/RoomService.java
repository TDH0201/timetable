package com.tbk.ThoiKhoaBieu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ThoiKhoaBieu.convert.RoomConvert;
import com.tbk.ThoiKhoaBieu.entity.RoomEntity;
import com.tbk.ThoiKhoaBieu.model.RoomDto;
import com.tbk.ThoiKhoaBieu.repository.RoomRepository;
import com.tbk.ThoiKhoaBieu.service.IRoomService;

@Service
public class RoomService implements IRoomService {

	@Autowired
	RoomRepository roomrepostory;

	@Autowired
	RoomConvert roomConvert;

	@Override
	public RoomDto save(RoomDto roomDto) {
		return roomConvert.toDto(roomrepostory.save(roomConvert.toEnity(roomDto)));
	}

	@Override
	public List<RoomDto> findAll() {
		return roomrepostory.findAll().stream().map(dto -> {
			return roomConvert.toDto(dto);
		}).collect(Collectors.toList());

	}

}
