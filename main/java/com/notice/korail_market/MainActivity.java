package com.notice.korail_market;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.notice.korail_market.ItemData.Food;
import com.notice.korail_market.ItemData.ListAdapter;


import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView time_text;

    RecyclerView itemView;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Food> items = new ArrayList<>();







    Locale systemLocale;
    String local_str;

    Toolbar back_btn_tb;
    ImageButton tab_drink, tab_snack, home_btn;
    ScrollView sclview, sclview_snack, ScrollViewBottomBar;
    View drink_view, snack_view, viewBottomBar;

    View drinkW, drinkBlackBori, drinkKakao, drinkSkyBori, drinkCola, drinkCider, drinkHibiscus, drinkHong, drinkFantaPine, drinkPororo, mapleCorn, CaramelCorn, vgtime, churros, pong, sinjjang, crab, sindangtteokbokki, honeybutter, jagabe, whale, sweetpotatoSnack, ShrimpSnack, concheese, bakedOnion, cornChip;

    ImageButton addCart, paybtn1,itemAdd,itemOut;

    TextView totalPay;
    int total;
    int quantity;

    int dW;
    int blackBori;
    int KaKao;
    int SkyBori;
    int Cola;
    int Cider;
    int Hibiscus;
    int Hong;
    int FantaPine;
    int Pororo;

    String[] namee = {"식후비법 더블유", "블랙보리", "하늘보리","코카콜라","칠성사이다","카카오닙스차","히비스커스","홍도라지차","파인애플 환타","뽀로로"};
    int[] pricee = {1200,1500,1200,2000,1800,1800,1500,2000,2000,2500};

    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /********** Hide Action Bar *************/
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i("is on?", "off");
        } else {
            Log.i("is on?", "on");
        }
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        /*********** End *****************/

        back_btn_tb = (Toolbar) findViewById(R.id.back_btn_tb);
        setSupportActionBar(back_btn_tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        systemLocale = getApplicationContext().getResources().getConfiguration().locale;

        local_str = systemLocale.getLanguage();

        mtime_Handler.sendMessage(mtime_Handler.obtainMessage(0));


        // 리싸이클러뷰
        itemAdd = findViewById(R.id.itemAdd);
        itemView = findViewById(R.id.itemView);
        itemView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        itemView.setLayoutManager(mLayoutManager);


        ListAdapter listAdapter = new ListAdapter(items);
        itemView.setAdapter(listAdapter);


        // 리싸이클러뷰

        sclview = (ScrollView) findViewById(R.id.sclview);
        sclview_snack = (ScrollView) findViewById(R.id.sclview_snack);

        viewBottomBar = (View) findViewById(R.id.viewBottomBar);

        addCart = (ImageButton) findViewById(R.id.addCart);
        paybtn1 = (ImageButton) findViewById(R.id.paybtn1);

        totalPay = (TextView) findViewById(R.id.totalPay);

        drinkW = (View) findViewById(R.id.drinkW);
        drinkBlackBori = (View) findViewById(R.id.drinkBlackBori);
        drinkSkyBori = (View) findViewById(R.id.drinkSkyBori);
        drinkKakao = (View) findViewById(R.id.drinkKakao);
        drinkCola = (View) findViewById(R.id.drinkCola);
        drinkCider = (View) findViewById(R.id.drinkCider);
        drinkHibiscus = (View) findViewById(R.id.drinkHibiscus);
        drinkHong = (View) findViewById(R.id.drinkHong);
        drinkFantaPine = (View) findViewById(R.id.drinkFantaPine);
        drinkPororo = (View) findViewById(R.id.drinkPororo);

        mapleCorn = (View) findViewById(R.id.mapleCorn);
        CaramelCorn = (View) findViewById(R.id.CaramelCorn);
        vgtime = (View) findViewById(R.id.vgtime);
        churros = (View) findViewById(R.id.churros);
        pong = (View) findViewById(R.id.pong);
        sinjjang = (View) findViewById(R.id.sinjjang);
        crab = (View) findViewById(R.id.crab);
        sindangtteokbokki = (View) findViewById(R.id.sindangtteokbokki);
        honeybutter = (View) findViewById(R.id.honeybutter);
        jagabe = (View) findViewById(R.id.jagabe);
        whale = (View) findViewById(R.id.whale);
        sweetpotatoSnack = (View) findViewById(R.id.sweetpotatoSnack);
        ShrimpSnack = (View) findViewById(R.id.shrimpSnack);
        concheese = (View) findViewById(R.id.concheese);
        bakedOnion = (View) findViewById(R.id.bakedOnion);
        cornChip = (View) findViewById(R.id.cornChip);

        tab_drink = (ImageButton) findViewById(R.id.tab_drink);
        tab_snack = (ImageButton) findViewById(R.id.tab_snack);
        home_btn = (ImageButton) findViewById(R.id.home_btn);
        time_text = (TextView) findViewById(R.id.time_text);

        tab_drink.setOnClickListener(tabtab);
        tab_snack.setOnClickListener(tabtab);

        sclview_snack.setVisibility(View.INVISIBLE);

        // start

        drinkW.setOnClickListener(addFood);
        drinkBlackBori.setOnClickListener(addFood);
        drinkSkyBori.setOnClickListener(addFood);
        drinkKakao.setOnClickListener(addFood);
        drinkCola.setOnClickListener(addFood);
        drinkCider.setOnClickListener(addFood);
        drinkHibiscus.setOnClickListener(addFood);
        drinkHong.setOnClickListener(addFood);
        drinkFantaPine.setOnClickListener(addFood);
        drinkPororo.setOnClickListener(addFood);




        // end


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String launcher = "com.korail.motrex.launcher";
                Intent intent = getPackageManager().getLaunchIntentForPackage(launcher);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCartPage = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intentCartPage);
            }
        });

    }



    private Handler mtime_Handler = new Handler() {
        public void handleMessage(Message msg) {

            try {
                Date date = new Date(System.currentTimeMillis());
                if ("ko".equals(local_str)) {
                    String formattedDate = new SimpleDateFormat("MM월 dd일 HH:mm", Locale.KOREA).format(date);
                    time_text.setText(formattedDate);
                } else {
                    String formattedDate = new SimpleDateFormat("MMM dd HH:mm", Locale.ENGLISH).format(date);
                    time_text.setText(formattedDate);
                }

            } catch (Exception e) {

            }
            mtime_Handler.sendMessageDelayed(mtime_Handler.obtainMessage(0), 500);

        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    View.OnClickListener tabtab = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tab_drink: {
                    tab_drink.setImageResource(R.drawable.order_tab_01_sel);
                    tab_snack.setImageResource(R.drawable.order_tab_02_nor);
                    sclview.setVisibility(View.VISIBLE);
                    sclview_snack.setVisibility(View.INVISIBLE);
                }
                break;
                case R.id.tab_snack: {
                    tab_drink.setImageResource(R.drawable.order_tab_01_nor);
                    tab_snack.setImageResource(R.drawable.order_tab_02_sel);
                    sclview_snack.setVisibility(View.VISIBLE);
                    sclview.setVisibility(View.INVISIBLE);

                }
            }
        }
    };

    public static int grandTotal(ArrayList <Food> items){

        int total = 0;
        for(int i = 0 ; i < items.size(); i++) {
            total += items.get(i).totalPrice();
        }
        return total;
    }

    View.OnClickListener addFood = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            ListAdapter listAdapter = new ListAdapter(items);
            itemView.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
            //price = Integer.parseInt(items.get();
            totalPay.setText(Integer.toString(grandTotal(items))+"원");


        switch (view.getId()){
            case R.id.drinkW:{
                items.add(new Food(namee[0],pricee[0],1,R.drawable.order_checked));

            }
            break;
            case R.id.drinkBlackBori:{
                items.add(new Food(namee[1],pricee[1],1,R.drawable.order_checked));

            }
            break;
            case R.id.drinkSkyBori:{
                items.add(new Food(namee[2],pricee[2],1,R.drawable.order_checked));

            }
            break;
         }

        }


    };



}


