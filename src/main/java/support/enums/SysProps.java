package support.enums;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;

public enum SysProps {
    IS_LOGGED;
    public static final short MAX_SEARCH_ATTEMPTS = 5;
    public static final String LOG = "isToLog";

    public static boolean isLoggedPlataforma() {
        return parseBoolean(getProperty(String.valueOf(IS_LOGGED)));
    }
}
