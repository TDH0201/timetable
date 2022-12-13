package com.tbk.ThoiKhoaBieu.service;

import java.util.List;

import com.tbk.ThoiKhoaBieu.model.RoomDto;


public interface IRoomService {
	
	public RoomDto save(RoomDto roomDto);
	public List<RoomDto> findAll();
}
