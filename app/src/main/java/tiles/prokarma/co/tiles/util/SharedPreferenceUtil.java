package tiles.prokarma.co.tiles.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by USER on 9/6/2019.
 */

public class SharedPreferenceUtil {

    public static final String PREF_NAME = "tilesPrefs";
    public static final String BEST_SCORE = "bestScore";

    Context mContext;

    SharedPreferences sharedPreferences;

    public SharedPreferenceUtil(Context context){
        mContext = context;
    }

    public void saveBestAttempts(String attempts){
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(BEST_SCORE, attempts);
        editor.commit();
        Toast.makeText(mContext,"Congratulations! this is your best score",Toast.LENGTH_LONG).show();
    }

    public String getBestAttempts(){
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BEST_SCORE, "0");
    }
}
