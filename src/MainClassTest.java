import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber()
    {
        Assert.assertTrue("Expected value is 14. Actual: " + this.getLocalNumber(), this.getLocalNumber() == 14);
    }
}
