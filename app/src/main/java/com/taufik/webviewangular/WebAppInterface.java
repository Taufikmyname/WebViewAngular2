package com.taufik.webviewangular;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebAppInterface {
    private Activity _activity;
    private Context _context;

    public Intent i;

    public WebAppInterface(Context context, Activity activity){
        _context = context;
        _activity = activity;
    }

    @JavascriptInterface
    public void showNotification(String title, String message){
        NotificationChannel channel = new NotificationChannel("twCHannel", "TW", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(_context.getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(channel.getId());

        NotificationManager manager= (NotificationManager) _context.getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1, builder.build());
    }

    @JavascriptInterface
    public void showCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        _context.startActivity(intent);
    }



    @JavascriptInterface
    public void showCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        _context.startActivity(intent);
    }


    @JavascriptInterface
    public void showWhatsApp() {
        String url = "https://api.whatsapp.com/send?phone=086287811667";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        _context.startActivity(i);
    }
}
