package util;

import java.util.Properties;

public abstract class Credentials {
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "local_r0789294");
        dbProperties.setProperty("password", "");
    }
}