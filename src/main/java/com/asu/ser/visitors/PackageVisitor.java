/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.visitors;

import com.asu.ser.Summary;
import com.asu.ser.dto.PackageInfo;
import com.asu.ser.scan.Folder;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author melroy
 */
public class PackageVisitor extends VoidVisitorAdapter<List<PackageInfo>> {
    @Override
        public void visit(PackageDeclaration n, List<PackageInfo> arg) {
            List<PackageInfo> packageList = Folder.packageList;

            String packageName = (n.getName()).toString();
            if (packageList.size() == 0) {
                Folder.currentPackage = new PackageInfo(n.getName().toString());
                packageList.add(Folder.currentPackage);
                Summary.noOfPackages += 1;
            } else {
                boolean contains = false;
                for (PackageInfo p : packageList) {
                    if (p.getPackageName() != null && p.getPackageName().equals(packageName)) {
                        contains = true;
                        Folder.currentPackage = p;
                    }
                }
                if (!contains) {
                    Folder.currentPackage = new PackageInfo(n.getName().toString());
                    packageList.add(Folder.currentPackage);
                    Summary.noOfPackages += 1;
                }
            }
            super.visit(n, arg);
        }
}
