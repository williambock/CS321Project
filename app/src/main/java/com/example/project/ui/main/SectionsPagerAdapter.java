package com.example.project.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project.Post;
import com.example.project.ui.main.Home;
import com.example.project.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public Post[] postList;

    public SectionsPagerAdapter(Context context, FragmentManager fm, Post[] posts) {
        super(fm);
        mContext = context;
        postList = posts;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position == 0) {
            System.out.println("new instance of Home being created *******************");
            if(postList == null) {
                System.out.println("WARNING, postList is null *******************");
            }
            return Home.newInstance(postList);
        }
        else {
            return Recent.newInstance("Home1", "Home2");
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 2;
    }

    public void updatePostList(Post[] allPosts) {
        postList = allPosts;
        if(postList == null) {
            System.out.println("THE POST LIST IS NULLASJUIFIAUWEHFIUEFWIUENIFUIUEANFIUJNE");
        }
        else {
            System.out.println("THE POST LIST IS NOT NULL");
        }
    }
}