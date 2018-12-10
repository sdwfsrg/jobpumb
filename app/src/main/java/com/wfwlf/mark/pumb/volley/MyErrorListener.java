package com.wfwlf.mark.pumb.volley;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

/**
 * 自定义返回错误信息监听
 * 
 * @author 石岩
 * 
 */
public abstract class MyErrorListener implements ErrorListener {

	public void onErrorResponse(VolleyError error) {

		//		DialogMaker.closeProgressDialog();
		//		String errorInfo = VolleyErrorHelper.getMessage(arg0);
		//		Log.e("MyErrorListener", "errorInfo: " + errorInfo);
		//		ToastMaker.showToast(AppData.getContext(), errorInfo, 0);
	}
}
