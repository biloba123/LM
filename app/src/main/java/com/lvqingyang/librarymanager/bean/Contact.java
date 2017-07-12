package com.lvqingyang.librarymanager.bean;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * 　　┏┓　　  ┏┓+ +
 * 　┏┛┻━ ━ ━┛┻┓ + +
 * 　┃　　　             ┃
 * 　┃　　　━　　   ┃ ++ + + +
 * ████━████     ┃+
 * 　┃　　　　　　  ┃ +
 * 　┃　　　┻　　  ┃
 * 　┃　　　　　　  ┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug！
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * ━━━━━━神兽出没━━━━━━
 * Author：LvQingYang
 * Date：2017/7/7
 * Email：biloba12345@gamil.com
 * Info：
 */

public class Contact implements IndexableEntity {
    private Manager manager;

    public Contact(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String getFieldIndexBy() {
        return manager.getName();
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        manager.setName(indexField);
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
