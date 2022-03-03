# Changelog

As mudanças feitas neste projeto serão documentadas nesse arquivo.

O formato é baseado em [Mantenha uma Changelog](https://keepachangelog.com/en/1.0.0/),
e este projeto segue [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.8.0] - 03-03-2022
Task: 415993
### Adicionado
- Runner: TestCaseSmoke, TestCaseCloudObjectStorage, TestCaseGruposDeAcesso, TestCaseModelos, TestCaseWatsonAssistant, TestCaseWatsonKnowledgeCatalog, TestCaseWatsonStudio e TestCaseNotebook

## [3.7.0] - 02-03-2022
Task: 413574
### Adicionado
- Cenário: CT009 - Exibir notebook
### Mudado
- Extração de mapeamento de elemntos para paginação
### Corrigido
- Delay para abrir notebook

## [3.6.0] - 01-03-2022
Task: 413536
### Adicionado
- Cenário: CT008 - Validar mensagem inexistência de notebooks no projeto
- Método para mapear lista de elementos sem o FTABB

## [3.5.0] - 01-03-2022
Task: 413525
### Adicionado
- Cenário:  CT007 - Atualizar listagem de projetos

## [3.4.0] - 01-03-2022
Task: 413471
### Adicionado
- Cenário:  CT006 - Filtrar projetos por sigla
### Removido
- Delay para captuar tela

## [3.3.0] - 28-02-2022
Task: 411831
### Adicionado
- Cenário de exceção: CT005 - Adicionar membro exceção
- Page Object para Modal Adicionar Membro
- Runner: TestCaseAdicionarMembroException e TestCasePesquisaModalComponente
- Atualizar página caso não apareca o formulário de login
### Mudado
- Diminuir delay para carregamento da página para 1 segundo

## [3.2.0] - 24-02-2022
Task: 409547
### Adicionado
- Cenário: CT004 - Pesquisar no modal
- Page Object para o Watson Studio
- Cenário: CT002 - Exibir componente no Watson Studio
### Mudado
- Adequação dos sufixos das classes de object page para terminar com Page
- Refatoração dos seletores dos mapeamentos CSS
- Globalização da resposta de erro ElementoNaoLocalizadoException
- União dos steps referente ao CT003
- Diferenciação de pesquisa entre modal e componente

## [3.2.0] - 24-02-2022
Task: 406210
### Adicionado
- Cenário: CT003 - Pesquisar componente, CT004 - Filtrar componente sem resultado e CT005 - Limpar pesquisa de componente
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
- Funcionalidade: Acessar e exibir modal do componente
- Funcionalidade: Fechar plataforma
### Mudado
- Validação se já está na página da plataforma quando for testar outro cenário

## [1.0.0] - 14-02-2022
Task: 397239
### Adicionado
- Funcionalidade: Acessar plataforma IA
