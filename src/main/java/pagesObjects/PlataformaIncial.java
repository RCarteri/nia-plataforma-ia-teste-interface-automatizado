package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.gaw.AreaComumPlataforma;

@AreaComumPlataforma
public class PlataformaIncial extends Pagina {
	@MapearElementoWeb(xPath = "//*[@id=\"perfil\"]/a/div/figure")
	public Elemento btnPerfil;
}