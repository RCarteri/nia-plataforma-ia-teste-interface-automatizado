@Library(['aic-jenkins-sharedlib']) _

mavenBuildPipeline {
    nomePod                         = 'jdk8' // habilita a troca da versão do compilador
    habilitarValidacaoEstatica      = true // habilita a validação estática do código fonte
    habilitarValidacaoSeguranca     = false // habilita a validação de seguranca do código fonte
    habilitarConstrucao             = true // habilita a construção da aplicação
    habilitarTestesUnidade          = false // habilita a execução dos testes de unidade
    habilitarTestesIntegracao       = false // habilita a execução dos testes de integração
    habilitarTestesFumaca           = false // habilita a execução dos testes de fumaça
    habilitarSonar                  = true // habilita a execução do SonarQube Scanner
    habilitarEmpacotamento          = true // habilita o empacotamento da aplicação
    habilitarEmpacotamentoDocker    = false // habilita o build e publicação da imagem docker
    habilitarPublicacao             = true // habilita a publicação do pacote no repositório corporativo
    habilitarDebug                  = false // habilita o debug
}
// Documentação das pipelines: https://fontes.intranet.bb.com.br/aic/publico/atendimento/-/wikis/Pipelines