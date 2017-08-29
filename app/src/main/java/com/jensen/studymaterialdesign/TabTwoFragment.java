package com.jensen.studymaterialdesign;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/28.
 */

public class TabTwoFragment extends Fragment {
    private static final String TAG = "TabTwoFragment";
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    WebView mWebView;
    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_two, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mProgressDialog = initProgressDialog();
        mWebView = new WebView(getActivity());
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        settings.setLoadWithOverviewMode(true);
        settings.setSupportMultipleWindows(true);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.loadUrl("http://www.jianshu.com/");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(layoutParams);
        mNestedScrollView.addView(mWebView);
        return view;
    }

    class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Log.i(TAG, "shouldOverrideUrlLoading: "+url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁WebView
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    public ProgressDialog initProgressDialog(){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("加载中...");
        return progressDialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
