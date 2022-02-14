package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.gaw.AreaComumPlataforma;

@AreaComumPlataforma
public class PlataformaIncial extends Pagina {

	public PlataformaIncial() {
	}

	@MapearElementoWeb(xPath = "//*[@id=\"perfil\"]/a/div/figure")
	public Elemento btnPerfil;

	// Menu Lateral Esquerdo:
	@MapearElementoWeb(xPath = "//li[@data-item-id='39050']") // Contas
	public Elemento menuConta;

	@MapearElementoWeb(xPath = "//a[@data-item-id='39273']") // Contas => Informações da conta
	public Elemento menuInfoConta;

	@MapearElementoWeb(xPath = "//span[@class='head__context']/span[contains(text(),'Contas | Consultas')]")
	public ElementoTexto breadCrumb;
	
	@MapearElementoWeb(xPath = "//div[@class='modal__close']")
	public Elemento elemFecharModal;

}