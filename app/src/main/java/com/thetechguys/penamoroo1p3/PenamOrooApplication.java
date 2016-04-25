package com.thetechguys.penamoroo1p3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.thetechguys.penamoroo1p3.utils.ParseConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by TheBank on 17/9/14.
 */
public class PenamOrooApplication extends android.app.Application {
    @Override public void onCreate() {




        super.onCreate();

        Parse.initialize(this, "EdVkoz6hzoQF7CWxyBPZI8h8DrUC5GJXqVnXu6sn", "nMLmqKTYPflKoiKhnwWUCPSN6qZ137aORnjfo7zM");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseFacebookUtils.initialize(this);
        FirstRun();

        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false);






    }

    public static void updateParseInstallation(ParseUser user){
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put(ParseConstants.KEY_USER_ID, user.getObjectId());
        installation.saveInBackground();
    }




    private void FirstRun() {
        SharedPreferences settings = this.getSharedPreferences("PenanOroo", 0);
        boolean firstrun = settings.getBoolean("firstrun", true);
        if (firstrun) { // Checks to see if we've ran the application b4
            SharedPreferences.Editor e = settings.edit();
            e.putBoolean("firstrun", false);
            e.commit();
            // If not, run these methods:

            copyFilesToSdCard();



        } else { // Otherwise start the application here:


        }
    }

    public static String TAG = PenamOrooApplication.class.getSimpleName();

    final static String TARGET_BASE_PATH = "/sdcard/PenanOroo/";
    protected Uri mMediaUri;






    private void copyFileOrDir(String path) {
       // ContextWrapper cw2 = new ContextWrapper(getApplicationContext());
        AssetManager assetManager = this.getAssets();
        String assets[] = null;
        try {
            Log.i("tag", "copyFileOrDir() " + path);
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(path);

            } else {
                String fullPath =  TARGET_BASE_PATH + path;
                Log.i("tag", "path="+fullPath);
                File dir = new File(fullPath);
                if (!dir.exists() && !path.startsWith("sounds") && !path.startsWith("images") && !path.startsWith("webkit"))
                    if (!dir.mkdirs())
                        Log.i("tag", "could not create dir "+fullPath);
                for (int i = 0; i < assets.length; ++i) {
                    String p;

                    if (path.equals(""))
                        p = "";

                    else
                        p = path + "/";

                    if (!path.startsWith("sounds") && !path.startsWith("images") && !path.startsWith("webkit"))
                        copyFileOrDir( p + assets[i]);


                }
            }
        } catch (IOException ex) {
            Log.e("tag", "I/O Exception", ex);
        }



    }

    private void copyFile(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream in = null;
        OutputStream out = null;
        String newFileName = null;
        try {
            Log.i("tag", "copyFile() " + filename);

           // ContextWrapper cw = new ContextWrapper(getApplicationContext());
            in = assetManager.open(filename);
            if (filename.endsWith(".png")) // extension was added to avoid compression on APK file

                newFileName = TARGET_BASE_PATH + filename.substring(0, filename.length()-4) +".png";


            else
                newFileName =  TARGET_BASE_PATH + filename +".png";

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            //Intent mediascanstarted = new Intent(Intent.ACTION_MEDIA_SCANNER_STARTED);
           // Intent mediascanfinished = new Intent(Intent.ACTION_MEDIA_SCANNER_FINISHED);

            mediaScanIntent.setData(Uri.parse(newFileName));
            mediaScanIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);

           // sendBroadcast(mediascanstarted);
            sendBroadcast(mediaScanIntent);
           // sendBroadcast(mediascanstarted);
           // Log.i(TAG, "" + mediascanstarted + mediascanfinished );



            out = new FileOutputStream(newFileName);


            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;




        } catch (Exception e) {
            Log.e("tag", "Exception in copyFile() of "+newFileName);
            Log.e("tag", "Exception in copyFile() "+e.toString());
        }

    }



    private void copyFilesToSdCard() {
        copyFileOrDir(""); // copy all files in assets folder in my project
    }




}
