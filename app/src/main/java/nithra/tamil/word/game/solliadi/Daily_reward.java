package nithra.tamil.word.game.solliadi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

public class Daily_reward extends Fragment {
View v;
    customlist_myreport adapt;
    ListView list;
    SQLiteDatabase exdb;
    String date[],gamecount[],score[],time[];
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.activity_daily_reward, container, false);

        exdb = getActivity().openOrCreateDatabase("Solli_Adi", getActivity().MODE_PRIVATE, null);
        list=(ListView)v.findViewById(R.id.list);

        exdb.execSQL("create table if not exists userdata_r(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,type varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");

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

        Cursor c=exdb.rawQuery("select * from userdata_r where date <='"+std+"' order by date DESC",null);
        c.moveToFirst();
        date = new String[c.getCount()];
        gamecount = new String[c.getCount()];
        score = new String[c.getCount()];
        time = new String[c.getCount()];
        if (c.getCount() != 0) {
            for (int i=0; i<c.getCount();i++) {
                c.moveToPosition(i);
                date[i] = c.getString(c.getColumnIndexOrThrow("date"));
                int gm1=c.getInt(c.getColumnIndexOrThrow("gm1"));
                int gm2=c.getInt(c.getColumnIndexOrThrow("gm1"));
                int gm3=c.getInt(c.getColumnIndexOrThrow("gm1"));
                int gm4=c.getInt(c.getColumnIndexOrThrow("gm1"));
                int count=gm1+gm2+gm3+gm4;
                String gmcount= String.valueOf(count);
                gamecount[i]= gmcount;
                score[i]=c.getString(c.getColumnIndexOrThrow("score"));
                time[i]=c.getString(c.getColumnIndexOrThrow("playtime"));
                //Toast.makeText(aathichudi.this, ""+pro[i], Toast.LENGTH_SHORT).show();

            }
        }else{
           // nodata.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Nodata", Toast.LENGTH_SHORT).show();
        }
       /* adapt=new customlist_myreport(getActivity(),date,gamecount,score,time);
        list.setAdapter(adapt);*/





    return v;
    }
}
