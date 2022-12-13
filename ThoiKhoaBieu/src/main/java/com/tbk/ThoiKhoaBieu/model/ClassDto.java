package com.tbk.ThoiKhoaBieu.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ClassDto {

	public Long id;

	public Integer roomId;

	public Integer grade;

	public String classname;

	public String classSize;

	public Integer timeLearn;
}
