package nithra.tamil.word.game.solliadi

import android.app.Activity
import android.app.Dialog
import android.os.Environment
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
        val stringRequest: StringRequest =
            object : StringRequest(
                Method.POST,
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


}