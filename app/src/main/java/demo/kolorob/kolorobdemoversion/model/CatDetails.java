package demo.kolorob.kolorobdemoversion.model;

import java.io.Serializable;

/**
 * Created by touhid on 10/30/15.
 * @author touhid
 */
public class CatDetails implements Serializable{

    private String catName, otherDetails;

    public CatDetails(String catName, String otherDetails) {
        this.catName = catName;
        this.otherDetails = otherDetails;
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
        return "CatDetails{" +
                "catName='" + catName + '\'' +
                ", otherDetails='" + otherDetails + '\'' +
                '}';
    }
}
