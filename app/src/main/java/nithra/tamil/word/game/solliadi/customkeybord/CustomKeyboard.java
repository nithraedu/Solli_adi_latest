/**
 * Copyright 2013 Maarten Pennings
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * If you use this software in a product, an acknowledgment in the product
 * documentation would be appreciated but is not required.
 */

package nithra.tamil.word.game.solliadi.customkeybord;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.List;

/**
 * When an activity hosts a keyboardView, this class allows several EditText's to register for it.
 *
 * @author Maarten Pennings
 * @date 2012 December 23
 */
class CustomKeyboard {

    /**
     * A link to the KeyboardView that is used to render this CustomKeyboard1.
     */
    private final KeyboardView mKeyboardView;
    /**
     * A link to the activity that hosts the {@link #mKeyboardView}.
     */
    private final Activity mHostActivity;
    /**
     * The key (code) handler.
     */

    String str = "";
    int vall = 0;
    private final OnKeyboardActionListener mOnKeyboardActionListener = new OnKeyboardActionListener() {

        public final static int CodeDelete = -5; // Keyboard.KEYCODE_DELETE

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            // NOTE We can say '<Key android:codes="49,50" ... >' in the xml file; all codes come in keyCodes, the first in this list in primaryCode
            // Get the EditText and its Editable
            str = "";
            vall = 0;
            View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
            if (focusCurrent == null || focusCurrent.getClass() != AppCompatEditText.class) return;
            AppCompatEditText edittext = (AppCompatEditText) focusCurrent;
            Editable editable = edittext.getText();
            int start = edittext.getSelectionStart();
            // Apply the key to the edittext
            if (primaryCode == CodeDelete) {
                if (editable != null && start > 0) {
                    editable.delete(start - 1, start);
                    /*  if(edittext.getText().toString().length() == 0){*/
                    vall = 1;
                    num_channge1();
                    /*   }*/
                } else {
                    vall = 1;
                    num_channge1();
                }
            } else if (primaryCode == 32) {
                editable.insert(start, Character.toString((char) primaryCode));
            } else if (primaryCode == 66) {
                hideCustomKeyboard();
                /*Noti_Search.done(mHostActivity);*/
            } else if (primaryCode == -55000) {
                num_channge();
            } else if (primaryCode == -55001) {
                num_channge1();
            } else if (primaryCode == 46 || primaryCode == 44 || primaryCode == 48 || primaryCode == 49 || primaryCode == 50 || primaryCode == 126 || primaryCode == 96 || primaryCode == 33
                    || primaryCode == 64 || primaryCode == 35 || primaryCode == 36 || primaryCode == 51 || primaryCode == 52 || primaryCode == 53 || primaryCode == 37 || primaryCode == 94
                    || primaryCode == 38 || primaryCode == 40 || primaryCode == 41 || primaryCode == 63 || primaryCode == 54 || primaryCode == 55 || primaryCode == 56 || primaryCode == 43
                    || primaryCode == 45 || primaryCode == 42 || primaryCode == 47 || primaryCode == 92 || primaryCode == 124 || primaryCode == 57 || primaryCode == 95 || primaryCode == 91
                    || primaryCode == 93 || primaryCode == 123 || primaryCode == 125 || primaryCode == 34 || primaryCode == 39) {
                editable.insert(start, Character.toString((char) primaryCode));
            } else { // insert character
                if (primaryCode == -10001 || primaryCode == -10002 || primaryCode == -10003 || primaryCode == -10004 || primaryCode == -10005 || primaryCode == -10006 || primaryCode == -10007 || primaryCode == -10008
                        || primaryCode == -10009 || primaryCode == -10010 || primaryCode == -10011 || primaryCode == -10012 || primaryCode == -10013 || primaryCode == -10014 || primaryCode == -10041
                        || primaryCode == -10042 || primaryCode == -10043 || primaryCode == -10044 || primaryCode == -10045 || primaryCode == -10046 || primaryCode == -10047 || primaryCode == -10048 || primaryCode == -10049
                        || primaryCode == -10050 || primaryCode == -10051 || primaryCode == -10052 || primaryCode == -10053 || primaryCode == -10054 || primaryCode == -10055 || primaryCode == -10056 || primaryCode == -10057
                        || primaryCode == -10058 || primaryCode == -10059 || primaryCode == -10060 || primaryCode == -10061 || primaryCode == -10062 || primaryCode == -10063 || primaryCode == -10402) {
                    edittext.getEditableText().insert(start, word_return(primaryCode));
                } else {
                    edittext.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    edittext.getEditableText().insert(start - 1, word_return(primaryCode));
                    vall = 1;
                    num_channge1();
                }

            }

        }

        @Override
        public void onPress(int primaryCode) {
            if (vall == 1) {
                vall = 0;
                num_channge1();

            } else {
                letter_change(primaryCode);
            }

        }

        @Override
        public void onRelease(int primaryCode) {

            if (primaryCode == -55000) {
                num_channge();
            } else if (primaryCode == -55001) {
                num_channge1();
            } else {
                if (vall == 1) {
                    vall = 0;
                    num_channge1();
                } else {
                    letter_change(primaryCode);
                }


            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeUp() {
        }
    };

    /**
     * Create a custom keyboard, that uses the KeyboardView (with resource id <var>viewid</var>) of the <var>host</var> activity,
     * and load the keyboard layout from xml file <var>layoutid</var> (see {@link Keyboard} for description).
     * Note that the <var>host</var> activity must have a <var>KeyboardView</var> in its layout (typically aligned with the bottom of the activity).
     * Note that the keyboard layout xml file may include key codes forword navigation; see the constants in this class forword their values.
     * Note that to enable EditText's to use this custom keyboard, call the {@link #registerEditText(int)}.
     *
     * @param host     The hosting activity.
     * @param viewid   The id of the KeyboardView.
     * @param layoutid The id of the xml file containing the keyboard layout.
     */
    public CustomKeyboard(Activity host, int viewid, int layoutid) {
        mHostActivity = host;
        mKeyboardView = (KeyboardView) mHostActivity.findViewById(viewid);
        mKeyboardView.setKeyboard(new Keyboard(mHostActivity, layoutid));
        mKeyboardView.setPreviewEnabled(false); // NOTE Do not show the preview balloons
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);
        // Hide the standard keyboard initially
        mHostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    /**
     * Returns whether the CustomKeyboard1 is visible.
     */
    public boolean isCustomKeyboardVisible() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    /**
     * Make the CustomKeyboard1 visible, and hide the system keyboard for view v.
     */
    public void showCustomKeyboard(View v) {
        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setEnabled(true);
        if (v != null)
            ((InputMethodManager) mHostActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * Make the CustomKeyboard1 invisible.
     */
    public void hideCustomKeyboard() {
        mKeyboardView.setVisibility(View.GONE);
        mKeyboardView.setEnabled(false);
    }

    /**
     * Register <var>EditText<var> with resource id <var>resid</var> (on the hosting activity) for using this custom keyboard.
     *
     * @param resid The resource id of the EditText that registers to the custom keyboard.
     */
    public void registerEditText(int resid) {
        // Find the EditText 'resid'
        AppCompatEditText edittext = (AppCompatEditText) mHostActivity.findViewById(resid);
        // Make the custom keyboard appear
        edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
            // NOTE By setting the on focus listener, we can show the custom keyboard when the edit box gets focus, but also hide it when the edit box loses focus
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) showCustomKeyboard(v);
                else hideCustomKeyboard();
            }
        });
        edittext.setOnClickListener(new OnClickListener() {
            // NOTE By setting the on click listener, we can show the custom keyboard again, by tapping on an edit box that already had focus (but that had the keyboard hidden).
            @Override
            public void onClick(View v) {
                showCustomKeyboard(v);
            }
        });
        // Disable standard keyboard hard way
        // NOTE There is also an easy way: 'edittext.setInputType(InputType.TYPE_NULL)' (but you will not have a cursor, and no 'edittext.setCursorVisible(true)' doesn't work )
        edittext.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText edittext = (EditText) v;
                int inType = edittext.getInputType();       // Backup the input type
                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                edittext.onTouchEvent(event);               // Call native handler
                edittext.setInputType(inType);              // Restore input type
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) mHostActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return false; // Consume touch event
            }
        });
        // Disable spell check (hex strings look like words to Android)
        edittext.setInputType(edittext.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }


    public String word_return(int val) {

        if (val == -10001) {
            return "அ";
        } else if (val == -10002) {
            return "ஆ";
        } else if (val == -10003) {
            return "இ";
        } else if (val == -10004) {
            return "ஈ";
        } else if (val == -10005) {
            return "உ";
        } else if (val == -10006) {
            return "ஊ";
        } else if (val == -10007) {
            return "எ";
        } else if (val == -10008) {
            return "ஏ";
        } else if (val == -10009) {
            return "ஐ";
        } else if (val == -10010) {
            return "ஒ";
        } else if (val == -10011) {
            return "ஓ";
        } else if (val == -10012) {
            return "ஔ";
        } else if (val == -10013) {
            return "ஃ";
        } else if (val == -10014) {
            return "ஸ்ரீ";
        } else if (val == -10041) {
            return "க";
        } else if (val == -10042) {
            return "ங";
        } else if (val == -10043) {
            return "ச";
        } else if (val == -10044) {
            return "ஞ";
        } else if (val == -10045) {
            return "ட";
        } else if (val == -10046) {
            return "ண";
        } else if (val == -10047) {
            return "த";
        } else if (val == -10048) {
            return "ந";
        } else if (val == -10049) {
            return "ப";
        } else if (val == -10050) {
            return "ம";
        } else if (val == -10051) {
            return "ய";
        } else if (val == -10052) {
            return "ர";
        } else if (val == -10053) {
            return "ல";
        } else if (val == -10054) {
            return "வ";
        } else if (val == -10055) {
            return "ழ";
        } else if (val == -10056) {
            return "ள";
        } else if (val == -10057) {
            return "ற";
        } else if (val == -10058) {
            return "ன";
        } else if (val == -10059) {
            return "ஷ";
        } else if (val == -10060) {
            return "ஸ";
        } else if (val == -10061) {
            return "ஜ";
        } else if (val == -10062) {
            return "ஹ";
        } else if (val == -10063) {
            return "க்ஷ";
        } else if (val == -10071) {
            return "க்";
        } else if (val == -10072) {
            return "கா";
        } else if (val == -10073) {
            return "கி";
        } else if (val == -10074) {
            return "கீ";
        } else if (val == -10075) {
            return "கு";
        } else if (val == -10076) {
            return "கூ";
        } else if (val == -10077) {
            return "கெ";
        } else if (val == -10078) {
            return "கே";
        } else if (val == -10079) {
            return "கை";
        } else if (val == -10080) {
            return "கொ";
        } else if (val == -10081) {
            return "கோ";
        } else if (val == -10082) {
            return "கௌ";
        } else if (val == -10083) {
            return "ங்";
        } else if (val == -10084) {
            return "ஙா";
        } else if (val == -10085) {
            return "ஙி";
        } else if (val == -10086) {
            return "ஙீ";
        } else if (val == -10087) {
            return "ஙு";
        } else if (val == -10088) {
            return "ஙூ";
        } else if (val == -10089) {
            return "ஙெ";
        } else if (val == -10090) {
            return "ஙே";
        } else if (val == -10091) {
            return "ஙை";
        } else if (val == -10092) {
            return "ஙொ";
        } else if (val == -10093) {
            return "ஙோ";
        } else if (val == -10094) {
            return "ஙௌ";
        } else if (val == -10095) {
            return "ச்";
        } else if (val == -10096) {
            return "சா";
        } else if (val == -10097) {
            return "சி";
        } else if (val == -10098) {
            return "சீ";
        } else if (val == -10099) {
            return "சு";
        } else if (val == -10100) {
            return "சூ";
        } else if (val == -10101) {
            return "செ";
        } else if (val == -10102) {
            return "சே";
        } else if (val == -10103) {
            return "சை";
        } else if (val == -10104) {
            return "சொ";
        } else if (val == -10105) {
            return "சோ";
        } else if (val == -10106) {
            return "சௌ";
        } else if (val == -10107) {
            return "ஞ்";
        } else if (val == -10108) {
            return "ஞா";
        } else if (val == -10109) {
            return "ஞி";
        } else if (val == -10110) {
            return "ஞீ";
        } else if (val == -10111) {
            return "ஞு";
        } else if (val == -10112) {
            return "ஞூ";
        } else if (val == -10113) {
            return "ஞெ";
        } else if (val == -10114) {
            return "ஞே";
        } else if (val == -10115) {
            return "ஞை";
        } else if (val == -10116) {
            return "ஞொ";
        } else if (val == -10117) {
            return "ஞோ";
        } else if (val == -10118) {
            return "ஞௌ";
        } else if (val == -10119) {
            return "ட்";
        } else if (val == -10120) {
            return "டா";
        } else if (val == -10121) {
            return "டி";
        } else if (val == -10122) {
            return "டீ";
        } else if (val == -10123) {
            return "டு";
        } else if (val == -10124) {
            return "டூ";
        } else if (val == -10125) {
            return "டெ";
        } else if (val == -10126) {
            return "டே";
        } else if (val == -10127) {
            return "டை";
        } else if (val == -10128) {
            return "டொ";
        } else if (val == -10129) {
            return "டோ";
        } else if (val == -10130) {
            return "டௌ";
        } else if (val == -10131) {
            return "ண்";
        } else if (val == -10132) {
            return "ணா";
        } else if (val == -10133) {
            return "ணி";
        } else if (val == -10134) {
            return "ணீ";
        } else if (val == -10135) {
            return "ணு";
        } else if (val == -10136) {
            return "ணூ";
        } else if (val == -10137) {
            return "ணெ";
        } else if (val == -10138) {
            return "ணே";
        } else if (val == -10139) {
            return "ணை";
        } else if (val == -10140) {
            return "ணொ";
        } else if (val == -10141) {
            return "ணோ";
        } else if (val == -10142) {
            return "ணௌ";// 1. last verified
        } else if (val == -10143) {
            return "த்";
        } else if (val == -10144) {
            return "தா";
        } else if (val == -10145) {
            return "தி";
        } else if (val == -10146) {
            return "தீ";
        } else if (val == -10147) {
            return "து";
        } else if (val == -10148) {
            return "தூ";
        } else if (val == -10149) {
            return "தெ";
        } else if (val == -10150) {
            return "தே";
        } else if (val == -10151) {
            return "தை";
        } else if (val == -10152) {
            return "தொ";
        } else if (val == -10153) {
            return "தோ";
        } else if (val == -10154) {
            return "தௌ";// 2. last verified
        } else if (val == -10155) {
            return "ந்";
        } else if (val == -10156) {
            return "நா";
        } else if (val == -10157) {
            return "நி";
        } else if (val == -10158) {
            return "நீ";
        } else if (val == -10159) {
            return "நு";
        } else if (val == -10160) {
            return "நூ";
        } else if (val == -10161) {
            return "நெ";
        } else if (val == -10162) {
            return "நே";
        } else if (val == -10163) {
            return "நை";
        } else if (val == -10164) {
            return "நொ";
        } else if (val == -10165) {
            return "நோ";
        } else if (val == -10166) {
            return "நௌ";// 3. last verified
        } else if (val == -10167) {
            return "ப்";
        } else if (val == -10168) {
            return "பா";
        } else if (val == -10169) {
            return "பி";
        } else if (val == -10170) {
            return "பீ";
        } else if (val == -10171) {
            return "பு";
        } else if (val == -10172) {
            return "பூ";
        } else if (val == -10173) {
            return "பெ";
        } else if (val == -10174) {
            return "பே";
        } else if (val == -10175) {
            return "பை";
        } else if (val == -10176) {
            return "பொ";
        } else if (val == -10177) {
            return "போ";
        } else if (val == -10178) {
            return "பௌ";// 4. last verified
        } else if (val == -10179) {
            return "ம்";
        } else if (val == -10180) {
            return "மா";
        } else if (val == -10181) {
            return "மி";
        } else if (val == -10182) {
            return "மீ";
        } else if (val == -10183) {
            return "மு";
        } else if (val == -10184) {
            return "மூ";
        } else if (val == -10185) {
            return "மெ";
        } else if (val == -10186) {
            return "மே";
        } else if (val == -10187) {
            return "மை";
        } else if (val == -10188) {
            return "மொ";
        } else if (val == -10189) {
            return "மோ";
        } else if (val == -10190) {
            return "மௌ";// 5. last verified
        } else if (val == -10191) {
            return "ய்";
        } else if (val == -10192) {
            return "யா";
        } else if (val == -10193) {
            return "யி";
        } else if (val == -10194) {
            return "யீ";
        } else if (val == -10195) {
            return "யு";
        } else if (val == -10196) {
            return "யூ";
        } else if (val == -10197) {
            return "யெ";
        } else if (val == -10198) {
            return "யே";
        } else if (val == -10199) {
            return "யை";
        } else if (val == -10200) {
            return "யொ";
        } else if (val == -10201) {
            return "யோ";
        } else if (val == -10202) {
            return "யௌ";// 6. last verified
        } else if (val == -10203) {
            return "ர்";
        } else if (val == -10204) {
            return "ரா";
        } else if (val == -10205) {
            return "ரி";
        } else if (val == -10206) {
            return "ரீ";
        } else if (val == -10207) {
            return "ரு";
        } else if (val == -10208) {
            return "ரூ";
        } else if (val == -10209) {
            return "ரெ";
        } else if (val == -10210) {
            return "ரே";
        } else if (val == -10211) {
            return "ரை";
        } else if (val == -10212) {
            return "ரொ";
        } else if (val == -10213) {
            return "ரோ";
        } else if (val == -10214) {
            return "ரௌ";// 7. last verified
        } else if (val == -10215) {
            return "ல்";
        } else if (val == -10216) {
            return "லா";
        } else if (val == -10217) {
            return "லி";
        } else if (val == -10218) {
            return "லீ";
        } else if (val == -10219) {
            return "லு";
        } else if (val == -10220) {
            return "லூ";
        } else if (val == -10221) {
            return "லெ";
        } else if (val == -10222) {
            return "லே";
        } else if (val == -10223) {
            return "லை";
        } else if (val == -10224) {
            return "லொ";
        } else if (val == -10225) {
            return "லோ";
        } else if (val == -10226) {
            return "லௌ";// 8. last verified
        } else if (val == -10227) {
            return "வ்";
        } else if (val == -10228) {
            return "வா";
        } else if (val == -10229) {
            return "வி";
        } else if (val == -10230) {
            return "வீ";
        } else if (val == -10231) {
            return "வு";
        } else if (val == -10232) {
            return "வூ";
        } else if (val == -10233) {
            return "வெ";
        } else if (val == -10234) {
            return "வே";
        } else if (val == -10235) {
            return "வை";
        } else if (val == -10236) {
            return "வொ";
        } else if (val == -10237) {
            return "வோ";
        } else if (val == -10238) {
            return "வௌ";// 9 last verified
        } else if (val == -10239) {
            return "ழ்";
        } else if (val == -10240) {
            return "ழா";
        } else if (val == -10241) {
            return "ழி";
        } else if (val == -10242) {
            return "ழீ";
        } else if (val == -10243) {
            return "ழு";
        } else if (val == -10244) {
            return "ழூ";
        } else if (val == -10245) {
            return "ழெ";
        } else if (val == -10246) {
            return "ழே";
        } else if (val == -10247) {
            return "ழை";
        } else if (val == -10248) {
            return "ழொ";
        } else if (val == -10249) {
            return "ழோ";
        } else if (val == -10250) {
            return "ழௌ";// 10. last verified
        } else if (val == -10251) {
            return "ள்";
        } else if (val == -10252) {
            return "ளா";
        } else if (val == -10253) {
            return "ளி";
        } else if (val == -10254) {
            return "ளீ";
        } else if (val == -10255) {
            return "ளு";
        } else if (val == -10256) {
            return "ளூ";
        } else if (val == -10257) {
            return "ளெ";
        } else if (val == -10258) {
            return "ளே";
        } else if (val == -10259) {
            return "ளை";
        } else if (val == -10260) {
            return "ளொ";
        } else if (val == -10261) {
            return "ளோ";
        } else if (val == -10262) {
            return "ளௌ";// 11. last verified
        } else if (val == -10263) {
            return "ற்";
        } else if (val == -10264) {
            return "றா";
        } else if (val == -10265) {
            return "றி";
        } else if (val == -10266) {
            return "றீ";
        } else if (val == -10267) {
            return "று";
        } else if (val == -10268) {
            return "றூ";
        } else if (val == -10269) {
            return "றெ";
        } else if (val == -10270) {
            return "றே";
        } else if (val == -10271) {
            return "றை";
        } else if (val == -10272) {
            return "றொ";
        } else if (val == -10273) {
            return "றோ";
        } else if (val == -10274) {
            return "றௌ";// 12. last verified
        } else if (val == -10275) {
            return "ன்";
        } else if (val == -10276) {
            return "னா";
        } else if (val == -10277) {
            return "னி";
        } else if (val == -10278) {
            return "னீ";
        } else if (val == -10279) {
            return "னு";
        } else if (val == -10280) {
            return "னூ";
        } else if (val == -10281) {
            return "னெ";
        } else if (val == -10282) {
            return "னே";
        } else if (val == -10283) {
            return "னை";
        } else if (val == -10284) {
            return "னொ";
        } else if (val == -10285) {
            return "னோ";
        } else if (val == -10286) {
            return "னௌ";// 13. last verified
        } else if (val == -10287) {
            return "ஜ்";
        } else if (val == -10288) {
            return "ஜா";
        } else if (val == -10289) {
            return "ஜி";
        } else if (val == -10290) {
            return "ஜீ";
        } else if (val == -10291) {
            return "ஜு";
        } else if (val == -10292) {
            return "ஜூ";
        } else if (val == -10293) {
            return "ஜெ";
        } else if (val == -10294) {
            return "ஜே";
        } else if (val == -10295) {
            return "ஜை";
        } else if (val == -10296) {
            return "ஜொ";
        } else if (val == -10297) {
            return "ஜோ";
        } else if (val == -10298) {
            return "ஜௌ";// 14. last verified
        } else if (val == -10299) {
            return "ஷ்";
        } else if (val == -10300) {
            return "ஷா";
        } else if (val == -10301) {
            return "ஷி";
        } else if (val == -10302) {
            return "ஷீ";
        } else if (val == -10303) {
            return "ஷு";
        } else if (val == -10304) {
            return "ஷூ";
        } else if (val == -10305) {
            return "ஷெ";
        } else if (val == -10306) {
            return "ஷே";
        } else if (val == -10307) {
            return "ஷை";
        } else if (val == -10308) {
            return "ஷொ";
        } else if (val == -10309) {
            return "ஷோ";
        } else if (val == -10310) {
            return "ஷௌ";// 15. last verified
        } else if (val == -10311) {
            return "ஸ்";
        } else if (val == -10312) {
            return "ஸா";
        } else if (val == -10313) {
            return "ஸி";
        } else if (val == -10314) {
            return "ஸீ";
        } else if (val == -10315) {
            return "ஸு";
        } else if (val == -10316) {
            return "ஸூ";
        } else if (val == -10317) {
            return "ஸெ";
        } else if (val == -10318) {
            return "ஸே";
        } else if (val == -10319) {
            return "ஸை";
        } else if (val == -10320) {
            return "ஸொ";
        } else if (val == -10321) {
            return "ஸோ";
        } else if (val == -10322) {
            return "ஸௌ";// 16. last verified
        } else if (val == -10323) {
            return "ஹ்";
        } else if (val == -10324) {
            return "ஹா";
        } else if (val == -10325) {
            return "ஹி";
        } else if (val == -10326) {
            return "ஹீ";
        } else if (val == -10327) {
            return "ஹு";
        } else if (val == -10328) {
            return "ஹூ";
        } else if (val == -10329) {
            return "ஹெ";
        } else if (val == -10330) {
            return "ஹே";
        } else if (val == -10331) {
            return "ஹை";
        } else if (val == -10332) {
            return "ஹொ";
        } else if (val == -10333) {
            return "ஹோ";
        } else if (val == -10334) {
            return "ஹௌ";// 17. last verified
        } else if (val == -10335) {
            return "க்ஷ்";
        } else if (val == -10336) {
            return "க்ஷா";
        } else if (val == -10337) {
            return "க்ஷி";
        } else if (val == -10338) {
            return "க்ஷீ";
        } else if (val == -10339) {
            return "க்ஷு";
        } else if (val == -10340) {
            return "க்ஷூ";
        } else if (val == -10341) {
            return "க்ஷெ";
        } else if (val == -10342) {
            return "க்ஷே";
        } else if (val == -10343) {
            return "க்ஷை";
        } else if (val == -10344) {
            return "க்ஷொ";
        } else if (val == -10345) {
            return "க்ஷோ";
        } else if (val == -10346) {
            return "க்ஷெ";// 18. last verified
        } else if (val == -10402) {
            return "ௐ";// 18. last verified
        }
        return "";
    }

    public void letter_change(int val) {

        if (val == -10041) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "க்";
            keys.get(0).codes[0] = -10071;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "கா";
            keys.get(1).codes[0] = -10072;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "கி";
            keys.get(2).codes[0] = -10073;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "கீ";
            keys.get(9).codes[0] = -10074;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "கு";
            keys.get(10).codes[0] = -10075;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "கூ";
            keys.get(11).codes[0] = -10076;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "கெ";
            keys.get(18).codes[0] = -10077;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "கே";
            keys.get(19).codes[0] = -10078;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "கை";
            keys.get(20).codes[0] = -10079;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "கொ";
            keys.get(27).codes[0] = -10080;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "கோ";
            keys.get(28).codes[0] = -10081;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "கௌ";
            keys.get(29).codes[0] = -10082;
        } else if (val == -10042) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ங்";
            keys.get(0).codes[0] = -10083;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ஙா";
            keys.get(1).codes[0] = -10084;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ஙி";
            keys.get(2).codes[0] = -10085;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ஙீ";
            keys.get(9).codes[0] = -10086;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ஙு";
            keys.get(10).codes[0] = -10087;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ஙூ";
            keys.get(11).codes[0] = -10088;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ஙெ";
            keys.get(18).codes[0] = -10089;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ஙே";
            keys.get(19).codes[0] = -10090;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ஙை";
            keys.get(20).codes[0] = -10091;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ஙொ";
            keys.get(27).codes[0] = -10092;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ஙோ";
            keys.get(28).codes[0] = -10093;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ஙௌ";
            keys.get(29).codes[0] = -10094;
        } else if (val == -10043) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ச்";
            keys.get(0).codes[0] = -10095;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "சா";
            keys.get(1).codes[0] = -10096;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "சி";
            keys.get(2).codes[0] = -10097;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "சீ";
            keys.get(9).codes[0] = -10098;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "சு";
            keys.get(10).codes[0] = -10099;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "சூ";
            keys.get(11).codes[0] = -10100;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "செ";
            keys.get(18).codes[0] = -10101;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "சே";
            keys.get(19).codes[0] = -10102;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "சை";
            keys.get(20).codes[0] = -10103;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "சொ";
            keys.get(27).codes[0] = -10104;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "சோ";
            keys.get(28).codes[0] = -10105;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "சௌ";
            keys.get(29).codes[0] = -10106;
        } else if (val == -10044) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ஞ்";
            keys.get(0).codes[0] = -10107;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ஞா";
            keys.get(1).codes[0] = -10108;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ஞி";
            keys.get(2).codes[0] = -10109;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ஞீ";
            keys.get(9).codes[0] = -10110;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ஞு";
            keys.get(10).codes[0] = -10111;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ஞூ";
            keys.get(11).codes[0] = -10112;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ஞெ";
            keys.get(18).codes[0] = -10113;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ஞே";
            keys.get(19).codes[0] = -10114;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ஞை";
            keys.get(20).codes[0] = -10115;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ஞொ";
            keys.get(27).codes[0] = -10116;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ஞோ";
            keys.get(28).codes[0] = -10117;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ஞௌ";
            keys.get(29).codes[0] = -10118;
        } else if (val == -10045) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ட்";
            keys.get(0).codes[0] = -10119;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "டா";
            keys.get(1).codes[0] = -10120;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "டி";
            keys.get(2).codes[0] = -10121;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "டீ";
            keys.get(9).codes[0] = -10122;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "டு";
            keys.get(10).codes[0] = -10123;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "டூ";
            keys.get(11).codes[0] = -10124;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "டெ";
            keys.get(18).codes[0] = -10125;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "டே";
            keys.get(19).codes[0] = -10126;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "டை";
            keys.get(20).codes[0] = -10127;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "டொ";
            keys.get(27).codes[0] = -10128;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "டோ";
            keys.get(28).codes[0] = -10129;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "டௌ";
            keys.get(29).codes[0] = -10130;
        } else if (val == -10046) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ண்";
            keys.get(0).codes[0] = -10131;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ணா";
            keys.get(1).codes[0] = -10132;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ணி";
            keys.get(2).codes[0] = -10133;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ணீ";
            keys.get(9).codes[0] = -10134;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ணு";
            keys.get(10).codes[0] = -10135;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ணூ";
            keys.get(11).codes[0] = -10136;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ணெ";
            keys.get(18).codes[0] = -10137;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ணே";
            keys.get(19).codes[0] = -10138;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ணை";
            keys.get(20).codes[0] = -10139;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ணொ";
            keys.get(27).codes[0] = -10140;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ணோ";
            keys.get(28).codes[0] = -10141;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ணௌ";
            keys.get(29).codes[0] = -10142;
        } else if (val == -10047) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "த்";
            keys.get(0).codes[0] = -10143;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "தா";
            keys.get(1).codes[0] = -10144;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "தி";
            keys.get(2).codes[0] = -10145;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "தீ";
            keys.get(9).codes[0] = -10146;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "து";
            keys.get(10).codes[0] = -10147;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "தூ";
            keys.get(11).codes[0] = -10148;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "தெ";
            keys.get(18).codes[0] = -10149;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "தே";
            keys.get(19).codes[0] = -10150;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "தை";
            keys.get(20).codes[0] = -10151;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "தொ";
            keys.get(27).codes[0] = -10152;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "தோ";
            keys.get(28).codes[0] = -10153;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "தௌ";
            keys.get(29).codes[0] = -10154;
        } else if (val == -10048) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ந்";
            keys.get(0).codes[0] = -10155;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "நா";
            keys.get(1).codes[0] = -10156;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "நி";
            keys.get(2).codes[0] = -10157;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "நீ";
            keys.get(9).codes[0] = -10158;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "நு";
            keys.get(10).codes[0] = -10159;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "நூ";
            keys.get(11).codes[0] = -10160;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "நெ";
            keys.get(18).codes[0] = -10161;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "நே";
            keys.get(19).codes[0] = -10162;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "நை";
            keys.get(20).codes[0] = -10163;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "நொ";
            keys.get(27).codes[0] = -10164;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "நோ";
            keys.get(28).codes[0] = -10165;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "நௌ";
            keys.get(29).codes[0] = -10166;
        } else if (val == -10049) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ப்";
            keys.get(0).codes[0] = -10167;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "பா";
            keys.get(1).codes[0] = -10168;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "பி";
            keys.get(2).codes[0] = -10169;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "பீ";
            keys.get(9).codes[0] = -10170;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "பு";
            keys.get(10).codes[0] = -10171;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "பூ";
            keys.get(11).codes[0] = -10172;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "பெ";
            keys.get(18).codes[0] = -10173;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "பே";
            keys.get(19).codes[0] = -10174;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "பை";
            keys.get(20).codes[0] = -10175;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "பொ";
            keys.get(27).codes[0] = -10176;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "போ";
            keys.get(28).codes[0] = -10177;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "பௌ";
            keys.get(29).codes[0] = -10178;
        } else if (val == -10050) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ம்";
            keys.get(0).codes[0] = -10179;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "மா";
            keys.get(1).codes[0] = -10180;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "மி";
            keys.get(2).codes[0] = -10181;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "மீ";
            keys.get(9).codes[0] = -10182;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "மு";
            keys.get(10).codes[0] = -10183;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "மூ";
            keys.get(11).codes[0] = -10184;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "மெ";
            keys.get(18).codes[0] = -10185;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "மே";
            keys.get(19).codes[0] = -10186;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "மை";
            keys.get(20).codes[0] = -10187;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "மொ";
            keys.get(27).codes[0] = -10188;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "மோ";
            keys.get(28).codes[0] = -10189;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "மௌ";
            keys.get(29).codes[0] = -10190;
        } else if (val == -10051) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ய்";
            keys.get(0).codes[0] = -10191;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "யா";
            keys.get(1).codes[0] = -10192;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "யி";
            keys.get(2).codes[0] = -10193;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "யீ";
            keys.get(9).codes[0] = -10194;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "யு";
            keys.get(10).codes[0] = -10195;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "யூ";
            keys.get(11).codes[0] = -10196;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "யெ";
            keys.get(18).codes[0] = -10197;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "யே";
            keys.get(19).codes[0] = -10198;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "யை";
            keys.get(20).codes[0] = -10199;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "யொ";
            keys.get(27).codes[0] = -10200;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "யோ";
            keys.get(28).codes[0] = -10201;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "யௌ";
            keys.get(29).codes[0] = -10202;
        } else if (val == -10052) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ர்";
            keys.get(0).codes[0] = -10203;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ரா";
            keys.get(1).codes[0] = -10204;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ரி";
            keys.get(2).codes[0] = -10205;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ரீ";
            keys.get(9).codes[0] = -10206;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ரு";
            keys.get(10).codes[0] = -10207;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ரூ";
            keys.get(11).codes[0] = -10208;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ரெ";
            keys.get(18).codes[0] = -10209;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ரே";
            keys.get(19).codes[0] = -10210;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ரை";
            keys.get(20).codes[0] = -10211;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ரொ";
            keys.get(27).codes[0] = -10212;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ரோ";
            keys.get(28).codes[0] = -10213;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ரௌ";
            keys.get(29).codes[0] = -10214;
        } else if (val == -10053) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ல்";
            keys.get(0).codes[0] = -10215;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "லா";
            keys.get(1).codes[0] = -10216;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "லி";
            keys.get(2).codes[0] = -10217;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "லீ";
            keys.get(9).codes[0] = -10218;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "லு";
            keys.get(10).codes[0] = -10219;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "லூ";
            keys.get(11).codes[0] = -10220;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "லெ";
            keys.get(18).codes[0] = -10221;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "லே";
            keys.get(19).codes[0] = -10222;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "லை";
            keys.get(20).codes[0] = -10223;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "லொ";
            keys.get(27).codes[0] = -10224;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "லோ";
            keys.get(28).codes[0] = -10225;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "லௌ";
            keys.get(29).codes[0] = -10226;
        } else if (val == -10054) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "வ்";
            keys.get(0).codes[0] = -10227;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "வா";
            keys.get(1).codes[0] = -10228;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "வி";
            keys.get(2).codes[0] = -10229;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "வீ";
            keys.get(9).codes[0] = -10230;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "வு";
            keys.get(10).codes[0] = -10231;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "வூ";
            keys.get(11).codes[0] = -10232;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "வெ";
            keys.get(18).codes[0] = -10233;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "வே";
            keys.get(19).codes[0] = -10234;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "வை";
            keys.get(20).codes[0] = -10235;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "வொ";
            keys.get(27).codes[0] = -10236;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "வோ";
            keys.get(28).codes[0] = -10237;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "வௌ";
            keys.get(29).codes[0] = -10238;
        } else if (val == -10055) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ழ்";
            keys.get(0).codes[0] = -10239;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ழா";
            keys.get(1).codes[0] = -10240;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ழி";
            keys.get(2).codes[0] = -10241;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ழீ";
            keys.get(9).codes[0] = -10242;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ழு";
            keys.get(10).codes[0] = -10243;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ழூ";
            keys.get(11).codes[0] = -10244;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ழெ";
            keys.get(18).codes[0] = -10245;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ழே";
            keys.get(19).codes[0] = -10246;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ழை";
            keys.get(20).codes[0] = -10247;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ழொ";
            keys.get(27).codes[0] = -10248;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ழோ";
            keys.get(28).codes[0] = -10249;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ழௌ";
            keys.get(29).codes[0] = -10250;
        } else if (val == -10056) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ள்";
            keys.get(0).codes[0] = -10251;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ளா";
            keys.get(1).codes[0] = -10252;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ளி";
            keys.get(2).codes[0] = -10253;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ளீ";
            keys.get(9).codes[0] = -10254;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ளு";
            keys.get(10).codes[0] = -10255;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ளூ";
            keys.get(11).codes[0] = -10256;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ளெ";
            keys.get(18).codes[0] = -10257;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ளே";
            keys.get(19).codes[0] = -10258;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ளை";
            keys.get(20).codes[0] = -10259;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ளொ";
            keys.get(27).codes[0] = -10260;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ளோ";
            keys.get(28).codes[0] = -10261;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ளௌ";
            keys.get(29).codes[0] = -10262;
        } else if (val == -10057) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ற்";
            keys.get(0).codes[0] = -10263;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "றா";
            keys.get(1).codes[0] = -10264;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "றி";
            keys.get(2).codes[0] = -10265;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "றீ";
            keys.get(9).codes[0] = -10266;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "று";
            keys.get(10).codes[0] = -10267;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "றூ";
            keys.get(11).codes[0] = -10268;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "றெ";
            keys.get(18).codes[0] = -10269;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "றே";
            keys.get(19).codes[0] = -10270;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "றை";
            keys.get(20).codes[0] = -10271;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "றொ";
            keys.get(27).codes[0] = -10272;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "றோ";
            keys.get(28).codes[0] = -10273;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "றௌ";
            keys.get(29).codes[0] = -10274;
        } else if (val == -10058) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ன்";
            keys.get(0).codes[0] = -10275;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "னா";
            keys.get(1).codes[0] = -10276;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "னி";
            keys.get(2).codes[0] = -10277;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "னீ";
            keys.get(9).codes[0] = -10278;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "னு";
            keys.get(10).codes[0] = -10279;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "னூ";
            keys.get(11).codes[0] = -10280;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "னெ";
            keys.get(18).codes[0] = -10281;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "னே";
            keys.get(19).codes[0] = -10282;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "னை";
            keys.get(20).codes[0] = -10283;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "னொ";
            keys.get(27).codes[0] = -10284;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "னோ";
            keys.get(28).codes[0] = -10285;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "னௌ";
            keys.get(29).codes[0] = -10286;
        } else if (val == -10061) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ஜ்";
            keys.get(0).codes[0] = -10287;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ஜா";
            keys.get(1).codes[0] = -10288;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ஜி";
            keys.get(2).codes[0] = -10289;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ஜீ";
            keys.get(9).codes[0] = -10290;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ஜு";
            keys.get(10).codes[0] = -10291;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ஜூ";
            keys.get(11).codes[0] = -10292;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ஜெ";
            keys.get(18).codes[0] = -10293;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ஜே";
            keys.get(19).codes[0] = -10294;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ஜை";
            keys.get(20).codes[0] = -10295;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ஜொ";
            keys.get(27).codes[0] = -10296;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ஜோ";
            keys.get(28).codes[0] = -10297;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ஜௌ";
            keys.get(29).codes[0] = -10298;
        } else if (val == -10059) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ஷ்";
            keys.get(0).codes[0] = -10299;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ஷா";
            keys.get(1).codes[0] = -10300;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ஷி";
            keys.get(2).codes[0] = -10301;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ஷீ";
            keys.get(9).codes[0] = -10302;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ஷு";
            keys.get(10).codes[0] = -10303;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ஷூ";
            keys.get(11).codes[0] = -10304;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ஷெ";
            keys.get(18).codes[0] = -10305;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ஷே";
            keys.get(19).codes[0] = -10306;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ஷை";
            keys.get(20).codes[0] = -10307;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ஷொ";
            keys.get(27).codes[0] = -10308;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ஷோ";
            keys.get(28).codes[0] = -10309;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ஷௌ";
            keys.get(29).codes[0] = -10310;
        } else if (val == -10060) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ஸ்";
            keys.get(0).codes[0] = -10311;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ஸா";
            keys.get(1).codes[0] = -10312;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ஸி";
            keys.get(2).codes[0] = -10313;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ஸீ";
            keys.get(9).codes[0] = -10314;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ஸு";
            keys.get(10).codes[0] = -10315;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ஸூ";
            keys.get(11).codes[0] = -10316;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ஸெ";
            keys.get(18).codes[0] = -10317;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ஸே";
            keys.get(19).codes[0] = -10318;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ஸை";
            keys.get(20).codes[0] = -10319;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ஸொ";
            keys.get(27).codes[0] = -10320;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ஸோ";
            keys.get(28).codes[0] = -10321;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ஸௌ";
            keys.get(29).codes[0] = -10322;
        } else if (val == -10062) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "ஹ்";
            keys.get(0).codes[0] = -10323;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "ஹா";
            keys.get(1).codes[0] = -10324;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "ஹி";
            keys.get(2).codes[0] = -10325;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "ஹீ";
            keys.get(9).codes[0] = -10326;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "ஹு";
            keys.get(10).codes[0] = -10327;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "ஹூ";
            keys.get(11).codes[0] = -10328;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "ஹெ";
            keys.get(18).codes[0] = -10329;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "ஹே";
            keys.get(19).codes[0] = -10330;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "ஹை";
            keys.get(20).codes[0] = -10331;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "ஹொ";
            keys.get(27).codes[0] = -10332;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "ஹோ";
            keys.get(28).codes[0] = -10333;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "ஹௌ";
            keys.get(29).codes[0] = -10334;
        } else if (val == -10063) {
            Keyboard currentKeyboard = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys = currentKeyboard.getKeys();
            mKeyboardView.invalidateKey(0);
            keys.get(0).label = "க்ஷ்";
            keys.get(0).codes[0] = -10335;
            mKeyboardView.invalidateKey(1);
            keys.get(1).label = "க்ஷா";
            keys.get(1).codes[0] = -10336;
            mKeyboardView.invalidateKey(2);
            keys.get(2).label = "க்ஷி";
            keys.get(2).codes[0] = -10337;
            mKeyboardView.invalidateKey(9);
            keys.get(9).label = "க்ஷீ";
            keys.get(9).codes[0] = -10338;
            mKeyboardView.invalidateKey(10);
            keys.get(10).label = "க்ஷு";
            keys.get(10).codes[0] = -10339;
            mKeyboardView.invalidateKey(11);
            keys.get(11).label = "க்ஷூ";
            keys.get(11).codes[0] = -10340;
            mKeyboardView.invalidateKey(18);
            keys.get(18).label = "க்ஷெ";
            keys.get(18).codes[0] = -10341;
            mKeyboardView.invalidateKey(19);
            keys.get(19).label = "க்ஷே";
            keys.get(19).codes[0] = -10342;
            mKeyboardView.invalidateKey(20);
            keys.get(20).label = "க்ஷை";
            keys.get(20).codes[0] = -10343;
            mKeyboardView.invalidateKey(27);
            keys.get(27).label = "க்ஷொ";
            keys.get(27).codes[0] = -10344;
            mKeyboardView.invalidateKey(28);
            keys.get(28).label = "க்ஷோ";
            keys.get(28).codes[0] = -10345;
            mKeyboardView.invalidateKey(29);
            keys.get(29).label = "க்ஷெள";
            keys.get(29).codes[0] = -10346;
        } else if (val == 32 || val == 46 || val == -10014 || val == -10013) {
            Keyboard currentKeyboard1 = mKeyboardView.getKeyboard();
            List<Keyboard.Key> keys1 = currentKeyboard1.getKeys();
            mKeyboardView.invalidateKey(0);
            if (keys1.get(0).label == "0") {

            } else {
                Keyboard currentKeyboard = mKeyboardView.getKeyboard();
                List<Keyboard.Key> keys = currentKeyboard.getKeys();
                mKeyboardView.invalidateKey(0);
                keys.get(0).label = "அ";
                keys.get(0).codes[0] = -10001;
                mKeyboardView.invalidateKey(1);
                keys.get(1).label = "ஆ";
                keys.get(1).codes[0] = -10002;
                mKeyboardView.invalidateKey(2);
                keys.get(2).label = "இ";
                keys.get(2).codes[0] = -10003;
                mKeyboardView.invalidateKey(9);
                keys.get(9).label = "ஈ";
                keys.get(9).codes[0] = -10004;
                mKeyboardView.invalidateKey(10);
                keys.get(10).label = "உ";
                keys.get(10).codes[0] = -10005;
                mKeyboardView.invalidateKey(11);
                keys.get(11).label = "ஊ";
                keys.get(11).codes[0] = -10006;
                mKeyboardView.invalidateKey(18);
                keys.get(18).label = "எ";
                keys.get(18).codes[0] = -10007;
                mKeyboardView.invalidateKey(19);
                keys.get(19).label = "ஏ";
                keys.get(19).codes[0] = -10008;
                mKeyboardView.invalidateKey(20);
                keys.get(20).label = "ஐ";
                keys.get(20).codes[0] = -10009;
                mKeyboardView.invalidateKey(27);
                keys.get(27).label = "ஒ";
                keys.get(27).codes[0] = -10010;
                mKeyboardView.invalidateKey(28);
                keys.get(28).label = "ஓ";
                keys.get(28).codes[0] = -10011;
                mKeyboardView.invalidateKey(29);
                keys.get(29).label = "ஔ";
                keys.get(29).codes[0] = -10012;
            }

        }

    }

    public void letter_del_change(String str) {
        if (str.equals("க")) {
            letter_change(-10041);
        } else if (str.equals("ங")) {
            letter_change(-10042);
        } else if (str.equals("ச")) {
            letter_change(-10043);
        } else if (str.equals("ஞ")) {
            letter_change(-10044);
        } else if (str.equals("ட")) {
            letter_change(-10045);
        } else if (str.equals("ண")) {
            letter_change(-10046);
        } else if (str.equals("த")) {
            letter_change(-10047);
        } else if (str.equals("ந")) {
            letter_change(-10048);
        } else if (str.equals("ப")) {
            letter_change(-10049);
        } else if (str.equals("ம")) {
            letter_change(-10050);
        } else if (str.equals("ய")) {
            letter_change(-10051);
        } else if (str.equals("ர")) {
            letter_change(-10052);
        } else if (str.equals("ல")) {
            letter_change(-10053);
        } else if (str.equals("வ")) {
            letter_change(-10054);
        } else if (str.equals("ழ")) {
            letter_change(-10055);
        } else if (str.equals("ள")) {
            letter_change(-10056);
        } else if (str.equals("ற")) {
            letter_change(-10057);
        } else if (str.equals("ன")) {
            letter_change(-10058);
        } else if (str.equals("ஜ")) {
            letter_change(-10059);
        } else if (str.equals("ஷ")) {
            letter_change(-10060);
        } else if (str.equals("ஸ")) {
            letter_change(-10061);
        } else if (str.equals("ஹ")) {
            letter_change(-10062);
        } else if (str.equals("க்ஷ")) {
            letter_change(-10063);
        }
    }

    public void num_channge() {
        Keyboard currentKeyboard = mKeyboardView.getKeyboard();
        List<Keyboard.Key> keys = currentKeyboard.getKeys();
        mKeyboardView.invalidateKey(0);
        keys.get(0).label = "0";
        keys.get(0).codes[0] = 48;
        mKeyboardView.invalidateKey(1);
        keys.get(1).label = "1";
        keys.get(1).codes[0] = 49;
        mKeyboardView.invalidateKey(2);
        keys.get(2).label = "2";
        keys.get(2).codes[0] = 50;
        mKeyboardView.invalidateKey(3);
        keys.get(3).label = "~";
        keys.get(3).codes[0] = 126;
        mKeyboardView.invalidateKey(4);
        keys.get(4).label = "`";
        keys.get(4).codes[0] = 96;
        mKeyboardView.invalidateKey(5);
        keys.get(5).label = "!";
        keys.get(5).codes[0] = 33;
        mKeyboardView.invalidateKey(6);
        keys.get(6).label = "@";
        keys.get(6).codes[0] = 64;
        mKeyboardView.invalidateKey(7);
        keys.get(7).label = "#";
        keys.get(7).codes[0] = 35;
        mKeyboardView.invalidateKey(8);
        keys.get(8).label = "$";
        keys.get(8).codes[0] = 36;
        mKeyboardView.invalidateKey(9);
        keys.get(9).label = "3";
        keys.get(9).codes[0] = 51;
        mKeyboardView.invalidateKey(10);
        keys.get(10).label = "4";
        keys.get(10).codes[0] = 52;
        mKeyboardView.invalidateKey(11);
        keys.get(11).label = "5";
        keys.get(11).codes[0] = 53;
        mKeyboardView.invalidateKey(12);
        keys.get(12).label = "%";
        keys.get(12).codes[0] = 37;
        mKeyboardView.invalidateKey(13);
        keys.get(13).label = "^";
        keys.get(13).codes[0] = 94;
        mKeyboardView.invalidateKey(14);
        keys.get(14).label = "&";
        keys.get(14).codes[0] = 38;
        mKeyboardView.invalidateKey(15);
        keys.get(15).label = "(";
        keys.get(15).codes[0] = 40;
        mKeyboardView.invalidateKey(16);
        keys.get(16).label = ")";
        keys.get(16).codes[0] = 41;
        mKeyboardView.invalidateKey(17);
        keys.get(17).label = "?";
        keys.get(17).codes[0] = 63;
        mKeyboardView.invalidateKey(18);
        keys.get(18).label = "6";
        keys.get(18).codes[0] = 54;
        mKeyboardView.invalidateKey(19);
        keys.get(19).label = "7";
        keys.get(19).codes[0] = 55;
        mKeyboardView.invalidateKey(20);
        keys.get(20).label = "8";
        keys.get(20).codes[0] = 56;
        mKeyboardView.invalidateKey(21);
        keys.get(21).label = "+";
        keys.get(21).codes[0] = 43;
        mKeyboardView.invalidateKey(22);
        keys.get(22).label = "-";
        keys.get(22).codes[0] = 45;
        mKeyboardView.invalidateKey(23);
        keys.get(23).label = "*";
        keys.get(23).codes[0] = 42;
        mKeyboardView.invalidateKey(24);
        keys.get(24).label = "/";
        keys.get(24).codes[0] = 47;
        mKeyboardView.invalidateKey(25);
        keys.get(25).label = "\\";
        keys.get(25).codes[0] = 92;
        mKeyboardView.invalidateKey(26);
        keys.get(26).label = "|";
        keys.get(26).codes[0] = 124;
        mKeyboardView.invalidateKey(27);
        keys.get(27).label = "9";
        keys.get(27).codes[0] = 57;
        mKeyboardView.invalidateKey(28);
        keys.get(28).label = "ௐ";
        keys.get(28).codes[0] = -10402;
        mKeyboardView.invalidateKey(29);
        keys.get(29).label = "_";
        keys.get(29).codes[0] = 95;
        mKeyboardView.invalidateKey(30);
        keys.get(30).label = "[";
        keys.get(30).codes[0] = 91;
        mKeyboardView.invalidateKey(31);
        keys.get(31).label = "]";
        keys.get(31).codes[0] = 93;
        mKeyboardView.invalidateKey(32);
        keys.get(32).label = "{";
        keys.get(32).codes[0] = 123;
        mKeyboardView.invalidateKey(33);
        keys.get(33).label = "}";
        keys.get(33).codes[0] = 125;
        mKeyboardView.invalidateKey(34);
        keys.get(34).label = "\"";
        keys.get(34).codes[0] = 34;
        mKeyboardView.invalidateKey(41);
        mKeyboardView.invalidateKey(36);
        keys.get(36).label = "அ";
        keys.get(36).codes[0] = -55001;
    }

    public void num_channge1() {
        Keyboard currentKeyboard = mKeyboardView.getKeyboard();
        List<Keyboard.Key> keys = currentKeyboard.getKeys();
        mKeyboardView.invalidateKey(0);
        keys.get(0).label = "அ";
        keys.get(0).codes[0] = -10001;
        mKeyboardView.invalidateKey(1);
        keys.get(1).label = "ஆ";
        keys.get(1).codes[0] = -10002;
        mKeyboardView.invalidateKey(2);
        keys.get(2).label = "இ";
        keys.get(2).codes[0] = -10003;
        mKeyboardView.invalidateKey(3);
        keys.get(3).label = "க";
        keys.get(3).codes[0] = -10041;
        mKeyboardView.invalidateKey(4);
        keys.get(4).label = "ங";
        keys.get(4).codes[0] = -10042;
        mKeyboardView.invalidateKey(5);
        keys.get(5).label = "ச";
        keys.get(5).codes[0] = -10043;
        mKeyboardView.invalidateKey(6);
        keys.get(6).label = "ஞ";
        keys.get(6).codes[0] = -10044;
        mKeyboardView.invalidateKey(7);
        keys.get(7).label = "ட";
        keys.get(7).codes[0] = -10045;
        mKeyboardView.invalidateKey(8);
        keys.get(8).label = "ண";
        keys.get(8).codes[0] = -10046;
        mKeyboardView.invalidateKey(9);
        keys.get(9).label = "ஈ";
        keys.get(9).codes[0] = -10004;
        mKeyboardView.invalidateKey(10);
        keys.get(10).label = "உ";
        keys.get(10).codes[0] = -10005;
        mKeyboardView.invalidateKey(11);
        keys.get(11).label = "ஊ";
        keys.get(11).codes[0] = -10006;
        mKeyboardView.invalidateKey(12);
        keys.get(12).label = "த";
        keys.get(12).codes[0] = -10047;
        mKeyboardView.invalidateKey(13);
        keys.get(13).label = "ந";
        keys.get(13).codes[0] = -10048;
        mKeyboardView.invalidateKey(14);
        keys.get(14).label = "ப";
        keys.get(14).codes[0] = -10049;
        mKeyboardView.invalidateKey(15);
        keys.get(15).label = "ம";
        keys.get(15).codes[0] = -10050;
        mKeyboardView.invalidateKey(16);
        keys.get(16).label = "ய";
        keys.get(16).codes[0] = -10051;
        mKeyboardView.invalidateKey(17);
        keys.get(17).label = "ர";
        keys.get(17).codes[0] = -10052;
        mKeyboardView.invalidateKey(18);
        keys.get(18).label = "எ";
        keys.get(18).codes[0] = -10007;
        mKeyboardView.invalidateKey(19);
        keys.get(19).label = "ஏ";
        keys.get(19).codes[0] = -10008;
        mKeyboardView.invalidateKey(20);
        keys.get(20).label = "ஐ";
        keys.get(20).codes[0] = -10009;
        mKeyboardView.invalidateKey(21);
        keys.get(21).label = "ல";
        keys.get(21).codes[0] = -10053;
        mKeyboardView.invalidateKey(22);
        keys.get(22).label = "வ";
        keys.get(22).codes[0] = -10054;
        mKeyboardView.invalidateKey(23);
        keys.get(23).label = "ழ";
        keys.get(23).codes[0] = -10055;
        mKeyboardView.invalidateKey(24);
        keys.get(24).label = "ள";
        keys.get(24).codes[0] = -10056;
        mKeyboardView.invalidateKey(25);
        keys.get(25).label = "ற";
        keys.get(25).codes[0] = -10057;
        mKeyboardView.invalidateKey(26);
        keys.get(26).label = "ன";
        keys.get(26).codes[0] = -10058;
        mKeyboardView.invalidateKey(27);
        keys.get(27).label = "ஒ";
        keys.get(27).codes[0] = -10010;
        mKeyboardView.invalidateKey(28);
        keys.get(28).label = "ஓ";
        keys.get(28).codes[0] = -10011;
        mKeyboardView.invalidateKey(29);
        keys.get(29).label = "ஔ";
        keys.get(29).codes[0] = -10012;
        mKeyboardView.invalidateKey(30);
        keys.get(30).label = "ஷ";
        keys.get(30).codes[0] = -10059;
        mKeyboardView.invalidateKey(31);
        keys.get(31).label = "ஸ";
        keys.get(31).codes[0] = -10060;
        mKeyboardView.invalidateKey(32);
        keys.get(32).label = "ஜ";
        keys.get(32).codes[0] = -10061;
        mKeyboardView.invalidateKey(33);
        keys.get(33).label = "ஹ";
        keys.get(33).codes[0] = -10062;
        mKeyboardView.invalidateKey(34);
        keys.get(34).label = "க்ஷ";
        keys.get(34).codes[0] = -10063;
        mKeyboardView.invalidateKey(41);
        keys.get(41).label = "ஸ்ரீ";
        keys.get(41).codes[0] = -10014;
        mKeyboardView.invalidateKey(36);
        keys.get(36).label = "12?";
        keys.get(36).codes[0] = -55000;
    }

}
