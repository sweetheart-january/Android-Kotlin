package com.example.calcapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calcapplication.base.BaseActivity;

public class Main2Activity extends BaseActivity {

    private TextView tv;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initLayout() {
        tv = findViewById(R.id.calc);

        CalcResult obj = (CalcResult) getIntent().getSerializableExtra("result");

        float sum = 0f;

        //根据运算类型选择对应的运算方式
        switch (obj.getFlag()){
            case "➗":sum = Float.parseFloat(obj.getNum1()) / Float.parseFloat(obj.getNum2());break;
            case "✖":sum = Float.parseFloat(obj.getNum1()) * Float.parseFloat(obj.getNum2());break;
            case "➕":sum = Float.parseFloat(obj.getNum1()) + Float.parseFloat(obj.getNum2());break;
            case "➖":sum = Float.parseFloat(obj.getNum1()) - Float.parseFloat(obj.getNum2());break;
        }

        //最后将结果设置到“TextView”中
        tv.setText(obj.getNum1()+obj.getFlag()+obj.getNum2()+" = "+sum);

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        tv = findViewById(R.id.calc);
//
//
//        CalcResult obj = (CalcResult) getIntent().getSerializableExtra("result");
//
//        float sum = 0f;
//
//        //根据运算类型选择对应的运算方式
//        switch (obj.getFlag()){
//            case "➗":sum = Float.parseFloat(obj.getNum1()) / Float.parseFloat(obj.getNum2());break;
//            case "✖":sum = Float.parseFloat(obj.getNum1()) * Float.parseFloat(obj.getNum2());break;
//            case "➕":sum = Float.parseFloat(obj.getNum1()) + Float.parseFloat(obj.getNum2());break;
//            case "➖":sum = Float.parseFloat(obj.getNum1()) - Float.parseFloat(obj.getNum2());break;
//        }
//
//        //最后将结果设置到“TextView”中
//        tv.setText(obj.getNum1()+obj.getFlag()+obj.getNum2()+" = "+sum);
//
//
//    }


}
