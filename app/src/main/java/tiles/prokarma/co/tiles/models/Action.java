package tiles.prokarma.co.tiles.models;

/**
 * Created by USER on 9/6/2019.
 */

public class Action {
    String pageId;
    long imageId;

    public Action(String pageId){
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
