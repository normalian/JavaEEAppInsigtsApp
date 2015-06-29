package com.domain.mavenjavaeeapp1.action;

import com.microsoft.applicationinsights.web.javaee.RequestName;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class IndexAction {

    @RequestName
    public String do1() {
        System.out.println("IndexAction#do1() =");
        return "0";
    }

    public String do2() {
        return "0";
    }
}
