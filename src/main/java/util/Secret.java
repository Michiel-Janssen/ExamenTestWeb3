package util;

import java.util.Properties;

public class Secret extends Credentials {
    public static void setPass(Properties properties) {
        properties.setProperty("user", "local_r0789294");
        properties.setProperty("password", "");
    }
}
