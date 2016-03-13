package com.domain.javaeeapp.action;

import com.domain.javaeeapp.dto.IndexViewDto;
import com.microsoft.applicationinsights.web.javaee.RequestName;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class IndexAction {

	@Inject
	IndexViewDto indexViewDto;

	@RequestName
	public String do1(String arg) {
		System.out.println("IndexAction#do1(" + arg + ") = "
				+ indexViewDto.getName());
		return "/index.xhtml";
	}

	public String do2() {
		return "0";
	}
}
