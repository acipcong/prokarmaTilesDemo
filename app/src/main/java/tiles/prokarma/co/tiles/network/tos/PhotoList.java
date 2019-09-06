package tiles.prokarma.co.tiles.network.tos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by USER on 9/6/2019.
 */

public class PhotoList {
    @SerializedName("page")
    int page;

    @SerializedName("pages")
    String pages;

    @SerializedName("perpage")
    int perpage;

    @SerializedName("total")
    String total;

    @SerializedName("photo")
    ArrayList<Photo> photoList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<Photo> photoList) {
        this.photoList = photoList;
    }
}
