package com.jason.webviewdemo.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.jason.webviewdemo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MineFragment.OnFragmentInteractionListener,
        DiscoverFragment.OnFragmentInteractionListener {

    private ArrayList<Fragment> mFragments;
    private HomeFragment mHomeFragment;
    private DiscoverFragment mDiscoverFragment;
    private MineFragment mMineFragment;

    private TextView mTextMessage;

    private int mLastFgIndex;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    switchFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_discover);
                    switchFragment(1);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_mine);
                    switchFragment(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        initFragments();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mHomeFragment = HomeFragment.newInstance();
        mDiscoverFragment = DiscoverFragment.newInstance(null, null);
        mMineFragment = MineFragment.newInstance(null, null);
        mFragments.add(mHomeFragment);
        mFragments.add(mDiscoverFragment);
        mFragments.add(mMineFragment);
        switchFragment(0);
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment lastFg = mFragments.get(mLastFgIndex);
        Fragment targetFg = mFragments.get(position);//获取目标fragment，当position=-1时表示回退，报出异常，走catch
        mLastFgIndex = position;//正常情况，标记开启的fragment
        ft.hide(lastFg);//隐藏上一个fragment
        if (!targetFg.isAdded()) {//fragment是否被添加过
            getSupportFragmentManager().beginTransaction().remove(targetFg).commit();//先移除，防止重复添加？
            ft.add(R.id.fl_mainview, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();//允许传送值丢失
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
