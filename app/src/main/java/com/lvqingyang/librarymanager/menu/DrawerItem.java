package com.lvqingyang.librarymanager.menu;

import android.view.ViewGroup;

import com.lvqingyang.librarymanager.tool.SolidRVBaseAdapter;

/**
 * Created by yarolegovich on 25.03.2017.
 */

public abstract class DrawerItem<T extends DrawerAdapter.ViewHolder> {

    protected boolean isChecked;

    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

    public DrawerItem setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isSelectable() {
        return true;
    }

}
