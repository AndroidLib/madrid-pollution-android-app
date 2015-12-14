package cloudmessaging;

public interface ICloudMessaging {


    String TRAFFIC_RESTRICTION_ALERT = "traffic_restriction_alert";
    String TYPE = "type";
    String DATE = "date";
    String LEVEL = "level";
    String DEBUG_ONLY = "debug_only";

    void register();
}
