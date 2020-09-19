package com.wjr.icephone_base.base.framework;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


/**
 * created by @文景睿
 * description：分页加载的Activity封装
 * brvah适配器：https://www.jianshu.com/p/b343fcff51b0/
 */
public abstract class BasePagingActivity<T> extends BaseActivity {

    /**
     * SmartRefreshLayout刷新布局
     **/

    protected SmartRefreshLayout refreshLayout;

    /**
     * 列表适配器
     **/
    private BaseQuickAdapter<T, BaseViewHolder> pagingAdapter;

    /**
     * 当前已经请求的页码（可以通过getCurrentPage方法获取，但内部已经做好加1和重置的封装，故外部不可修改）
     * （只读）
     **/
    private int currentPage = 1;

    /**
     * 数据为空时的视图
     **/
    private View emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emptyView = getLayoutInflater().inflate(getEmptyViewId(), null);
    }


    /**
     * 初始化SmartRefreshLayout（这个函数在initView()中第一句调用）
     *
     * @param refreshLayoutId SmartRefreshLayout的布局id
     */
    protected final void initSmartRefreshLayout(int refreshLayoutId) {
        refreshLayout = findViewById(refreshLayoutId);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                /**  在加载更多时候进行加1  **/
                currentPage++;
                requestNextPageData(currentPage);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                /**  currentPage重置为1  **/
                currentPage = 1;
                refreshData(currentPage);
            }
        });
    }

    /**
     * 刷新数据的方法
     *
     * @param currentPage 现在的页数(已经重置为1，直接拿去请求即可）
     */
    protected abstract void refreshData(int currentPage);

    /**
     * 请求下一页数据（currentPage已经完成了加1操作，无需在加1,直接用currentPage去请求即可）
     *
     * @param currentPage 现在的页数(已经+1，直接拿去请求即可）
     */
    protected abstract void requestNextPageData(int currentPage);

    /**
     * 在请求下一页数据失败的时候，回退这一页
     */
    protected void resetCurrentPage() {
        currentPage -= 1;
    }

    /**
     * 每次在请求完数据之后都要调用一次！！更新SmartRefreshLayout的状态，包括结束刷新，结束加载等操作！
     * 重要！！！
     * 这个方法必须在网络数据返回后，也就是在onResponse回调中
     * 此函数你写在每次onResponse回调处理中最后一步一般就没错了）
     *
     * @param totalPage 从服务器返回的总共页数
     */
    public final void updateRefreshLayoutState(int totalPage) {

        if (pagingAdapter == null) {
            throw new NullPointerException("\n=============================================\n" +
                    "框架内部pagingAdapter为空，请先用setPagingAdapter方法给其赋值\n" +
                    "=============================================");
        }

        refreshLayout.finishRefresh();
        if (currentPage >= totalPage) {
            currentPage = totalPage;
            refreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            refreshLayout.finishLoadMore();
        }

        /**  若适配器中有数据，才开启下拉加载和上拉刷新,否则关闭上拉和刷新并且设置空视图  **/
        if (pagingAdapter.getData().size() != 0) {
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setEnableLoadMore(true);
        } else {
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableLoadMore(false);
            ViewGroup parent = (ViewGroup) emptyView.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            pagingAdapter.setEmptyView(emptyView);
        }
    }

    /**
     * 设置空布局的id
     *
     * @return 空布局的id
     */
    protected abstract int getEmptyViewId();

    public final void setPagingAdapter(@NonNull BaseQuickAdapter<T, BaseViewHolder> pagingAdapter) {
        this.pagingAdapter = pagingAdapter;
    }

    public final BaseQuickAdapter<T, BaseViewHolder> getPagingAdapter() {
        if (pagingAdapter == null) {
            throw new NullPointerException("\n=============================================\n" +
                    "框架内部pagingAdapter为空，请先用setPagingAdapter方法给其赋值\n" +
                    "=============================================");
        }
        return pagingAdapter;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}