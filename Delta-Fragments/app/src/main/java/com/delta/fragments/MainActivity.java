package com.delta.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            HeadlinesFragment headlinesFragment = new HeadlinesFragment();
            headlinesFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.container, headlinesFragment).commit();
        }
    }

    @Override
    public void onArticleSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.articles_fragment);
        if (articleFragment != null) {
            articleFragment.updateArticleView(position);
        } else {
            ArticleFragment swapFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            swapFragment.setArguments(args);
            getFragmentManager().beginTransaction().replace(R.id.container, swapFragment).addToBackStack(null).commit();
        }
    }
}
