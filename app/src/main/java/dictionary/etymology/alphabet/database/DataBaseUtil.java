package dictionary.etymology.alphabet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import dictionary.etymology.alphabet.database.databean.WordBean;
import dictionary.etymology.alphabet.greendao.gen.DaoMaster;
import dictionary.etymology.alphabet.greendao.gen.DaoSession;
import dictionary.etymology.alphabet.greendao.gen.WordBeanDao;

/**
 * Created by CN-11 on 2017/8/22.
 */

public class DataBaseUtil {
    private static DaoSession daoSession;

    public static synchronized DaoSession getDaoSession(Context context) {
        if(daoSession == null){
            DaoMaster.DevOpenHelper helper = new CustomDevOpenHelper(context, "etymology.db");
            SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public WordBean getWord(Context context,String wold){
        return  getDaoSession(context).getWordBeanDao().queryBuilder().where(WordBeanDao.Properties.Word.eq(wold)).build().unique();

    }

    public List<WordBean> getAllWord(Context context){
        return getDaoSession(context).getWordBeanDao().queryBuilder().build().forCurrentThread().list();
    }

    public List<WordBean> getMatchedWords(Context context,String str){
        return getDaoSession(context).getWordBeanDao().queryBuilder().where(WordBeanDao.Properties.Word.like("%"+str+"%")).build().list();
    }

//    public void saveContacts(Context context, List<Contact> contacts){
//        getDaoSession(context).getContactDao().deleteAll();
//        getDaoSession(context).getContactDao().insertInTx(contacts);
//    }
}
