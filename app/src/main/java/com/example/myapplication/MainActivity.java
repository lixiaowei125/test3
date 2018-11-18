package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.os.Vibrator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int update=1;
    public static final int zhendong=2;

    Vibrator vibrator;

    Button hour_left,hour_right,min_left,min_right,second_left,second_right,begin,zero;

    Caozuo caozuo=new Caozuo();
    boolean BTimeThread=true;

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case update:
                    toButton(caozuo.run(toString()));
                    break;
                case zhendong:
                    vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    long[] pattern={750,250};
                    vibrator.vibrate(pattern,-1);
                default:
                    break;
            }
        }

        @Override
        public String toString(){
            return hour_left.getText().toString()+hour_right.getText().toString()+min_left.getText().toString()+min_right.getText().toString()+second_left.getText().toString()+second_right.getText().toString();
        }

        public void toButton(String str){
            hour_left.setText(str.charAt(0)+"");
            hour_right.setText(str.charAt(1)+"");
            min_left.setText(str.charAt(2)+"");
            min_right.setText(str.charAt(3)+"");
            second_left.setText(str.charAt(4)+"");
            second_right.setText(str.charAt(5)+"");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hour_left=(Button)findViewById(R.id.hour_left);
        hour_right=(Button)findViewById(R.id.hour_right);
        min_left=(Button)findViewById(R.id.min_left);
        min_right=(Button)findViewById(R.id.min_right);
        second_left=(Button)findViewById(R.id.second_left);
        second_right=(Button)findViewById(R.id.second_right);
        begin=(Button)findViewById(R.id.begin);
        zero=(Button)findViewById(R.id.zero);
        hour_left.setOnClickListener(this);
        hour_right.setOnClickListener(this);
        min_left.setOnClickListener(this);
        min_right.setOnClickListener(this);
        second_left.setOnClickListener(this);
        second_right.setOnClickListener(this);
        begin.setOnClickListener(this);
        zero.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.hour_left:
                hour_left.setText(caozuo.b(hour_left.getText().toString()));
                break;
            case R.id.hour_right:
                hour_right.setText(caozuo.b(hour_right.getText().toString()));
                break;
            case R.id.min_left:
                min_left.setText(caozuo.a(min_left.getText().toString()));
                break;
            case R.id.min_right:
                min_right.setText(caozuo.b(min_right.getText().toString()));
                break;
            case R.id.second_left:
                second_left.setText(caozuo.a(second_left.getText().toString()));
                break;
            case R.id.second_right:
                second_right.setText(caozuo.b(second_right.getText().toString()));
                break;
            case R.id.begin:
                if (begin.getText().equals("开始")) {
                    BTimeThread=true;
                    begin.setText("暂停");
                    new TimeThread().start();
                }else if (begin.getText().equals("暂停")) {
                    BTimeThread=false;
                    begin.setText("继续");
                }else if (begin.getText().equals("继续")) {
                    BTimeThread=true;
                    begin.setText("暂停");
                    new TimeThread().start();
                }
                caozuo.begin();
                break;
            case R.id.zero:
                BTimeThread=false;
                begin.setText("开始");
                toButton(caozuo.zero());
                break;
        }
        if(begin.getText().equals("开始")) zero.setText("清零");
        else zero.setText("结束");
    }

    @Override
    public String toString(){
        return hour_left.getText().toString()+hour_right.getText().toString()+min_left.getText().toString()+min_right.getText().toString()+second_left.getText().toString()+second_right.getText().toString();
    }

    public void toButton(String str){
        hour_left.setText(str.charAt(0)+"");
        hour_right.setText(str.charAt(1)+"");
        min_left.setText(str.charAt(2)+"");
        min_right.setText(str.charAt(3)+"");
        second_left.setText(str.charAt(4)+"");
        second_right.setText(str.charAt(5)+"");
    }

    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (BTimeThread&&!"000000".equals(toString())){
                try{
                    Thread.sleep(1000);
                    Message message=new Message();
                    message.what=update;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(BTimeThread&&"000000".equals(toString())){
                try{
                    System.out.println("123456");
                    Message message=new Message();
                    message.what=zhendong;
                    handler.sendMessage(message);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
