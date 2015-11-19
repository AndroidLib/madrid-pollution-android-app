package entities;

public class RawLatLng {

    private Double mLat;
    private Double mLon;


    public RawLatLng() {

    }

    public RawLatLng(Double lat, Double lon) {
        this.mLat = lat;
        this.mLon = lon;
    }


    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        mLon = lon;
    }
}
