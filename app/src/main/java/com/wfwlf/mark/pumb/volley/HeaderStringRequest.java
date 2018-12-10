package com.wfwlf.mark.pumb.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marksong on 2018/11/30.
 */

public class HeaderStringRequest  extends StringRequest {
    public HeaderStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public HeaderStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String,String> params = new HashMap<>();
        params.put("isPhone","1");
        return params;
    }
}
