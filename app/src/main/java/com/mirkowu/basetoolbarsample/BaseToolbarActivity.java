package com.mirkowu.basetoolbarsample;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.mirkowu.basetoolbar.BaseToolbar;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by MirkoWu on 2018/3/22 0022.
 */

public abstract class BaseToolbarActivity extends AppCompatActivity {

    private BaseToolbar mBaseToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContentView();
        initialize();
    }

    /**
     * 这里设置可以不用每次都布局文件写Toolbar，只需代码配置
     */
    protected void initContentView() {
        /*** 这里可以对Toolbar进行统一的预设置 */
        BaseToolbar.Builder builder
                = new BaseToolbar.Builder(this)
                // .setStatusBarColor(Color.TRANSPARENT)//统一设置颜色
                .setBackButton(R.mipmap.back)//统一设置返回键
                .setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setTitleTextColor(ContextCompat.getColor(this, R.color.white));

        builder = setToolbar(builder);
        if (builder != null) {
            mBaseToolbar = builder.build();
        }
        if (mBaseToolbar != null) {
            //添加Toolbar
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(mBaseToolbar);
            View mView = getLayoutInflater().inflate(getLayoutId(), layout, false);
            layout.addView(mView);
            setContentView(layout);
            //将toolbar设置为actionbar
            setSupportActionBar(mBaseToolbar);
        } else {
            setContentView(getLayoutId());
        }

    }


    public BaseToolbar getToolbar() {
        return mBaseToolbar;
    }


    public void showToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.GONE);
    }

    @LayoutRes
    protected abstract int getLayoutId();


    /**
     * 不需要toolbar的 可以不用管
     *
     * @return
     */
    @Nullable
    protected abstract BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder);

    protected abstract void initialize();

}
