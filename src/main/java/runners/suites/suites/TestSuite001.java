package runners.suites.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.suites.TestSuiteS001AcessarPlataforma;

@RunWith(Suite.class)
@SuiteClasses({
        TestSuiteS001AcessarPlataforma.class
})
public class TestSuite001 {
}
