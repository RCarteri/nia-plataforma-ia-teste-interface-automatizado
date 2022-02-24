package stepsDefinitions.ibmCloud.WatsonStudio;

import cucumber.api.java.pt.E;
import pagesObjects.WatsonStudio.WatsonStudioPage;

public class WatsonStudio {
    @E("^escolher \"([^\"]*)\"$")
    public void escolher(String opcao){
        new WatsonStudioPage().clicarBotaoOpcao(opcao);
    }
}
