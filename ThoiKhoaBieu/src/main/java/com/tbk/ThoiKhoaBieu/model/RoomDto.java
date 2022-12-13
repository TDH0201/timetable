package com.tbk.ThoiKhoaBieu.model;

import lombok.Data;
import lombok.Setter;

@Data
public class RoomDto {

	@Setter
	private String name;
	private Integer size;
	
}
