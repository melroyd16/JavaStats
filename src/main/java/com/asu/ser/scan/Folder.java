/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.scan;

import com.asu.ser.dto.ClassInfo;
import com.asu.ser.dto.FunctionInfo;
import com.asu.ser.dto.InterfaceInfo;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.asu.ser.dto.PackageInfo;
import com.asu.ser.visitors.ClassVisitor;
import com.asu.ser.visitors.MethodVisitor;
import com.asu.ser.visitors.PackageVisitor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melroy
 */
public class Folder {

    public static List<PackageInfo> packageList = new ArrayList<>();
    public static PackageInfo currentPackage;
    public static ClassInfo currentClass;
    public static PackageInfo navigationPackage;
    public static ClassInfo navigationClass;
    public static InterfaceInfo currentInterface;
    public static boolean isInterface = false;

    public List<PackageInfo> scanFolder(String folderPath) throws IOException {
        packageList = packageList = new ArrayList<>();
        currentPackage = null;
        currentClass = null;
        navigationPackage = null;
        navigationClass = null;
        currentInterface = null;
        isInterface = false;
        File[] files = new File(folderPath).listFiles();
        traverseFolder(files);
        for (PackageInfo p : packageList) {
            System.out.println(p.getPackageName() + p.getLinesOfCode());
            for (ClassInfo c : p.getClassList()) {
                System.out.println("\t" + c.getClassName());
                for(String s: c.getImplementsInterface()){
                    System.out.println("\t\t" + "implements " + s);
                }
                System.out.println("\t\textends " + c.getInheritsFrom());
                for(FunctionInfo f: c.getFunctionList()){
                    System.out.println("\t\t" + "function " + f.getFunctionName());
                }
            }
            for (InterfaceInfo i : p.getInterfaceList()) {
                System.out.println("\t" + i.getInterfaceName());
                for(String s: i.getInheritsFrom()){
                    System.out.println("\t\t" + "extends " + s);
                }
                for(FunctionInfo f: i.getFunctionList()){
                    System.out.println("\t\t" + "function " + f.getFunctionName());
                }
            }
        }
        return packageList;
    }

    public void traverseFolder(File[] files) throws IOException {
        for (File file : files) {
            if (file.isDirectory()) {
                traverseFolder(file.listFiles()); // Calls same method again.
            } else if (file.getName().substring(file.getName().length() - 4).equals("java")) {
                scanJavaFile(file, packageList);
            }
        }
    }

    public void scanJavaFile(File file, List<PackageInfo> packageList) throws IOException {
        FileInputStream in = null;
        CompilationUnit cm = null;
        try {
            in = new FileInputStream(file);
            cm = JavaParser.parse(in);
        } catch (Exception e) {

        } finally {
            in.close();
        }
        new PackageVisitor().visit(cm, packageList);
        new ClassVisitor().visit(cm, null);
        new MethodVisitor().visit(cm, null);
    }
}
