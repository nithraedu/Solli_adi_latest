package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.MainActivity.main_act;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class Nextgame_dialog extends Dialog {
    SharedPreference sps = new SharedPreference();
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper4 newhelper4;

    public Nextgame_dialog(@NonNull final Context context) {
        super(context);
        myDbHelper = new DataBaseHelper(context);
        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);
        final Dialog openDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);
        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(context, "date");
        if (date.equals("0")) {
            next_game.setText("இணைச்சொற்களை  கண்டுபிடி தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("இணைச்சொற்களை  கண்டுபிடி புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");

        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Clue_Game_Hard.class);
                context.startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Solukul_Sol.class);
                context.startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Word_Game_Hard.class);
                context.startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Picture_Game_Hard.class);
                context.startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });

        Cursor ct;
        ct = myDbHelper.getQry("select * from maintable where isfinish='0' order by id limit 1");
        ct.moveToFirst();
        if (ct.getCount() != 0) {
            Cursor c;
            c = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='0' order by id limit 1");
            c.moveToFirst();
            if (c.getCount() != 0) {
                c_game.setVisibility(View.VISIBLE);
            }
            Cursor c2;
            c2 = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='0' order by id limit 1");
            c2.moveToFirst();
            if (c2.getCount() != 0) {
                s_game.setVisibility(View.VISIBLE);
            }
            Cursor c3;
            c3 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0' order by id limit 1");
            c3.moveToFirst();
            if (c3.getCount() != 0) {
                w_game.setVisibility(View.VISIBLE);
            }
            Cursor c4;
            c4 = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0' order by id limit 1");
            c4.moveToFirst();
            if (c4.getCount() != 0) {
                p_game.setVisibility(View.VISIBLE);
            }
        } else {

            next_game.setText("புதிய பதிவுகள் ஏதும் இல்லை.விரைவில் வினாக்கள் பதிவேற்றம் செய்யப்படும்.");
            exit.setText("சரி ");
            exit.setVisibility(View.VISIBLE);
        }

        TextView odd_man_out = (TextView) openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = (TextView) openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Match_Word.class);
                context.startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Odd_man_out.class);
                context.startActivity(i);
            }
        });
        Cursor cts;
        cts = newhelper.getQry("select * from newmaintable where isfinish='0' order by id limit 1");
        cts.moveToFirst();
        if (cts.getCount() != 0) {
            Cursor sc;
            sc = newhelper.getQry("select * from newmaintable where gameid='5' and isfinish='0' order by id limit 1");
            sc.moveToFirst();
            if (sc.getCount() != 0) {
                odd_man_out.setVisibility(View.VISIBLE);
            }
            Cursor scs;
            scs = newhelper.getQry("select * from newmaintable where gameid='6' and isfinish='0' order by id limit 1");
            scs.moveToFirst();
            if (scs.getCount() != 0) {
                matchword.setVisibility(View.VISIBLE);
            }
        }


        TextView opposite_word = (TextView) openDialog.findViewById(R.id.opposite_word);
        TextView ote_to_tamil = (TextView) openDialog.findViewById(R.id.ote_to_tamil);
        opposite_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Opposite_word.class);
                context.startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Ote_to_Tamil.class);
                context.startActivity(i);
            }
        });

        Cursor ctd;
        ctd = newhelper2.getQry("select * from newmaintable2 where isfinish='0' order by id limit 1");
        ctd.moveToFirst();
        if (ctd.getCount() != 0) {
            Cursor scd;
            scd = newhelper2.getQry("select * from newmaintable2 where gameid='7' and isfinish='0' order by id limit 1");
            scd.moveToFirst();
            if (scd.getCount() != 0) {
                opposite_word.setVisibility(View.VISIBLE);
            }
            Cursor scdd;
            scdd = newhelper2.getQry("select * from newmaintable2 where gameid='8' and isfinish='0' order by id limit 1");
            scdd.moveToFirst();
            if (scdd.getCount() != 0) {
                ote_to_tamil.setVisibility(View.VISIBLE);
            }
        }

        TextView seerpaduthu = (TextView) openDialog.findViewById(R.id.seerpaduthu);
        TextView puthir = (TextView) openDialog.findViewById(R.id.puthir);
        TextView tirukural = (TextView) openDialog.findViewById(R.id.tirukural);
        TextView pilaithiruthu = (TextView) openDialog.findViewById(R.id.pilaithiruthu);

        Cursor ctds;
        ctds = newhelper3.getQry("select * from right_order where isfinish='0' order by id limit 1");
        ctds.moveToFirst();
        if (ctds.getCount() != 0) {
            Cursor scds;
            scds = newhelper3.getQry("select * from right_order where gameid='9' and isfinish='0' order by id limit 1");
            scds.moveToFirst();
            if (scds.getCount() != 0) {
                seerpaduthu.setVisibility(View.VISIBLE);
            }
            Cursor scdds;
            scdds = newhelper3.getQry("select * from right_order where gameid='10' and isfinish='0' order by id limit 1");
            scdds.moveToFirst();
            if (scdds.getCount() != 0) {
                puthir.setVisibility(View.VISIBLE);
            }

            Cursor c3s;
            c3s = newhelper3.getQry("select * from right_order where gameid='11' and isfinish='0' order by id limit 1");
            c3s.moveToFirst();
            if (c3s.getCount() != 0) {
                pilaithiruthu.setVisibility(View.VISIBLE);
            }
            Cursor c4s;
            c4s = newhelper3.getQry("select * from right_order where gameid='12' and isfinish='0' order by id limit 1");
            c4s.moveToFirst();
            if (c4s.getCount() != 0) {
                tirukural.setVisibility(View.VISIBLE);
            }
        }


        seerpaduthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Makeword_Rightorder.class);
                context.startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Riddle_game.class);
                context.startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Tirukural.class);
                context.startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, WordError_correction.class);
                context.startActivity(i);
            }
        });

        TextView fill_in_blanks = (TextView) openDialog.findViewById(R.id.fill_in_blanks);
        TextView eng_to_tamil = (TextView) openDialog.findViewById(R.id.eng_to_tamil);

        Cursor scds;
        scds = newhelper4.getQry("select * from newgamesdb4 where gameid='13' and isfinish='0' order by id limit 1");
        scds.moveToFirst();
        if (scds.getCount() != 0) {
            fill_in_blanks.setVisibility(View.VISIBLE);
        }

        Cursor scdss;
        scdss = newhelper4.getQry("select * from newgamesdb4 where gameid='14' and isfinish='0' order by id limit 1");
        scdss.moveToFirst();
        if (scdss.getCount() != 0) {
            eng_to_tamil.setVisibility(View.VISIBLE);
        }

        fill_in_blanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, Fill_in_blanks.class);
                context.startActivity(i);
            }
        });

        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                sps.putString(context, "date", "0");
                Intent i = new Intent(context, English_to_tamil.class);
                context.startActivity(i);
            }
        });

        openDialog.show();

        openDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (main_act.equals("")) {


                    ((Activity) context).finish();
                    //     openDialog_s.dismiss();
                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);
                } else {

                    sps.putString(context, "game_area", "on");
                    ((Activity) context).finish();
                }
                openDialog.dismiss();
                //sps.putString(Odd_man_out.this, "date", "0");
              /*  Intent i = new Intent(context, MainActivity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
    }

    public void nextgamesdialog(final Context context) {

    }


}
