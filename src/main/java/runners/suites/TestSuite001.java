package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.features.TestCaseAcessarPlataforma;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseAcessarPlataforma.class
})
public class TestSuite001 {
}
