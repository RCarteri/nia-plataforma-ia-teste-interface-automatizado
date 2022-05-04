package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.componentes.ibmCloud.TestCaseWatsonAssistant;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseWatsonAssistant.class
})
public class TestSuite005 {
}
