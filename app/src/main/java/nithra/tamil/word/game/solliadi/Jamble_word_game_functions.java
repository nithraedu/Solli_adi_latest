package nithra.tamil.word.game.solliadi;

import android.content.Context;
import android.database.Cursor;

public class Jamble_word_game_functions {
    DataBaseHelper myDbHelper;

    public static int score(Context context, int val) {
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);
        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        val = skq + val;
        myDbHelper.executeSql("UPDATE score SET coins='" + val + "'");
        return val;
    }

    /*
    public static void  scorescreen(final Context context){
        final Typeface typ, tyr;

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);
        tyr = Typeface.createFromAsset(context.getAssets(), "TAMHN0BT.TTF");
        final Dialog openDialog_s;
        TextView next_continue;
        final TextView ttscores;
        final SharedPreference sps = new SharedPreference();
        openDialog_s = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);

        next_continue = (TextView) openDialog_s.findViewById(R.id.continues2);
        ttscores = (TextView) openDialog_s.findViewById(R.id.tts_score2);
        final TextView wtp = (TextView) openDialog_s.findViewById(R.id.wtp);
        final TextView fbs = (TextView) openDialog_s.findViewById(R.id.fbp);
        final TextView kuduthal = (TextView) openDialog_s.findViewById(R.id.tt22);
        final TextView gplus = (TextView) openDialog_s.findViewById(R.id.gplus2);
        final TextView word = (TextView) openDialog_s.findViewById(R.id.arputham2);
        final LinearLayout rewardvideo = (LinearLayout) openDialog_s.findViewById(R.id.rewardvideo);
        final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);
        LinearLayout ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);

        ImageView prize_logo=(ImageView)openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(context, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(context)) {
                    if (sps.getString(context, "price_registration").equals("com")) {
                        context.finish();
                        Intent i = new Intent(context, Game_Status.class);
                        context.context.startActivity(i);
                    } else {
                        if (sps.getString(context, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(context, LoginActivity.class);
                            context.startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(context, Price_Login.class);
                            context.startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sps.getInt(context, "reward_coin_txt") + "+நாணயங்கள் பெற");

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(context, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            New_Main_Activity.load_addFromMain_multiplayer(context, ads_layout);
        }


        word.setTypeface(tyr);
        next_continue.setVisibility(View.VISIBLE);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);

        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");

        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(context, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            String aStringx = Integer.toString(skx);
            ttscores.setText(aStringx);
        }


        if (sps.getString(context, "complite_reg").equals("yes")) {
            String dates = sps.getString(context, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }

        if (ry == 1) {

        } else {
            rewardvideo.setVisibility(View.INVISIBLE);
        }

        RelativeLayout adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(context, R.anim.pendulam);
        adsicon.startAnimation(shake);
        //  final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);

        vid_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(context)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                    if (mRewardedVideoAd.isLoaded()) {
                        reward_progressBar.dismiss();
                        showRewardedVideo();
                        //   vid_earn.setVisibility(View.INVISIBLE);
                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        startGame();
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (mRewardedVideoAd.isLoaded()) {
                                    showRewardedVideo();
                                    //    vid_earn.setVisibility(View.VISIBLE);

                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    startGame();
                                    Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        rewardvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(context)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                    if (mRewardedVideoAd.isLoaded()) {
                        reward_progressBar.dismiss();
                        showRewardedVideo();
                        rewardvideo.setVisibility(View.INVISIBLE);
                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        startGame();
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (mRewardedVideoAd.isLoaded()) {
                                    showRewardedVideo();
                                    rewardvideo.setVisibility(View.INVISIBLE);

                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    startGame();
                                    Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        wtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(context)) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");

                        String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivity(Intent.createChooser(i, "Share via"));
                        startActivityForResult(Intent.createChooser(i, "Share via"), 21);



                    } else {
                        Toast.makeText(context, "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    // toast("இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(context)) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");

                        String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 16);

                    } else {
                        Toast.makeText(context, "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    // toast("இணையதள சேவையை சரிபார்க்கவும் ");
                }

            }

        });

        /////////////////////////////////////////Reward tittle////////////////////////////////////////////
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;

        f_sec = sec + sec2 + seconds;
        word.setText("ï¡Á");
        if (f_sec < 30) {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        } else if (f_sec > 30 && f_sec < 60) {
            word.setText("");
            word.setText("ÜŸ¹î‹");

        } else if (f_sec > 60) {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        }

        if (r == 1) {
            word.setText("");
            word.setText("ºòŸC ªêŒè");
            r = 0;
        }else {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        }
        /////////////////////////////////////////Reward tittle////////////////////////////////////////////


        next_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sps.getInt(context, "purchase_ads") == 1) {

                } else {
                    // advancads_content();
                    //  advancads();
                }
                //noclue=0;
                sps.putInt(context, "cluetime", 0);
                if (sps.getInt(context, "ins_ad_new") == 4) {
                    if (Utils.isNetworkAvailable(context)) {
                        if (interstitialAd_game != null) {
                            if (interstitialAd_game.isLoaded()) {
                                interstitialAd_game.show();
                                interstitialAd_game.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdClosed() {
                                        next();
                                        ins_add();
                                    }
                                });
                            } else {
                                next();
                            }
                        }
                    } else {
                        next();
                    }
                    sps.putInt(context, "ins_ad_new", 0);
                } else {
                    next();
                    sps.putInt(context, "ins_ad_new", (sps.getInt(context, "ins_ad_new") + 1));
                }

                */
/*if (Utils.isNetworkAvailable(context)) {
                    if(getApiClient().isConnected()) {
                        if (isSignedIn()) {
                            int k1 = 0;
                            Cursor sc2 = myDbHelper.getQry("select * from score ");
                            sc2.moveToFirst();
                            if (sc2.getCount() != 0) {
                                k1 = sc2.getInt(sc2.getColumnIndexOrThrow("l_points"));
                            }
                            Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard), k1);
                        }
                    }
                }*//*


                dia_dismiss=1;
                openDialog_s.dismiss();
            }
        });

        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss!=1){
                    sps.putString(context, "game_area", "on");
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                        interstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                String date = sps.getString(context, "date");
                                if (date.equals("0")) {
                                    if (main_act.equals("")) {
                                        finish();
                                        openDialog_s.dismiss();
                                        Intent i = new Intent(context, New_Main_Activity.class);
                                        context.startActivity(i);
                                    } else {
                                        finish();
                                        openDialog_s.dismiss();
                                    }


                                } else {
                                    if (sps.getString(context, "Exp_list").equals("on")) {
                                        finish();
                                        openDialog_s.dismiss();
                                        Intent i = new Intent(context, Expandable_List_View.class);
                                        context.startActivity(i);

                                    } else {
                                        if (main_act.equals("")) {
                                            finish();
                                            openDialog_s.dismiss();
                                            Intent i = new Intent(context, New_Main_Activity.class);
                                            context.startActivity(i);
                                        } else {
                                            finish();
                                            openDialog_s.dismiss();
                                        }
                                    }

                                }
                            }
                        });
                    } else {
                        String date = sps.getString(context, "date");
                        if (date.equals("0")) {
                            if (main_act.equals("")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(context, New_Main_Activity.class);
                                context.startActivity(i);
                            } else {
                                finish();
                                openDialog_s.dismiss();
                            }
                        } else {
                            if (sps.getString(context, "Exp_list").equals("on")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(context, Expandable_List_View.class);
                                context.startActivity(i);

                            } else {
                                if (main_act.equals("")) {
                                    finish();
                                    openDialog_s.dismiss();
                                    Intent i = new Intent(context, New_Main_Activity.class);
                                    context.startActivity(i);
                                } else {
                                    finish();
                                    openDialog_s.dismiss();
                                }
                            }

                        }
                    }

                }else {
                    dia_dismiss=0;
                }

            }
        });

        if (!isFinishing()) {
            openDialog_s.show();
        }

    }
*/
}
