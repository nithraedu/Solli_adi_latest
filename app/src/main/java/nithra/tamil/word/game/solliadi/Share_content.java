package nithra.tamil.word.game.solliadi;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Share_content {
    Context contexts;
    String str_title = "";
    java.util.List<ResolveInfo> listApp;

    public void Share_content(Context context, String sharefinal) {

        contexts = context;
        show_share_dia(context, sharefinal);
    }

    private java.util.List<ResolveInfo> showAllShareApp() {

        java.util.List<ResolveInfo> mApps = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        intent.setType("text/plain");
        PackageManager pManager = contexts.getPackageManager();
        mApps = pManager.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return mApps;
    }

    private void share(ResolveInfo appInfo, String sharefinal) {


        if (appInfo.activityInfo.packageName.equals("com.whatsapp")) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/*");
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "தமிழோடு விளையாடு சொல்லியடி");
            Uri uriUrl = Uri.parse("whatsapp://send?text=" + "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh" + "\n\n" +
                    sharefinal + "\n\nநான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh" + "");
            sendIntent.setAction(Intent.ACTION_VIEW);
            sendIntent.setData(uriUrl);
            sendIntent.setPackage("com.whatsapp");
            //sendIntent.setComponent(new ComponentName(appInfo.activityInfo.packageName, appInfo.activityInfo.name));
            contexts.startActivity(sendIntent);

        } else {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "தமிழோடு விளையாடு சொல்லியடி");
            sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh" + "\n\n" +
                            sharefinal + "\n\nநான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh" + "");
            sendIntent.setComponent(new ComponentName(appInfo.activityInfo.packageName, appInfo.activityInfo.name));
            sendIntent.setType("text/*");
            contexts.startActivity(sendIntent);
        }
    }

    public void show_share_dia(Context context, String result) {

        final Dialog shareDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        shareDialog.setContentView(R.layout.gk_share_dialog);

        ListView share_list = (ListView) shareDialog.findViewById(R.id.share_list);

        listApp = showAllShareApp();

        if (listApp != null) {
            share_list.setAdapter(new MyAdapter(context));
            final String finalResult = result;
            share_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    share(listApp.get(position), finalResult);
                    shareDialog.dismiss();
                }
            });
        }
        shareDialog.show();
    }

    static class ViewHolder {
        ImageView ivLogo;
        TextView tvAppName;
        TextView tvPackageName;
    }

    class MyAdapter extends BaseAdapter {
        Context cons;
        PackageManager pm;

        public MyAdapter(Context context) {
            pm = context.getPackageManager();
            cons = context;
        }


        @Override
        public int getCount() {
            return listApp.size();
        }

        @Override
        public Object getItem(int position) {
            return listApp.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(cons).inflate(R.layout.gk_layout_share_app, parent, false);
                holder.ivLogo = (ImageView) convertView.findViewById(R.id.iv_logo);
                holder.tvAppName = (TextView) convertView.findViewById(R.id.tv_app_name);
                holder.tvPackageName = (TextView) convertView.findViewById(R.id.tv_app_package_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ResolveInfo appInfo = listApp.get(position);
            holder.ivLogo.setImageDrawable(appInfo.loadIcon(pm));
            holder.tvAppName.setText(appInfo.loadLabel(pm));
            holder.tvPackageName.setText(appInfo.activityInfo.packageName);

            return convertView;
        }
    }
}
