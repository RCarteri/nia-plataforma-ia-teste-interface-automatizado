package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.componentes.ibmCloud.TestCaseWatsonKnowledgeCatalog;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseWatsonKnowledgeCatalog.class
})
public class TestSuite006 {
}
