package com.example.paytm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    GameModel gameModel;
    GameAdapter gameAdapter;
    List<GameModel> gameModelList = new ArrayList<>();

    private static final int CAMERA_PERMISSION_CODE = 101;
    private static final int FILE_SHARE_PERMISSION = 102;


    private List<The_Slider_Item_Model_Class> listItems;
    private ViewPager page;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclar_h);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        gameAdapter = new GameAdapter(getApplicationContext(), gameModelList);
        recyclerView.setAdapter(gameAdapter);
        loadHorizantalMethod();

        findViewById(R.id.toselfid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ToselfActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.personid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ScreenActivity.class);
                startActivity(intent);

            }


        });
        findViewById(R.id.searchid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,rechargeActivity.class);
                startActivity(intent);

            }
        });

        page = findViewById(R.id.my_pager);
        tabLayout = findViewById(R.id.my_tablayout);

        listItems = new ArrayList<>();
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.auto1));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.auto2));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.auto3));
        The_Slider_item_Page_Adapter itempager_adapter = new The_Slider_item_Page_Adapter(this, listItems);
        page.setAdapter(itempager_adapter);

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slider_timer(), 2000, 3000);
        tabLayout.setupWithViewPager(page, true);
    }

    private void loadHorizantalMethod() {
        gameModel = new GameModel(R.drawable.all,"All Services");
        gameModelList.add(gameModel);
        gameModel = new GameModel(R.drawable.earn,"Refer & Earn");
        gameModelList.add(gameModel);
        gameModel = new GameModel(R.drawable.bike1,"Bike Insurance");
        gameModelList.add(gameModel);
        gameModel = new GameModel(R.drawable.cash,"Redeem Now");
        gameModelList.add(gameModel);
        gameModel = new GameModel(R.drawable.re,"Recharge & Win");
        gameModelList.add(gameModel);
        gameModel = new GameModel(R.drawable.heaith1,"Health Pass");
        gameModelList.add(gameModel);
        gameModel = new GameModel(R.drawable.autop,"Paytm Autopay");
        gameModelList.add(gameModel);
        gameModel = new GameModel(R.drawable.car1,"Car Insurance");
        gameModelList.add(gameModel);
        gameAdapter.notifyDataSetChanged();
    }

    public class The_slider_timer extends TimerTask {

            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (page.getCurrentItem() < listItems.size() - 1) {
                            page.setCurrentItem(page.getCurrentItem() + 1);
                        } else
                            page.setCurrentItem(0);


                        findViewById(R.id.tomobileid).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent1 = new Intent(MainActivity.this, rechargeActivity.class);
                                startActivity(intent1);

                            }
                        });

                        findViewById(R.id.scanmain).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "scan", Toast.LENGTH_SHORT).show();
                                if (Build.VERSION.SDK_INT >= 23) {
                                    if (checkPermission(Manifest.permission.CAMERA)) {
                                        openScanner();
                                    } else {
                                        requestPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
                                    }
                                } else {
                                    openScanner1();
                                }
                            }
                        });


                        findViewById(R.id.conimg).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, rechargeActivity.class);
                                startActivity(intent);
                            }
                        });


                        findViewById(R.id.scanandpay).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    if (checkPermission(Manifest.permission.CAMERA)) {
                                        openScanner();
                                    } else {
                                        requestPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
                                    }
                                } else {
                                    openScanner();
                                }

                            }
                        });
                    }
                });
            }
        }

    private void openScanner1() {
        new IntentIntegrator(MainActivity.this).initiateScan();
    }

    private void openScanner() {
        new IntentIntegrator(MainActivity.this).initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
            } else {
//                .setText("" + result.getContents());
            }
        } else {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission(String permision, int code) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permision)) {

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permision}, code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openScanner();
                }
        }
    }


}