package support.enums;

import static java.lang.Boolean.parseBoolean;

public enum SysProps {
    IS_LOGGED;

    public static boolean isLogged() {
        return parseBoolean(System.getProperty(String.valueOf(IS_LOGGED)));
    }
}
