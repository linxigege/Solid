package com.github.zhangyingwei.solid.items.pipline;

/**
 * 追加
 *
 * @author zhangyw
 * @date 2018/7/3
 */
public class AppendSolidMethod implements SolidMethod<String> {
    @Override
    public String doFormate(String content, Object[] args) {
        return content.concat(args[0].toString());
    }
}
