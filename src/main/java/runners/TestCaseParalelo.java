package runners;


import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import runners.suites.*;
import support.parallel.FTABBCucumberConcurrentRunner;
import support.parallel.FTABBCucumberConcurrentRunner.AppConfig;

@RunWith(FTABBCucumberConcurrentRunner.class)
@SuiteClasses({
        TestSuite002.class,
        TestSuite003.class,
        TestSuite004.class,
        TestSuite005.class,
        TestSuite006.class,
        TestSuite007.class,
        TestSuite008.class,
        TestSuite009.class,
})
@AppConfig(value={"", "", "", "", "", "", "", ""})
public class TestCaseParalelo {
}
