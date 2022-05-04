package runners.suites.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.suites.TestSuiteS002CloudObjectStorage;

@RunWith(Suite.class)
@SuiteClasses({
        TestSuiteS002CloudObjectStorage.class
})
public class TestSuite002 {
}
