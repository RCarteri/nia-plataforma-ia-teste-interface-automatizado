# Changelog

As mudanças feitas neste projeto serão documentadas nesse arquivo.

O formato é baseado em [Mantenha uma Changelog](https://keepachangelog.com/en/1.0.0/),
e este projeto segue [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.13.3] - 08-04-2022
Task: 458281
###Adicionado
- Cenário: S008 Triton - CT004 Pesquisar componente
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
- Cenário: CT004 - Pesquisar componente, CT004 - Filtrar componente sem resultado e CT005 - Limpar pesquisa de componente
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
