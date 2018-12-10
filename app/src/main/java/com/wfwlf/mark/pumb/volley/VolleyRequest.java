package com.wfwlf.mark.pumb.volley;

import android.content.Context;

import java.util.Map;

public class VolleyRequest {

    private Context mContext;


    public VolleyRequest(Context ctx) {
        mContext = ctx;
        MyVolley.init(ctx);
    }

    public  void VolleyPost(String url, Map<String ,String> map, Class cls, MyReponseListener myReponseListener, MyErrorListener myErrorListener){
        GsonRequest request=new GsonRequest(url, map,cls,myReponseListener,myErrorListener);
        MyVolley.addRequest(request);
    }
    public void VolleyGet(String url, Map<String, Object> map, Class cls, MyReponseListener myReponseListener, MyErrorListener myErrorListener){
        url=getUrlString(url,map);
        GsonRequest request=new GsonRequest(url,cls,myReponseListener,myErrorListener);
        MyVolley.addRequest(request);
    }

    public String getUrlString(String responseURL, Map<String, Object> body) {
        String qureyURL = responseURL  ;
        String param = "";
        if (body != null) {
            qureyURL= responseURL + "?" ;
            for (String key : body.keySet()) {
                if("".equals(param)){
                    param =  key + "=" + body.get(key);
                }else {
                    param = param + "&" + key + "=" + body.get(key);
                }

            }
        }
        qureyURL = qureyURL + param;
        return qureyURL;
    }
}
