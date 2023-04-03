package nithra.tamil.word.game.solliadi

import android.app.Activity
import android.app.Dialog
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.applovin.mediation.ads.MaxAdView
import com.applovin.sdk.AppLovinSdk
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import nithra.tamil.word.game.solliadi.Utils.getDeviceName
import java.io.File
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


/**
 * Created by NITHRA-G6 on 12/19/2015.
 */
object Utills {
    const val interstitialadCount = 9
    const val notiInterstitialadCount = 3
    const val DATE_TIME_FORMAT_WS = "dd/MM/yyyy HH:mm:ss"
    const val TAG_BACKUP_LOCATION = "/Nithra/Tamil_Game/Backup/"
    var Loading_dialog: Dialog? = null
    fun getCurrentDateTime(format: String?): Date? {
        var date: Date? = null
        try {
            val c = Calendar.getInstance()
            println("Current time => " + c.time)
            val df = SimpleDateFormat(format)
            val formattedDate = df.format(c.time)
            date = c.time
        } catch (e: Exception) {
            //  printValue("getCurrentDateTime", e.toString());
        }
        return date
    }

    fun createStorageLocation() {
        try {
            val sdCard = Environment.getExternalStorageDirectory()
            val dir = File(sdCard.absolutePath + TAG_BACKUP_LOCATION)
            if (!dir.exists()) dir.mkdirs()
        } catch (e: Exception) {
            //Utills.printValue("HomeActivity - createStorageLocation()", e.toString());
        }
    }

    fun sendFeed(activity: Activity, name: String, email: String, ph_no: String, feed: String) {
        val stringRequest: StringRequest = object : StringRequest(Method.POST,
            "https://www.nithra.mobi/apps/appfeedback.php",
            Response.Listener {
                Utils.toast_normal(activity, "கருத்துக்கள்  அனுப்பப்பட்டது .நன்றி ")

            },
            Response.ErrorListener {

            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["feedback"] = URLEncoder.encode(feed, "UTF-8")
                params["email"] = email
                params["phno"] = ph_no
                params["name"] = name
                params["vcode"] = BuildConfig.VERSION_CODE.toString() + ""
                params["model"] = getDeviceName()
                params["type"] = "Tamil_Odu_Viliyadu"
                return params
            }
        }

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(stringRequest)
    }

    fun Loading_Dialog(activity: Activity) {
        val layoutInflater = activity.layoutInflater.inflate(R.layout.loading_dialog, null)
        Loading_dialog = Dialog(
            activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen
        )
        Loading_dialog!!.setContentView(layoutInflater)
        Loading_dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
        Loading_dialog!!.setCanceledOnTouchOutside(false)
        Loading_dialog!!.setCancelable(false)

        Loading_dialog!!.show()
    }

    fun Loading_Dialog_dismiss() {
        if (Loading_dialog != null && Loading_dialog!!.isShowing) {
            try {
                Loading_dialog!!.dismiss()
            } catch (e: Exception) {
            }
        }
    }

    fun loads_ads_banner(
        activity: Activity,
        ads_lay: AdView,
        banner_adParent: LinearLayout,
        adId: String
    ) {
        println("print_Ad")
        if (SharedPreference().getInt(activity, "purchase_ads") == 1) {
            ads_lay.visibility = View.GONE
            banner_adParent.visibility = View.GONE
        } else if (Utils.isNetworkAvailable(activity)) {
            MobileAds.initialize(activity)
            val adView = AdView(activity)
            adView.setAdSize(
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    activity, 320
                )
            )
            adView.adUnitId = adId
            val adRequest = AdRequest.Builder().build()
            ads_lay.loadAd(adRequest)
        } else {
            ads_lay.visibility = View.GONE
            banner_adParent.visibility = View.GONE
        }
    }

    fun load_add_AppLovin(activity: Activity, ads_lay: LinearLayout) {
        if (SharedPreference().getInt(activity, "purchase_ads") == 1) {
            ads_lay.visibility = View.GONE
        } else if (Utils.isNetworkAvailable(activity)) {
            AppLovinSdk.initializeSdk(activity)
            AppLovinSdk.getInstance(activity).mediationProvider = "max"

            val adView = MaxAdView(activity.resources.getString(R.string.Bottom_Banner), activity)
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val heightPx = activity.resources.getDimensionPixelSize(R.dimen.banner_height)
            adView.layoutParams = FrameLayout.LayoutParams(width, heightPx)
            ads_lay.addView(adView)
            adView.loadAd()
        } else {
            ads_lay.visibility = View.GONE
        }
    }

    fun initializeAdzz(activity: Activity) {

        AudienceNetworkAds.initialize(activity)
        AppLovinSdk.initializeSdk(activity)
        AppLovinSdk.getInstance(activity).mediationProvider = "max"
    }

    fun GameComplete(activity: Activity) {
//padam paarththu
        var gid = "1"
        var qid = ""
        for (i in 0..199) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        DataBaseHelper(activity).executeSql("UPDATE maintable SET isfinish='1' WHERE levelid in ($qid) and gameid='1'")
//padaththirkul kandupidi

        gid = "16"
        qid = ""
        for (i in 1..29) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        // newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + qid + "'and gameid='" + gid + "'");
        // newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + qid + "'and gameid='" + gid + "'");
        Newgame_DataBaseHelper5(activity).executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
//vinaadi vinaa

        gid = "17"
        qid = ""
        for (i in 0..499) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='17'")
        Newgame_DataBaseHelper5(activity).executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='17'")
//thirukkural

        gid = "12"
        qid = ""
        for (i in 0..1329) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='17'")
        Newgame_DataBaseHelper3(activity).executeSql("UPDATE right_order SET isfinish='1' WHERE questionid in ($qid) and gameid='12'")


        //6 diff

        gid = "20"
        qid = ""
        for (i in 1..29) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        Newgame_DataBaseHelper6(activity).executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='20'")


        //kurippu

        gid = "2"
        qid = ""
        for (i in 0..198) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        DataBaseHelper(activity).executeSql("UPDATE maintable SET isfinish='1' WHERE levelid in ($qid) and gameid='2'")

        //puthir

        gid = "10"
        qid = ""
        for (i in 1..699) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        Newgame_DataBaseHelper3(activity).executeSql("UPDATE right_order SET isfinish='1' WHERE questionid in ($qid) and gameid='10'")

        //inai sol

        gid = "6"
        qid = ""
        for (i in 0..499) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        Newgame_DataBaseHelper(activity).executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid in ($qid) and gameid='6'")

        //ethir sol
        gid = "7"
        qid = ""
        for (i in 0..999) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        Newgame_DataBaseHelper2(activity).executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE questionid in ($qid) and gameid='7'")
//vearupaadu
        gid = "5"
        qid = ""
        for (i in 0..999) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        Newgame_DataBaseHelper(activity).executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid in ($qid) and gameid='5'")

        //sollukkul sol
        gid = "3"
        qid = ""
        for (i in 0..198) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        DataBaseHelper(activity).executeSql("UPDATE maintable SET isfinish='1' WHERE levelid in ($qid) and gameid='3'")
//vidupatta eluththu
        gid = "13"
        qid = ""
        for (i in 0..999) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        Newgame_DataBaseHelper4(activity).executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE levelid in ($qid) and gameid='13'")
//vidupatta vaarththai
        gid = "19"
        qid = ""
        for (i in 0..299) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        Newgame_DataBaseHelper6(activity).executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='19'")
//thadam maariya vaarththai
        gid = "18"
        qid = ""
        for (i in 0..499) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        Newgame_DataBaseHelper6(activity).executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='18'")
//sol viaiyaattu
        gid = "4"
        qid = ""
        for (i in 0..199) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        // newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='4'");
        // newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='4'");
        DataBaseHelper(activity).executeSql("UPDATE maintable SET isfinish='1' WHERE levelid  in ($qid) and gameid='4'")
//poruththuka
        gid = "15"
        qid = ""
        for (i in 1..99) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        // newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + qid + "'and gameid='" + gid + "'");
        // newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + qid + "'and gameid='" + gid + "'");
        Newgame_DataBaseHelper5(activity).executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='15'")
//pilaithiruththu
        gid = "11"
        qid = ""
        for (i in 1..999) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        Newgame_DataBaseHelper3(activity).executeSql("UPDATE right_order SET isfinish='1' WHERE questionid in ($qid) and gameid='11'")
//sirpaduththu
        gid = "9"
        qid = ""
        for (i in 1..2499) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        Newgame_DataBaseHelper3(activity).executeSql("UPDATE right_order SET isfinish='1' WHERE questionid in ($qid) and gameid='9'")
//piramoli sorkal
        gid = "8"
        qid = ""
        for (i in 0..999) {
            qid = if (qid == "") {
                "" + i
            } else {
                "$qid,$i"
            }
        }
        println("---qid : $qid")
        println("---qid : UPDATE newgames5 SET isfinish='1' WHERE questionid in ($qid) and gameid='16'")
        Newgame_DataBaseHelper2(activity).executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE questionid in ($qid) and gameid='8'")


    }


}