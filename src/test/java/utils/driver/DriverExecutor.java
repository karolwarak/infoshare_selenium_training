package utils.driver;

import java.io.IOException;

public class DriverExecutor {

    public static void setFullPermissionsForDriver(String driverPath) {
        try {
            Runtime.getRuntime().exec("chmod 777 " + driverPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
