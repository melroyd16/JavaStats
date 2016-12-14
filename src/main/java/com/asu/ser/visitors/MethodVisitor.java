/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.visitors;

import com.asu.ser.dto.Arguement;
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
public class MethodVisitor extends VoidVisitorAdapter{
    @Override
        public void visit(MethodDeclaration n, Object arg) {
            List<Parameter> parameterList = n.getParameters();
            List<Arguement> currentArguementList = new ArrayList<Arguement>();
            for(Parameter p: parameterList){
                currentArguementList.add(new Arguement(p.getType().toString(), p.getName()));
            }
            FunctionInfo currentFunction = new FunctionInfo(n.getName());
            currentFunction.setReturnType(n.getType().toString());
            ModifierValue mv = new ModifierValue(n.getModifiers());
            currentFunction.setAccessModifier(mv.getAccessModifier());
            currentFunction.setNonAccessModifiers(mv.getNonAccessModifiers());

            List<FunctionInfo> currentFunctionList;
            if(Folder.isInterface){
                currentFunctionList = Folder.currentInterface.getFunctionList();   
            } else {
                currentFunctionList = Folder.currentClass.getFunctionList();
                currentFunction.setLinesOfCode(n.getEnd().line - n.getBegin().line);
            }
            currentFunctionList.add(currentFunction);
            super.visit(n, arg);
        }
}
