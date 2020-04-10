package com.example.calcapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calcapplication.base.BaseActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private List<Button> btn = new ArrayList<>();//用来存储所有按钮，方便管理和使用
    private TextView resultView;//用来实时显示用户输入的数据
    private String sum = "";//用来拼接用户输入的数据并在TextView中显示
    private String flag = "";//用来标记当前选择的运算类型
    private String[] sqlitArr;//用来接收分割成功后的字符数组
    private CalcResult calc;//用来保存运算数据的对象

    //所有按钮的id
    private int[] btnId={R.id.clear,R.id.quit,R.id.mode,R.id.chufa,R.id.one,R.id.two,R.id.three,R.id.chengfa,
            R.id.four,R.id.five,R.id.six,R.id.jiafa,R.id.seven,R.id.eight,R.id.nine,R.id.jianfa,R.id.onehundred,R.id.zero,R.id.dot,R.id.dengyu};

    //返回要显示的布局文件
    @Override
    protected int getLayout() {
        return R.layout.activity_main;//返回给基类要显示的布局id
    }

    //初始化控件
    @Override
    protected void initBtn() {
        resultView = findViewById(R.id.result);
        //查找并存入集合中
        for (int i = 0;i<btnId.length;i++){
            btn.add((Button)findViewById(btnId[i]));
        }
        //依次取出绑定点击事件
        for (int i = 0;i<btn.size();i++){
            btn.get(i).setOnClickListener(this);//设置按钮监听事件为本身,因为自身实现了“View.OnClickListener”接口
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //用户点击“清除”按钮
            case R.id.clear:  sum = ""; flag = ""; Toast.makeText(this,"清除完成",Toast.LENGTH_SHORT).show();break;
            //用户点击“退出”按钮
            case R.id.quit: System.exit(0);//退出应用程序
                break;
            //用户点击“模除”按钮
            case R.id.mode:  sum+= "%";
                break;
            //用户点击“除法”按钮
            case R.id.chufa:  sum+= "➗";flag = "➗";
                break;
            //用户点击数字“1”
            case R.id.one:  sum+="1";
                break;
            //用户点击数字“2”
            case R.id.two:  sum += "2";
                break;
            //用户点击数字“3”
            case R.id.three:  sum += "3";
                break;
            //用户点击“乘法”按钮
            case R.id.chengfa:  sum+= "✖";flag = "✖";
                break;
            //用户点击数字“4”
            case R.id.four:  sum += "4";
                break;
            //用户点击数字“5”
            case R.id.five: sum += "5";
                break;
            //用户点击数字“6”
            case R.id.six:  sum += "6";
                break;
            //用户点击“加法”按钮
            case R.id.jiafa:  sum+= "➕";flag = "➕";
                break;
            //用户点击数字“7”
            case R.id.seven: sum += "7";
                break;
            //用户点击数字“8”
            case R.id.eight: sum += "8";
                break;
            //用户点击数字“9”
            case R.id.nine: sum += "9";
                break;
            //用户点击“减法”按钮
            case R.id.jianfa:  sum+= "➖";flag = "➖";
                break;
            //用户点击数字“100”
            case R.id.onehundred:  sum += "00";
                break;
            //用户点击数字“0”
            case R.id.zero:  sum += "0";
                break;
            //用户点击“小数点”
            case R.id.dot:  sum += ".";flag = ".";
                break;
            //用户点击“等于”按钮
            case R.id.dengyu:  isSumDispose(sum,flag);
                break;
        }

        //简单判断   用来判断当前是否点击了清除按钮，如果点击了就应该显示“0”,但如果sum的值已经不为“0”了,就应该返回sum自身而不是“0”
        resultView.setText(sum.equals("")?"0":sum);
    }

    //计算结果处理: 按运算类型分割字符串，并将分割好的字符串保存到一个对象中，将该对象传递到另一个Activity中
    private void isSumDispose(String sum,String flag){
        switch (flag){
            case "➗": sqlitArr = sum.split("➗");dataSave(sqlitArr); break;
            case "✖": sqlitArr = sum.split("✖");dataSave(sqlitArr); break;
            case "➕": sqlitArr = sum.split("➕");dataSave(sqlitArr); break;
            case "➖": sqlitArr = sum.split("➖");dataSave(sqlitArr); break;
            default: Toast.makeText(MainActivity.this,"请选择要进行何种运算",Toast.LENGTH_SHORT).show();
        }
        //清除当前的运算状态
        flag = "";
    }

    //结果保存
    private void dataSave(String[] arr){
        //拿到用户输入的内容并保存在“calc”对象中
        calc = new CalcResult(arr[0],arr[1],flag);

        //匿名创建一个Intent对象并传入要序列化的对象，跳转到“Main2Activity”
        startActivity(new Intent(MainActivity.this,Main2Activity.class).putExtra("result",calc));
    }
}




//创建一个类，用来保存计算的结果并实现Serializable接口
class CalcResult implements  Serializable{
    private String num1;//用户输入的数字1
    private String num2;//用户输入的数字2
    private String flag;//用户输入的运算形式

    public CalcResult() {
    }

    public CalcResult(String num1, String num2, String flag) {
        this.num1 = num1;
        this.num2 = num2;
        this.flag = flag;
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }

    public String getFlag() {
        return flag;
    }
}
