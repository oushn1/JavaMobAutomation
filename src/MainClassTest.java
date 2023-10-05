import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("", this.getClassNumber() > 45);
    }
}
