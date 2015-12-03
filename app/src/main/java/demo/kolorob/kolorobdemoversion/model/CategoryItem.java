package demo.kolorob.kolorobdemoversion.model;

import java.io.Serializable;

/**
 * Created by touhid on 10/30/15.
 * @author touhid
 */
public class CategoryItem implements Serializable{

    private int iconId;
    private String catName, otherDetails;

    public CategoryItem(int iconId, String catName, String otherDetails) {
        this.iconId = iconId;
        this.catName = catName;
        this.otherDetails = otherDetails;
    }

    public CategoryItem(int iconId, String catName) {
        this.iconId = iconId;
        this.catName = catName;
        this.otherDetails = "";
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "iconId=" + iconId +
                ", catName='" + catName + '\'' +
                ", otherDetails='" + otherDetails + '\'' +
                '}';
    }
}
