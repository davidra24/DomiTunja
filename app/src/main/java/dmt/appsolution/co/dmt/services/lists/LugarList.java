package dmt.appsolution.co.dmt.services.lists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dmt.appsolution.co.dmt.services.entity.Lugar;

/**
 * Created by davic on 15/01/2018.
 */

public class LugarList {
    @SerializedName("lugar")
    @Expose
    private List<Lugar> places;

    public List<Lugar> getPlaces() {
        return places;
    }

    public void setPlaces(List<Lugar> places) {
        this.places = places;
    }
    public void addPlace(Lugar place){
        places.add(place);
    }
}
