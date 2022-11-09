package com.notice.korail_market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PayActivity extends AppCompatActivity {

    TextView time_text;   //시간 불러오는 텍스트 위젯
    Locale systemLocale;  //국가코드 불러오기위한 키워드
    String local_str;     //Locale에서 가져온 시간 값을 string 형식으로 textview위젯에 반환
    ImageButton home_btn,credit_btn;
    View pay_view,qr_view,order_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

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

        Toolbar back_btn_tb = (Toolbar) findViewById(R.id.back_btn_tb);
        setSupportActionBar(back_btn_tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        order_view = (View)findViewById(R.id.order_view);
        credit_btn = (ImageButton)findViewById(R.id.credit_btn);
        pay_view = (View) findViewById(R.id.pay_view);
        qr_view = (View) findViewById(R.id.qr_view);
        time_text = (TextView)findViewById(R.id.time_text);  // 변수 초기화
        systemLocale = getApplicationContext().getResources().getConfiguration().locale;
        local_str = systemLocale.getLanguage();
        mtime_Handler.sendMessage( mtime_Handler.obtainMessage(0));

        qr_view.setVisibility(View.INVISIBLE);
        pay_view.setVisibility(View.VISIBLE);
        order_view.setVisibility(View.INVISIBLE);

        home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String launcher = "com.korail.motrex.launcher";
                Intent intent = getPackageManager().getLaunchIntentForPackage(launcher);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        credit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_view.setVisibility(View.INVISIBLE);
                qr_view.setVisibility(View.INVISIBLE);
                order_view.setVisibility(View.VISIBLE);

            }
        });

    }

    private Handler mtime_Handler = new Handler(){
        public void handleMessage(Message msg) {

            try {
                Date date = new Date(System.currentTimeMillis());
                if("ko".equals(local_str)){
                    String formattedDate = new SimpleDateFormat("MM월 dd일 HH:mm", Locale.KOREA).format(date);
                    time_text.setText(formattedDate);
                }else{
                    String formattedDate = new SimpleDateFormat("MMM dd HH:mm", Locale.ENGLISH).format(date);
                    time_text.setText(formattedDate);
                }

            }catch (Exception e){

            }
            mtime_Handler.sendMessageDelayed(mtime_Handler.obtainMessage(0), 500);

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
