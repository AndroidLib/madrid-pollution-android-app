package entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ColorRulesTest {

    @Test
    public void testGetStationColor() throws Exception {

        PollutionStation station = new PollutionStation();

        assertEquals(ColorRules.COLOR_GREY, ColorRules.getStationColor(station));

        station.co = "1";

        assertEquals(ColorRules.COLOR_GREEN, ColorRules.getStationColor(station));

        station.no2 = "100";

        assertEquals(ColorRules.COLOR_YELLOW, ColorRules.getStationColor(station));

        station.so2 = "400";

        assertEquals(ColorRules.COLOR_RED, ColorRules.getStationColor(station));


    }

    @Test
    public void testGetColorForPollutantValue() throws Exception {

        assertEquals(ColorRules.COLOR_GREEN, ColorRules.getColorForPollutantValue(Pollutant.NO2, "0"));
        assertEquals(ColorRules.COLOR_GREEN, ColorRules.getColorForPollutantValue(Pollutant.NO2, "99"));
        assertEquals(ColorRules.COLOR_YELLOW, ColorRules.getColorForPollutantValue(Pollutant.NO2, "100"));
        assertEquals(ColorRules.COLOR_YELLOW, ColorRules.getColorForPollutantValue(Pollutant.NO2, "199"));
        assertEquals(ColorRules.COLOR_RED, ColorRules.getColorForPollutantValue(Pollutant.NO2, "200"));
        assertEquals(ColorRules.COLOR_RED, ColorRules.getColorForPollutantValue(Pollutant.NO2, "201"));


    }

    @Test
    public void testIsValidValue() throws Exception {

        assertFalse(ColorRules.isValidValue(null));
        assertFalse(ColorRules.isValidValue(""));
        assertFalse(ColorRules.isValidValue("-"));
        assertFalse(ColorRules.isValidValue("x"));
        assertTrue(ColorRules.isValidValue("25"));
    }

    @Test
    public void testColorUponLimit() throws Exception {

        assertEquals(ColorRules.COLOR_GREEN, ColorRules.colorUponLimit("0", 100));
        assertEquals(ColorRules.COLOR_GREEN, ColorRules.colorUponLimit("25", 100));
        assertEquals(ColorRules.COLOR_GREEN, ColorRules.colorUponLimit("49", 100));
        assertEquals(ColorRules.COLOR_YELLOW, ColorRules.colorUponLimit("50", 100));
        assertEquals(ColorRules.COLOR_YELLOW, ColorRules.colorUponLimit("99", 100));
        assertEquals(ColorRules.COLOR_RED, ColorRules.colorUponLimit("100", 100));

        assertEquals(ColorRules.COLOR_GREY, ColorRules.colorUponLimit("100", 0));


    }

    @Test
    public void testGetLimitForPollutant() throws Exception {

        assertTrue(25f == ColorRules.getLimitForPollutant(Pollutant.PM25));
        assertTrue(50f == ColorRules.getLimitForPollutant(Pollutant.PM10));
        assertTrue(120f == ColorRules.getLimitForPollutant(Pollutant.O3));
        assertTrue(0f == ColorRules.getLimitForPollutant(Pollutant.BEN));
    }

    @Test
    public void testGetUnitsOfInt() throws Exception {

        assertEquals(4, ColorRules.getUnitsOfInt(4));
        assertEquals(0, ColorRules.getUnitsOfInt(40));
        assertEquals(5, ColorRules.getUnitsOfInt(415));
        assertEquals(0, ColorRules.getUnitsOfInt(0));
    }

    @Test
    public void testGetTensOfInt() throws Exception {

        assertEquals(2, ColorRules.getTensOfInt(124));
        assertEquals(0, ColorRules.getTensOfInt(5));
        assertEquals(1, ColorRules.getTensOfInt(10));
        assertEquals(9, ColorRules.getTensOfInt(90));

    }
}