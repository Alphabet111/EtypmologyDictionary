package dictionary.etymology.alphabet.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import dictionary.etymology.alphabet.greendao.gen.DaoMaster;

/**
 * Created by CN-11 on 2017/8/22.
 */

public class CustomDevOpenHelper extends DaoMaster.DevOpenHelper {
    public CustomDevOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

    }
}
