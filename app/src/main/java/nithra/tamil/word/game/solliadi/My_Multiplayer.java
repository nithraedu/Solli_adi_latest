package nithra.tamil.word.game.solliadi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class My_Multiplayer extends AppCompatActivity implements RoomUpdateListener, RealTimeMessageReceivedListener, RoomStatusUpdateListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnInvitationReceivedListener {

    TextView quickgame, m_score;
    private GoogleApiClient mGoogleApiClient;

    int play_after = 0;
    final static String TAG = "ButtonClicker2000";

    // Request codes for the UIs that we show with startActivityForResult:
    final static int RC_SELECT_PLAYERS = 10000;
    final static int RC_INVITATION_INBOX = 10001;
    final static int RC_WAITING_ROOM = 10002;
    String mRoomId = null;
    // Are we currently resolving a connection failure?
    private boolean mResolvingConnectionFailure = false;

    // Has the user clicked the sign-in button?
    private boolean mSignInClicked = false;

    // Set to true to automatically start the sign in flow when the Activity starts.
    // Set to false to require the user to click the button in order to sign in.
    private boolean mAutoStartSignInFlow = true;

    // Request code used to invoke sign in user interactions.
    private static final int RC_SIGN_IN = 9001;


    // Score of other participants. We update this as we receive their scores
    // from the network.
    Map<String, Integer> mParticipantScore = new HashMap<String, Integer>();
    Set<String> mFinishedParticipants = new HashSet<String>();
    byte[] mMsgBuf = new byte[3];
    boolean mMultiplayer = false;
    ArrayList<Participant> mParticipants = null;
    int mCurScreen = -1;
    ///////////////////////game values/////////////////////////
    SQLiteDatabase exdb, dbs, dbn;
    TextView bt1;
    TextView bt2;
    TextView bt3;
    TextView bt4;
    TextView bt5;
    TextView bt6;
    TextView bts1;
    TextView bts2;
    TextView bts3;
    TextView bts4, score;
    RelativeLayout sixcat, fourcat;

    int u_id;
    int questionid;
    String question;
    String answer;
    int q_type, ans_position, gameid = 5;
    int score_s;
    String mMyId = null;
    SharedPreference sp = new SharedPreference();


    /////////////////////////game values/////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__multiplayer);


        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        // Create the Google Api Client with access to Games
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();


        switchToScreen(R.layout.activity_my__multiplayer);


        quickgame = (TextView) findViewById(R.id.quickgame);
        m_score = (TextView) findViewById(R.id.m_score);
        m_score.setText("" + sp.getInt(My_Multiplayer.this, "muliplay_score"));

        // user wants to sign in
        // Check to see the developer who's running this sample code read the instructions :-)
        // NOTE: this check is here only because this is a sample! Don't include this
        // check in your actual production app.
        if (!BaseGameUtils.verifySampleSetup(this, R.string.app_id)) {
            Log.w(TAG, "*** Warning: setup problems detected. Sign in may not work!");
        }

        // start the sign-in flow
        Log.d(TAG, "Sign-in button clicked");
        mSignInClicked = true;
        mGoogleApiClient.connect();


        quickgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=sp.getInt(My_Multiplayer.this, "muliplay_score") - 50;
                sp.putInt(My_Multiplayer.this, "muliplay_score", a);
                startQuickGame();
            }
        });


    }

    void startQuickGame() {
        // quick-start a game with 1 randomly selected opponent
        final int MIN_OPPONENTS = 1, MAX_OPPONENTS = 1;
        Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(MIN_OPPONENTS, MAX_OPPONENTS, 0);
        RoomConfig.Builder rtmConfigBuilder = RoomConfig.builder(this);
        rtmConfigBuilder.setMessageReceivedListener(this);
        rtmConfigBuilder.setRoomStatusUpdateListener(this);
        rtmConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
        // switchToScreen(R.id.screen_wait);
        Toast.makeText(My_Multiplayer.this, "Loading....", Toast.LENGTH_SHORT).show();
        keepScreenOn();
        // resetGameVars();
        Games.RealTimeMultiplayer.create(mGoogleApiClient, rtmConfigBuilder.build());


    }

    // Sets the flag to keep this screen on. It's recommended to do that during
    // the
    // handshake when setting up a game, because if the screen turns off, the
    // game will be
    // cancelled.
    void keepScreenOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onRoomCreated(int statusCode, Room room) {
        Log.d(TAG, "onRoomCreated(" + statusCode + ", " + room + ")");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Log.e(TAG, "*** Error: onRoomCreated, status " + statusCode);
            showGameError();
            return;
        }

        // save room ID so we can leave cleanly before the game starts.
        mRoomId = room.getRoomId();

        // show the waiting room UI
        showWaitingRoom(room);
    }

    // Show error message about game being cancelled and return to main screen.
    void showGameError() {
        System.out.println("=====================showGameError");
        Log.d(TAG, "showGameError, code ");
        BaseGameUtils.makeSimpleDialog(this, getString(R.string.game_problem));
        //switchToMainScreen();
        exitgame();
    }

    void switchToMainScreen() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            // switchToScreen(R.id.screen_main);
        } else {
            //  switchToScreen(R.id.screen_sign_in);
        }
    }

    // Show the waiting room UI to track the progress of other players as they enter the
    // room and get connected.
    void showWaitingRoom(Room room) {
        // minimum number of players required for our game
        // For simplicity, we require everyone to join the game before we start it
        // (this is signaled by Integer.MAX_VALUE).
        final int MIN_PLAYERS = Integer.MAX_VALUE;
        Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(mGoogleApiClient, room, MIN_PLAYERS);
        // show waiting room UI
        startActivityForResult(i, RC_WAITING_ROOM);

    }

    @Override
    public void onJoinedRoom(int statusCode, Room room) {
        Log.d(TAG, "onJoinedRoom(" + statusCode + ", " + room + ")");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Log.e(TAG, "*** Error: onRoomConnected, status " + statusCode);
            showGameError();
            return;
        }
        // show the waiting room UI
        showWaitingRoom(room);
    }
    @Override
    public void onLeftRoom(int i, String s) {
        System.out.println("=====================onleft room");
        exitgame();
    }
    private void exitgame() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            setContentView(R.layout.activity_my__multiplayer);
            switchToScreen(R.layout.activity_my__multiplayer);
            quickgame = (TextView) findViewById(R.id.quickgame);
            m_score = (TextView) findViewById(R.id.m_score);
            m_score.setText("" + sp.getInt(My_Multiplayer.this, "muliplay_score"));
            quickgame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    play_after=0;
                    int a=sp.getInt(My_Multiplayer.this, "muliplay_score") - 50;
                    sp.putInt(My_Multiplayer.this, "muliplay_score", a);
                    startQuickGame();
                }
            });
            System.out.println("========================exitgame_--activity_my__multiplayer");
        } else {
            finish();
            Intent i = new Intent(My_Multiplayer.this, MainActivity.class);
            startActivity(i);
            System.out.println("========================exitgame else");
        }
    }
    @Override
    public void onRoomConnected(int statusCode, Room room) {
        Log.d(TAG, "onRoomConnected(" + statusCode + ", " + room + ")");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Log.e(TAG, "*** Error: onRoomConnected, status " + statusCode);
            showGameError();
            return;
        }
        updateRoom(room);
    }

    void updateRoom(Room room) {
        if (room != null) {
            mParticipants = room.getParticipants();
        }
        if (mParticipants != null) {
            //updatePeerScoresDisplay(0);
        }
    }
    @Override
    public void onRealTimeMessageReceived(RealTimeMessage rtm) {
        byte[] buf = rtm.getMessageData();
        String sender = rtm.getSenderParticipantId();

        Log.d(TAG, "Message received: " + (char) buf[0] + "/" + (int) buf[1] + "/" + (int) buf[2]);

        if (buf[0] == 'F' || buf[0] == 'U') {
            // score update.
            int existingScore = mParticipantScore.containsKey(sender) ?
                    mParticipantScore.get(sender) : 0;
            int thisScore = (int) buf[1];
            System.out.println("=========onRealTimeMessageReceived==existingScore========" + existingScore);
            System.out.println("=========onRealTimeMessageReceived==thisScore========" + thisScore);
            if (thisScore > existingScore) {
                // this check is necessary because packets may arrive out of
                // order, so we
                // should only ever consider the highest score we received, as
                // we know in our
                // game there is no way to lose points. If there was a way to
                // lose points,
                // we'd have to add a "serial number" to the packet.
                mParticipantScore.put(sender, thisScore);
            }

            // update the scores on the screen
            updatePeerScoresDisplay((int) buf[1]);

            // if it's a final score, mark this participant as having finished
            // the game
            if ((char) buf[0] == 'F') {
                mFinishedParticipants.add(rtm.getSenderParticipantId());
            }
        }
    }

    private void updatePeerScoresDisplay(int i) {
        play_after = 1;
        if (i == 1) {
            Cursor c1 = dbs.rawQuery("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "' and isfinish='0'", null);
            c1.moveToFirst();
            if (c1.getCount() != 0) {
                String answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
                show_answer(answer);
                Toast.makeText(My_Multiplayer.this, "You Loose The Match and You Loss 50 Points", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        leaveRoom();
                    }
                }, 2300);
            }
        } else if (i == 0) {
            Cursor c1 = dbs.rawQuery("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "' and isfinish='0'", null);
            c1.moveToFirst();
            if (c1.getCount() != 0) {
                String answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
                show_answer(answer);
                Toast.makeText(My_Multiplayer.this, "You Won The Match and You Got 50 Points", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        leaveRoom();
                    }
                }, 2300);
                int a=sp.getInt(My_Multiplayer.this, "muliplay_score") + 100;
                sp.putInt(My_Multiplayer.this, "muliplay_score", a);
            }
        }
        //score.setText("" + i);
    }

    private void show_answer(String answer) {
        if (q_type == 4) {
            if (answer.equals(bts1.getText().toString())) {
                bts1.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bts2.getText().toString())) {
                bts2.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bts3.getText().toString())) {
                bts3.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bts4.getText().toString())) {
                bts4.setBackgroundResource(R.color.rightans);
            }
        } else if (q_type == 6) {
            if (answer.equals(bt1.getText().toString())) {
                bt1.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt2.getText().toString())) {
                bt2.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt3.getText().toString())) {
                bt3.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt4.getText().toString())) {
                bt4.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt5.getText().toString())) {
                bt5.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt6.getText().toString())) {
                bt6.setBackgroundResource(R.color.rightans);
            }
        }
    }

    // Broadcast my score to everybody else.
    void broadcastScore(boolean finalScore) {
        System.out.println("=========broadcastScore==finalScore========" + finalScore);

        if (!mMultiplayer)
            return; // playing single-player mode

        // First byte in message indicates whether it's a final score or not
        mMsgBuf[0] = (byte) (finalScore ? 'F' : 'U');

        // Second byte is the score.
        mMsgBuf[1] = (byte) score_s;
        System.out.println("=========score=======" + score_s);

        mMsgBuf[2] = (byte) 5;
        // Send to every other participant.
        for (Participant p : mParticipants) {
            if (p.getParticipantId().equals(mMyId))
                continue;
            if (p.getStatus() != Participant.STATUS_JOINED)
                continue;
            if (finalScore) {
                // final score notification must be sent via reliable message
                Games.RealTimeMultiplayer.sendReliableMessage(mGoogleApiClient, null, mMsgBuf,
                        mRoomId, p.getParticipantId());
            } else {
                // it's an interim score notification, so we can use unreliable
                Games.RealTimeMultiplayer.sendUnreliableMessage(mGoogleApiClient, mMsgBuf, mRoomId,
                        p.getParticipantId());
            }
        }
    }

    @Override
    public void onRoomConnecting(Room room) {
        updateRoom(room);
    }

    @Override
    public void onRoomAutoMatching(Room room) {
        updateRoom(room);
    }

    @Override
    public void onPeerInvitedToRoom(Room room, List<String> list) {
        updateRoom(room);
    }

    @Override
    public void onPeerDeclined(Room room, List<String> list) {
        updateRoom(room);
    }

    @Override
    public void onPeerJoined(Room room, List<String> list) {
        updateRoom(room);
    }

    @Override
    public void onPeerLeft(Room room, List<String> list) {
        updateRoom(room);
    }

    @Override
    public void onConnectedToRoom(Room room) {
        Log.d(TAG, "onConnectedToRoom.");

        //get participants and my ID:
        mParticipants = room.getParticipants();
        mMyId = room.getParticipantId(Games.Players.getCurrentPlayerId(mGoogleApiClient));

        // save room ID if its not initialized in onRoomCreated() so we can leave cleanly before the game starts.
        if (mRoomId == null)
            mRoomId = room.getRoomId();

        // print out the list of participants (for debug purposes)
        Log.d(TAG, "Room ID: " + mRoomId);
        Log.d(TAG, "My ID " + mMyId);
        Log.d(TAG, "<< CONNECTED TO ROOM>>");
    }

    @Override
    public void onDisconnectedFromRoom(Room room) {


        System.out.println("====================onDisconnectedFromRoom");
        mRoomId = null;
        Log.d(TAG, "onDisconnectedFromRoom, code ");
        Cursor c1 = dbs.rawQuery("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "' and isfinish='0'", null);
        c1.moveToFirst();
        if (c1.getCount() != 0) {
            String answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
            show_answer(answer);
            Toast.makeText(My_Multiplayer.this, "Your Opponent Left.... You Won The Match and You Got 50 Points", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showGameError();
                }
            }, 2300);

            if (play_after == 0) {
                int a=sp.getInt(My_Multiplayer.this, "muliplay_score") + 100;
                sp.putInt(My_Multiplayer.this, "muliplay_score", a);

            }

        }
    }

    @Override
    public void onPeersConnected(Room room, List<String> list) {

    }

    @Override
    public void onPeersDisconnected(Room room, List<String> list) {

    }

    @Override
    public void onP2PConnected(String s) {

    }

    @Override
    public void onP2PDisconnected(String s) {

    }

    @Override
    public void onActivityResult(int requestCode, int responseCode,
                                 Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);


        System.out.println("=========*******requestCode ===== " + requestCode);
        System.out.println("=========*******responseCode ===== " + requestCode);

        switch (requestCode) {

            case RC_SELECT_PLAYERS:
                // we got the result from the "select players" UI -- ready to create the room
                // handleSelectPlayersResult(responseCode, intent);

                break;
            case RC_INVITATION_INBOX:
                // we got the result from the "select invitation" UI (invitation inbox). We're
                // ready to accept the selected invitation:
                //  handleInvitationInboxResult(responseCode, intent);
                break;
            case RC_WAITING_ROOM:
                // we got the result from the "waiting room" UI.
                if (responseCode == Activity.RESULT_OK) {
                    // ready to start playing
                    Log.d(TAG, "Starting game (waiting room returned OK).");
                    //  startGame(true);
                   /* finish();
                    Intent i = new Intent(Multiplayer.this, Multiplayer_Odd_man_out.class);
                    startActivity(i)*/
                    ;
                    game_start();
                    Toast.makeText(My_Multiplayer.this, "Success!!!!!", Toast.LENGTH_SHORT).show();

                } else if (responseCode == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                    // player indicated that they want to leave the room
                    leaveRoom();
                } else if (responseCode == Activity.RESULT_CANCELED) {
                    // Dialog was cancelled (user pressed back key, for instance). In our game,
                    // this means leaving the room too. In more elaborate games, this could mean
                    // something else (like minimizing the waiting room UI).
                    leaveRoom();
                }
                break;
            case RC_SIGN_IN:
                Log.d(TAG, "onActivityResult with requestCode == RC_SIGN_IN, responseCode="
                        + responseCode + ", intent=" + intent);
                mSignInClicked = false;
                mResolvingConnectionFailure = false;
                if (responseCode == RESULT_OK) {
                    mGoogleApiClient.connect();
                } else {
                    BaseGameUtils.showActivityResultError(this, requestCode, responseCode, R.string.signin_other_error);
                }
                break;
        }
        super.onActivityResult(requestCode, responseCode, intent);
    }

    void switchToScreen(int screenId) {
        // make the requested screen visible; hide all others.
       /* for (int id : SCREENS) {
            findViewById(id).setVisibility(screenId == id ? View.VISIBLE : View.GONE);
        }*/
        mCurScreen = screenId;

        // should we show the invitation popup?
    /*    boolean showInvPopup;
        if (mIncomingInvitationId == null) {
            // no invitation, so no popup
            showInvPopup = false;
        } else if (mMultiplayer) {
            // if in multiplayer, only show invitation on main screen
            showInvPopup = (mCurScreen == R.id.screen_main);
        } else {
            // single-player: show on main screen and gameplay screen
            showInvPopup = (mCurScreen == R.id.screen_main || mCurScreen == R.id.screen_game);
        }
        findViewById(R.id.invitation_popup).setVisibility(showInvPopup ? View.VISIBLE : View.GONE);*/
    }

    private void game_start() {
        setContentView(R.layout.my_game_oddmanout);

        bt1 = (TextView) findViewById(R.id.bt1);
        bt2 = (TextView) findViewById(R.id.bt2);
        bt3 = (TextView) findViewById(R.id.bt3);
        bt4 = (TextView) findViewById(R.id.bt4);
        bt5 = (TextView) findViewById(R.id.bt5);
        bt6 = (TextView) findViewById(R.id.bt6);

        bts1 = (TextView) findViewById(R.id.bts1);
        bts2 = (TextView) findViewById(R.id.bts2);
        bts3 = (TextView) findViewById(R.id.bts3);
        bts4 = (TextView) findViewById(R.id.bts4);
        score = (TextView) findViewById(R.id.score);
        sixcat = (RelativeLayout) findViewById(R.id.sixcat);
        fourcat = (RelativeLayout) findViewById(R.id.fourcat);

        switchToScreen(R.layout.my_game_oddmanout);
        initialize();
        bt1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bt1.getText().toString();
                    verify(ts, "bt1");


                }

                return true;

            }
        });
        bt2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bt2.getText().toString();
                    verify(ts, "bt2");


                }

                return true;

            }
        });
        bt3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bt3.getText().toString();
                    verify(ts, "bt3");


                }

                return true;

            }
        });
        bt4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bt4.getText().toString();
                    verify(ts, "bt4");


                }

                return true;

            }
        });
        bt5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bt5.getText().toString();
                    verify(ts, "bt5");


                }

                return true;

            }
        });
        bt6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bt6.getText().toString();
                    verify(ts, "bt6");


                }

                return true;

            }
        });

        bts1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bts1.getText().toString();
                    verify(ts, "bts1");


                }

                return true;

            }
        });


        bts2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bts2.getText().toString();
                    verify(ts, "bts2");


                }

                return true;

            }
        });
        bts3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bts3.getText().toString();
                    verify(ts, "bts3");


                }

                return true;

            }
        });
        bts4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String ts = bts4.getText().toString();
                    verify(ts, "bts4");


                }

                return true;

            }
        });
    }

    private void initialize() {

        Cursor c1 = dbs.rawQuery("select * from newmaintable where gameid='" + gameid + "' and isfinish='0'", null);
        c1.moveToFirst();
        if (c1.getCount() != 0) {
            u_id = c1.getInt(c1.getColumnIndexOrThrow("id"));
            questionid = c1.getInt(c1.getColumnIndexOrThrow("questionid"));
            question = c1.getString(c1.getColumnIndexOrThrow("question"));
            answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
            int clue = c1.getInt(c1.getColumnIndexOrThrow("clue"));
            int playtime = c1.getInt(c1.getColumnIndexOrThrow("playtime"));
            String tfoption = question;
            String[] first = tfoption.split(",");
            int letter_type = first.length;
            // Toast.makeText(Multiplayer_Odd_man_out.this, ""+clue, Toast.LENGTH_SHORT).show();


            if (letter_type == 1) {
                bt1.setVisibility(View.VISIBLE);
                bt1.setText(question);

            } else if (letter_type == 2) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
            } else if (letter_type == 3) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
                bt3.setText(letters3);
            } else if (letter_type == 4) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                sixcat.setVisibility(View.GONE);
                fourcat.setVisibility(View.VISIBLE);
                bts1.setVisibility(View.VISIBLE);
                bts2.setVisibility(View.VISIBLE);
                bts3.setVisibility(View.VISIBLE);
                bts4.setVisibility(View.VISIBLE);
                bts1.setText(letters1);
                bts2.setText(letters2);
                bts3.setText(letters3);
                bts4.setText(letters4);
                q_type = 4;
                if (letters1.equals(answer)) {
                    ans_position = 1;
                } else if (letters2.equals(answer)) {
                    ans_position = 2;
                } else if (letters3.equals(answer)) {
                    ans_position = 3;
                } else if (letters4.equals(answer)) {
                    ans_position = 4;
                }
            } else if (letter_type == 5) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt4.setVisibility(View.VISIBLE);
                bt5.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
                bt3.setText(letters3);
                bt4.setText(letters4);
                bt5.setText(letters5);
            } else if (letter_type == 6) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                final String letters6 = tokenizerw.nextToken().trim();
                sixcat.setVisibility(View.VISIBLE);
                fourcat.setVisibility(View.GONE);
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt4.setVisibility(View.VISIBLE);
                bt5.setVisibility(View.VISIBLE);
                bt6.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
                bt3.setText(letters3);
                bt4.setText(letters4);
                bt5.setText(letters5);
                bt6.setText(letters6);
                q_type = 6;
                if (letters1.equals(answer)) {
                    ans_position = 1;
                } else if (letters2.equals(answer)) {
                    ans_position = 2;
                } else if (letters3.equals(answer)) {
                    ans_position = 3;
                } else if (letters4.equals(answer)) {
                    ans_position = 4;
                } else if (letters5.equals(answer)) {
                    ans_position = 5;
                } else if (letters6.equals(answer)) {
                    ans_position = 6;
                }
            }


        }
    }

    // Leave the room.
    void leaveRoom() {
        score_s = 2;
        Log.d(TAG, "Leaving room.");
        // mSecondsLeft = 0;
        stopKeepingScreenOn();
        if (mRoomId != null) {
            Games.RealTimeMultiplayer.leave(mGoogleApiClient, this, mRoomId);
            mRoomId = null;
            // switchToScreen(R.id.screen_wait);
            exitgame();
            Toast.makeText(My_Multiplayer.this, "PL Loading", Toast.LENGTH_SHORT).show();
        } else {
            //  switchToMainScreen();
            exitgame();
        }
    }

    // Clears the flag that keeps the screen on.
    void stopKeepingScreenOn() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onConnected(@Nullable Bundle connectionHint) {
        Log.d(TAG, "onConnected() called. Sign in successful!");

        Log.d(TAG, "Sign-in succeeded.");

        // register listener so we are notified if we receive an invitation to play
        // while we are in the game
        Games.Invitations.registerInvitationListener(mGoogleApiClient, this);

        if (connectionHint != null) {
            Log.d(TAG, "onConnected: connection hint provided. Checking for invite.");
            Invitation inv = connectionHint
                    .getParcelable(com.google.android.gms.games.multiplayer.Multiplayer.EXTRA_INVITATION);
            if (inv != null && inv.getInvitationId() != null) {
                // retrieve and cache the invitation ID
                Log.d(TAG, "onConnected: connection hint has a room invite!");
                acceptInviteToRoom(inv.getInvitationId());
                return;
            }
        }
        // switchToMainScreen();
    }

    // Accept the given invitation.
    void acceptInviteToRoom(String invId) {
        // accept the invitation
        Log.d(TAG, "Accepting invitation: " + invId);
        RoomConfig.Builder roomConfigBuilder = RoomConfig.builder(this);
        roomConfigBuilder.setInvitationIdToAccept(invId)
                .setMessageReceivedListener(this)
                .setRoomStatusUpdateListener(this);
        // switchToScreen(R.id.screen_wait);
        keepScreenOn();
        //  resetGameVars();
        Games.RealTimeMultiplayer.join(mGoogleApiClient, roomConfigBuilder.build());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended() called. Trying to reconnect.");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed() called, result: " + connectionResult);

        if (mResolvingConnectionFailure) {
            Log.d(TAG, "onConnectionFailed() ignoring connection failure; already resolving.");
            return;
        }

        if (mSignInClicked || mAutoStartSignInFlow) {
            mAutoStartSignInFlow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = BaseGameUtils.resolveConnectionFailure(this, mGoogleApiClient,
                    connectionResult, RC_SIGN_IN, getString(R.string.signin_other_error));
        }
        exitgame();
    }

    // Activity just got to the foreground. We switch to the wait screen because we will now
    // go through the sign-in flow (remember that, yes, every time the Activity comes back to the
    // foreground we go through the sign-in flow -- but if the user is already authenticated,
    // this flow simply succeeds and is imperceptible).
    @Override
    public void onStart() {
        // switchToScreen(R.id.screen_wait);
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Log.w(TAG,
                    "GameHelper: client was already connected on onStart()");
        } else {
            Log.d(TAG, "Connecting client.");
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    public void onInvitationReceived(Invitation invitation) {

    }

    @Override
    public void onInvitationRemoved(String s) {

    }

    // Activity is going to the background. We have to leave the current room.
    @Override
    public void onStop() {
        Log.d(TAG, "**** got onStop");

        // if we're in a room, leave it.
        leaveRoom();

        // stop trying to keep the screen on
        stopKeepingScreenOn();

        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
            setContentView(R.layout.activity_my__multiplayer);
        } else {
            //switchToScreen(R.id.screen_wait);
            Toast.makeText(My_Multiplayer.this, "OnS Loading....... ", Toast.LENGTH_SHORT).show();
        }
        super.onStop();
    }

    // Handle back key to make sure we cleanly leave a game if we are in the middle of one
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent e) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.out.println("===========================mCurScreen " + mCurScreen + " R.layout.activity_my__multiplayer" + R.layout.my_game_oddmanout + "R.layout.activity_my__multiplayer" + R.layout.activity_my__multiplayer);
            if (mCurScreen == R.layout.my_game_oddmanout) {
                leaveRoom();
            } else if (mCurScreen == R.layout.activity_my__multiplayer) {
                finish();
                Intent i = new Intent(My_Multiplayer.this, MainActivity.class);
                startActivity(i);
            }


            return true;
        }

        return super.onKeyDown(keyCode, e);
    }

    public void verify(String ans, String button) {
        play_after=1;
        Cursor cs = dbs.rawQuery("select * from newmaintable where answer LIKE'" + ans + "'and isfinish='0'and questionid=" + questionid + " and gameid=" + gameid + "", null);
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            Toast.makeText(My_Multiplayer.this, "You Won The Match and You Got 50 Points", Toast.LENGTH_SHORT).show();
            changecolour_r(button);
            score_s = 1;
            mMultiplayer = true;
            broadcastScore(true);
            int a=sp.getInt(My_Multiplayer.this, "muliplay_score") + 100;
            sp.putInt(My_Multiplayer.this, "muliplay_score", a);

        } else {
            Toast.makeText(My_Multiplayer.this, "You Loose The Match and You Loose 50 Points", Toast.LENGTH_SHORT).show();
            changecolour_w(button);
            score_s = 0;
            mMultiplayer = true;
            broadcastScore(false);

        }
    }


    private void changecolour_r(String button) {

        if (button.equals("bts1")) {
            bts1.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts2")) {
            bts2.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts3")) {
            bts3.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts4")) {
            bts4.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt1")) {
            bt1.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt2")) {
            bt2.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt3")) {
            bt3.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt4")) {
            bt4.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt5")) {
            bt5.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt6")) {
            bt6.setBackgroundResource(R.color.rightans);
        }
        bts1.setEnabled(false);
        bts2.setEnabled(false);
        bts3.setEnabled(false);
        bts4.setEnabled(false);
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
        bt5.setEnabled(false);
        bt6.setEnabled(false);
    }

    private void changecolour_w(String button) {

        if (button.equals("bts1")) {
            bts1.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts2")) {
            bts2.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts3")) {
            bts3.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts4")) {
            bts4.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt1")) {
            bt1.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt2")) {
            bt2.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt3")) {
            bt3.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt4")) {
            bt4.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt5")) {
            bt5.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt6")) {
            bt6.setBackgroundResource(R.color.worngans);
        }
        bts1.setEnabled(false);
        bts2.setEnabled(false);
        bts3.setEnabled(false);
        bts4.setEnabled(false);
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
        bt5.setEnabled(false);
        bt6.setEnabled(false);
    }

}
