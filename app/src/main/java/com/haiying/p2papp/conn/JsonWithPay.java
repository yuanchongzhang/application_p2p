package com.haiying.p2papp.conn;

import android.util.Log;

import com.google.gson.Gson;
import com.zcx.helper.http.AsyCallBack;
import com.zcx.helper.http.note.HttpInlet;

import org.json.JSONObject;

/**
 * Created by Administrator on 4/26/2016.
 */
@HttpInlet("pay/charge")
public class JsonWithPay extends HYAsyPost<JsonWithPay.Info> {
    public String access_token, uid, money, bank_id, way;

    public JsonWithPay(String access_token, String uid, String money, String bank_id, String way, AsyCallBack<Info> asyCallBack) {
        super(asyCallBack);
        this.access_token = access_token;
        this.uid = uid;
        this.money = money;

        this.bank_id = bank_id;
        this.way = way;
    }


    @Override
    protected Info parser(JSONObject object) {

        Info info = null;
        if (object.optString("status").equals("1")) {
            Log.d(object.toString(), "adsfklllllll");
            info = new Info();
            info.tips = object.optString("tips");
            /*info.account_money = object.optString("account_money");

            JSONObject mObject = object.optJSONObject("submitdata");

            Submitdata submitdata = new Submitdata();

            submitdata.MCHNTCD = mObject.optString("MCHNTCD");
            submitdata.MCHNTORDERID = mObject.optString("MCHNTORDERID");
            submitdata.USERID = mObject.optString("USERID");
            submitdata.AMT = mObject.optString("AMT");
            submitdata.BANKCARD = mObject.optString("BANKCARD");
            submitdata.BACKURL = mObject.optString("BACKURL");
            submitdata.NAME = mObject.optString("NAME");
            submitdata.IDNO = mObject.optString("IDNO");
            submitdata.IDTYPE = mObject.optString("IDTYPE");
            submitdata.SIGNTP = mObject.optString("SIGNTP");
            submitdata.SIGN = mObject.optString("SIGN");
            submitdata.VERSION = mObject.optString("VERSION");
            submitdata.TYPE = mObject.optString("TYPE");

            info.submitdata = submitdata;*/
            Gson gson=new Gson();
            info=gson.fromJson(object.toString(), Info.class);
            return info;

        }

        TOAST = object.optString("tips");


        return null;
    }


    public static class Info {
        public String tips;
        public String account_money;
        public Submitdata submitdata;
    }

    public static class Submitdata {
        public String MCHNTCD;
        public String MCHNTORDERID;
        public String USERID;
        public String AMT;
        public String BANKCARD;
        public String BACKURL;
        public String NAME;
        public String IDNO;
        public String IDTYPE;
        public String SIGNTP;
        public String SIGN;
        public String VERSION;
        public String TYPE;

    }

}