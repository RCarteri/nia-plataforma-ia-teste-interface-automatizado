package support.enums;

import java.util.List;

public final class Siglas {
    private static Siglas instance;
    public List<String> sigla;

    public static Siglas getInstance() {
        if (instance == null) {
            instance = new Siglas();
        }
        return instance;
    }

    public void seSiglas(List<String> sigla) {
        this.sigla = sigla;
    }

    public List<String> getSiglas() {
        return sigla;
    }
}