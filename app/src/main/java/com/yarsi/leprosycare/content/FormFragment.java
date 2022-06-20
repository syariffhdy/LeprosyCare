package com.yarsi.leprosycare.content;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yarsi.leprosycare.R;

public class FormFragment extends Fragment {
    private WebView webview ;
    private ProgressBar spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        String url = ("https://docs.google.com/forms/d/e/1FAIpQLSfiNLyzb1WuXYR_WYvA420hXLSGhAbTq5gRuRQP7oySiJXfxQ/viewform");
        webview =(WebView)view.findViewById(R.id.webView);
        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);
        webview.setWebViewClient(new CustomWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(url);
        return view;
    }
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            webview.setVisibility(webview.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            spinner.setVisibility(View.GONE);

            view.setVisibility(webview.VISIBLE);
            super.onPageFinished(view, url);

        }
    }
}
