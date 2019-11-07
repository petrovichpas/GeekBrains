import Task6.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestAfterFour {
    private Main main;
    private int[] result;
    private int[] arr;

    public TestAfterFour(int[] result, int[] arr){
        this.result = result;
        this.arr = arr;
    }

    @Before
    public void init(){
        main = new Main();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
            {new int[] {5,6,7,8,9}, new int[] {0,1,2,3,4,5,6,7,8,9}},
            {new int[] {9}, new int[] {0,0,2,3,4,5,6,7,4,9}},
            {new int[] {1,2,3,0,5,6,7,8,9}, new int[] {4,1,2,3,0,5,6,7,8,9}},
        });
    }

    @Test
    public void testAfterFour(){
        Assert.assertTrue(Arrays.equals(result, main.afterFour(arr)));
    }

    @Test(expected = RuntimeException.class)
    public void testAfterFourExpectedException() {
        Assert.assertEquals(new RuntimeException(), main.afterFour(new int[] {0,0,2,3,0,5,6,7,8,9}));
    }
}
