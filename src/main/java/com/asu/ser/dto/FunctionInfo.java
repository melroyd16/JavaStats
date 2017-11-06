/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melroy
 */
public class FunctionInfo {
    private String functionName;
    private String accessModifier;
    private String returnType;
    private int linesOfCode;
    private List<Argument> argumentList;
    private List<String> nonAccessModifiers;

    public FunctionInfo(String functionName){
        this.functionName = functionName;
        this.argumentList = new ArrayList<>();
    }
    
    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public List<Argument> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<Argument> argumentList) {
        this.argumentList = argumentList;
    }

    public List<String> getNonAccessModifiers() {
        return nonAccessModifiers;
    }

    public void setNonAccessModifiers(List<String> nonAccessModifiers) {
        this.nonAccessModifiers = nonAccessModifiers;
    }
    
}