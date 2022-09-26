package com.example.paytm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScreenActivity extends AppCompatActivity {

    ImageView imageView,person;

    Bitmap bitmap = null;

    byte img[];

    private MyDatabase mdb = null;

    private SQLiteDatabase db = null;

    private Cursor c = null;

    private static final String DATABASE_NAME = "ImageDb.db";

    public static final int DATABASE_VERSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        imageView = (ImageView) findViewById(R.id.camaraid);
        person=(ImageView) findViewById(R.id.personimgId);


        mdb = new MyDatabase(getApplicationContext(), DATABASE_NAME, null,

                DATABASE_VERSION);
    }

    public void camara(View view) {


        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, 0);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {

            Uri selectedImage = data.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),


                        selectedImage);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

                img = bos.toByteArray();

                person.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}
