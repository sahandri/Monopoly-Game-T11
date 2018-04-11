
import Model.*;
import View.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.JUnit4TestAdapter;

// This section declares all of the test classes in program.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Add test classes here.
        TestStreet.class,
        TestRailroad.class,
        TestTax.class
})

public class AllTests
{
    //This can be empty if you are using an IDE
    //However, if you are using Java on the command line this main is helpful

    public static void main (String[] args)
    {
        junit.textui.TestRunner.run (suite());
    }

    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter(AllTests.class);
    }

}