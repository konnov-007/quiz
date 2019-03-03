package konnov.commr.vk.geographicalquiz.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import konnov.commr.vk.geographicalquiz.interfaces.Interfaces;

public class DataStorage {
    private static DataStorage mInstance = null;
    private DatabaseReference mRootRefQuestions = FirebaseDatabase.getInstance().getReference("questions");
    private DatabaseReference mRootRefTranslations = FirebaseDatabase.getInstance().getReference("translations");
    private GenericTypeIndicator<ArrayList<Object>> objectsGTypeInd = new GenericTypeIndicator<ArrayList<Object>>() {};
    private Interfaces interfaces = Interfaces.getInstance();

    public static DataStorage getInstance() {
        if (mInstance == null) {
            mInstance = new DataStorage();
        }
        return mInstance;
    }

    private DataStorage(){
        startListeningToServer();
    }

    private void startListeningToServer(){
        mRootRefQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                interfaces.reportQuestionsReceived(objectHashMap);
                System.out.println(objectHashMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        mRootRefTranslations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                interfaces.reportTranslationsReceived(objectHashMap);
                System.out.println(objectHashMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
