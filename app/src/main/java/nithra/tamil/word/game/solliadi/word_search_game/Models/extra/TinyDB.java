package nithra.tamil.word.game.solliadi.word_search_game.Models.extra;

/**
 * Created by NITHRA-G7 on 3/17/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import nithra.tamil.word.game.solliadi.word_search_game.Models.Models.Word;


public class TinyDB {

    private final SharedPreferences preferences;
    private String DEFAULT_APP_IMAGEDATA_DIRECTORY;
    private String lastImagePath = "";

    public TinyDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    /**
     * Check if external storage is writable or not
     *
     * @return true if writable, false otherwise
     */
    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * Check if external storage is readable or not
     *
     * @return true if readable, false otherwise
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * Decodes the Bitmap from 'path' and returns it
     *
     * @param path image path
     * @return the Bitmap from 'path'
     */
    public Bitmap getImage(String path) {
        Bitmap bitmapFromPath = null;
        try {
            bitmapFromPath = BitmapFactory.decodeFile(path);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return bitmapFromPath;
    }

    /**
     * Returns the String path of the last saved image
     *
     * @return string path of the last saved image
     */
    public String getSavedImagePath() {
        return lastImagePath;
    }

    /**
     * Saves 'theBitmap' into folder 'theFolder' with the name 'theImageName'
     *
     * @param theFolder    the folder path dir you want to save it to e.g "DropBox/WorkImages"
     * @param theImageName the name you want to assign to the image file e.g "MeAtLunch.png"
     * @param theBitmap    the image you want to save as a Bitmap
     * @return returns the full path(file system address) of the saved image
     */
    public String putImage(String theFolder, String theImageName, Bitmap theBitmap) {
        if (theFolder == null || theImageName == null || theBitmap == null)
            return null;

        this.DEFAULT_APP_IMAGEDATA_DIRECTORY = theFolder;
        String mFullPath = setupFullPath(theImageName);

        if (!mFullPath.equals("")) {
            lastImagePath = mFullPath;
            saveBitmap(mFullPath, theBitmap);
        }

        return mFullPath;
    }

    /**
     * Saves 'theBitmap' into 'fullPath'
     *
     * @param fullPath  full path of the image file e.g. "Images/MeAtLunch.png"
     * @param theBitmap the image you want to save as a Bitmap
     * @return true if image was saved, false otherwise
     */
    public boolean putImageWithFullPath(String fullPath, Bitmap theBitmap) {
        return !(fullPath == null || theBitmap == null) && saveBitmap(fullPath, theBitmap);
    }

    // Getters

    /**
     * Creates the path for the image with name 'imageName' in DEFAULT_APP.. directory
     *
     * @param imageName name of the image
     * @return the full path of the image. If it failed to create directory, return empty string
     */
    private String setupFullPath(String imageName) {
        File mFolder = new File(Environment.getExternalStorageDirectory(), DEFAULT_APP_IMAGEDATA_DIRECTORY);

        if (isExternalStorageReadable() && isExternalStorageWritable() && !mFolder.exists()) {
            if (!mFolder.mkdirs()) {
                Log.e("ERROR", "Failed to setup folder");
                return "";
            }
        }

        return mFolder.getPath() + '/' + imageName;
    }

    /**
     * Saves the Bitmap as a PNG file at path 'fullPath'
     *
     * @param fullPath path of the image file
     * @param bitmap   the image as a Bitmap
     * @return true if it successfully saved, false otherwise
     */
    private boolean saveBitmap(String fullPath, Bitmap bitmap) {
        if (fullPath == null || bitmap == null)
            return false;

        boolean fileCreated = false;
        boolean bitmapCompressed = false;
        boolean streamClosed = false;

        File imageFile = new File(fullPath);

        if (imageFile.exists())
            if (!imageFile.delete())
                return false;

        try {
            fileCreated = imageFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imageFile);
            bitmapCompressed = bitmap.compress(CompressFormat.PNG, 100, out);

        } catch (Exception e) {
            e.printStackTrace();
            bitmapCompressed = false;

        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                    streamClosed = true;

                } catch (IOException e) {
                    e.printStackTrace();
                    streamClosed = false;
                }
            }
        }

        return (fileCreated && bitmapCompressed && streamClosed);
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public ArrayList<Integer> getListInt(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Integer> newList = new ArrayList<Integer>();

        for (String item : arrayToList)
            newList.add(Integer.parseInt(item));

        return newList;
    }

    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }

    public double getDouble(String key, double defaultValue) {
        String number = getString(key);

        try {
            return Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public ArrayList<Double> getListDouble(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Double> newList = new ArrayList<Double>();

        for (String item : arrayToList)
            newList.add(Double.parseDouble(item));

        return newList;
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public ArrayList<String> getListString(String key) {
        System.out.println("excepkey:" + preferences.getString(key, ""));
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public ArrayList<Boolean> getListBoolean(String key) {
        ArrayList<String> myList = getListString(key);
        ArrayList<Boolean> newList = new ArrayList<Boolean>();

        for (String item : myList) {
            if (item.equals("true")) {
                newList.add(true);
            } else {
                newList.add(false);
            }
        }

        return newList;
    }

//    public  Object getObject(String key, Class<?> classOfT){
//
//        String json = getString(key);
//        Object value = new Gson().fromJson(json, classOfT);
//        if (value == null)
//            throw new NullPointerException();
//        return value;
//    }


    // Put methods

    public List<Word> getListObject(String key, Class<?> mClass) {
        Gson gson = new Gson();

        System.out.println("excepkeyyyy:" + key);
        ArrayList<String> objStrings = getListString(key);
        List<Word> objects = new ArrayList<>();

        for (String jObjString : objStrings) {
            Object value = gson.fromJson(jObjString, mClass);
            objects.add((Word) value);
        }
        return objects;
    }

    public List<String> getList(String key, Class<?> mClass) {
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        List<String> objects = new ArrayList<>();

        for (String jObjString : objStrings) {
            Object value = gson.fromJson(jObjString, mClass);
            objects.add((String) value);
        }
        return objects;
    }

    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }

    public void putListInt(String key, ArrayList<Integer> intList) {
        checkForNullKey(key);
        Integer[] myIntList = intList.toArray(new Integer[intList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myIntList)).apply();
    }

    public void putLong(String key, long value) {
        checkForNullKey(key);
        preferences.edit().putLong(key, value).apply();
    }

    public void putFloat(String key, float value) {
        checkForNullKey(key);
        preferences.edit().putFloat(key, value).apply();
    }

    public void putDouble(String key, double value) {
        checkForNullKey(key);
        putString(key, String.valueOf(value));
    }

    public void putListDouble(String key, ArrayList<Double> doubleList) {
        checkForNullKey(key);
        Double[] myDoubleList = doubleList.toArray(new Double[doubleList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myDoubleList)).apply();
    }

    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }

    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }

    public void putBoolean(String key, boolean value) {
        checkForNullKey(key);
        preferences.edit().putBoolean(key, value).apply();
    }

    public void putListBoolean(String key, ArrayList<Boolean> boolList) {
        checkForNullKey(key);
        ArrayList<String> newList = new ArrayList<String>();

        for (Boolean item : boolList) {
            if (item) {
                newList.add("true");
            } else {
                newList.add("false");
            }
        }

        putListString(key, newList);
    }

    public void putObject(String key, Object obj) {
        checkForNullKey(key);
        Gson gson = new Gson();
        putString(key, gson.toJson(obj));
    }

    public void putListObject(String key, List<Word> objArray) {
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for (Object obj : objArray) {
            objStrings.add(gson.toJson(obj));
        }
        putListString(key, objStrings);
    }

    public void putList(String key, List<String> objArray) {
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for (Object obj : objArray) {
            objStrings.add(gson.toJson(obj));
        }
        putListString(key, objStrings);
    }

    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    public boolean deleteImage(String path) {
        return new File(path).delete();
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    public void registerOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     *
     * @param the pref key
     */
    public void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     *
     * @param the pref key
     */
    public void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }
}