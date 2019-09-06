package tiles.prokarma.co.tiles.network.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 9/6/2019.
 */

public class ImageRequest {
    @SerializedName("api_key")
    String apiKey;
    @SerializedName("extras")
    String extras;
    @SerializedName("per_page")
    int perPage;
    @SerializedName("page")
    String page;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
