import Task6.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCheckArrays {
    private Main main;
    private boolean result;
    private int[] array;

    public TestCheckArrays(boolean result, int[] array){
        this.result = result;
        this.array = array;
    }

    @Before
    public void init(){
        main = new Main();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {true, new int[] {0,1,2,3,4,5,6,7,8,9}},
                {true, new int[] {0,0,2,3,4,5,6,7,8,9}},
                {true, new int[] {0,1,2,3,0,5,6,7,8,9}},
                {false, new int[] {0,0,2,3,0,5,6,7,8,9}}
        });
    }

    @Test
    public void testCheckArray(){
        Assert.assertEquals(result, main.checkArray(array));
    }
}
