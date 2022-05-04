package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.features.TestCaseWatsonStudio;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseWatsonStudio.class
})
public class TestSuite007 {
}
