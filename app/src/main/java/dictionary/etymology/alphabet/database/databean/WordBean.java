package dictionary.etymology.alphabet.database.databean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by alphabet on 2018/1/30.
 */
@Entity(nameInDb = "etymology",createInDb = false)
public class WordBean {
    @Property(nameInDb = "word")
    private String word;
    @Property(nameInDb = "description")
    private String description;
    @Generated(hash = 847938890)
    public WordBean(String word, String description) {
        this.word = word;
        this.description = description;
    }
    @Generated(hash = 1596526216)
    public WordBean() {
    }
    public String getWord() {
        return this.word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
