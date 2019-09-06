package tiles.prokarma.co.tiles.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

import tiles.prokarma.co.tiles.models.Action;
import tiles.prokarma.co.tiles.network.request.ImageRequest;
import tiles.prokarma.co.tiles.network.tos.Photos;

/**
 * Created by USER on 9/6/2019.
 */

public class ImageResourceRequestor {
    String baseUrl = "https://www.flickr.com/services/api/explore/flickr.photoList.";
    Context context;
    ResponseListener listener;

    public ImageResourceRequestor(Context context, ResponseListener listener){
        this.context = context;
        this.listener = listener;
    }

    public void executeRequest(Action action, ImageRequest imageRequest) throws JSONException {
        String url = baseUrl + action.getPageId();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = baseUrl + action.getPageId();

        final String requestBody = new Gson().toJson(imageRequest);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
                Photos photos = new Gson().fromJson(response, Photos.class);
                listener.onSuccess(photos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                listener.onError(error);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as Photos.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }
}
