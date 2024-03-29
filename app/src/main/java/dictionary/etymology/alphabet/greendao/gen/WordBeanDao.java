package dictionary.etymology.alphabet.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import dictionary.etymology.alphabet.database.databean.WordBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "etymology".
*/
public class WordBeanDao extends AbstractDao<WordBean, Void> {

    public static final String TABLENAME = "etymology";

    /**
     * Properties of entity WordBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Word = new Property(0, String.class, "word", false, "word");
        public final static Property Description = new Property(1, String.class, "description", false, "description");
    }


    public WordBeanDao(DaoConfig config) {
        super(config);
    }
    
    public WordBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WordBean entity) {
        stmt.clearBindings();
 
        String word = entity.getWord();
        if (word != null) {
            stmt.bindString(1, word);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(2, description);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WordBean entity) {
        stmt.clearBindings();
 
        String word = entity.getWord();
        if (word != null) {
            stmt.bindString(1, word);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(2, description);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public WordBean readEntity(Cursor cursor, int offset) {
        WordBean entity = new WordBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // word
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // description
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WordBean entity, int offset) {
        entity.setWord(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setDescription(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(WordBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(WordBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(WordBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
