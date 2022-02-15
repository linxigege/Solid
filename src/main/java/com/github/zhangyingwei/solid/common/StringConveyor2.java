package com.github.zhangyingwei.solid.common;

public class StringConveyor2 {

    private int globalToIndex = 0;
    private int globalFromIndex = 0;
    private String template;

    public StringConveyor2(String template) {
        this.template = template;
    }

    public StringConveyor2 getFromTo(String from, String to) {
        getUntil(from, true);
        if (this.globalToIndex == this.templateLength()) {
            this.globalToIndex = this.globalFromIndex;
            return this;
        }
        getUntil(to, true);
        this.globalFromIndex -= from.length();
        return this;
    }

    public StringConveyor2 getUntil(String untilStr, Boolean contain) {
        this.globalFromIndex = this.globalToIndex;
        int strLength = untilStr.length();
        int length = this.templateLength();
        for (int i = this.globalToIndex; i < length; i++) {
            boolean eq = true;
            for (int j = 0; j < strLength; j++) {
                char tempChar = this.template.charAt(i + j);
                if (untilStr.charAt(j) != tempChar) {
                    eq = false;
                    break;
                }
            }
            if (eq) {
                this.globalFromIndex = i;
                if (contain) {
                    this.globalFromIndex += strLength;
                }
                break;
            }
        }
        if (this.globalFromIndex == this.globalToIndex) {
            this.globalToIndex = this.templateLength();
        }
        return this;
    }

    private int templateLength() {
        return this.template.length();
    }

    public int length() {
        return this.template.length() - this.globalToIndex;
    }

    public String string() {
        return this.template.substring(this.globalToIndex);
    }

    public String result() {
        return this.template.substring(this.globalFromIndex, this.globalToIndex);
    }

    public StringConveyor2 getBetween(String from, String to) {
        getUntil(from, true);
        getUntil(to, false);
        if (this.globalToIndex == this.templateLength()) {
            this.globalToIndex = this.globalFromIndex;
            return this;
        }
        return this;
    }

    public StringConveyor2 trimLeft() {
        if (this.length() > 0) {
            char cut = template.charAt(this.globalToIndex);
            while (cut == ' ' && this.length() > 0) {
                this.globalToIndex++;
                if (this.length() > 0) {
                    cut = template.charAt(this.globalToIndex);
                }
            }
        }
        return this;
    }

    public boolean startWith(String str){
        return this.string().startsWith(str);
    }
}