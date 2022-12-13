package com.tbk.ThoiKhoaBieu.model;

import lombok.Data;

@Data
public class TimetableClass {

	private String timeSlot;
	private String subjectName;
	private String teacherName;
	private String roomName;
	
	private Long timSlotId;
	private Long subJectsId;
	private Long teacherId;
	private Long roomId;
	
	private Boolean isCheck;

}
