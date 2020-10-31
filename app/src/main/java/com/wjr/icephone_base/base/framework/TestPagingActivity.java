package com.wjr.icephone_base.base.framework;

import android.view.View;
import com.wjr.icephone_base.R;
import com.wjr.icephone_base.TestModel;
import com.wjr.icephone_base.base.net.ApiClient;

/**
 * TestModel为泛型 必须填入
 */
public class TestPagingActivity extends BasePagingActivity<TestModel> {
    ///当刷新数据的时候会自动调用这个方法
    @Override
    protected void refreshData(int currentPage) {
        ///一般在此处调用initData()即可
    }

    ///加载 下一页数据，其中currentPage已经+1，直接用即可
    @Override
    protected void requestNextPageData(int currentPage) {

        ApiBuilder builder = new ApiBuilder()
                .Url("http//xxxx")
                .Params("page", currentPage)
        ApiClient.getInstance().doGet(builder, new CallBack<TestModel>() {
            @Override
            public void onResponse(TestModel model) {
                ///写数据更新的逻辑



                ///更新状态
                updateRefreshLayoutState(totolPage);
            }


            @Override
            public void onFail(String msg) {
                //如果请求失败了，要调用resetCurrentPage()
                resetCurrentPage();

            }
        }, TestModel.class, TestPagingActivity.this);
    }



    ///返回你的空布局Id（就是没有数据的时候，展示的页面的xmlId ）
    @Override
    protected int getEmptyViewId() {
        return 0;
    }

    @Override
    protected void initView() {
        /**
         *这句话在initView第一句话写，必须写
         * 作用就是初始化刷新控件，填上你的SmartRefreshLayout的id即可
         */
        initSmartRefreshLayout(R.id.layout);
    }

    ///初始化数据，自动调用
    @Override
    protected void initData() {


        ApiBuilder builder = new ApiBuilder()
                .Url("http//xxxx")
                .Params("page", currentPage)
        ApiClient.getInstance().doGet(builder, new CallBack<TestModel>() {
            @Override
            public void onResponse(TestModel model) {

                ///设置内部适配器
                setPagingAdapter(adapter);


                ///中间写你的逻辑



                getPagingAdapter().addData(model.getData());
                ///最后完数据都要更新SmartRefreshLayout状态，传入服务器返回的totalPage
                updateRefreshLayoutState(totalPage);

            }

            @Override
            public void onFail(String msg) {

            }
        }, TestModel.class, TestPagingActivity.this);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onLimitClick(View view) {

    }
}
