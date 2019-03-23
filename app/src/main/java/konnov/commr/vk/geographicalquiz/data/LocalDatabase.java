package konnov.commr.vk.geographicalquiz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.objects.Question;
import konnov.commr.vk.geographicalquiz.objects.Translation;


public class LocalDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "questions_db";

    private static final String QUESTION_TABLE = "questions";
    private static final String TRANSLATION_TABLE = "translations";

    static final String TRANSLATION_ID = "translation_id";
    static final String QUESTION_ID = "question_id";
    static final String LANGUAGE_ID = "language_id";
    static final String DIFFICULTY_LEVEL = "difficulty";
    static final String TITLE = "title";
    static final String IMG = "image_location";
    static final String ANSWER_1 = "answer_1";
    static final String ANSWER_2 = "answer_2";
    static final String ANSWER_3 = "answer_3";
    static final String ANSWER_4 = "answer_4";
    static final String RIGHT_ANSWER = "right_answer";
    static final String WRONG_ANSWER_COMMENT = "wrong_answer_comment";

    private Context mContext;
    private static LocalDatabase mInstance = null;
    private SQLiteDatabase db;

    public static LocalDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LocalDatabase(context.getApplicationContext());
        }
        return mInstance;
    }


    private LocalDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
        createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void deleteDB(){
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSLATION_TABLE);
        onCreate(db);
    }

    private void createTables(){
        String query ="CREATE TABLE " + QUESTION_TABLE + "(" +
                QUESTION_ID + " INTEGER PRIMARY KEY, " +
                DIFFICULTY_LEVEL + " INTEGER, " + //from 0 to 2 (easy, medium, hard)
                RIGHT_ANSWER + " INTEGER"  + ");"; //from 1 to 4
        db.execSQL(query) ;

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
        db.execSQL(query) ;
    }


    public void insertQuestions(HashMap<Integer, Question> questionsMap){
        try {
            for(Question question : questionsMap.values()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(QUESTION_ID, question.getQuestionId());
                contentValues.put(DIFFICULTY_LEVEL, question.getDifficulty());
                contentValues.put(RIGHT_ANSWER, question.getRightAnswer());
                db.insert(QUESTION_TABLE, null, contentValues);
                contentValues.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertTranslations(HashMap<Integer, Translation> translationMap){
        try {
            for (Translation translation : translationMap.values()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(QUESTION_ID, translation.getQuestionId());
                contentValues.put(LANGUAGE_ID, translation.getTranslationId());
                contentValues.put(TITLE, translation.getTitle());
                contentValues.put(IMG, translation.getImgLocation());
                contentValues.put(ANSWER_1, translation.getAnswerOne());
                contentValues.put(ANSWER_2, translation.getAnswerTwo());
                contentValues.put(ANSWER_3, translation.getAnswerThree());
                contentValues.put(ANSWER_4, translation.getAnswerFour());
                contentValues.put(WRONG_ANSWER_COMMENT, translation.getWrongAnswerComment());
                db.insert(TRANSLATION_TABLE, null, contentValues);
//                String query = "UPDATE " + TRANSLATION_TABLE +
//                        " SET " + QUESTION_ID + " = " + translation.getQuestionId() + ", " +
//                        LANGUAGE_ID + " = " + translation.getTranslationId() + ", " +
//                        TITLE + " = " + translation.getTitle() + ", " +
//                        IMG + " = " + translation.getImgLocation() + ", " +
//                        ANSWER_1 + " = " + translation.getAnswerOne() + ", " +
//                        ANSWER_2 + " = " + translation.getAnswerTwo() + ", " +
//                        ANSWER_3 + " = " + translation.getAnswerThree() + ", " +
//                        ANSWER_4 + " = " + translation.getAnswerFour() + ", " +
//                        WRONG_ANSWER_COMMENT + " = " + translation.getWrongAnswerComment() +
//                        " WHERE " + QUESTION_ID
//                db.execSQL();
                contentValues.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Question> getQuestions(){
        HashMap<Integer, Question> questionsMap = new HashMap<>();
        String selectQuery = "SELECT * FROM " + QUESTION_TABLE
                + " ORDER BY " + QUESTION_ID + " DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                int questionId = cursor.getInt(0);
                int difficultyLevel = cursor.getInt(1);
                int rightAnswer = cursor.getInt(2);
                Question question = new Question(questionId, difficultyLevel, rightAnswer);
                questionsMap.put(questionId, question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionsMap;
    }

    public HashMap<Integer, Translation> getTranslations(){
        HashMap<Integer, Translation> translationsMap = new HashMap<>();
        String selectQuery = "SELECT * FROM " + TRANSLATION_TABLE
                + " ORDER BY " + QUESTION_ID + " DESC";
//        String selectQuery = "SELECT * FROM " + TRANSLATION_TABLE;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                int questionId = cursor.getInt(1);
                String languageId = cursor.getString(2);  //TODO choose language based on phone settings
                String title = cursor.getString(3);
                String imgLocation = cursor.getString(4);
                String answerOne = cursor.getString(5);
                String answerTwo = cursor.getString(6);
                String answerThree = cursor.getString(7);
                String answerFour = cursor.getString(8);
                String wrongAnswerComment = cursor.getString(9);
                Translation translation = new Translation(questionId, languageId, title,
                        imgLocation, answerOne, answerTwo, answerThree, answerFour, wrongAnswerComment);
                translationsMap.put(questionId, translation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return translationsMap;
    }

}
