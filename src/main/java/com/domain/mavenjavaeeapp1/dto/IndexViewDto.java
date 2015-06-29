package com.domain.mavenjavaeeapp1.dto;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexViewDto implements Serializable {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
