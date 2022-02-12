package com.github.zhangyingwei.solid.config;

import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.SolidUtils;

import java.io.File;

public class FileTemplateResourceLoader2 implements SolidTemplateResourcesLoader2 {

    private String basePath;
    private String prefix;
    private String suffix;

    public FileTemplateResourceLoader2() {
        this.init();
    }

    public FileTemplateResourceLoader2(String basePath) {
        this.init();
        this.basePath = basePath;
    }

    private void init() {
        this.prefix = Constants.TEMPLATE_PREFIX;
        this.suffix = Constants.TEMPLATE_SUFFIX;
    }

    @Override
    public String load(String source) {
        String content = this.readContentFromFile(this.getFilePath(source));
        return content;
    }

    private String readContentFromFile(String filePath) {
        return SolidUtils.readContentFromFile(filePath, Constants.CHAR_SET_UTF_8);
    }

    private String getFilePath(String source) {
        String filePath = this.basePath + File.separator;
        if (this.prefix != null && this.prefix.trim().length() > 0) {
            filePath += this.prefix;
            filePath += File.separator;
        }
        filePath += source;
        if (this.suffix != null && this.suffix.trim().length() > 0) {
            filePath += this.suffix;
        }
        return filePath;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public String getBasePath() {
        return basePath;
    }
}