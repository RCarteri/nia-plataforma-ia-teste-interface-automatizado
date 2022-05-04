package runners.suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import runners.componentes.triton.TestCaseModelosTriton;

@RunWith(Suite.class)
@SuiteClasses({
        TestCaseModelosTriton.class
})
public class TestSuite008 {
}
