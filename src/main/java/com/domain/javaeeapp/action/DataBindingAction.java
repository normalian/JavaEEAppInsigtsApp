package com.domain.javaeeapp.action;

import com.domain.javaeeapp.dto.ViewDto;
import com.microsoft.applicationinsights.web.javaee.RequestName;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class DataBindingAction {

	@Inject
	ViewDto viewDto;

	@RequestName
	public String do1(String arg) {
		System.out.println("DataBindingAction#do1(" + arg + ") = " + viewDto.getName());
		return "/index.xhtml";
	}

	public String do2() {
		return "0";
	}
}
