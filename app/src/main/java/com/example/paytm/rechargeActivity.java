package com.example.paytm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class rechargeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ContentResolver contentResolver;
    Cursor cursor;
    ContactModel contactModel;
    ContactAdapter contactAdapter;
    TextView textView;
    List<ContactModel> contactModelList = new ArrayList<>();
    List<ContactModel> filterList = new ArrayList<>();
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        recyclerView = findViewById(R.id.rewedget);
        textView = findViewById(R.id.totalcount);


        searchView = findViewById(R.id.search_view);
        if (ContextCompat.checkSelfPermission(rechargeActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(rechargeActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 201);
        } else {
            loadaContacts();
            searchView.setQueryHint("" + "Search among" + contactModelList.size() + "contacts(s)");
        }

        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList.clear();
                if (newText.toString().isEmpty()) {
                    textView.setVisibility(View.GONE);
                    recyclerView.setAdapter(new ContactAdapter(getApplicationContext(), contactModelList));
                    contactAdapter.notifyDataSetChanged();
                } else {
                    Filter(newText.toString());
                    recyclerView.setAdapter(new ContactAdapter(getApplicationContext(), filterList));
                    contactAdapter.notifyDataSetChanged();
                }

                return true;
            }


        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactAdapter = new ContactAdapter(this, contactModelList);
        recyclerView.setAdapter(contactAdapter);
    }
    private void Filter(String text) {

        textView.setVisibility(View.VISIBLE);


        for (ContactModel post:contactModelList) {
            if (post.getName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(post);
            }
        }
        recyclerView.setAdapter(new ContactAdapter(getApplicationContext(), filterList));
        contactAdapter.notifyDataSetChanged();
        textView.setText(" "+filterList.size()+" "+"CONTACTS FOUND");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 201:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    loadaContacts();
                }
                else
                {
                    Toast.makeText(rechargeActivity.this, "call Log Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void loadaContacts() {
        contactModelList.clear();
        contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projections = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Photo.PHOTO_URI};
        String selection = null;
        String[] args = null;

        String order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" asc";
        cursor = contentResolver.query(uri, projections, selection, args, order);
        if (cursor.getCount() > 0 && cursor != null)
        {
            while (cursor.moveToNext())
            {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                @SuppressLint("Range") String photo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI));

                contactModel = new ContactModel(name, number, photo);
                contactModelList.add(contactModel);

                contactAdapter = new ContactAdapter(getApplicationContext(),contactModelList);
                recyclerView.setAdapter(contactAdapter);

            }

        }else
        {
            Toast.makeText(rechargeActivity.this, "No Contacts Found In Your Device", Toast.LENGTH_SHORT).show();
        }



    }
}