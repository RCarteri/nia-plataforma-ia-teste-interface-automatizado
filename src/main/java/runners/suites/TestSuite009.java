package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.features.TestCasePrimeiroAcesso;

@RunWith(Suite.class)
@SuiteClasses({
        TestCasePrimeiroAcesso.class
})
public class TestSuite009 {
}
