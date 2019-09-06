package tiles.prokarma.co.tiles.network;

import com.android.volley.VolleyError;

import tiles.prokarma.co.tiles.network.tos.Photos;

/**
 * Created by USER on 9/6/2019.
 */

public interface ResponseListener {
    void onSuccess(Photos response);
    void onError(VolleyError error);
    void updateAttempts(Integer number);
}
