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
public class InterfaceInfo {
    private String interfaceName;
    private String accessModifier;
    private int linesOfCode;
    private List<String> inheritsFrom;
    private List<FunctionInfo> functionList;

    public InterfaceInfo(String interfaceName){
        this.interfaceName = interfaceName;
        this.inheritsFrom = new ArrayList<String>();
        this.functionList = new ArrayList<FunctionInfo>();
    }
    
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
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

    public List<String> getInheritsFrom() {
        return inheritsFrom;
    }

    public void setInheritsFrom(List<String> inheritsFrom) {
        this.inheritsFrom = inheritsFrom;
    }
    
    
}
