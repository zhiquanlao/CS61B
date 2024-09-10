import org.junit.Test;
import static org.junit.Assert.*;


public class TestArrayDequeGold {
    @Test 
    public void TestStudentArrayDeque() {
        int timeIterate = 1000;
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<Integer>();
        //ArrayDequeSolution<String> message = new ArrayDequeSolution<String>();
        String mess = "";
        for (int i = 0; i < timeIterate; i++) {
            //1=addLast, 2=removeLast, 3=addFirst, 4=removeFirst
            int move = StdRandom.uniform(1, 5);
            if (move == 1) {
                Integer temp = StdRandom.uniform(10);
                sad.addLast(temp);
                sol.addLast(temp);
                //message.addLast("addLast("+temp+")");
                mess = mess + "addLast("+temp+")\n";
            }
            else if (move == 2) {
                if (sol.isEmpty()) {
                    continue;
                }
                Integer expected = sol.removeLast();
                Integer actual = sad.removeLast();
                //message.addLast("removeLast()");
                mess += "removeLast()\n";
                assertEquals(mess, actual, expected);
            }
            else if (move == 3) {
                Integer temp = StdRandom.uniform(10);
                sad.addFirst(temp);
                sol.addFirst(temp);
                //message.addLast("addFirst("+temp+")");
                mess = mess + "addFirst("+temp+")\n";
            }
            else {
                if (sol.isEmpty()) {
                    continue;
                }
                Integer expected = sol.removeFirst();
                Integer actual = sad.removeFirst();
                //message.addLast("removeFirst()");
                mess += "removeFirst()\n";
                assertEquals(mess, actual, expected);
            }
        }
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("failed", TestArrayDequeGold.class);
    }
}
