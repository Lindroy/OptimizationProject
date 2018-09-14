package com.lindroid.optimizationproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


/**
 * http://www.android-doc.com/guide/topics/resources/layout-resource.html
 */
public class ViewOptimizationActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewStub viewStub;
    private boolean isViewStubShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_optimization);
        TextView tvInclude1 = findViewById(R.id.tv_include1);
        TextView tvInclude2 = findViewById(R.id.tv_include2);
        tvInclude1.setText("1.1 常规下的include布局");
        tvInclude2.setText("1.2 设置了右边距的include布局");

        TextView tvSame = findViewById(R.id.tv_same);
        tvSame.setText("1.3 这里的TextView的ID是tv_same");
        FrameLayout viewSame = findViewById(R.id.view_same);
        TextView tvSame2 = viewSame.findViewById(R.id.tv_same);
        tvSame2.setText("1.3 这里的TextView的ID也是tv_same");

        Button btnShow = findViewById(R.id.btn_show);
        Button btnHide = findViewById(R.id.btn_hide);
        Button btnControl = findViewById(R.id.btn_control);
        btnShow.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        btnControl.setOnClickListener(this);

        viewStub = findViewById(R.id.view_stub);
        viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub viewStub, View view) {
                isViewStubShow = true;
                Toast.makeText(ViewOptimizationActivity.this, "ViewStub加载了", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                //1、捕获异常
             /*   try {
                    viewStub.inflate();
                } catch (IllegalStateException e) {
                    Log.e("Tag",e.toString());
                    viewStub.setVisibility(View.VISIBLE);
                }*/
                //2、通过监听ViewStub的填充事件
                 /*  if (isViewStubShow){
                    viewStub.setVisibility(View.VISIBLE);
                }else {
                    viewStub.inflate();
                }*/
                //3、直接调用setVisibility()方法
                viewStub.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_hide:
                viewStub.setVisibility(View.GONE);
                break;
            case R.id.btn_control:
                //1、初始化被inflate的布局后再初始化其中的控件，
                FrameLayout frameLayout = findViewById(R.id.view_inflate);//android:inflatedId设置的id
                Switch sw = frameLayout.findViewById(R.id.sw);
                //2、直接初始化控件

//                Switch sw = findViewById(R.id.sw);
                sw.toggle();
                break;
            default:
                break;
        }
    }
}
