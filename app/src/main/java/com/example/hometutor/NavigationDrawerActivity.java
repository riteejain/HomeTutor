package com.example.hometutor;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import me.relex.circleindicator.CircleIndicator;


public class NavigationDrawerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<CardMenu> menuList;
    private AppBarLayout appBarLayout;

    private DrawerLayout drawer;

    ViewPager viewPager;
    CustomSwipeAdapter adapter;


    private Timer timer;
    Handler handler;
    Runnable runnable;

    CircleIndicator indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);
        //setContentView(R.layout.activity_homepage);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }


//        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//
//                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
//                {
//                    //  Collapsed
//                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//                }
//                else
//                {
//                    //Expanded
//                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//
//                }
//            }
//        });
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        adapter = new CustomSwipeAdapter(this);

        indicator = (CircleIndicator)findViewById(R.id.indicator);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

        createSlideShow();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(this,menuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(menuAdapter);

        prepareMenus();




        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

//    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);
//
//        // hiding & showing the title when toolbar expanded & collapsed
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsingToolbar.setTitle(getString(R.string.app_name));
//                    isShow = true;
//                } else if (isShow) {
//                    collapsingToolbar.setTitle(" ");
//                    isShow = false;
//                }
//            }
//        });
//    }






            /**
             * Adding few albums for testing
             */
    private void prepareMenus() {
        int cards[]={R.drawable.totalstudent,R.drawable.newrequest,R.drawable.cancelledreq,R.drawable.teachnewsub,R.drawable.book_search};
        CardMenu cm = new CardMenu(cards[0]);
        menuList.add(cm);
        cm = new CardMenu(cards[1]);
        menuList.add(cm);
        cm = new CardMenu(cards[2]);
        menuList.add(cm);
        cm = new CardMenu(cards[3]);
        menuList.add(cm);
        cm = new CardMenu(cards[4]);
        menuList.add(cm);
        menuAdapter.notifyDataSetChanged();

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }





    private void createSlideShow(){
         handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                int i = viewPager.getCurrentItem();

                if(i== CustomSwipeAdapter.image_res.length-1){
                    i=0;
                    viewPager.setCurrentItem(i,true);

                }
                else
                {
                    i++;
                    viewPager.setCurrentItem(i,true);
                    //indicator.animatePageSelected(i);
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },1000,3000);

    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }
}
