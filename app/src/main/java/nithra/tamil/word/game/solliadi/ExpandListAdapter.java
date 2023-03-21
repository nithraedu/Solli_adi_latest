package nithra.tamil.word.game.solliadi;

/**
 * Created by NITHRA-1 on 11/11/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ExpandListAdapter extends BaseExpandableListAdapter {

    String[] first;
    String[] firsta;
    SQLiteDatabase exdb;
    SharedPreference sps = new SharedPreference();
    private final Context context;
    private final ArrayList<Group> groups;

    public ExpandListAdapter(Context context, ArrayList<Group> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> chList = groups.get(groupPosition)
                .getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Child child = (Child) getChild(groupPosition,
                childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_list, null);
        }
        TextView game_name = (TextView) convertView
                .findViewById(R.id.gamename);
        TextView gamedate = (TextView) convertView
                .findViewById(R.id.date);
        TextView gamefinish = (TextView) convertView
                .findViewById(R.id.play);

        RelativeLayout list1 = (RelativeLayout) convertView.findViewById(R.id.list1);
        String tfoption = child.getName();
        first = tfoption.split(",");

        String tfoptiona = first[0];
        firsta = tfoptiona.split("-");
        gamedate.setText("" + firsta[2] + "-" + firsta[1] + "-" + firsta[0]);

        if (first[1].equals("1")) {
            list1.setBackgroundResource(R.color.dark_blue);
            game_name.setText("படம் பார்த்து கண்டுபிடி");
        } else if (first[1].equals("2")) {
            list1.setBackgroundResource(R.color.red);
            game_name.setText("குறிப்பு மூலம் கண்டுபிடி");
        } else if (first[1].equals("3")) {
            list1.setBackgroundResource(R.color.dark_yellow);
            game_name.setText("சொல்லுக்குள் சொல்");
        } else if (first[1].equals("4")) {
            list1.setBackgroundResource(R.color.background_floating_material_dark);
            game_name.setText("சொல் விளையாட்டு");
        }

        if (first[2].equals("1")) {
            gamefinish.setBackgroundResource(R.drawable.tick_background);
        } else {
            gamefinish.setBackgroundResource(R.drawable.yellow_question);
        }
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tfoption = child.getName();
                String[] ans = tfoption.split(",");

                if (ans[2].equals("0")) {
                    if (ans[1].equals("1")) {
                        ((Activity) context).finish();
                        sps.putString(context, "Exp_list", "on");
                        sps.putString(context, "" + first[0], "yes");
                        sps.putString(context, "yes_reward", "no");
                        Intent intent = new Intent(context, Picture_Game_Hard.class);
                        intent.putExtra("datee", ans[0]);
                        context.startActivity(intent);

                    } else if (ans[1].equals("2")) {
                        ((Activity) context).finish();
                        sps.putString(context, "Exp_list", "on");
                        sps.putString(context, "" + first[0], "yes");
                        sps.putString(context, "yes_reward", "no");
                        Intent intent = new Intent(context, Clue_Game_Hard.class);
                        intent.putExtra("datee", ans[0]);
                        context.startActivity(intent);
                    } else if (ans[1].equals("3")) {
                        ((Activity) context).finish();
                        sps.putString(context, "Exp_list", "on");
                        sps.putString(context, "" + first[0], "yes");
                        sps.putString(context, "yes_reward", "no");
                        Intent intent = new Intent(context, Solukul_Sol.class);
                        intent.putExtra("datee", ans[0]);
                        context.startActivity(intent);

                    } else if (ans[1].equals("4")) {
                        ((Activity) context).finish();
                        sps.putString(context, "Exp_list", "on");
                        sps.putString(context, "" + first[0], "yes");
                        sps.putString(context, "yes_reward", "no");
                        Intent intent = new Intent(context, Word_Game_Hard.class);
                        intent.putExtra("datee", ans[0]);
                        context.startActivity(intent);
                    }
                } else {
                    if (ans[1].equals("1")) {
                        Toast.makeText(context, "" + firsta[2] + "-" + firsta[1] + "-" + firsta[0] + " படம் பார்த்து கண்டுபிடி முடிக்கப்பட்டது", Toast.LENGTH_SHORT).show();
                    } else if (ans[1].equals("2")) {
                        Toast.makeText(context, "" + firsta[2] + "-" + firsta[1] + "-" + firsta[0] + " குறிப்பு மூலம் கண்டுபிடி முடிக்கப்பட்டது", Toast.LENGTH_SHORT).show();
                    } else if (ans[1].equals("3")) {
                        Toast.makeText(context, "" + firsta[2] + "-" + firsta[1] + "-" + firsta[0] + " சொல்லுக்குள் சொல் முடிக்கப்பட்டது", Toast.LENGTH_SHORT).show();
                    } else if (ans[1].equals("4")) {
                        Toast.makeText(context, "" + firsta[2] + "-" + firsta[1] + "-" + firsta[0] + " சொல் விளையாட்டு முடிக்கப்பட்டது", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> chList = groups.get(groupPosition)
                .getItems();

        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.parent_list, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.textView1);
        //  listTitleTextView.setTypeface(null, Typeface.BOLD);


        String tfoption = group.getName();
        String[] lastdate = tfoption.split("-");

        listTitleTextView.setText("" + lastdate[2] + "-" + lastdate[1] + "-" + lastdate[0]);


        System.out.println("%%%%%% Parent Data" + tfoption);
       /* if (isExpanded) {
            imageView.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
        } else {
            imageView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        }*/
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}