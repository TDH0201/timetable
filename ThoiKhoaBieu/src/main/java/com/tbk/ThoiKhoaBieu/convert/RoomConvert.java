package com.tbk.ThoiKhoaBieu.convert;

import org.springframework.stereotype.Component;

import com.tbk.ThoiKhoaBieu.entity.RoomEntity;
import com.tbk.ThoiKhoaBieu.model.RoomDto;

@Component
public class RoomConvert {

	public RoomDto toDto(RoomEntity roomEntity) {

		RoomDto room = new RoomDto();
		room.setName(roomEntity.getName());
		room.setSize(roomEntity.getSize());

		return room;
	}

	public RoomEntity toEnity(RoomDto roomDto) {

		RoomEntity roomEntity = new RoomEntity();
		roomEntity.setName(roomDto.getName());
		roomEntity.setSize(roomDto.getSize());

		return roomEntity;
	}
}
