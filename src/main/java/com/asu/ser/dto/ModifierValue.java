/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.dto;

import com.github.javaparser.ast.body.ModifierSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melroy
 */
public class ModifierValue {

    private int modifierNumber;
    private String accessModifier;
    private List<String> nonAccessModifier;

    public ModifierValue(int modifierNumber) {
        this.modifierNumber = modifierNumber;
        this.nonAccessModifier = new ArrayList<String>();
    }

    public String getAccessModifier() {
        if (ModifierSet.isPrivate(this.modifierNumber)) {
            return("private ");
        }else if (ModifierSet.isProtected(this.modifierNumber)) {
            return("protected ");
        }else if (ModifierSet.isPublic(this.modifierNumber)) {
            return("public ");
        } else {
            return("None / Default");
        }
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public List<String> getNonAccessModifier() {
        return nonAccessModifier;
    }

    public void setNonAccessModifier(List<String> nonAccessModifier) {
        this.nonAccessModifier = nonAccessModifier;
    }

    public List<String> getNonAccessModifiers() {
        if (ModifierSet.isAbstract(this.modifierNumber)) {
            this.getNonAccessModifier().add("Abstract");
        }
        if (ModifierSet.isStatic(this.modifierNumber)) {
            this.getNonAccessModifier().add("Static");
        }
        if (ModifierSet.isFinal(this.modifierNumber)) {
            this.getNonAccessModifier().add("Final");
        }
        if (ModifierSet.isNative(this.modifierNumber)) {
            this.getNonAccessModifier().add("Native");
        }
        if (ModifierSet.isStrictfp(this.modifierNumber)) {
            this.getNonAccessModifier().add("StrictFP");
        }
        if (ModifierSet.isSynchronized(this.modifierNumber)) {
            this.getNonAccessModifier().add("Synchronized");
        }
        if (ModifierSet.isTransient(this.modifierNumber)) {
            this.getNonAccessModifier().add("Transient");
        }
        if (ModifierSet.isVolatile(this.modifierNumber)) {
            this.getNonAccessModifier().add("Volatile");
        }
        return this.getNonAccessModifier();
    }
}
