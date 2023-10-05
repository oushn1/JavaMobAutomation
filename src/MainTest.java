import org.junit.Assert;
import org.junit.Test;

public class MainTest
{

    public int getLocalNumber()
    {
        return 14;
    }

    @Test
    public void testGetLocalNumber()
    {
        Assert.assertTrue("Expected value is 14. Actual: " + this.getLocalNumber(), this.getLocalNumber() == 14);
    }

}