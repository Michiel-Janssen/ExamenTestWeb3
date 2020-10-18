package util;

import java.util.Properties;

public abstract class Credentials {
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "je studentnr");
        dbProperties.setProperty("password", "dagadegijniebepalen!");
    }
}