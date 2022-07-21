package nit_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.Utils;

public class Apps_Fragment1 extends Fragment {




    String[] eng_app = {"nithra.math.aptitude","com.nithra.bestenglishgrammar","nithra.math.logicalreasoning","nithra.numberreasoningpuzzle","nithra.quiz","nithra.agecalculator","nithra.business.card.invitation.wedding.cardmaker",
            "com.nithra.learnwords","com.nithra.resume","nithra.reminder.tasks.alarm","nithra.unitconverter"};


    String[] eng_tit = {"Aptitude Test and Preparation","Basic English Grammar Practice","Logical Reasoning Test","Number Reasoning Puzzle","General Knowledge Quiz",
    "Age Calculator","Card Maker: Business & Wedding","Learn English Words Offline","My Resume Builder,CV Free Jobs","Best Reminder: To-Do&Task List","Unit Converter"};

    int [] eng_logo = {R.drawable.aptitude,R.drawable.grammar,R.drawable.logical,R.drawable.number,R.drawable.quiz,R.drawable.agecalculator,R.drawable.newcardmaker,
            R.drawable.learnwords,R.drawable.myresume,R.drawable.reminder,R.drawable.unitconverter};

    String[] eng_app_des = {"Aptitude Test & Preparation - Improve your skills in order to face the Interview and Competitive examination.",
            "English Grammar - Learn more about the English language with our grammar lessons & Practices",
            "Logical Reasoning - Logical Reasoning questions and answers with explanation for interviews,entrance tests and competitive exams",
            "Number Reasoning Puzzle - Free math puzzles and brain teasers are interactive, challenging and entertaining",
            "General Knowledge Quiz - GK Quiz is a form of game in which everybody can be strengthen their knowledge by answering the questions",
    "Age Calculator – Make calculate of your age in perfect calculus and enjoy with our crazy calculator",
    "Card Maker – Create a design of you own business and wedding card making/designing in a simplest way",
    "Learn English words - Reading is the best way to increase your vocabulary. Learn words by using our different categories option",
    "My Resume - Service helping you write a better resume by providing you with content, templates resume builder to use forever",
    "Best Reminder - “Make life easier”. This reminds you on all of your work process to do",
    "Unit Converter - Unit conversion will helps you to convert measurement units anytime on single value and multiple units of output display"};

    String[] app_rate = {"4.5","4.2","4.5","4.4","4.5","4.4","4.2","4.3","4.4","4.3","4.4"};
    String[] app_downlods = {"500,000","100,000","500,000","10,000","500,000","10,000","50,000","10,000","1,000,000","50,000","500"};


    TextView title;
    ImageView icon;
    TextView dess;
    TextView click_but;

    String[] back_colr = {"#D204A4","#F58634","#Ed3237","#0098DA","#AAA542"};
    SQLiteDatabase myDB;
    Button share_but;

    public static Apps_Fragment newInstance(String param1) {
        Apps_Fragment fragment = new Apps_Fragment();
        Bundle args = new Bundle();
        args.putString("title", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_apps, container, false);

        myDB = getActivity().openOrCreateDatabase("myDB", getActivity().MODE_PRIVATE, null);

        Bundle data=this.getArguments();

         title = (TextView)rootView.findViewById(R.id.title);
         icon = (ImageView)rootView.findViewById(R.id.icon);
         dess = (TextView)rootView.findViewById(R.id.dess);
         click_but = (TextView)rootView.findViewById(R.id.click_but);
        share_but = (Button)rootView.findViewById(R.id.share_but);
        RelativeLayout backk = (RelativeLayout)rootView.findViewById(R.id.backk);

        Random ran = new Random();
        int ran_val = ran.nextInt(4);
        backk.setBackgroundColor(Color.parseColor(back_colr[ran_val]));
        click_but.setTextColor(Color.parseColor(back_colr[ran_val]));

        String str_title = data.getString("title");

        /*String[] str_val = str_title.split("\\,");*/
        int val = Integer.parseInt(str_title);

        TextView down_txt = (TextView)rootView.findViewById(R.id.down_txt);
        TextView  rate_txt = (TextView)rootView.findViewById(R.id.rate_txt);


        down_txt.setText(app_downlods[val]);
        rate_txt.setText(app_rate[val]);


            set_data(eng_tit[val],eng_logo[val],eng_app_des[val],eng_app[val]);


        return rootView;
    }

    public  void set_data(String str_tit,int logo, final String str_des, final String pack){

        title.setText(str_tit);
        icon.setImageResource(logo);
        dess.setText(str_des);

        if(Utils.isAppInstalled(getActivity(), pack)){
            click_but.setText("Open");
        }
        else {
            click_but.setText("Download");
        }

        click_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click_but.getText().toString().equals("Download")){
                   /* Cursor c = myDB.rawQuery("select uid from point_earn where uid = '2' and type = '"+pack+"'", null);
                    if(c.getCount()==0){

                        myDB.execSQL("INSERT INTO point_earn (uid,type,date,point)values('2','"+pack+"','"+Utils.getDate()+"','25');");
                    }*/
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" +pack + "&referrer=utm_source%3DSOLLIADI_MORE_APP")));
                }
                else {
                    Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage(pack);
                    startActivity(launchIntent);
                }
            }
        });

        share_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/*");
                //i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Tamil SMS");
                i.putExtra(Intent.EXTRA_TEXT, ""+str_des+"Click to download:"+"\nhttp://play.google.com/store/apps/details?id=" + pack+ "&referrer=utm_source%3DSOLLIADI_MORE_APP");
                startActivity(Intent.createChooser(i, "Share via"));
            }
        });

    }



}
