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
public class ClassInfo{
    private String className;
    private String accessModifier;
    private String inheritsFrom;
    private List<String> implementsInterface;
    private int linesOfCode;
    private List<FunctionInfo> functionList;
    private List<String> nonAccessModifiers;

    public ClassInfo(String className){
        this.className = className;
        this.functionList = new ArrayList<FunctionInfo>();
        this.implementsInterface = new ArrayList<String>();
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public String getInheritsFrom() {
        return inheritsFrom;
    }

    public void setInheritsFrom(String inheritsFrom) {
        this.inheritsFrom = inheritsFrom;
    }

    public List<String> getImplementsInterface() {
        return implementsInterface;
    }

    public void setImplementsInterface(List<String> implementsInterface) {
        this.implementsInterface = implementsInterface;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public List<FunctionInfo> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<FunctionInfo> functionList) {
        this.functionList = functionList;
    }

    public List<String> getNonAccessModifiers() {
        return nonAccessModifiers;
    }

    public void setNonAccessModifiers(List<String> nonAccessModifiers) {
        this.nonAccessModifiers = nonAccessModifiers;
    }
}
