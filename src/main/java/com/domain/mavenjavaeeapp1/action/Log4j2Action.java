/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.mavenjavaeeapp1.action;

import com.microsoft.applicationinsights.web.javaee.RequestName;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author normalian
 */
@RequestScoped
@Named
public class Log4j2Action {

    @RequestName
    public String do1() {
        System.out.println("Log4j2Action#do1() =");
        return "0";
    }

    public String do2() {
        return "0";
    }
}
