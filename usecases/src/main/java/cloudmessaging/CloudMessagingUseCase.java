package cloudmessaging;

public class CloudMessagingUseCase {


    private ICloudMessaging mCloudMessaging;

    public CloudMessagingUseCase(ICloudMessaging cloudMessaging) {
        this.mCloudMessaging = cloudMessaging;
    }

    public void register() {

        mCloudMessaging.register();
    }

}
