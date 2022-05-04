package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.componentes.ibmCloud.TestCaseModelos;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseModelos.class
})
public class TestSuite004 {
}
