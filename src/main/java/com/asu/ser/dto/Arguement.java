/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.dto;

/**
 *
 * @author melroy
 */
public class Arguement {
    private String dataType;
    private String variableName;

    public Arguement(String dataType, String variableName){
        this.dataType = dataType;
        this.variableName = variableName;
    }
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
}
