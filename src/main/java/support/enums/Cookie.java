package support.enums;

import static java.lang.System.getProperty;

public enum Cookie {
    IBBID;

    public static boolean isLoggedIntranet() {
        return !(getIBBID() == null);
    }

    public static String getIBBID() {
        return getProperty(String.valueOf(IBBID));
    }
}
