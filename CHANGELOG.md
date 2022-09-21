# Changelog

As mudanças feitas neste projeto serão documentadas nesse arquivo.

O formato é baseado em [Mantenha uma Changelog](https://keepachangelog.com/en/1.0.0/),
e este projeto segue [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [6.7.4] - 21-09-2022

Task: 638399, 645038, 645768, 
###Adicionado
- Exemplo de cenário: APISuite002 - Listar recursos BB na IBM Cloud - APICT001 - OK - 200 - Grupos de acesso
- Exemplo de cenário: APISuite004 - Listar recursos BB na IBM Cloud por código do componente - APICT001 - OK - 200 - Membros do catálogo
- Método: getPayload(String endpoint, String tipoPayload), para retornar o payload a ser usado no request
- Método: datapoolInit(), para inicializar os datapools de .yml

###Corrigido
- Problema no qTeste. O qTeste passou a usar o binários como repositório, não mais o ATF.

###Mudado
- Passando a utilizar o método $() do FTABB para ler arquivos .yml

## [6.5.0] - 14-09-2022
* Versão instável no qTeste

Task: 610527, 614861, 631491, 638454, 638677
###Adicionado
- Suite: APISuite002 - Listar recursos BB na IBM Cloud - Endpoint nia/op580677-v3
- Suite: APISuite003 - Listar dados interveniente do funcionário - Endpoint dpr/op614861-v1
- Suite: APISuite004 - Listar recursos BB na IBM Cloud por código do componente - Endpoint nia/op5839181-v1
- Cenário: APICT001 - OK - 200 - Listar modelos Watson Studio por sigla - Endpoint nia/op580677-v3 Status code 200
- Cenário: APICT002 - Internal Server Error - 500 - Endpoint Op6586305-v1
- Cenário: APICT001 - OK - 200 - Listar instâncias do Watson Assistant - Endpoint nia/op5806077-v2 Status code 200
- Cenário: APICT001 - OK - 200 - Listar catálogos do Watson Knowledge Catalog - Endpoint nia/op5806077-v2 Status code 200
- Cenário: APICT001 - OK - 200 - Listar storages do Cloud Object Storage - Endpoint nia/op5806077-v2 Status code 200
- Cenário: APICT001 - OK - 200 - Listar modelos - Endpoint nia/op5839181-v1 Status code 200
- Método: getListaRetorno() para retornar o array listaRetorno do response
- Printando diretórios para validar o erro que o qTeste não está encontrando os arquivos .yml

###Mudado
- Enviando requests do NIA a partir da url "https://plataforma.desenv.bb.com.br" e os outros para a url "http://nia-access-manager.nia.desenv.bb.com.br/"
- Retornando os resultados dos arquivos .yml com os métodos getYamlMap() e getValueYamlMap() e não mais através do FTABB

## [6.1.1] - 11-08-2022
Task: 561341, 563345, 581179, 594696, 596883, 597010, 597647  
###Adicionado
- Testes com API
- Cenário: APICT001 - OK - 200, APICT002 - Bad Request - 400
- Tabela request/respnse da API no Allure
- Exemplo: Data Asset no ID-007-003 (Watson Studio - Exibir componente)
- Tag - @ignore para ignorar cenário

###Mudado
- Ignorar step de verificar mensagem no ID-007-004 (Watson Studio - Pesquisar), pois não foi implementado as mensagens no modal de Notebook, Data Assets e Modelos

###Corrigido
- Rolar a página até o alerta para fechá-lo
- Retornar erro quando não há nenhum item na lista
- Verificar inclusão do membro
- Parar de avançar a página quando atingir o limite

## [5.7.0] - 06-07-2022
Task: 549521, 554598, 557037
###Adicionado
- Configuração: Publicar evidência no qTeste
- Cenário: CT014 - Limpar request
- Cenário: CT015 - Excluir membro
- Chave: Incluida chave do Othavio para teste de exclusão de membro

###Mudado
- Cenário: CT009 - Testar Modelo Triton, executando o request
- Passando a usar o usuário do Rafael para os teste se não mais o do bruno

###Corrigido
- selecionarSigla() não capturava tela quando não encontrava nenhuma sigla

## [5.5.1] - 27-06-2022
Task: 487395, 496594, 506847, 515638, 534804, 536105, 545664
###Adicionado
- TestSuite e TestCase: Runners para executar suites e cenários individualmente
- esperarQTeste(): Método para alterar o tempo de espera se for executado no qTeste
- printLog(): Método para padronizar os logs
- TestCaseParalelo(): Para execução dos testes em paralelo
- SelectorsDelays: Unificação dos tempos de espera em um enum
- Cenário: CT011 - Editar membro
- Cenário: CT012 - Solicitção de deploy modelo para Triton
- Cenário: CT013 - Transferir arquivos do S# para IBM Exception
- Cenário: S007 Watson Studio - CT004 Pesquisar, exemplo Notebooks
- Cenário: S007 Watson Studio - CT004 Pesquisar, exemplo Data assets
- Cenário: S007 Watson Studio - CT004 Pesquisar, exemplo Modelos
- Mensagem: Elemento não encontrado no waitLoadPage()
- Ambientes Allure: Homologação e Produção
- Captura de tela ao falhar cenário
- Chave- Rafael para testes

###Mudado
- logError(): Printar tela ao chamar método
- maxTentativas: Aumentado para 20 o número de tentativas de encontrar o elemento na tela
- Cenário: CT005 Adicionar membro - utilizando datatable
- SelecionarSigla() aleatóriamente
- Cenário: CT005 - Adicionar membro Exception, escolher função aleatóriamente

###Corrigido
- ConfRunner: Removido construtor.
- Recursividade ao logar
- avancarPagina() - Só estava aavançando até as 5 primeiras páginas

###Removido
- Step: Então deverá ser apresentado o alerta de "sucesso" com a mensagem "Dados do usuário logado carregados com sucesso!"
- Anotação: @NotNull, @Nullable
- Chave- Othavio para testes

## [3.15.1] - 23-05-2022
Task: 469729, 478473, 480126
###Adicionado
- Cenário: S007 Watson Studio - CT003 Exemplo Data Assets
- Step: E deverá apresenter o mesmo nome do item selecionado
- Step: Então deverá ser apresentado o alerta com a mensagem
- Validação: Botão voltar do primeiro acesso desabilitado
- Annotation: FindBy por CSSSelector para não usar o mapeamento do FTABB
- Método: waitLoadPage(), printResultadoEsperadoObtito(), waitElement()
- Construtores nas classes
### Mudado
- Implementação de stream no código
- Mapeamento: Identificação do modal aberto
- Unificação do mapeamento dos alertas de sucesso, de informação e do btnFechar
- Uniao do CT004 e CT005 para CT004 - Pesquisa

## [3.13.6] - 18-04-2022
Task: 462267
###Adicionado
- Step: S007 Watson Studio - CT009 Inexistência do componente. Exemplo Data Assets
- Método: isListaOpcoesDisplayed para validar as opçoes dos componentes que possuem o submenu como Watson Studio e Modelos Triton

## [3.13.4] - 08-04-2022
Task: 458281
###Adicionado
- Cenário: S008 Triton - CT004 Pesquisar
- Classe: Map para isolar o mapeamento dos elementos das páginas
- Classe: ModalComponente, Pesquisa e PesquisaSection
- Step: E todas as validações devem retornar sucesso
###Mudado
- Step: Quando pesquisar "String" no "String", retornando dados o primeiro dado da lista para pesquisa válida

## [3.13.1] - 05-04-2022
Task: 423083
###Adicionado
- Cenário: S002 CloudObjectStorage - CT009 Inexistência do componente
- Classe: PanelContentSection para isolar as ações que acontecem só nesse frame do resto da página
###Mudado
- Identificação das tags e cenários por CT e Suites
- Cenário: S007 WatsonStudio - S009 Inexistência do componente
###Removido
- Step: "Quando exibir 'String'" e 
        "E escolher 'String'"

## [3.12.8] - 18-03-2022
Task: 416902, 421557, 421902, 423159, 425165, 431616
### Adicionado
- Funcionalidade: Modelos Triton
- Funcionalidade: Primeiro acesso
- Cenario: CT011 - Validar mensagem inexistência de modelos no projeto
- Cenario: CT012 - Testar modelo Triton
- Cenario: CT013 - Cadastrar usuário na IBM Cloud
### Mudado
- Cenário: CT002 - Validar componente
### Corrigido
- Delay para as mensagens de alerta

## [3.9.10] - 07-03-2022
Task: 416902
### Adicionado
- Método waitElemento: Esperar até o elemento aparecer
- Método tempoQteste: Manter o tempo de wait mesmo executando no qTeste

### Resolvido
- Step: Então deverá apresentar o titulo "Grupos de acesso" no modal

## [3.8.0] - 03-03-2022
Task: 415993
### Adicionado
- Runner: TestCaseSmoke, TestCaseCloudObjectStorage, TestCaseGruposDeAcesso, TestCaseModelos, TestCaseWatsonAssistant, TestCaseWatsonKnowledgeCatalog, TestCaseWatsonStudio e TestCaseNotebook

## [3.7.0] - 02-03-2022
Task: 413574
### Adicionado
- Cenário: CT010 - Exibir notebook
### Mudado
- Extração de mapeamento de elementos para paginação
### Corrigido
- Delay para abrir notebook

## [3.6.0] - 01-03-2022
Task: 413536
### Adicionado
- Cenário: CT009 - Validar mensagem inexistência de notebooks no projeto
- Método: getElement(), para mapear lista de elementos sem o FTABB

## [3.5.0] - 01-03-2022
Task: 413525
### Adicionado
- Cenário:  CT008 - Atualizar listagem de projetos

## [3.4.0] - 01-03-2022
Task: 413471
### Adicionado
- Cenário:  CT007 - Filtrar projetos por sigla
### Removido
- Delay para captuar tela

## [3.3.0] - 28-02-2022
Task: 411831
### Adicionado
- Cenário: CT006 - Adicionar membro exceção
- Page Object para Modal Adicionar Membro
- Runner: TestCaseAdicionarMembroException e TestCasePesquisaModalComponente
- Atualizar página caso não apareca o formulário de login
### Mudado
- Diminuir delay para carregamento da página para 1 segundo

## [3.2.0] - 24-02-2022
Task: 409547
### Adicionado
- Cenário: CT005 - Pesquisar no modal
- Page Object para o Watson Studio
- Cenário: CT003 - Exibir componente no Watson Studio
### Mudado
- Adequação dos sufixos das classes de object page para terminar com Page
- Refatoração dos seletores dos mapeamentos CSS
- Globalização da resposta de erro ElementoNaoLocalizadoException
- União dos steps referente ao CT004
- Diferenciação de pesquisa entre modal e componente

## [3.2.0] - 24-02-2022
Task: 406210
### Adicionado
- Cenário: CT004 - Pesquisar, CT004 - Filtrar componente sem resultado e CT005 - Limpar pesquisa de componente
- Runner: TestCaseExibirComponente e TestCasePesquisarComponente
### Mudado
- Runners passaram a usar tags
- Delay carregar página aumentado para 3 segundos

## [3.0.4] - 21-02-2022
Task: 399779
### Adicionado
- Funcionalidade: Implementação dos testes no qTeste
- Runner: Acessar plataforma
- Inclusão de tags nos cenários
### Mudado
- Login sem usar o FTABB
### Removido
- Limpeza dos históricos de relatórios do Allure
- Delays da plataforma

## [2.0.0] - 17-02-2022
Task: 399973
### Adicionado
- Funcionalidade: Implementação dos relatórios no Allure

## [1.1.0] - 15-02-2022
Task: 397818, 395858
### Adicionado
- Cenário: CT002 - Exibir componente
- Funcionalidade: Fechar plataforma
### Mudado
- Validação se já está na página da plataforma quando for testar outro cenário

## [1.0.0] - 14-02-2022
Task: 397239
### Adicionado
- Cenário: CT001 - Acessar plataforma IA
