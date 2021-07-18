package com.example.coinbaseapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsFragment extends Fragment {

    WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate=inflater.inflate(R.layout.fragment_news, container, false);

        webView=inflate.findViewById(R.id.news_webview);

        webView.setWebViewClient(new WebViewClient());

        String HOME_PAGE="https://coindesk-news.com/".toString();
       webView.loadUrl(HOME_PAGE);

        WebSettings fixset = webView.getSettings();
        fixset.setJavaScriptEnabled(true);


        return inflate;
    }
}