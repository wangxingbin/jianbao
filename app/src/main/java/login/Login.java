package login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wxb.jianbao.R;

import java.util.HashMap;

import contants.Contans;
import javabeen.LandBeen;
import mainframe.MainFrameAcitvity;
import utils.OkHttpUtil;
import view.ShowToastUtils;



/**
 * Created by 孙贝贝 on 2016/12/2.
 */

public class Login extends Activity implements View.OnClickListener{



        private TextView register;
        private Button land;
        private EditText yonhuming;
        private EditText mima;
        private String canshu = "username=13800138000" + "&password=123456";
        private String status;
        private CheckBox cb1;
        private String password1;
        private String name;
        private String username;
        private String password;
    private ProgressDialog progressDialog;
    private String token;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);
            initview();

            remberpsna();
            checklister();


        }

        private void checklister() {
            cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                    // TODO Auto-generated method stub
                    SharedPreferences settings = getSharedPreferences("SETTING_Infos", 0);
                    if (arg1 == true) {//勾选时，存入EditText中的用户名密码
                        settings.edit().putString("judgeText", "yes")
                                .putString("userNameText", yonhuming.getText().toString())
                                .putString("passwordText", mima.getText().toString())
                                .commit();
                        Toast.makeText(Login.this, "记住用户名和密码", Toast.LENGTH_SHORT)
                                .show();

                    } else {//不勾选，存入空String对象
                        settings.edit().putString("judgeText", "no")
                                .putString("UserNameText", "")
                                .putString("passwordText", "")
                                .commit();
                        Toast.makeText(Login.this, "不记住用户名和密码", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        }

        private void remberpsna() {
            //从配置文件中取用户名密码的键值对
            //若第一运行，则取出的键值对为所设置的默认值
            SharedPreferences settings = getSharedPreferences("SETTING_Infos", 0);
            String strJudge = settings.getString("judgeText", "no");// 选中状态
            String strUserName = settings.getString("userNameText", "");// 用户名
            String strPassword = settings.getString("passwordText", "");// 密码
            if (strJudge.equals("yes")) {
                cb1.setChecked(true);
                yonhuming.setText(strUserName);
                mima.setText(strPassword);
            } else {
                cb1.setChecked(false);
                yonhuming.setText("");
                mima.setText("");
            }

        }

        private void initdata() {

            HashMap<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            String path = Contans.LAND;
            OkHttpUtil.post(map, path, this, LandBeen.class);
            OkHttpUtil.setGetEntiydata(new OkHttpUtil.EntiyData() {
                @Override
                public void getEntiy(Object o) {
                    LandBeen landbeen = (LandBeen) o;
                    if (landbeen == null) {
                        return;
                    }
                    status = landbeen.getStatus();
                    if (status.equals("200")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();

                                ShowToastUtils.showToast(Login.this,"登陆成功");
                                finish();
                                startActivity(new Intent(Login.this, MainFrameAcitvity.class));
                            }
                        });
                    }else if (status.equals("302")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ShowToastUtils.showToast(Login.this,"异地登录");
                            }
                        });
                        return;
                    }else if(status.equals("303")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                ShowToastUtils.showToast(Login.this,"用户不存在");
                            }
                        });
                        return;
                    }else if(status.equals("304")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                ShowToastUtils.showToast(Login.this,"用户名或者密码不正确");
                            }
                        });
                        return;
                    }


                    LandBeen.DataBean data = landbeen.getData();

                    int state = data.getState();
                    token = landbeen.getToken();
                    SharedPreferences share= getSharedPreferences("TOKEN",MODE_PRIVATE);
                    SharedPreferences.Editor edit = share.edit();
                    edit.putString("token", token);
                    edit.commit();
                    name = data.getName();

                }
            });

        }
        private void initview() {
            register = (TextView) findViewById(R.id.register);
            register.setOnClickListener(this);
            land = (Button) findViewById(R.id.signin_button);
            land.setOnClickListener(this);
            yonhuming = (EditText) findViewById(R.id.username_edit);
            mima = (EditText) findViewById(R.id.password_edit);
            cb1 = (CheckBox) findViewById(R.id.check);
        }


        private void jumpregister() {
            startActivity(new Intent(this, Register.class));
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signin_button:
                    username = yonhuming.getText().toString().trim();
                    password = mima.getText().toString().trim();
                    if (username.equals("") && password.equals("")) {
                        ShowToastUtils.showToast(this,"请输入手机号跟密码");
                        return;
                    } else if (username.length()>11||username.length()<11) {
                        ShowToastUtils.showToast(this,"请输入正确手机号");
                        return;
                    } else {
                        progressDialog = new ProgressDialog(Login.this);
                        progressDialog.setMessage("请稍等....");
                        progressDialog.setTitle("正在登陆");
                        progressDialog.show();
                        initdata();
                    }
                    break;
                case R.id.register:
                    jumpregister();
                    break;
            }
        }
    }


