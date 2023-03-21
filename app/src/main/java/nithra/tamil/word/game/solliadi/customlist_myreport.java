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

import java.util.ArrayList;

/**
 * Created by lenovo on 17-06-2016.
 */
public class customlist_myreport extends BaseAdapter {

    ArrayList<Integer> nos = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> gamecounts = new ArrayList<String>();
    ArrayList<Integer> scores = new ArrayList<Integer>();
    ArrayList<String> citys = new ArrayList<String>();

    // String dates[],gamecounts[],scores[],times[];
    Context context;
    Typeface typ;
    TextView txtpro;
    TextView no, name, score, city;
    SQLiteDatabase exdb;
    RelativeLayout list1;

    public customlist_myreport(Context context, ArrayList<Integer> noss, ArrayList<String> name, ArrayList<Integer> score, ArrayList<String> city) {

        this.nos = noss;
        this.names = name;
        this.scores = score;
        this.citys = city;
        this.context = context;

    }

    @Override
    public int getCount() {

        return scores.size();
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
        View view = inflater.inflate(R.layout.activity_customlist_myreport, null);
        no = (TextView) view.findViewById(R.id.no);
        name = (TextView) view.findViewById(R.id.name);
        score = (TextView) view.findViewById(R.id.score);
        city = (TextView) view.findViewById(R.id.city);
        no.setText("" + nos.get(position));
        //name.setText(""+names.get(position));
        score.setText("" + scores.get(position));

        if (citys.get(position).length() > 11) {
            String da = citys.get(position);
            city.setText("" + da.charAt(0) + da.charAt(1) + da.charAt(2) + da.charAt(3) + da.charAt(4) + da.charAt(5) + da.charAt(6) + da.charAt(7) + da.charAt(8) + "...");
        } else {
            city.setText("" + citys.get(position));
        }

        if (names.get(position).length() > 11) {
            String da = names.get(position);
            name.setText("" + da.charAt(0) + da.charAt(1) + da.charAt(2) + da.charAt(3) + da.charAt(4) + da.charAt(5) + da.charAt(6) + da.charAt(7) + da.charAt(8) + "...");
        } else {
            name.setText("" + names.get(position));
        }

        return view;
    }
}

