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


public class Apps_Fragment extends Fragment {

    String[] tam_app = {"nithra.tamilcalender","com.bharathdictionary","nithra.samayalkurippu","com.nithra.tamilsms","com.nithra.riddles",
            /*"com.nithra.tamilproverbs",*/"nithra.thirukkural","nithra.technicaldictionary","nithra.tamilcrosswordpuzzle","nithra.temple.history_details","nithra.tamil.story"};

    String[] tam_tit = {"Tamil Calendar 2016 Offline","English to Tamil Dictionary","Samayal Tamil - Veg & Non-Veg","Tamil SMS Text, Images Share","Tamil Riddles",/*"Tamil Proverbs",*/"Thirukkural","Technical Dictionary",
    "Tamil Crossword Game","Tamilnadu Temples, Districts","Tamil Stories - Kathaigal"};

    int [] tam_logo = {R.drawable.tamilcalendar,R.drawable.englishtotamildictionary,R.drawable.samayal,R.drawable.tamilsms,R.drawable.ridd,/*R.drawable.prov,*/R.drawable.thr,R.drawable.tech,
            R.drawable.tamilcrossword,R.drawable.tamilnadutempledisctric,R.drawable.tamilstory};

    String[] tam_app_des = {"தினசரி நாட்களுக்கான தகவல்கள், திருமண பொருத்தம் போன்ற பல தகவல்களைக் கொண்ட இலவச அப்ளிகேசன்.",
            "ஆங்கில வார்த்தைகளுக்கு இணையான தமிழ் வார்த்தைகள், எளிமையாக ஆங்கிலம் கற்க பல சிறப்பம்சங்கள்",
    "சைவ, அசைவ உணவுகளின் செய்முறைகள், மருத்துவம், அழகு குறிப்புகள் கொண்ட இலவச அப்ளிகேசன்.",
            "உறவுகளோடும், உணர்வுகளோடும் இணைந்து உங்கள் தகவல்களை பகிர்ந்து கொள்ள இலவச அப்ளிகேசன்.",
            "சிந்தனைக்கு வித்தாகும் விடுகதைகள் பற்றிய இலவச  அப்ளிகேஷேன்",
           /* "நமது முன்னோர்கள் சுருங்க கூறி விளங்க வைக்கும் விதத்தில் உதிர்த்த வாய் மொழிகளாகிய பழமொழிகள் பற்றிய இலவச அப்ளிகேஷேன்",*/
            "ஈரடியில் உலகை அடக்கி ஆளும் திருக்குறளை பற்றிய தமிழ் மற்றும் ஆங்கில விளக்கம் கொண்ட இலவச அப்ளிகேஷேன்",
            "தொழில்நுட்ப வார்த்தைகளுக்கான அர்த்தங்களை எளிதில் அறிய உதவும் இலவச   அப்ளிகேஷேன்",
            "அறிவுத் திறனை மேம்படுத்தி, சிந்தனைத் திறனை தூண்டும் வகையில் உருவாக்கப்பட்ட தமிழ் குறுக்கெழுத்துப்போட்டி ",
    "ஆன்மீகத்தை நோக்கி ஒரு சிறப்பு பயணமாக கடவுள்களின் தல வரலாறு, சிறப்புப்பெயர்கள் பற்றிய தெய்வீகத் தகவல்கள்.",
    "கற்பனைத் திறனிற்கு உயிரோட்டம் கொடுத்து படித்து மகிழ நீதிக்கதைகள், பேய்கதைகள், ஆன்மீகக் கதைகள் போன்ற பல்வேறு தலைப்புகளில் 500-க்கும் மேற்பட்ட கதைகள்"};

    String[] app_rate = {"4.6","4.6","4.5","4.6","4.4",/*"4.5",*/"4.7","4.5","4.5","4.7","4.7"};
    String[] app_downlods = {"100,000","500,000","50,000","100,000","50,000",/*"50,000",*/"10,000","10,000","500","1,000","10,000"};



    TextView title;
    ImageView icon;
    TextView dess;
    TextView click_but;
    Button share_but;

    String[] back_colr = {"#D204A4","#F58634","#Ed3237","#0098DA","#AAA542"};
    SQLiteDatabase myDB;

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

        myDB = getActivity().openOrCreateDatabase("myDB", getActivity().MODE_PRIVATE, null);

        View rootView = inflater.inflate(R.layout.fragment_apps, container, false);

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


        int val = Integer.parseInt(str_title);

        TextView down_txt = (TextView)rootView.findViewById(R.id.down_txt);
        TextView  rate_txt = (TextView)rootView.findViewById(R.id.rate_txt);


        down_txt.setText(app_downlods[val]);
        rate_txt.setText(app_rate[val]);


            set_data(tam_tit[val],tam_logo[val],tam_app_des[val],tam_app[val]);


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
                  /*  Cursor c = myDB.rawQuery("select uid from point_earn where uid = '2' and type = '"+pack+"'", null);
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
