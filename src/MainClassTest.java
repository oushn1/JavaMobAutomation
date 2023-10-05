import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("Expected value is > 45. Actual: " + this.getClassNumber(), this.getClassNumber() > 45);
    }
}
