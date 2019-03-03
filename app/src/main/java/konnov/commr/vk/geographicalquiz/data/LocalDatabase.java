package konnov.commr.vk.geographicalquiz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LocalDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "questions_db";

    private static final String QUESTION_TABLE = "questions";
    private static final String TRANSLATION_TABLE = "translations";

    private static final String TRANSLATION_ID = "translation_id";
    private static final String QUESTION_ID = "question_id";
    private static final String LANGUAGE_ID = "language_id";
    private static final String DIFFICULTY_LEVEL = "difficulty";
    private static final String TITLE = "title";
    private static final String IMG = "image_location";
    private static final String ANSWER_1 = "answer_1";
    private static final String ANSWER_2 = "answer_2";
    private static final String ANSWER_3 = "answer_3";
    private static final String ANSWER_4 = "answer_4";
    private static final String RIGHT_ANSWER = "right_answer";
    private static final String WRONG_ANSWER_COMMENT = "wrong_answer_comment";

    private Context mContext;
    private static LocalDatabase mInstance = null;

    public static LocalDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LocalDatabase(context.getApplicationContext());
        }
        return mInstance;
    }


    private LocalDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void deleteDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSLATION_TABLE);
        onCreate(db);
    }

    private void createTables(SQLiteDatabase sqLiteDatabase){
        String query ="CREATE TABLE " + QUESTION_TABLE + "(" +
                QUESTION_ID + " INTEGER PRIMARY KEY, " +
                DIFFICULTY_LEVEL + " INTEGER, " + //from 0 to 2 (easy, medium, hard)
                RIGHT_ANSWER + " INTEGER"  + ");"; //from 1 to 4
        sqLiteDatabase.execSQL(query) ;

        query = "CREATE TABLE " + TRANSLATION_TABLE + "(" +
                TRANSLATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTION_ID + "INTEGER" +
                LANGUAGE_ID + "TEXT" + //string such as "en" for english, "ru" for Russian
                TITLE + " TEXT, " +
                IMG + " TEXT, " +
                ANSWER_1 + " TEXT, " +
                ANSWER_2 + " TEXT, " +
                ANSWER_3 + " TEXT, " +
                ANSWER_4 + " TEXT, " +
                WRONG_ANSWER_COMMENT + " TEXT" + ");";
        sqLiteDatabase.execSQL(query) ;
    }


//    public void insertQuestion(QuestionTest question){ //TODO
//        HashMap<Integer, Object> questionMap = question.getQuestionMap();
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(RIGHT_ANSWER, (Integer) questionMap.get(QuestionTest.RIGHT_ANSWER_MAP_KEY));
//        sqLiteDatabase.insert(QUESTION_TABLE, null, contentValues);
//        contentValues.clear();
//        contentValues.put(TRANSLATION_ID, );
//    }

//    public void addTranslation(QuestionTest question){ //TODO
//        HashMap<Integer, Object> translationMap = question.getTranslationMap();
//
//    }

}
