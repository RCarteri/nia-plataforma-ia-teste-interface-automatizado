package support.enums;

import java.util.List;

public final class Siglas {
    private static Siglas instance;
    public List<String> siglas;

    public static Siglas getInstance() {
        if (instance == null) {
            instance = new Siglas();
        }
        return instance;
    }

    public void setSiglas(List<String> sigla) {
        this.siglas = sigla;
    }

    public List<String> getSiglas() {
        return siglas;
    }
}