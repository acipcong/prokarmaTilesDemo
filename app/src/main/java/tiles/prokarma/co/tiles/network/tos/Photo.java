package tiles.prokarma.co.tiles.network.tos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 9/6/2019.
 */

public class Photo {
    @SerializedName("id")
    String id;
    @SerializedName("owner")
    String owner;
    @SerializedName("secret")
    String secret;
    @SerializedName("url_sq")
    String url;
    @SerializedName("server")
    String server;
    @SerializedName("farm")
    int farm;
    @SerializedName("title")
    String title;
    @SerializedName("ispublic")
    boolean ispublic;
    @SerializedName("isfriend")
    boolean isfriend;
    @SerializedName("isfamily")
    boolean isfamily;

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIspublic() {
        return ispublic;
    }

    public void setIspublic(boolean ispublic) {
        this.ispublic = ispublic;
    }

    public boolean isIsfriend() {
        return isfriend;
    }

    public void setIsfriend(boolean isfriend) {
        this.isfriend = isfriend;
    }

    public boolean isIsfamily() {
        return isfamily;
    }

    public void setIsfamily(boolean isfamily) {
        this.isfamily = isfamily;
    }
}
