package pagesObjects.sections;

import map.PaginacaoMap;
import org.openqa.selenium.ElementClickInterceptedException;
import support.Utils;

import static org.junit.Assert.fail;
import static support.Utils.rolarPaginaAteElemento;

public class PaginacaoSection {
    public void avancarPagina(String opcao) {
        PaginacaoMap pM = new PaginacaoMap();
        rolarPaginaAteElemento(pM.getBtnAvancarPagina());
        try {
            pM.getBtnAvancarPagina().click();
        }catch (ElementClickInterceptedException e){
            new Utils().capturaTela();
            fail("Não é possível avançar a página pois atingiu o limite de páginas e não encontrou a opção desejada: '" + opcao + "'.");
        }
    }
}
