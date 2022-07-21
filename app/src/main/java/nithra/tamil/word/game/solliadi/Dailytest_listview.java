package nithra.tamil.word.game.solliadi;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Dailytest_listview extends AppCompatActivity {
    customlist adapt;
    SQLiteDatabase exdb;
    String listu[];
    ListView list;

    String gameid[],fin[],noans[];
    SharedPreference sps = new SharedPreference();
    TextView nodata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailytest_listview);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        list=(ListView)findViewById(R.id.dailytest_list);
        nodata=(TextView)findViewById(R.id.nodata);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view=getLayoutInflater().inflate(R.layout.action_sole, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        TextView action = (TextView) findViewById(R.id.actionword8);
        action.setText("Daily_Test");
        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d=getResources().getDrawable(R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(d);

        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Dailytest_listview.this, MainActivity.class);
                startActivity(intent);


            }
        });

        Calendar calendar2 = Calendar.getInstance();
        int cur_year = calendar2.get(Calendar.YEAR);
        int cur_month = calendar2.get(Calendar.MONTH);
        int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

        String str_month = "" + (cur_month + 1);
        if (str_month.length() == 1) {
            str_month = "0" + str_month;
        }

        String str_day = ""+cur_day;
        if(str_day.length()==1){
            str_day = "0"+str_day;
        }
        String std=cur_year+"-"+str_month+"-"+str_day;


        Cursor c=exdb.rawQuery("select * from dailytest where date <='"+std+"' order by date DESC",null);
        c.moveToFirst();
        listu = new String[c.getCount()];
        gameid = new String[c.getCount()];
        fin = new String[c.getCount()];
        if (c.getCount() != 0) {
            for (int i=0; i<c.getCount();i++) {
                c.moveToPosition(i);
                listu[i] = c.getString(c.getColumnIndex("date"));
                gameid[i]= c.getString(c.getColumnIndex("gameid"));
                fin[i]=c.getString(c.getColumnIndex("isfinish"));
                //Toast.makeText(aathichudi.this, ""+pro[i], Toast.LENGTH_SHORT).show();

            }

        }else{
            nodata.setVisibility(View.VISIBLE);
        }





        adapt=new customlist(getApplicationContext(),listu,gameid,fin);
        list.setAdapter(adapt);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

if (fin[i].equals("0")){
    if (gameid[i].equals("1")){
        finish();
        Intent intent = new Intent(Dailytest_listview.this, Picture_Game_Hard.class);
        intent.putExtra("datee",listu[i]);
        startActivity(intent);

    }else if (gameid[i].equals("2")){
        finish();
        Intent intent = new Intent(Dailytest_listview.this, Clue_Game_Hard.class);
        intent.putExtra("datee",listu[i]);
        startActivity(intent);
    }else if (gameid[i].equals("3")){
        finish();
        Intent intent = new Intent(Dailytest_listview.this, Solukul_Sol.class);
        intent.putExtra("datee",listu[i]);
        startActivity(intent);

    }else if (gameid[i].equals("4")){
        finish();
        Intent intent = new Intent(Dailytest_listview.this, Word_Game_Hard.class);
        intent.putExtra("datee",listu[i]);
        startActivity(intent);
    }    
}else
{
    Toast.makeText(Dailytest_listview.this, "Already Completed", Toast.LENGTH_SHORT).show();
}


            }
        });


    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if(keyCode==KeyEvent.KEYCODE_BACK) {
            finish();
            Intent intent = new Intent(Dailytest_listview.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

}
