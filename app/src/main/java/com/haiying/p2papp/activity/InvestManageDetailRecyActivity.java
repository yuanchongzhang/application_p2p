package com.haiying.p2papp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.haiying.p2papp.MyApplication;
import com.haiying.p2papp.conn.JsonAccessToken;
import com.haiying.p2papp.conn.JsonInvestList;
import com.zcx.helper.http.AsyCallBack;
import com.zcx.helper.util.UtilToast;

/**
 * Created by Administrator on 2017/1/4.
 */
public class InvestManageDetailRecyActivity extends BaseActivity {


    private String getId;


    private TextView account_date;
    private TextView account_money;
    private TextView account_Interest;
    private TextView account_shouxufei;
    private TextView shiji_benxi;
    private TextView yingshou_benxi;
    private TextView shiji_status;
    private TextView shiji_countbi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investmanagedetail_recy);
        getId = getIntent().getStringExtra("id");

        Log.d(getId, "9999999999999999999999999");
        initView();
        getBankListAccessToken();
    }


    public void initView() {
        account_date = (TextView) findViewById(R.id.account_date);
        account_money = (TextView) findViewById(R.id.account_money);
        account_Interest = (TextView) findViewById(R.id.account_Interest);
        account_shouxufei = (TextView) findViewById(R.id.account_shouxufei);
        shiji_benxi = (TextView) findViewById(R.id.shiji_benxi);
        yingshou_benxi = (TextView) findViewById(R.id.yingshou_benxi);
        shiji_status = (TextView) findViewById(R.id.shiji_status);
        shiji_countbi = (TextView) findViewById(R.id.shiji_countbi);

    }

    private void getBankListAccessToken() {
        new JsonAccessToken("user/tendout_detial", new AsyCallBack<JsonAccessToken.Info>() {
            @Override
            public void onSuccess(String toast, int type, JsonAccessToken.Info info) throws Exception {
                if (info != null) {
                    if (!TextUtils.isEmpty(info.access_token)) {
                        initData(info.access_token);
                    } else {
                        UtilToast.show(InvestManageDetailRecyActivity.this, "安全验证失败！");
                    }
                }
            }

            @Override
            public void onFail(String toast, int type) throws Exception {
                super.onFail(toast, type);
                UtilToast.show(InvestManageDetailRecyActivity.this, "安全验证失败！");
            }
        }).execute(this, true);

    }


    public void initData(String access_token) {
//        new AsyCallBack<JsonBanklist.Info>()
        new JsonInvestList(access_token, MyApplication.myPreferences.readUid(), getId, new AsyCallBack<JsonInvestList.Info>() {

            @Override
            public void onSuccess(String toast, int type, JsonInvestList.Info info) throws Exception {
                super.onSuccess(toast, type, info);
                // Toast.makeText(InvestManageDetailRecyActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                account_date.setText(info.list.get(type).deadline);
                account_money.setText(info.list.get(type).capital);
                account_Interest.setText(info.list.get(type).interest);
                account_shouxufei.setText(info.list.get(type).interest_fee);
                shiji_benxi.setText(info.list.get(type).receive_all);


                yingshou_benxi.setText(info.list.get(type).capital_all);
                shiji_status.setText(info.list.get(type).status_cn);
                shiji_countbi.setText(info.list.get(type).sort_order);

            }
        }).execute(InvestManageDetailRecyActivity.this, true);
    }


}
