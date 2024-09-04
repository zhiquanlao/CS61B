import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Test;

public class FlikTest {
    @Test 
    public void TestIsSame() {
        for (int i = 0; i < 130; i++) {
            if(! Flik.isSameNumber(i, i)){
                System.out.println(i);
            }
            assertTrue(Flik.isSameNumber(i, i));

        }
 
   }

   public static void main(String[] args) {
       jh61b.junit.TestRunner.runTests("failed", FlikTest.class);
       int a = 128;
       int b = 128;
       System.out.println(a == b);
   }
}
