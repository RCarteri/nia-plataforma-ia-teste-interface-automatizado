package support.enums;

import static java.lang.System.getProperty;

public enum Cookie {
    IBBID;

    public static boolean isLoggedIntranet() {
        System.out.println("verificando login intranet");
        return !(getIBBID() == null);
    }

    public static String getIBBID(){
        System.out.println("verificando valor IBBID " );
        System.out.println("verificando valor IBBID " + getProperty(String.valueOf(IBBID)));
        return getProperty(String.valueOf(IBBID));
    }
}
