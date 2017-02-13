package com.haiying.p2papp.conn;

import com.google.gson.Gson;
import com.zcx.helper.http.AsyCallBack;
import com.zcx.helper.http.note.HttpInlet;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 4/22/2016.
 */
@HttpInlet("user/tendout_detial")
public class JsonInvestList extends HYAsyPost<JsonInvestList.Info> {
    public String access_token, uid, id;

    public JsonInvestList(String access_token, String uid, String id, AsyCallBack<Info> asyCallBack) {
        super(asyCallBack);
        this.access_token = access_token;
        this.uid = uid;
        this.id = id;
    }

    @Override
    protected Info parser(JSONObject object) {

        Info info = null;
        if (object.optString("status").equals("1")) {
            info = new Info();
            Gson gson = new Gson();
            info = gson.fromJson(object.toString(), Info.class);
            TOAST = object.optString("tips");
            return info;
        }
        TOAST = object.optString("tips");
        return null;
    }

    public static class Info {
        public List<ListContent> list;

        public static class ListContent {
            public String deadline;
            public String capital;
            public String interest;
            public String interest_fee;
            public String receive_all;
            public String capital_all;
            public String status_cn;
            public String sort_order;

        }

    }
}