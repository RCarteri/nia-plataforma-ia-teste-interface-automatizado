package support.enums;

import static java.lang.System.getProperty;

public enum User {
    USER, CHAVE, SENHA, COD_CONF;

    public static String getUser() {
        return getProperty(String.valueOf(USER));
    }

    public static String getChave() {
        return getProperty(String.valueOf(CHAVE));
    }

    public static String getSenha() {
        return getProperty(String.valueOf(SENHA));
    }

    public static String getCodConf(){
        return getProperty(String.valueOf(COD_CONF));
    }
}
