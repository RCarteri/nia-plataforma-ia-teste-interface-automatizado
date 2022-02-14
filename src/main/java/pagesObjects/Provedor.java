package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;

public class Provedor extends Pagina {
    @MapearElementoWeb(xPath = "//h3[contains(text(), 'IBM Cloud')]")
    private ElementoTexto btnIBMCloud;

    @MapearElementoWeb(xPath = "//h3[contains(text(), 'Triton')]")
    private ElementoTexto btnTriton;

    public void acessarIBMCLoud() throws ElementoNaoLocalizadoException {
        btnIBMCloud.clicar();
    }
}
