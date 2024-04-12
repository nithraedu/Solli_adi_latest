package nithra.tamil.word.game.solliadi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lenovo on 17-06-2016.
 */
public class customlist extends BaseAdapter {

    final String[] listu;
    final String[] gameid;
    final String[] fin;
    final Context context;
    Typeface typ;
    TextView txtpro;
    TextView date, gamename, playbutton;
    SQLiteDatabase exdb;
    RelativeLayout list1;

    public customlist(Context context, String[] listu, String[] gameid, String[] fin) {

        this.fin = fin;
        this.gameid = gameid;
        this.listu = listu;
        this.context = context;

    }

    @Override
    public int getCount() {

        return listu.length;
    }

    @Override
    public Object getItem(int i) {

        return i;
    }


    @Override
    public long getItemId(int i) {

        return i;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        exdb = context.openOrCreateDatabase("Solli_Adi", Context.MODE_PRIVATE, null);
        int a = position + 1;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_customlist, null);
        date = (TextView) view.findViewById(R.id.date);
        gamename = (TextView) view.findViewById(R.id.gamename);
        playbutton = (TextView) view.findViewById(R.id.play);
        list1 = (RelativeLayout) view.findViewById(R.id.list1);
        String tfoption = listu[position];
        String[] first = tfoption.split("-");
        date.setText("" + first[2] + "-" + first[1] + "-" + first[0]);


        // date.setText(listu[position]);
        if (gameid[position].equals("1")) {
            gamename.setText("படம் பார்த்து கண்டுபிடி");
            list1.setBackgroundResource(R.color.dark_blue);
        } else if (gameid[position].equals("2")) {
            gamename.setText("குறிப்பு மூலம் கண்டுபிடி");
            list1.setBackgroundResource(R.color.red);
        } else if (gameid[position].equals("3")) {
            gamename.setText("சொல்லுக்குள் சொல்");
            list1.setBackgroundResource(R.color.dark_yellow);
        } else if (gameid[position].equals("4")) {
            gamename.setText("சொல் விளையாட்டு");
            list1.setBackgroundResource(R.color.background_floating_material_dark);
        }
        //gamename.setText(gameid[position]);

        if (fin[position].equals("1")) {

            playbutton.setBackgroundResource(R.drawable.tick_background);
        } else if (fin[position].equals("0")) {

            playbutton.setBackgroundResource(R.drawable.yellow_question);
        }


        return view;
    }
}

