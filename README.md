# Consulta informações conta estatico testes E2E

## Projeto de automação E2E, utilizando o FTABB para a [Plataforma BB](https://plataforma.hm.bb.com.br/gaw/v3/)

### Links úteis:
 - [Documentação FTABB](https://ftabb.intranet.bb.com.br/#/)

**Para mais detalhes sobre o versionamento, consultar o [CHANGELOG](https://fontes.intranet.bb.com.br/nia/nia-plataforma-ia-teste-interface-automatizado/nia-plataforma-ia-teste-interface-automatizado/-/blob/master/CHANGELOG.md).**

___
___
## Rodando o teste [localmente](https://fontes.intranet.bb.com.br/sol/sol-consulta-informacoes-conta-estatico-testesautomatizados/-/tree/master#rodar-este-teste-localmente) e/ou via [qTeste](https://fontes.intranet.bb.com.br/sol/sol-consulta-informacoes-conta-estatico-testesautomatizados/-/tree/master#rodar-este-teste-via-qteste):

### Rodar este teste localmente:
1.  ### Configuração de ambiente:
Obs.:
- Caso seu ambiente já esteja configurado, proceder para o passo **2. Executando o Teste**
- Caso prefira acomapnhar a *play list* com vídes de auto-instrução para a configuração até a execução de um teste localmente em sua máquina, basta seguir a seguinte sequência de vídeos **(21:40s)**, feita pelo [Alex Steeve de Oliveira Ghattas](https://humanograma.intranet.bb.com.br/F0430119):

1. [Apresentação](https://vhs.labbs.com.br/video/d0q-01-do-zero-ao-qteste-introducao-mov), **55s**;
2. [Instalando o JDK](https://vhs.labbs.com.br/video/d0q-02-instalando-jdk-mp4), **4:40s**;
3. [Instalando o MAVEN](https://vhs.labbs.com.br/video/d0q-03-instalando-maven-mp4), **5:17s**;
4. [Instalando o Eclipse - Parte 1](https://vhs.labbs.com.br/video/d0q-05-instalando-eclipse-parte-1-mp4), **3:37s**;
5. [Instalando o Eclipse - Parte 2](https://vhs.labbs.com.br/video/d0q-07-instalando-eclipse-parte-2-mp4); **5:23s**; e
6. [Executando um teste com Cucumber](https://vhs.labbs.com.br/video/d0q-08-executando-o-teste-mp4), **1:48s**.

#### JAVA:
Possuir o Java JDK 8 instalado. Seguir orientações de instalação conforme o [site da Oracle](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html) (requer [criação de conta na Oracle](https://profile.oracle.com/myprofile/account/create-account.jspx) para a realizaçao do donwload); É importante configurar as variáveis de ambiente (**JAVA_HOME** e **CLASSPATH**) para o Java;]

- #### Setando as variáveis de ambiente:
	- ##### Linux:
		- Abra o terminal e digite `sudo nano ~/.bashrc`;
		- No final do arquivo em edição, insira o trecho:
		```
        #Java 8 SDK:
		JAVA_HOME=/usr/lib/jvm/jdk1.8.0_202-oracle (ou o caminho para o diretório raiz onde Você instalou o JDK 8)
		export JAVA_HOME
		export PATH=$PATH:$JAVA_HOME
        ```
		- Salve e feche o arquivo com `Ctrl + S e Ctrl + X`;
		- Execute o comando `source ~/.bashrc`; e
		- Verificar se o **Java** foi **corretamente instalado**, execute o seguinte comando no seu terminal: `java - version`.
	
	- ##### Windows:
		- Em pesquisar do Windows, procure e selecione: Editar as variáveis de ambiente do sistema
		- Clique em **Variáveis de Ambiente**. Na seção **Variáveis de usuário**, clique em **Novo**;
		- Na janela **Nova Variável de Usuário**, especifique os campos:
			- Nome da variável: `JAVA_HOME`
			- Valor da variável: `C:\Program Files\Java\jdk1.8.0_311` *(ou o caminho para o diretório raiz onde você instalou o JDK 8)*
		- Clique em OK para salvar;
		- Na seção **Variáveis do sistema** localize a variável de ambiente **Path** e selecione-a. Clique em **Editar**.
		- Na janela **Editar a variável de ambiente** clique em **Novo**; e
		- Digite: `%JAVA_HOME%\bin`
		- Clique em OK nas próximas duas janelas
		- Verificar se o **Java** foi **corretamente instalado**, execute o seguinte comando no seu terminal: `java -version`.

#### MAVEN:
- #### Instalando o MAVEN:
	- ##### Linux:
        Para a instalação do **MAVEN**, utilizaremos o gerenciador de pacotes **[SDKMAN](https://sdkman.io/)**.
        Instále-o com o comando `$ curl -s "https://get.sdkman.io" | bash`, e verifique se a variável de ambiente **SDKMAN_DIR** foi inserida no final no arquivo **.bashrc**, utilize o comando `cat ~/.bashrc` para tal.
        Caso a variável **SDKMAN_DIR** não esteja no final do arquivo, insira-a manualmente:
		- Abra o terminal e digite `sudo nano ~/.bashrc`;
		- No final do arquivo em edição, insira o trecho:
		```
	    #SDKMAN:
		export SDKMAN_DIR="$HOME/.sdkman"
		[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"
        ```
		- Salve e feche o arquivo com `Ctrl + S e Ctrl + X`; e
		- Execute o comando `source ~/.bashrc`
	
        - ##### Instalando o MAVEN:
		    - Liste as versões do **MAVEN** disponíveis com o comando `sdk ls maven`;
		    - Copie a versão mais recente, hoje é a **3.8.4**, e aperte `q` para voltar ao prompt de comandos;
		    - Instale o **MAVEN** com o comando `sdk i maven 3.8.4`; e
		    - Verifique se o **MAVEN** foi instalado corretamente com os comandos: `sdk c maven` e `mvn -v`.

	- ##### Windows:
		Acesse o **[MAVEN](https://maven.apache.org/download.cgi)** e na seção de Files selecione o Link do **Binary zip archive** para baixar o arquivo .zip;
		- Crie uma pasta com o nome **ftabb** na pasta do seu usuário local e descompacte o arquivo baixado;
			- `C:\Usuários\<seu usuario>\ftabb\apache-maven-3.8.4`

		##### Configurando as variáveis de ambiente:
		- Em pesquisar do Windows, procure e selecione: Editar as variáveis de ambiente do sistema
		- Clique em **Variáveis de Ambiente**. Na seção **Variáveis de usuário**, clique em **Novo**;
		- Na janela **Nova Variável de Usuário**, especifique os campos:
			- Nome da variável: `M2_HOME`
			- Valor da variável: `C:\Users\<seu usuario>\ftabb\apache-maven-3.8.4` *(lembrando de alterar o valor da versão, caso seja diferente)*
		- Clique em OK para salvar;
		- Na seção **Variáveis do sistema** localize a variável de ambiente **Path** e selecione-a. Clique em **Editar**.
		- Na janela **Editar a variável de ambiente** clique em **Novo**; e
		- Digite: `%M2_HOME%\bin`
		- Clique em OK nas próximas duas janelas
		- Verificar se o **Maven** foi **corretamente instalado**, execute o seguinte comando no seu terminal: `mvn -v`.

#### IntelliJ:
- Possuir a IDE IntelliJ IDEA instalada na sua máquina, links de download: [Link](https://www.jetbrains.com/pt-br/idea/download/#section=windows);
	- Instalar o plugin Cucumber na sua IDE IntelliJ
		- Com a IDE aberta, acesse o menu: **Help => Find Action => Plugins => Marketplace =>** Busque por *Cucumber* => Instale o plugin "*Cucumber for Java*"

#### settings.xml:
- Para trabalhar com [Artfactory](http://atf.intranet.bb.com.br/), que é um repositório onde o Banco do Brasil mantém as libs do **Maven**, é necessário alterar o arquivo *settings.xml* do Maven, conforme os passos abaixo:
	- acessar o link [ATF - Home](http://atf.intranet.bb.com.br/artifactory/webapp/#/home) e fazer login;
		- Vá até o modulo **Set Me Up**, filtre por  *bb-maven-repo* e clique no módulo;
		- Digite dua senha e clique no link *Generate Maven Settings*;
		- Clique no botão **Generate Settings**; e
		- Faça o downlod do *Snippet* e salve como *settings.xml* no diretório **.\conf** onde o Maven foi instalado.
	- Com a **IDE IntelliJ** aberta, acesse o menu **Help => Find Action** pesquise por "maven" **=> Maven Settings** e no campo **Maven Home Path** selecione a pasta do Maven que você baixou: `C:\Users\<seu usuario>\ftabb\apache-maven-3.8.4`
		- Clique em *'Apply and Close'*.

2. ### Executando o teste:
	1. #### Clone:
		 - Clone o projeto através do [fontes BB](https://fontes.intranet.bb.com.br/nia/nia-plataforma-ia-teste-interface-automatizado/nia-plataforma-ia-teste-interface-automatizado);
	2. #### Abrindo o projeto MAVEN no IntelliJ:
		 - Com a IDE aberta, acesse o menu **File => Import => Maven => Existing Maven Project =>** localize a pasta onde foi feito o **clone** do passo anterior;
		  - Marque o arquvio */pom.xml* dentro da janela de Projetos;
		  - Clique em **Finish**; e
		  - Aguarde o download das dependendicas do **MAVEN**.
	3. #### Convertendo o projeto para um projeto Cucumber:
		- Com a IDE aberta, clique com o botão direito do mouse sobre a pasta do projeto e clique no menu **Configure => Convert to a Cucmber Project**
	4. #### RUN:
		- Na estrutura do projeto, navegue até a pasta **src/main/java/runners/**; e
		- Execute a classe **"...Runner.java"** referente ao cenário de testes que deseja.
		### ou
		- Na estrutura do projeto, navegue até a pasta **src/main/resources/features/**; e
		- Execute a classe **"*.feature"** referente ao cenário de testes que deseja.