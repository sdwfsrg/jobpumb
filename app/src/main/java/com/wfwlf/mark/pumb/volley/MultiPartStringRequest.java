/**
 * Copyright 2013 Mani Selvaraj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wfwlf.mark.pumb.volley;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import java.lang.reflect.Type;
/**
 * MultipartRequest - To handle the large file uploads.
 * Extended from JSONRequest. You might want to change to StringRequest based on your response type.
 * @author Mani Selvaraj
 *
 */
public class MultiPartStringRequest extends Request<BaseVO> implements MultiPartRequest{

	private final Listener<BaseVO> mListener;
	/* To hold the parameter name and the File to upload */
	private Map<String,File> fileUploads = new HashMap<String,File>();
	
	/* To hold the parameter name and the string content to upload */
	private Map<String,String> stringUploads = new HashMap<String,String>();

	Type type;
    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultiPartStringRequest(int method, String url, Type type, Listener<BaseVO> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        this.type=type;
    }



    public void addFileUpload(String param,File file) {
    	fileUploads.put(param,file);
    }
    
    public void addStringUpload(String param,String content) {
    	stringUploads.put(param,content);
    }
    
    /**
     * 要上传的文件
     */
    public Map<String,File> getFileUploads() {
    	return fileUploads;
    }
    
    /**
     * 要上传的参数
     */
    public Map<String,String> getStringUploads() {
    	return stringUploads;
    }
    

    @Override
    protected Response<BaseVO> parseNetworkResponse(NetworkResponse response) {
//        String parsed;
//        try {
//            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//        } catch (UnsupportedEncodingException e) {
//            parsed = new String(response.data);
//        }
//        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));

        try {
            // 获取返回的数据(在 Content-Type首部中获取编码集，如果没有找到，默认返回 ISO-8859-1)
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(parseNetworkResponseDelegate(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 将服务器返回的内容用gson进行封装
     * @param jsonString
     * @return
     */
    private BaseVO parseNetworkResponseDelegate(String jsonString) {
        return new Gson().fromJson(jsonString, type);
    }

    @Override
	protected void deliverResponse(BaseVO response) {
		if(mListener != null) {
			mListener.onResponse(response);
		}
	}
	
	/**
	 * 空表示不上传
	 */
    public String getBodyContentType() {
        return null;
    }
}