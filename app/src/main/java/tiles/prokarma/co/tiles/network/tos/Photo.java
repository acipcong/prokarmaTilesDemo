package tiles.prokarma.co.tiles.network.tos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 9/6/2019.
 */

public class Photo {
    @SerializedName("id")
    String id;
    @SerializedName("url_sq")
    String url;
    @SerializedName("title")
    String title;

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
