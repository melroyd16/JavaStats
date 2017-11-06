/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.visitors;

import com.asu.ser.dto.Argument;
import com.asu.ser.dto.FunctionInfo;
import com.asu.ser.dto.ModifierValue;
import com.asu.ser.scan.Folder;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melroy
 */
public class MethodVisitor extends VoidVisitorAdapter<Void> {
    @Override
        public void visit(MethodDeclaration n, Void arg) {
            List<Parameter> parameterList = n.getParameters();
            List<Argument> currentArgumentList = new ArrayList<Argument>();
            for(Parameter p: parameterList){
                currentArgumentList.add(new Argument(p.getType().asString(), p.getNameAsString()));
            }
            FunctionInfo currentFunction = new FunctionInfo(n.getNameAsString());
            currentFunction.setReturnType(n.getType().toString());
            ModifierValue mv = new ModifierValue(n.getModifiers());
            currentFunction.setAccessModifier(mv.getAccessModifier());
            currentFunction.setNonAccessModifiers(mv.getNonAccessModifiers());

            List<FunctionInfo> currentFunctionList;
            if(Folder.isInterface){
                currentFunctionList = Folder.currentInterface.getFunctionList();   
            } else {
                currentFunctionList = Folder.currentClass.getFunctionList();
                int noOfLines = n.getRange().map(range -> range.end.line - range.begin.line).orElse(0);
                currentFunction.setLinesOfCode(noOfLines);
            }
            currentFunctionList.add(currentFunction);
            super.visit(n, arg);
        }
}
