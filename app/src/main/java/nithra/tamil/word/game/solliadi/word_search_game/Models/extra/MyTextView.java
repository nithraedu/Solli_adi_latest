package nithra.tamil.word.game.solliadi.word_search_game.Models.extra;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import nithra.tamil.word.game.solliadi.SharedPreference;

/**
 * Created by NITHRA-G7 on 5/4/2017.
 */

public class MyTextView extends TextView {

    SharedPreference sp=new SharedPreference();


    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
       /* if(sp.getString(getContext(),"Fonts").equals("")){

            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "enbold.ttf");
            setTypeface(tf);
        }else{

            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), sp.getString(getContext(),"Fonts"));
            setTypeface(tf);
        }*/

        //Typeface tf = Typeface.createFromAsset(getContext().getAssets(), sp.getString(getContext(),"Fonts"));
        ///setTypeface(tf);
    }

}
