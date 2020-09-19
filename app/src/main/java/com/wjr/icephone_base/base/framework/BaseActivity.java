package com.wjr.icephone_base.base.framework;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wjr.icephone_base.R;
import com.wjr.icephone_base.base.listener.OnLimitClickListener;
import com.wjr.icephone_base.base.util.OnLimitClickListenerHelper;
import com.wjr.icephone_base.base.util.StatusBarUtil;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import qiu.niorgai.StatusBarCompat;


public abstract class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate,
        OnLimitClickListener {

    ///可用于页面跳转的
    protected BGASwipeBackHelper pageHelper;
    protected OnLimitClickListenerHelper onLimitClickListenerHelper;

    int layoutResID;
    private View networkErrorView;

    /**
     * 如果你需要你的页面在网络错误的时候显示错误的页面，那么设置这个为true
     **/
    protected boolean networkChangeActive = true;

    protected Context baseContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        baseContext = this;
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * 这三个无需多言
     */
    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    /**
     * 重写setContentView
     *
     * @param layoutResID 布局ID
     */
    @Override
    public void setContentView(int layoutResID) {
        /**  监听网络，显示网络错误的界面  **/
        this.layoutResID = layoutResID;
        if (networkChangeActive && !isNetworkConnected(this)) {
            if (networkErrorView == null) {
                /**      -》》》》》》》》》》》》》》》》》  》》       下面这个id需要到时候自己设置  **/
                networkErrorView = View.inflate(this, R.layout.activity_main, null);
                setupNetworkErrorView(networkErrorView);
            }
            setContentView(networkErrorView);
            return;
        }
        super.setContentView(layoutResID);
        onLimitClickListenerHelper = new OnLimitClickListenerHelper(this);
        initView();
        initData();
        initListener();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    /**
     * 设置网络错误相关点击重新加载相关操作
     *
     * @param networkErrorView
     */
    private void setupNetworkErrorView(View networkErrorView) {
        TextView textView = networkErrorView.findViewById(R.id.text);
        textView.setOnClickListener(v -> setContentView(layoutResID));
    }

    /**
     * 设置左滑返回相关参数
     */
    private void initSwipeBackFinish() {
        pageHelper = new BGASwipeBackHelper(this, this);
        pageHelper.setSwipeBackEnable(true);
        pageHelper.setIsOnlyTrackingLeftEdge(true);
        pageHelper.setIsWeChatStyle(true);
        pageHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        pageHelper.setIsNeedShowShadow(false);
        pageHelper.setIsShadowAlphaGradient(false);
        pageHelper.setSwipeBackThreshold(0.3f);
    }


    /**
     * 设置状态栏颜色和模式
     *
     * @param dark        false则为暗黑模式下（文字为白色）true则为白色模式（文字为黑色）
     * @param colorString 状态栏颜色，一般和AppBar的颜色一样
     */
    protected void setStatusBar(boolean dark, String colorString) {
        if (colorString == null) {
            colorString = "#f5f5f5";
        }
        StatusBarCompat.setStatusBarColor(this, Color.parseColor(colorString));
        StatusBarUtil.setStatusBarMode(this, dark);
    }

    /**
     * 网络连接状态
     *
     * @param context 上下文
     * @return
     */
    private boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    @Override
    public void onSwipeBackLayoutCancel() {
    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        pageHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        if (pageHelper.isSliding()) {
            return;
        }
        super.onBackPressed();
    }


}


