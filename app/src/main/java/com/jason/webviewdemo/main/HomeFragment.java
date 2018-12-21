package com.jason.webviewdemo.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.webviewdemo.R;
import com.jason.webviewdemo.main.adapter.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: HomeFragment
 *
 * @Date 2018/12/21 17:47
 * @Author Jason jianbo
 * @org www.hopson.com
 * @Email jianbo311@163.com
 * @Version
 * @Desc 首页Fragment
 */
public class HomeFragment extends Fragment {

    private FragmentActivity mActivity;
    RecyclerView mRecyclerView;
    private HomeRecyclerViewAdapter mAdapter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mActivity = getActivity();
        assert mActivity != null;
        mRecyclerView = view.findViewById(R.id.home_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        List<String> stringList = new ArrayList<>();
        stringList.add("Android调用javaScript代码");
        stringList.add("javaScript调用Android代码");
        mAdapter = new HomeRecyclerViewAdapter(mActivity, stringList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
