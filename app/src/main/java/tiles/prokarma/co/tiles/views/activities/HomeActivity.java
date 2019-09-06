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

import java.util.ArrayList;

import tiles.prokarma.co.tiles.R;
import tiles.prokarma.co.tiles.models.Action;
import tiles.prokarma.co.tiles.network.ImageResourceRequestor;
import tiles.prokarma.co.tiles.network.ResponseListener;
import tiles.prokarma.co.tiles.network.request.ImageRequest;
import tiles.prokarma.co.tiles.network.tos.Photo;
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

        try{
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
        }catch (Exception e){
            attempts.setText("on Create "+e.getMessage());
        }
    }



    @Override
    public void onSuccess(Photos response) {
        parseResponse();
    }

    private void parseResponse() {
        try{
            String response = "{\"photos\":{\"page\":1,\"pages\":\"125\",\"perpage\":8,\"total\":\"1000\",\"photo\":[{\"id\":\"48685889383\",\"owner\":\"96171744@N06\",\"secret\":\"e185c2b69d\",\"server\":\"65535\",\"farm\":66,\"title\":\"20190805-DSC_0096\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48685889383_e185c2b69d_s.jpg\",\"height_sq\":75,\"width_sq\":75},{\"id\":\"48685889508\",\"owner\":\"40498517@N04\",\"secret\":\"df857b424f\",\"server\":\"65535\",\"farm\":66,\"title\":\"untitled-128.jpg\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48685889508_df857b424f_s.jpg\",\"height_sq\":75,\"width_sq\":75},{\"id\":\"48685889558\",\"owner\":\"169485639@N03\",\"secret\":\"9e1ae9c16e\",\"server\":\"65535\",\"farm\":66,\"title\":\"_W9A6982.jpg\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48685889558_9e1ae9c16e_s.jpg\",\"height_sq\":75,\"width_sq\":75},{\"id\":\"48685889668\",\"owner\":\"131908582@N08\",\"secret\":\"a148092776\",\"server\":\"65535\",\"farm\":66,\"title\":0,\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48685889668_a148092776_s.jpg\",\"height_sq\":75,\"width_sq\":75},{\"id\":\"48686232146\",\"owner\":\"134873483@N07\",\"secret\":\"cd1bdcaef7\",\"server\":\"65535\",\"farm\":66,\"title\":\"IMG_9578.jpg\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48686232146_cd1bdcaef7_s.jpg\",\"height_sq\":75,\"width_sq\":75},{\"id\":\"48686403567\",\"owner\":\"155758803@N08\",\"secret\":\"62cfddd0e4\",\"server\":\"65535\",\"farm\":66,\"title\":\"21701647651428\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48686403567_62cfddd0e4_s.jpg\",\"height_sq\":75,\"width_sq\":75},{\"id\":\"48686403587\",\"owner\":\"92647703@N06\",\"secret\":\"4771d0f57a\",\"server\":\"65535\",\"farm\":66,\"title\":\"#sky #evenings\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48686403587_4771d0f57a_s.jpg\",\"height_sq\":75,\"width_sq\":75},{\"id\":\"48686403827\",\"owner\":\"143016846@N04\",\"secret\":\"0ef872236c\",\"server\":\"65535\",\"farm\":66,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0,\"url_sq\":\"https:\\/\\/live.staticflickr.com\\/65535\\/48686403827_0ef872236c_s.jpg\",\"height_sq\":75,\"width_sq\":75}]},\"stat\":\"ok\"}";
            Photos photos = new Gson().fromJson(response, Photos.class);
            GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
            tiles.setLayoutManager(layoutManager);
            ArrayList<Photo> photoArrayList = new ArrayList<>();
            photoArrayList.addAll(photos.getPhotoList().getPhotoList());
            photoArrayList.addAll(photos.getPhotoList().getPhotoList());
            adapter = new TilesAdapter(getApplicationContext(), photoArrayList, tiles, HomeActivity.this);
            tiles.setAdapter(adapter);
        }catch (Exception e){
            attempts.setText("parse response "+e.getMessage());
        }
    }

    @Override
    public void onError(VolleyError error) {
        parseResponse();
    }

    @Override
    public void updateAttempts(Integer number) {
        attempts.setText(number.toString());
    }

    public void updateBest(String attempts) {
        best.setText(attempts+"");
    }
}
