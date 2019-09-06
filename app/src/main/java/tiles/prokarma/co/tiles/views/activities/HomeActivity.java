package tiles.prokarma.co.tiles.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;

import tiles.prokarma.co.tiles.R;
import tiles.prokarma.co.tiles.models.Action;
import tiles.prokarma.co.tiles.network.ImageResourceRequestor;
import tiles.prokarma.co.tiles.network.ResponseListener;
import tiles.prokarma.co.tiles.network.request.ImageRequest;
import tiles.prokarma.co.tiles.network.tos.Photos;
import tiles.prokarma.co.tiles.util.Constants;
import tiles.prokarma.co.tiles.views.adapters.TilesAdapter;

public class HomeActivity extends AppCompatActivity implements ResponseListener {

    RecyclerView tiles;
    TilesAdapter adapter;

    TextView attempts, best;

    Button start, reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        attempts = findViewById(R.id.attempts);
        best = findViewById(R.id.best);

        tiles = findViewById(R.id.tiles);
        start = findViewById(R.id.start);
        reset = findViewById(R.id.reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageResourceRequestor requestor = new ImageResourceRequestor(getApplicationContext(), HomeActivity.this);
                Action action = new Action("getRecent");
                ImageRequest imageRequest = new ImageRequest();
                imageRequest.setApiKey(Constants.apiKey);
                imageRequest.setPerPage(8);
                try {
                    requestor.executeRequest(action, imageRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseResponse();
            }
        });
    }



    @Override
    public void onSuccess(Photos response) {
        parseResponse();
    }

    private void parseResponse() {
        String response = "{\"photoList\":{\"page\":1,\"pages\":\"125\",\"perpage\":8,\"total\":\"1000\",\"photo\":[{\"id\":\"48674988748\",\"owner\":\"184077747@N04\",\"secret\":\"f2881c3c75\",\"server\":\"65535\",\"farm\":66,\"title\":\"wedding-149.jpg\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"48675329191\",\"owner\":\"79022473@N00\",\"secret\":\"cdd6f1cb49\",\"server\":\"65535\",\"farm\":66,\"title\":\"2019-09-03_09-15-45\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"48675329246\",\"owner\":\"31960372@N03\",\"secret\":\"d9bae511f1\",\"server\":\"65535\",\"farm\":66,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"48675329351\",\"owner\":\"184048086@N05\",\"secret\":\"137b3a5f53\",\"server\":\"65535\",\"farm\":66,\"title\":\"澎湖part1\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"48675329356\",\"owner\":\"144329121@N08\",\"secret\":\"d696d11631\",\"server\":\"65535\",\"farm\":66,\"title\":\"This image is taken from Page 100 of Dental summary, 42, (1922)\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"48675503597\",\"owner\":\"11318135@N05\",\"secret\":\"e7af5dc019\",\"server\":\"65535\",\"farm\":66,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"48675503617\",\"owner\":\"184048782@N07\",\"secret\":\"15abc071cf\",\"server\":\"65535\",\"farm\":66,\"title\":\"Capture your reflection in an unexpected place\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"48675503702\",\"owner\":\"135746242@N05\",\"secret\":\"f66784f255\",\"server\":\"65535\",\"farm\":66,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0}]},\"stat\":\"ok\"}";
        Photos photos = new Gson().fromJson(response, Photos.class);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
        tiles.setLayoutManager(layoutManager);
        adapter = new TilesAdapter(getApplicationContext(), photos.getPhotoList().getPhotoList(), tiles, HomeActivity.this);
        tiles.setAdapter(adapter);
    }

    @Override
    public void onError(VolleyError error) {
        parseResponse();
    }

    @Override
    public void updateAttempts(Integer number) {
        attempts.setText(number.toString());
    }

    public void updateBest(int attempts) {
        best.setText(attempts+"");
    }
}
