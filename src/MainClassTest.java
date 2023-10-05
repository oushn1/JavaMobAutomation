import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString()
    {
        Assert.assertTrue("Expected string should contain: Hello or hello.  Actual: " + this.getClassString(),
                this.getClassString().contains("Hello") || this.getClassString().contains("hello"));
    }
}
