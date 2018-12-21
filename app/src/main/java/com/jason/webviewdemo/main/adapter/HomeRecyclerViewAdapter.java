package com.jason.webviewdemo.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jason.webviewdemo.JsCallNativeActivity;
import com.jason.webviewdemo.NativeCallJsActivity;
import com.jason.webviewdemo.R;

import java.util.List;

/**
 * FileName: HomeRecyclerViewAdapter
 *
 * @Date 2018/12/21 16:31
 * @Author Jason jianbo
 * @org www.hopson.com
 * @Email jianbo311@163.com
 * @Version
 * @Desc
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> data;

    public HomeRecyclerViewAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.button.setText(data.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        mContext.startActivity(new Intent(mContext, NativeCallJsActivity.class));
                        break;
                    case 1:
                        mContext.startActivity(new Intent(mContext, JsCallNativeActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btn_home_recyclerView);
        }
    }
}
