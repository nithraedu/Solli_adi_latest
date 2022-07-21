package nit_app;

import android.content.Context;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nithra.tamil.word.game.solliadi.R;


public class more_app extends AppCompatActivity {

    TabLayout tabs;
    ViewPager view_pager,view_pager1;
    CirclePageIndicator indicator,indicator1;

    Adapter adapter;
    Adapter1 adapter1;

    int tab_position;


    String[] tam_app = {"nithra.tamilcalender","com.bharathdictionary","nithra.samayalkurippu","com.nithra.tamilsms","com.nithra.riddles",
           /* "com.nithra.tamilproverbs",*/"nithra.thirukkural","nithra.technicaldictionary","nithra.tamilcrosswordpuzzle","nithra.temple.history_details","nithra.tamil.story"};

    String[] eng_app = {"nithra.math.aptitude","com.nithra.bestenglishgrammar","nithra.math.logicalreasoning","nithra.numberreasoningpuzzle","nithra.quiz","nithra.agecalculator","nithra.business.card.invitation.wedding.cardmaker",
            "com.nithra.learnwords","com.nithra.resume","nithra.reminder.tasks.alarm","nithra.unitconverter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_app);

        tabs = (TabLayout) findViewById(R.id.tabs);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        view_pager1 = (ViewPager) findViewById(R.id.view_pager1);
        indicator1 = (CirclePageIndicator) findViewById(R.id.indicator1);

        tabs.addTab(tabs.newTab().setCustomView(createTabView(this, "தமிழ்\nபடைப்புகள்")));
        tabs.addTab(tabs.newTab().setCustomView(createTabView(this, "ஆங்கில\nபடைப்புகள்")));

        adapter = new Adapter(getSupportFragmentManager());
        adapter1 = new Adapter1(getSupportFragmentManager());

        for(int i = 0; i<tam_app.length; i++){

            adapter.addFragment(new Apps_Fragment(), ""+i);
        }

        view_pager.setAdapter(adapter);
        indicator.setViewPager(view_pager);

        for(int i = 0; i<eng_app.length; i++){

            adapter1.addFragment(new Apps_Fragment1(), ""+i);
        }

        view_pager1.setAdapter(adapter1);
        indicator1.setViewPager(view_pager1);

        view_pager.setVisibility(View.VISIBLE);
        indicator.setVisibility(View.VISIBLE);

        view_pager1.setVisibility(View.GONE);
        indicator1.setVisibility(View.GONE);

        final ImageView pre_but = (ImageView) findViewById(R.id.pre_but);
        final ImageView nxt_but = (ImageView) findViewById(R.id.nxt_but);
        pre_but.setVisibility(View.GONE);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab_position = tab.getPosition();
                pre_but.setVisibility(View.GONE);
                if (tab_position == 0) {
                    view_pager.setVisibility(View.VISIBLE);
                    indicator.setVisibility(View.VISIBLE);
                    view_pager1.setVisibility(View.GONE);
                    indicator1.setVisibility(View.GONE);
                } else {
                    view_pager1.setVisibility(View.VISIBLE);
                    indicator1.setVisibility(View.VISIBLE);
                    view_pager.setVisibility(View.GONE);
                    indicator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(view_pager.getVisibility()==View.VISIBLE) {
                    if (position == 0) {
                        pre_but.setVisibility(View.GONE);
                        nxt_but.setVisibility(View.VISIBLE);
                    } else if (position == (tam_app.length - 1)) {
                        nxt_but.setVisibility(View.GONE);
                        pre_but.setVisibility(View.VISIBLE);
                    } else {
                        nxt_but.setVisibility(View.VISIBLE);
                        pre_but.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        view_pager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(view_pager1.getVisibility()==View.VISIBLE) {
                    if (position == 0) {
                        pre_but.setVisibility(View.GONE);
                        nxt_but.setVisibility(View.VISIBLE);
                    } else if (position == (eng_app.length - 1)) {
                        nxt_but.setVisibility(View.GONE);
                        pre_but.setVisibility(View.VISIBLE);
                    } else {
                        nxt_but.setVisibility(View.VISIBLE);
                        pre_but.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        pre_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tab_position == 0) {
                    if(tam_app.length!=0){
                        view_pager.setCurrentItem(view_pager.getCurrentItem()-1);
                    }
                }else{
                    if(eng_app.length!=0){
                        view_pager1.setCurrentItem(view_pager1.getCurrentItem()-1);
                    }
                }


            }
        });

        nxt_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tab_position == 0) {
                    if(tam_app.length!=(view_pager.getCurrentItem()+1)){
                        view_pager.setCurrentItem(view_pager.getCurrentItem()+1);
                    }
                }else {
                    if(eng_app.length!=(view_pager1.getCurrentItem()+1)){
                        view_pager1.setCurrentItem(view_pager1.getCurrentItem()+1);
                    }
                }


            }
        });


    }



    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            Bundle args = new Bundle();
            args.putString("title", title);
            fragment.setArguments(args);
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


    }

    static class Adapter1 extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter1(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            Bundle args = new Bundle();
            args.putString("title", title);
            fragment.setArguments(args);
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


    }

    public static View createTabView(Context context, String tabText) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_custom, null, false);
        TextView tv = (TextView) view.findViewById(R.id.tabTitleText);
        tv.setText(tabText);
        return view;
    }

}
