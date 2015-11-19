package entities;

public class PollutionStation {


    public PollutionStation() {

    }

    public PollutionStation(String name, Double lat, Double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String name;
    public String pm10;
    public String pm25;
    public String so2;
    public String co;
    public String o3;
    public String no2;
    public String ben;
    public String tol;
    public Double lat;
    public Double lon;

}
