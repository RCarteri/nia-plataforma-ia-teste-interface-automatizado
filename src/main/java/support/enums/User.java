package support.enums;

public enum User {
    USER, CHAVE, SENHA;

    public static String getUser() {
        return System.getProperty(String.valueOf(USER));
    }

    public static String getChave() {
        return System.getProperty(String.valueOf(CHAVE));
    }

    public static String getSenha() {
        return System.getProperty(String.valueOf(SENHA));
    }
}
