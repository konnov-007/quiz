package konnov.commr.vk.geographicalquiz.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import konnov.commr.vk.geographicalquiz.interfaces.Interfaces;
import konnov.commr.vk.geographicalquiz.objects.Question;
import konnov.commr.vk.geographicalquiz.objects.Translation;

public class UserRepository {
    private DatabaseReference mRootRefQuestions = FirebaseDatabase.getInstance().getReference("questions");
    private DatabaseReference mRootRefTranslations = FirebaseDatabase.getInstance().getReference("translations");
    private GenericTypeIndicator<ArrayList<Object>> objectsGTypeInd = new GenericTypeIndicator<ArrayList<Object>>() {};
    private Interfaces interfaces = Interfaces.getInstance();

    private static UserRepository mInstance = null;
    public static UserRepository getInstance() {
        if (mInstance == null) {
            mInstance = new UserRepository();
        }
        return mInstance;
    }

    private UserRepository(){
        startListeningToServer();
    }

    private void startListeningToServer(){
        mRootRefQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> objectsList = dataSnapshot.getValue(objectsGTypeInd);
                interfaces.reportQuestionsReceived(generateQuestionsMap(objectsList));
                System.out.println(objectsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        mRootRefTranslations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> objectsList = dataSnapshot.getValue(objectsGTypeInd);
                interfaces.reportTranslationsReceived(generateTranslationsMap(objectsList));
                System.out.println(objectsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private HashMap<Integer, Question> generateQuestionsMap(ArrayList<Object> objectList) {
        HashMap<Integer, Question> questionsMap = new HashMap<>();
        for(Object object : objectList) {
            HashMap <String, Long> questionSet = (HashMap<String, Long>) object;
            long questionId = questionSet.get(LocalDatabase.QUESTION_ID);
            long questionDifficulty = questionSet.get(LocalDatabase.DIFFICULTY_LEVEL);
            long questionRightAnswer = questionSet.get(LocalDatabase.RIGHT_ANSWER);
            Question question = new Question((int) questionId, (int) questionDifficulty, (int) questionRightAnswer);
            questionsMap.put((int) questionId, question);
        }
        return questionsMap;
    }

    private HashMap<Integer, Translation> generateTranslationsMap(ArrayList<Object> objectList) {
        HashMap<Integer, Translation> questionsMap = new HashMap<>();
        for(Object object : objectList) {
            HashMap <String, Object> questionSet = (HashMap<String, Object>) object;
            long questionId = (Long) questionSet.get(LocalDatabase.QUESTION_ID);
            String languageId = (String) questionSet.get(LocalDatabase.LANGUAGE_ID);
            String title = (String) questionSet.get(LocalDatabase.TITLE);
            String img = (String) questionSet.get(LocalDatabase.IMG);
            String answerOne = (String) questionSet.get(LocalDatabase.ANSWER_1);
            String answerTwo = (String) questionSet.get(LocalDatabase.ANSWER_2);
            String answerThree = (String) questionSet.get(LocalDatabase.ANSWER_3);
            String answerFour = (String) questionSet.get(LocalDatabase.ANSWER_4);
            String wrongAnswerComment = (String) questionSet.get(LocalDatabase.WRONG_ANSWER_COMMENT);
            Translation translation = new Translation((int) questionId, languageId, title,
                    img, answerOne, answerTwo, answerThree, answerFour, wrongAnswerComment);
            questionsMap.put((int) questionId, translation);
        }
        return questionsMap;
    }
}
