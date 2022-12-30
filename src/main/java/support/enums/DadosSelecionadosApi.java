package support.enums;

import static java.lang.System.getProperty;

public enum DadosSelecionadosApi {
    COD_COMPONENTE, PAPEL;

    public static String getCodComponente(){
        return getProperty(String.valueOf(COD_COMPONENTE));
    }

    public static String getPapelOriginal(){
        return getProperty(String.valueOf(PAPEL));
    }
}
