package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.features.TestCaseGruposDeAcesso;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseGruposDeAcesso.class
})
public class TestSuite003 {
}
