package sv.edu.ues.fia.eisi.fia;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceSingleton {
    private static sv.edu.ues.fia.eisi.fia.PreferenceSingleton mInstance;
    private Context mContext;
    //
    private SharedPreferences mMyPreferences;

    private PreferenceSingleton(){ }

    public static sv.edu.ues.fia.eisi.fia.PreferenceSingleton getInstance(){
        if (mInstance == null) mInstance = new sv.edu.ues.fia.eisi.fia.PreferenceSingleton();
        return mInstance;
    }

    public void Initialize(Context ctxt){
        mContext = ctxt;
        mMyPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public void writePreference(String key, String value){
        SharedPreferences.Editor e = mMyPreferences.edit();
        e.putString(key, value);
        e.apply();
    }

    public String readPreference(String key){
        return mMyPreferences.getString(key,"");
    }
}
