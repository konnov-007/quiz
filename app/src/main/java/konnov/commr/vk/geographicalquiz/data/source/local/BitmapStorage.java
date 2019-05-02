package konnov.commr.vk.geographicalquiz.data.source.local;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import konnov.commr.vk.geographicalquiz.data.pojo.Image;
import konnov.commr.vk.geographicalquiz.util.AppExecutors;

public class BitmapStorage {

    private static BitmapStorage INSTANCE;

    private static final Object sLock = new Object();

    private Context mContext;

    private AppExecutors mAppExecutors;

    private BitmapStorage(Context context, AppExecutors appExecutors) {
        mContext = context;
        mAppExecutors = appExecutors;
    }

    public static BitmapStorage getInstance(Context context, AppExecutors appExecutors) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = new BitmapStorage(context, appExecutors);
            }
            return INSTANCE;
        }
    }

    public void saveImages(final ArrayList<Image> images) {
        Runnable savingImagesRunnable = new Runnable() {
            @Override
            public void run() {
                for (Image image : images) {
                    try {
                        String filename = image.getFilename();
                        FileOutputStream fos = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        image.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
                        fos.write(stream.toByteArray());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mAppExecutors.diskIO().execute(savingImagesRunnable);
    }

    public Bitmap getImage(String filename) {
        FileInputStream fis = null;
        try {
            fis = mContext.openFileInput(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllImages() {
        Runnable deleteFilesRunnable = new Runnable() {
            @Override
            public void run() {
                File dir = mContext.getFilesDir();
                File[] allFiles = dir.listFiles();
                for (File file : allFiles) {
                    file.delete();
                }
            }
        };
        mAppExecutors.diskIO().execute(deleteFilesRunnable);
    }

}
