package compatibility;


/**
 * Device compatibility is validated here, e.g. Google Play Services, custom Hardware
 */
public class CompatibilityUseCase {


    private ICompatibility mCompatibility;

    public CompatibilityUseCase(ICompatibility compatibility) {
        this.mCompatibility = compatibility;
    }

    public int isThisDeviceCompatible() {
        return mCompatibility.isThisDeviceCompatible();
    }

}
