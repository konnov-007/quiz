package konnov.commr.vk.geographicalquiz.data.source.local;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import konnov.commr.vk.geographicalquiz.data.pojo.Image;

public class BitmapStorage {

    private static BitmapStorage INSTANCE;

    private static final Object sLock = new Object();

    private Context mContext;

    private BitmapStorage(Context context) {
        mContext = context;
    }

    public static BitmapStorage getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = new BitmapStorage(context);
            }
            return INSTANCE;
        }
    }

    public void saveImages(ArrayList<Image> images) {
        for(Image image: images) {
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


}
