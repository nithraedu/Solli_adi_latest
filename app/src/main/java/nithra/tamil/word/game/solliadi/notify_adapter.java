package nithra.tamil.word.game.solliadi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class notify_adapter extends ArrayAdapter<String> {

    final String[] url;
    final int[] idd;
    final Notify_del notifyDel;
    private final Activity context;
    private final String[] title;
    private final String[] msgDate;
    private final String[] msgTime;
    private final int[] read;


    public notify_adapter(Activity context, int[] read, String[] title, String[] urls, int[] id, String[] msgDate, String[] msgTime) {
        super(context, R.layout.notify_item, title);
        this.context = context;
        this.read = read;
        this.title = title;
        this.msgDate = msgDate;
        this.msgTime = msgTime;
        this.url = urls;
        this.idd = id;
        notifyDel = (Notify_del) context;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.notify_item, null, true);
        SQLiteDatabase myDB = context.openOrCreateDatabase("myDB", 0, null);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView1);
        textView1.setText(title[position]);
        ImageView noti_img = (ImageView) rowView.findViewById(R.id.noti_img);
        TextView noti_id = (TextView) rowView.findViewById(R.id.noti_id);
        TextView date = (TextView) rowView.findViewById(R.id.date);
        TextView time = (TextView) rowView.findViewById(R.id.time);

        Random ran = new Random();
        int color = Color.argb(255, ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
        noti_id.setBackgroundColor(color);

        rowView.setBackgroundColor(Color.parseColor("#B2DFDB"));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("##########Noti_Fragment");
                rowView.setBackgroundColor(Color.parseColor("#ffffff"));
                read[position] = 1;
                myDB.execSQL("update noti_cal set isclose='1' where id='" + idd[position] + "'");

                Cursor cv = myDB.rawQuery("select * from noti_cal where id='" + idd[position] + "'", null);
                cv.moveToFirst();
                String type = cv.getString(cv.getColumnIndexOrThrow("type"));
                String message = cv.getString(cv.getColumnIndexOrThrow("message"));
                String title_txt = cv.getString(cv.getColumnIndexOrThrow("title"));
                int iddd = cv.getInt(cv.getColumnIndexOrThrow("id"));
                if (type.equals("s")) {

                    Intent intent = new Intent(context, ST_Activity.class);
                    intent.putExtra("idd", iddd);
                    intent.putExtra("title", title[position]);
                    intent.putExtra("Noti_add", 2);
                    intent.putExtra("message", message);
                    context.startActivity(intent);

                } else if (type.equals("st")) {

                    Intent intent = new Intent(context, ST_Activity.class);
                    intent.putExtra("idd", iddd);
                    intent.putExtra("title", title[position]);
                    intent.putExtra("Noti_add", 2);
                    intent.putExtra("message", message);
                    context.startActivity(intent);

                }


            }


        });

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                notifyDel.onLongClick(idd[position], 0);
                Log.i("check_jaasim", "onItemLongClick: ");
                return true;
            }
        });


        int a = position + 1;
        noti_id.setText("" + a);

        //21.10.2022

        date.setText("" + msgDate[position]);

        String input = "15/02/2014 22:22:12";
        //Date/time pattern of input date
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        //Date/time pattern of desired output date
        DateFormat outputformat = new SimpleDateFormat("hh:mm:ss aa");
        Date date_cur = null;
        String output = null;
        try {
            //Conversion of input String to date
            date_cur = df.parse("" + msgTime[position]);
            //old date format to new date format
            output = outputformat.format(date_cur);
            System.out.println(output);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        time.setText("" + output);

        if (read[position] == 1) {
            rowView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        return rowView;
    }
}