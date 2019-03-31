package konnov.commr.vk.geographicalquiz.data.source.local;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import konnov.commr.vk.geographicalquiz.data.Entries;
import konnov.commr.vk.geographicalquiz.data.pojo.Translation;

@Dao
public interface TranslationsDao {

    @Query("SELECT * FROM " + Entries.TRANSLATION_TABLE)
    List<Translation> getTranslations();

    @Query("DELETE FROM " + Entries.TRANSLATION_TABLE)
    void deleteTranslations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTranslation(Translation translation);

}
