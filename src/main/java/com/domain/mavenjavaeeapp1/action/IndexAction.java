package com.domain.mavenjavaeeapp1.action;

import com.domain.mavenjavaeeapp1.dto.IndexViewDto;
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
	public String do1(String argument) {
		System.out.println("IndexAction#do1(" + argument + ") = "
				+ indexViewDto.getName());
		return "/index.xhtml";
	}

	public String do2() {
		return "0";
	}
}
