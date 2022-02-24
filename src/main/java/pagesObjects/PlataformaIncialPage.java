package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.gaw.AreaComumPlataforma;

@AreaComumPlataforma
public class PlataformaIncialPage extends Pagina {
	@MapearElementoWeb(css = "figure .mi--person")
	public Elemento btnPerfil;
}