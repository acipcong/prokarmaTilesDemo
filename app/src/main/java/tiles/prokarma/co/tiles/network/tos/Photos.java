package tiles.prokarma.co.tiles.network.tos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 9/6/2019.
 */

public class Photos {
    @SerializedName("photoList")
    PhotoList photoList;

    @SerializedName("stat")
    String stat;

    public PhotoList getPhotoList() {
        return photoList;
    }

    public void setPhotoList(PhotoList photoList) {
        this.photoList = photoList;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
