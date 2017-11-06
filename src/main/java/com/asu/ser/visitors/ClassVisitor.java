/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.visitors;

import com.asu.ser.Summary;
import com.asu.ser.dto.ClassInfo;
import com.asu.ser.dto.InterfaceInfo;
import com.asu.ser.dto.ModifierValue;
import com.asu.ser.scan.Folder;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melroy
 */
public class ClassVisitor extends VoidVisitorAdapter<Void> {
    @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            int noOfLines = n.getRange().map(range -> range.end.line - range.begin.line).orElse(0);
            ModifierValue mv = new ModifierValue(n.getModifiers());
            if (n.isInterface()) {
                Summary.noOfInterfaces += 1;
                InterfaceInfo currentInterface = new InterfaceInfo(n.getNameAsString());
                Folder.isInterface = true;
                currentInterface.setLinesOfCode(noOfLines);
                currentInterface.setAccessModifier(mv.getAccessModifier());
                List<ClassOrInterfaceType> ciList = n.getExtendedTypes();
                List<String> extendsList = new ArrayList<String>();
                for(ClassOrInterfaceType ci : ciList){
                    extendsList.add(ci.toString());
                }
                currentInterface.setInheritsFrom(extendsList);
                List<InterfaceInfo> currentInterfaceList = Folder.currentPackage.getInterfaceList();
                currentInterfaceList.add(currentInterface);
                Folder.currentInterface = currentInterface;
                Folder.currentPackage.setInterfaceList(currentInterfaceList);
                Folder.currentPackage.setNoOfInterfaces(Folder.currentPackage.getNoOfInterfaces() + 1);
            } else {
                Summary.noOfClasses += 1;
                ClassInfo currentClass = new ClassInfo(n.getNameAsString());
                Folder.isInterface = false;
                currentClass.setLinesOfCode(noOfLines);
                currentClass.setAccessModifier(mv.getAccessModifier());
                currentClass.setNonAccessModifiers(mv.getNonAccessModifiers());
                List<String> implementsList = new ArrayList<String>();
                List<ClassOrInterfaceType> ciList = n.getImplementedTypes();
                String inherits = (n.getExtendedTypes().size()>0)?n.getExtendedTypes().get(0).toString():null;
                for(ClassOrInterfaceType ci : ciList){
                    implementsList.add(ci.toString());
                }
                currentClass.setInheritsFrom(inherits);
                currentClass.setImplementsInterface(implementsList);
                List<ClassInfo> currentClassList = Folder.currentPackage.getClassList();
                currentClassList.add(currentClass);
                Folder.currentClass = currentClass;
                Folder.currentPackage.setClassList(currentClassList);
                Folder.currentPackage.setNoOfClasses(Folder.currentPackage.getNoOfClasses() + 1);
            }
            Folder.currentPackage.setLinesOfCode(Folder.currentPackage.getLinesOfCode() + noOfLines);
            Summary.linesOfcode += noOfLines;
            super.visit(n, arg);
        }
}
