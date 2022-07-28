package support.enums;

import static java.lang.System.getProperty;

public enum User {
    USER, CHAVE, SENHA;

    public static String getUser() {
        return getProperty(String.valueOf(USER));
    }

    public static String getChave() {
        return getProperty(String.valueOf(CHAVE));
    }

    public static String getSenha() {
        return getProperty(String.valueOf(SENHA));
    }
}
