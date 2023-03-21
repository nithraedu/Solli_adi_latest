package nithra.tamil.word.game.solliadi;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Expandable_List_View extends AppCompatActivity {

    static SharedPreference sps = new SharedPreference();
    SQLiteDatabase exdb;
    String[] date_c;
    String[] gameid;
    String[] complite;
    String[] data;
    String gameid_n, gameid_finish, gameid_date, date_n;
    TextView nodata;
    SharedPreference wee = new SharedPreference();
    int a = 1;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    ArrayList<Group> ExpListItems;
    LinearLayout adds;
    private int lastExpandedPosition = -1;
    private int sharep_value = -1;
    private ExpandListAdapter ExpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable__list__view);

        //  ParentListItems = new LinkedHashMap<String, List<String>>();

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        nodata = (TextView) findViewById(R.id.nodata);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);

        adds = (LinearLayout) findViewById(R.id.ads_lay);



      /*  if(Utils.isNetworkAvailable(Expandable_List_View.this)){

            MainActivity.load_addFromMain(Expandable_List_View.this, adds);
        }*/


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.action_sole2, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        TextView action = (TextView) findViewById(R.id.actionword8);
        action.setText("முந்தைய தினசரி விளையாட்டுகள் ");

        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d = getResources().getDrawable(R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(d);


        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sps.putInt(Expandable_List_View.this, "addlodedd", 0);
                finish();
                Intent intent = new Intent(Expandable_List_View.this, MainActivity.class);
                startActivity(intent);


            }
        });

        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(Expandable_List_View.this, ExpListItems);
        expandableListView.setAdapter(ExpAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                ArrayList<Child> ch_list = ExpListItems.get(
                        groupPosition).getItems();

                String title = ch_list.get(childPosition).getName();


                return false;
            }
        });

        if (wee.getString(Expandable_List_View.this, "exp_list").equals("")) {

        } else {
            expandableListView.expandGroup(Integer.parseInt(wee.getString(Expandable_List_View.this, "exp_list")));
            sharep_value = Integer.parseInt(wee.getString(Expandable_List_View.this, "exp_list"));
        }


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {


                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                if (sharep_value != -1) {
                    if (a == 1) {
                        expandableListView.collapseGroup(sharep_value);
                        a = 2;
                    }

                }
                lastExpandedPosition = groupPosition;
                sps.putString(Expandable_List_View.this, "exp_list", String.valueOf(groupPosition));
                int count = ExpListItems.get(groupPosition).getItems().size();
                String title = ExpListItems.get(groupPosition).getName();
/*
                if(expandableListView.isGroupExpanded(groupPosition)){
                    expandableListView.collapseGroup(groupPosition);
                }else{
                    expandableListView.expandGroup(groupPosition);
                }*/

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

                /*String group_name = ExpListItems.get(groupPosition).getName();
                showToastMsg(group_name + "\n Expanded");*/
            }
        });

    /*    expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });*/
    }







    /*public HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        Calendar calendar2 = Calendar.getInstance();
        int cur_year = calendar2.get(Calendar.YEAR);
        int cur_month = calendar2.get(Calendar.MONTH);
        int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

        String str_month = "" + (cur_month + 1);
        if (str_month.length() == 1) {
            str_month = "0" + str_month;
        }

        String str_day = "" + cur_day;
        if (str_day.length() == 1) {
            str_day = "0" + str_day;
        }
        String std = cur_year + "-" + str_month + "-" + str_day;
        System.out.println("%%%%%current_date"+std);
    //  Cursor c = exdb.rawQuery("select * from dailytest where date >'" + std + "' group by date order by date ='" + std + "' desc", null);
        Cursor c =  exdb.rawQuery("select distinct (date) from dailytest order by substr(date ,7,4) || '-' || substr(date  ,4,2)||'-'||substr(date ,1,2) desc", null);
       // Cursor c = exdb.rawQuery("SELECT * FROM dailytest where date >'" + std + "' ORDER BY date(date) DESC Limit 1", null);


        date_c = new String[c.getCount()];
        if (c.getCount() != 0) {
            for (int i=0; i<c.getCount();i++) {
                c.moveToPosition(i);
                String date = c.getString(c.getColumnIndexOrThrow("date"));
                date_n=date;

                Cursor cursor1 = exdb.rawQuery("SELECT * FROM dailytest where date = '"+date+"'", null);
                List<String> list = new ArrayList<String>();
                gameid = new String[cursor1.getCount()];
                System.out.println("%%%%%count"+cursor1.getCount());

                if (cursor1.getCount() != 0) {
                    for (int j = 0; j < cursor1.getCount(); j++) {
                        cursor1.moveToPosition(j);
                        gameid_n = cursor1.getString(cursor1.getColumnIndexOrThrow("gameid"));
                        gameid_finish = cursor1.getString(cursor1.getColumnIndexOrThrow("isfinish"));
                        gameid_date = cursor1.getString(cursor1.getColumnIndexOrThrow("date"));
                        System.out.println("%%%%%gameid_n"+gameid_n+"%%%%%gameid_finish"+gameid_finish+"gameid_date"+gameid_date);
                        list.add(gameid_date+","+gameid_n+","+gameid_finish);
                    }

                }



                System.out.println("%%%%%%%Date"+date+"%%%%%%list"+list);
                expandableListDetail.put(date, list);


            }

        }else {
            nodata.setText("பதிவுகள் இல்லை");
            nodata.setVisibility(View.VISIBLE);
        }

        return expandableListDetail;
    }*/


    public ArrayList<Group> SetStandardGroups() {
        ArrayList<Group> groupList = new ArrayList<Group>();
        ArrayList<Child> childList;


        Calendar calendar2 = Calendar.getInstance();
        int cur_year = calendar2.get(Calendar.YEAR);
        int cur_month = calendar2.get(Calendar.MONTH);
        int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

        String str_month = "" + (cur_month + 1);
        if (str_month.length() == 1) {
            str_month = "0" + str_month;
        }

        String str_day = "" + cur_day;
        if (str_day.length() == 1) {
            str_day = "0" + str_day;
        }
        String std = cur_year + "-" + str_month + "-" + str_day;
        System.out.println("%%%%%current_date" + std);
        //  Cursor c = exdb.rawQuery("select * from dailytest where date >'" + std + "' group by date order by date ='" + std + "' desc", null);
        Cursor c = exdb.rawQuery("select distinct (date) from dailytest where date <'" + std + "' and date >='" + sps.getString(Expandable_List_View.this, "install_date") + "' order by substr(date ,1,2) || '-' || substr(date  ,4,2)||'-'||substr(date ,7,4) desc limit 30", null);
        // Cursor c = exdb.rawQuery("SELECT * FROM dailytest where date >'" + std + "' ORDER BY date(date) DESC Limit 1", null);


        date_c = new String[c.getCount()];
        if (c.getCount() != 0) {
            for (int i = 0; i < c.getCount(); i++) {
                c.moveToPosition(i);
                String date = c.getString(c.getColumnIndexOrThrow("date"));
                date_n = date;
                childList = new ArrayList<Child>();
                Group group = new Group();
                group.setName(date);

                Cursor cursor1 = exdb.rawQuery("SELECT * FROM dailytest where date = '" + date + "'order by gameid asc", null);
                List<String> list = new ArrayList<String>();
                gameid = new String[cursor1.getCount()];
                System.out.println("%%%%%count" + cursor1.getCount());

                if (cursor1.getCount() != 0) {
                    for (int j = 0; j < cursor1.getCount(); j++) {
                        cursor1.moveToPosition(j);
                        Child child = new Child();
                        gameid_n = cursor1.getString(cursor1.getColumnIndexOrThrow("gameid"));
                        gameid_finish = cursor1.getString(cursor1.getColumnIndexOrThrow("isfinish"));
                        gameid_date = cursor1.getString(cursor1.getColumnIndexOrThrow("date"));
                        System.out.println("%%%%%gameid_n" + gameid_n + "%%%%%gameid_finish" + gameid_finish + "gameid_date" + gameid_date);

                        child.setName(gameid_date + "," + gameid_n + "," + gameid_finish);
                        childList.add(child);
                        // list.add(gameid_date+","+gameid_n+","+gameid_finish);
                    }

                }


                System.out.println("%%%%%%%Date" + date + "%%%%%%list" + gameid_date + "," + gameid_n + "," + gameid_finish);
                group.setItems(childList);
                groupList.add(group);
            }

        } else {
            nodata.setText("முந்தைய தினசரி விளையாட்டுகள் பதிவுகள் இல்லை");
            nodata.setVisibility(View.VISIBLE);
        }

        return groupList;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(Expandable_List_View.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onResume() {
        super.onResume();


    }
}
