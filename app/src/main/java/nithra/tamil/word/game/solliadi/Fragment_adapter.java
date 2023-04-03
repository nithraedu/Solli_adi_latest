package nithra.tamil.word.game.solliadi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_adapter extends FragmentPagerAdapter {

    final Context contexts;
    View as;

    public Fragment_adapter(Context context, FragmentManager fm) {
        super(fm);
        this.contexts = context;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View st;
        st = inflater.inflate(R.layout.fragment_layout, container, false);

        return st;

    }

    public Fragment getItem(int index) {

        switch (index) {

            case 0:
                return new Daily_reward();
            case 1:
                return new Weekly_reward();
            case 2:
                return new Monthly_reward();

        }

        return null;
    }

    public int getCount() {

        return 3;
    }

    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "Daily";
            case 1:
                return "Weekly";
            case 2:
                return "Monthly";

        }
        return null;
    }
}
