/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.dto;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static com.github.javaparser.ast.Modifier.*;

/**
 *
 * @author melroy
 */
public class ModifierValue {

    private final EnumSet<Modifier> modifiers;
    private String accessModifier;
    private List<String> nonAccessModifier;

    public ModifierValue(EnumSet<Modifier> modifiers) {
        this.modifiers = modifiers;
        this.nonAccessModifier = new ArrayList<>();
    }

    public String getAccessModifier() {
        if (modifiers.contains(PRIVATE)) {
            return("private ");
        }else if (modifiers.contains(PROTECTED)) {
            return("protected ");
        }else if (modifiers.contains(PUBLIC)) {
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
        if (modifiers.contains(ABSTRACT)) {
            this.getNonAccessModifier().add("Abstract");
        }
        if (modifiers.contains(STATIC)) {
            this.getNonAccessModifier().add("Static");
        }
        if (modifiers.contains(FINAL)) {
            this.getNonAccessModifier().add("Final");
        }
        if (modifiers.contains(NATIVE)) {
            this.getNonAccessModifier().add("Native");
        }
        if (modifiers.contains(STRICTFP)) {
            this.getNonAccessModifier().add("StrictFP");
        }
        if (modifiers.contains(SYNCHRONIZED)) {
            this.getNonAccessModifier().add("Synchronized");
        }
        if (modifiers.contains(TRANSIENT)) {
            this.getNonAccessModifier().add("Transient");
        }
        if (modifiers.contains(VOLATILE)) {
            this.getNonAccessModifier().add("Volatile");
        }
        return this.getNonAccessModifier();
    }
}
