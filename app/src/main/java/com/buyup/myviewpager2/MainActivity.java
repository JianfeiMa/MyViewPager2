package com.buyup.myviewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private MyFragmentStateAdapter myFragmentStateAdapter;
    private List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlList = new ArrayList<>();
        urlList.add("https://v-cdn.zjol.com.cn/280443.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276982.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276984.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276985.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276986.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276987.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276988.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276989.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276990.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276991.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276992.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276993.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276994.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276996.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/276998.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/277000.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/277001.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/277002.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/277003.mp4");
        urlList.add("https://v-cdn.zjol.com.cn/277004.mp4");
        viewPager2 = findViewById(R.id.activity_main_view_pager2);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MyFragment(0));
        myFragmentStateAdapter = new MyFragmentStateAdapter(this, fragmentList);
        viewPager2.registerOnPageChangeCallback(new MyCallback(viewPager2, myFragmentStateAdapter));
        viewPager2.setAdapter(myFragmentStateAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        android.view.MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main_menu_test) {
//            List<androidx.fragment.app.Fragment> fragmentList = new ArrayList<>();
//            int currentItem = viewPager2.getCurrentItem();
//            Log.d("debug", "获得当前条目->" + currentItem);
//            fragmentList.add(new MyFragment(currentItem + 1));
//            myFragmentStateAdapter.setMoreData(fragmentList);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getUrl(int position) {
        return urlList.get(position);
    }
}
