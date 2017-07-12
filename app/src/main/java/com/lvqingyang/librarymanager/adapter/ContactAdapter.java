package com.lvqingyang.librarymanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.bean.Contact;

import java.util.Random;

import me.yokeyword.indexablerv.IndexableAdapter;

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

public class ContactAdapter extends IndexableAdapter<Contact> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private static final String TAG = "ContactAdapter";

    public ContactAdapter(Context c){
        mContext=c;
        mLayoutInflater=LayoutInflater.from(c);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        return new IndexVH(mLayoutInflater.inflate(R.layout.item_contact_title,null));
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new ContentVH(mLayoutInflater.inflate(R.layout.item_contact_,null));
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        ((IndexVH)holder).tv.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Contact entity) {
        ContentVH contentVH= (ContentVH) holder;
        if (entity.getManager().getName() != null&&entity.getManager().getName().length()>0) {
            contentVH.firstTv.setText(entity.getManager().getName().charAt(0)+"");
        }
        contentVH.nameTv.setText(entity.getManager().getName());

        int[] colorArr=mContext.getResources().getIntArray(R.array.colors);
        int index=new Random().nextInt(colorArr.length-1)%colorArr.length;
        ((ContentVH) holder).iv.setImageResource(R.color.accent_amber+index);
    }

    private class IndexVH extends RecyclerView.ViewHolder {
        TextView tv;

        public IndexVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView firstTv, nameTv;
        ImageView iv;

        public ContentVH(View itemView) {
            super(itemView);
            firstTv = (TextView) itemView.findViewById(R.id.first_tv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            iv=(ImageView)itemView.findViewById(R.id.color_cv);
        }
    }
}
