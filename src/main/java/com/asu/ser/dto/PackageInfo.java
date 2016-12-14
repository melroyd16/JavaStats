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
public class PackageInfo{
    private String packageName;
    private int noOfClasses;
    private int noOfInterfaces;
    private int linesOfCode;
    private List<ClassInfo> classList;
    private List<InterfaceInfo> interfaceList;
    
    public PackageInfo(String packageName){
        this.packageName = packageName;
        this.classList = new ArrayList<ClassInfo>();
        this.interfaceList = new ArrayList<InterfaceInfo>();
        this.noOfClasses = 0;
        this.noOfInterfaces = 0;
    }
    
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getNoOfClasses() {
        return noOfClasses;
    }

    public void setNoOfClasses(int noOfClasses) {
        this.noOfClasses = noOfClasses;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public List<ClassInfo> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassInfo> classList) {
        this.classList = classList;
    }

    public List<InterfaceInfo> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<InterfaceInfo> interfaceList) {
        this.interfaceList = interfaceList;
    }

    public int getNoOfInterfaces() {
        return noOfInterfaces;
    }

    public void setNoOfInterfaces(int noOfInterfaces) {
        this.noOfInterfaces = noOfInterfaces;
    }
}
