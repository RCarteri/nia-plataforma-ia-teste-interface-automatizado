package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.features.TestCaseCloudObjectStorage;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseCloudObjectStorage.class
})
public class TestSuite002 {
}
