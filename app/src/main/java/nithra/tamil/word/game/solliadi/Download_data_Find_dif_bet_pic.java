package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.data_download_url;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.img_down_url;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Download_data_Find_dif_bet_pic extends AsyncTask<String, Void, String> {
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper6 newhelper6;
    ProgressDialog progressDialog;
    Context context_d;
    Download_complete_data_find_dif_bet_pic download_completed;
    String serverResponse = "set data";
    String data = "";
    String gameids = "", questionids = "", actions = "";
    SharedPreference sps = new SharedPreference();
    Download_data_Find_dif_bet_pic.DownloadFileAsync downloadFileAsync;
    ProgressDialog nProgressDialog;


    public Download_data_Find_dif_bet_pic(Context context, String questionid, String gameid) {
        myDbHelper = new DataBaseHelper(context);
        newhelper6 = new Newgame_DataBaseHelper6(context);
        download_completed = (Download_complete_data_find_dif_bet_pic) context;
        gameids = gameid;
        questionids = questionid;
        context_d = context;

    }

    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context_d);
        progressDialog.setMessage("காத்திருக்கவும்....");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(String... strings) {
        System.out.println("######################data_download_url" + data_download_url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, data_download_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        JSONArray jArray;
                        JSONObject json_data = null;
                        try {
                            jArray = new JSONArray(ServerResponse);
                            if (jArray.length() > 0) {
                                json_data = jArray.getJSONObject(0);
                                if (json_data.getString("status").equals("yesdata")) {
                                    data = "yesdata";
                                    System.out.println("###########################Data downloding yesdata");
                                    for (int i = 0; i < jArray.length(); i++) {
                                        System.out.print("Insert for=======");
                                        json_data = jArray.getJSONObject(i);
                                        if (gameids.equals("20")) {
                                            ContentValues cv = new ContentValues();
                                            cv.put("id", json_data.getString("id"));
                                            cv.put("gameid", json_data.getString("gameid"));
                                            cv.put("questionid", json_data.getString("questionid"));
                                            cv.put("question", json_data.getString("question"));
                                            cv.put("sf_word", json_data.getString("sf_words"));
                                            cv.put("answer", json_data.getString("answer"));
                                            cv.put("isfinish", "0");
                                            cv.put("isdown", "1");
                                            cv.put("my_maintain", "0");
                                            cv.put("playtime", "0");
                                            cv.put("rd", "0");
                                            newhelper6.insert_data("newgames5", null, cv);
                                        }
                                    }
                                    if (gameids.equals("20")) {
                                        if (exists(img_down_url + sps.getString(context_d, "email") + "-filename.zip")) {
                                            startDownload();
                                            System.out.println("##################file exist");
                                        } else {
                                            System.out.println("--check1");
                                            download_completed.download_completed_pic(data);
                                            progressDialog.dismiss();
                                        }
                                    }

                                } else if (json_data.getString("status").equals("nodata")) {
                                    data = "nodata";
                                    System.out.println("###########################Data downloding nodata");
                                }
                                System.out.println("checkdownload");
                                if (gameids.equals("20")) {
                                    System.out.println("--check2");
                                    download_completed.download_completed_pic(data);
                                    progressDialog.dismiss();
                                } else {
                                    System.out.println("--check3");
                                    download_completed.download_completed_pic(data);
                                    progressDialog.dismiss();
                                }

                            }

                            //  Toast.makeText(Price_Login.this, "" + otp + "" + reg_check, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Hiding the progress dialog after all task complete.
                        // Showing response message coming from server.
                        System.out.println("ServerResponse" + ServerResponse);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        // Showing error message if something goes wrong.
                        // Toast.makeText(Price_Login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                        String data = volleyError.toString();
                        System.out.println("##########################volleyError" + volleyError);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                System.out.println("--------------------Data downloding request");
                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                params.put("action", "get_gamedata");
                params.put("gameid", gameids);
                params.put("lastqid", questionids);
                params.put("android_id", sps.getString(context_d, "email"));

                System.out.println("#################Android ID" + sps.getString(context_d, "email"));
                System.out.println("#################gameids" + gameids);
                System.out.println("#################lastqid" + questionids);
                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(context_d);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    public void startDownload() {
        String str_url = img_down_url + sps.getString(context_d, "email") + "-filename.zip";
        //     new DownloadFileAsync().execute(str_url);
        downloadFileAsync = new Download_data_Find_dif_bet_pic.DownloadFileAsync();
        downloadFileAsync.execute(str_url);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DOWNLOAD_PROGRESS) {
            nProgressDialog = new ProgressDialog(context_d);
            nProgressDialog.setMessage("படங்கள் பதிவிறக்கம் செய்யப்படுகிறது காத்திருக்கவும்.... ");
            nProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            nProgressDialog.setCancelable(false);
            nProgressDialog.show();
            // playy();
            return nProgressDialog;
        }
        return null;
    }

    public int unpackZip(String ZIP_FILE_NAME) {
        InputStream is;
        ZipInputStream zis;
        try {

            String fullPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/Nithra/solliadi/";
            is = new FileInputStream(fullPath + ZIP_FILE_NAME);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;

            while ((ze = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;

                // zapis do souboru
                String filename = ze.getName();
                FileOutputStream fout = new FileOutputStream(fullPath
                        + filename);

                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
            File file = new File(fullPath + ZIP_FILE_NAME);
            file.delete();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            System.out.println("##############################started Downloded ");
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(aurl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                final int fileLength = connection.getContentLength();

                File SDCardRoot = context_d.getFilesDir();

                File fol = new File(SDCardRoot + "/Nithra/solliadi/");
                if (!fol.exists()) {
                    fol.mkdirs();
                }


                File file = new File(SDCardRoot + "/Nithra/solliadi/", sps.getString(context_d, "email") + "-filename.zip");

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(file);

                byte[] data = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    publishProgress("" + (int) ((total * 100) / fileLength));

                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        output.write(data, 0, count);
                }

                unpackZip(sps.getString(context_d, "email") + "-filename.zip");

            } catch (Exception e) {


                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();


            }
            return null;

        }


        @Override
        protected void onPostExecute(String unused) {
            //  nProgressDialog.dismiss();
            if (unused != null && unused.equals("ERROR_DOW")) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context_d);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setTitle("Network connection not available, please check it!");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        downloadFileAsync.isCancelled();
                        downloadFileAsync.cancel(true);

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }


            try {

            } catch (Exception e) {
                System.out.println("result=======////==" + e);
            }
            System.out.println("--check4");
            download_completed.download_completed_pic(data);
            progressDialog.dismiss();
        }
     /*   protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC",progress[0]);
            nProgressDialog.setProgress(Integer.parseInt(progress[0]));
			*//*if(!isNetworkAvailable()){
                downloadFileAsync.isCancelled();
				//downloadFileAsync.cancel(true);

			}*//*
        }*/
    }


}

