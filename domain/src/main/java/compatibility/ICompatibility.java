package compatibility;

public interface ICompatibility {

    int COMPATIBILITY_OK = 0;
    int COMPATIBILITY_USER_RESOLVABLE = 1;
    int COMPATIBILITY_FAILED = 2;

    int isThisDeviceCompatible();


}
