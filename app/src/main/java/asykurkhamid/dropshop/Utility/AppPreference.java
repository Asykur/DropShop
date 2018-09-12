package asykurkhamid.dropshop.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    public static final String TAG_TOTALBAYAR = "TOTALBAYAR";
    public static final String TAG_PATH1 = "PATH1";
    public static final String TAG_PATH2 = "PATH2";
    public static final String TAG_PATH3 = "PATH3";

    public static void setPreference(Context context,String key,String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,data);
        editor.apply();
    }
    public static String getPreference(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
    public static String getPreference(Context context, String key, String def){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,def);
    }
    public static boolean isExist(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }

    public static void removeData(Context context,String key){
        SharedPreferences settings = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        settings.edit().remove(key).commit();
    }

}
