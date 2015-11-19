package entities;

public class ColorRules {

    public static final int COLOR_GREEN = 0;
    public static final int COLOR_YELLOW = 1;
    public static final int COLOR_RED = 10;
    public static final int COLOR_GREY = 100;

    private static final float SO2_LIMIT = 350;
    private static final float CO_LIMIT = 10;
    private static final float NO2_LIMIT = 200;
    private static final float O3_LIMIT = 120;
    private static final float PM10_LIMIT = 50;
    private static final float PM25_LIMIT = 25;


    public static int getStationColor(PollutionStation station) {

        int colorWeight = 0;

        colorWeight += getColorForPollutantValue(Pollutant.SO2, station.so2);
        colorWeight += getColorForPollutantValue(Pollutant.CO, station.co);
        colorWeight += getColorForPollutantValue(Pollutant.NO2, station.no2);
        colorWeight += getColorForPollutantValue(Pollutant.O3, station.o3);
        colorWeight += getColorForPollutantValue(Pollutant.PM10, station.pm10);
        colorWeight += getColorForPollutantValue(Pollutant.PM25, station.pm25);

        if (getTensOfInt(colorWeight) != 0) {
            return COLOR_RED;
        } else if (getUnitsOfInt(colorWeight) != 0) {
            return COLOR_YELLOW;
        } else if (colorWeight == 600) {
            //All stations returned grey
            return COLOR_GREY;
        } else {
            return COLOR_GREEN;
        }
    }


    public static int getColorForPollutantValue(Pollutant pollutant, String value) {
        if (isValidValue(value)) {
            return colorUponLimit(value, getLimitForPollutant(pollutant));
        } else {
            return COLOR_GREY;
        }
    }


    public static boolean isValidValue(String value) {

        if (null == value || value.isEmpty() || value.equals("-")) {
            return false;
        } else {

            try {
                Float.parseFloat(value);
            } catch (NumberFormatException e) {
                return false;
            }

            return true;
        }
    }

    public static int colorUponLimit(String value, float limit) {

        float floatValue = Float.parseFloat(value);

        if (limit == 0) {
            return COLOR_GREY;
        } else if (floatValue < (limit / 2)) {
            return COLOR_GREEN;
        } else if (floatValue < limit) {
            return COLOR_YELLOW;
        } else {
            return COLOR_RED;
        }
    }

    public static float getLimitForPollutant(Pollutant pollutant) {

        switch (pollutant) {

            case SO2:
                return SO2_LIMIT;
            case CO:
                return CO_LIMIT;
            case NO2:
                return NO2_LIMIT;
            case PM25:
                return PM25_LIMIT;
            case PM10:
                return PM10_LIMIT;
            case O3:
                return O3_LIMIT;
            default:
                return 0;
        }

    }

    public static int getUnitsOfInt(int value) {

        return value % 10;
    }

    public static int getTensOfInt(int value) {

        return ((value - getUnitsOfInt(value)) % 100) / 10;
    }


}
