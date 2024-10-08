
import java.awt.desktop.SystemEventListener;

/** Performs some basic linked list tests. */
public class ArrayDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		
		ArrayDeque<String> lld1 = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
		
	}

	public static void TestGet(){
		ArrayDeque<Integer> ad1= new ArrayDeque<Integer>();
		ad1.addLast(0);
         ad1.addFirst(1);
         ad1.addFirst(2);
        ad1.addLast(0);
         ad1.addFirst(1);
         ad1.addFirst(2);
         ad1.addFirst(3);
         ad1.addLast(4);
		 ad1.printDeque();
		 System.out.println();
         System.out.println(ad1.get(3)+" ");
         System.out.println(ad1.removeLast());
		ad1.printDeque();
		 System.out.println();
         ad1.addFirst(7);
         ad1.addFirst(8);
         ad1.addFirst(9);
         ad1.addLast(10);
ad1.printDeque();
		 System.out.println();
         System.out.println(ad1.get(2));
         System.out.println(ad1.get(6));
ad1.printDeque();
		 System.out.println();
         System.out.println(ad1.removeLast());
         System.out.println(ad1.get(0));
         System.out.println(ad1.removeLast());
ad1.printDeque();
		 System.out.println();
         System.out.println(ad1.get(5));
         System.out.println(ad1.get(3));
         System.out.println(ad1.removeFirst());
ad1.printDeque();
		 System.out.println();
         ad1.addFirst(19);
ad1.printDeque();
		 System.out.println();
         System.out.println(ad1.removeLast());
	}
	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
		
	}

	public static void ADBasic(){
		ArrayDeque<Integer> ad1= new ArrayDeque<Integer>();
		ad1.addLast(0);
		ad1.printDeque();
		System.out.println();
		System.out.println(ad1.removeLast());
		System.out.println(ad1.isEmpty());
		ad1.addLast(3);
		ad1.printDeque();
		System.out.println();
		System.out.println(ad1.removeLast());
	}
	public static void ADBasic2(){
		ArrayDeque<Integer> ad1= new ArrayDeque<Integer>();
		ad1.addLast(0);
		ad1.printDeque();
		System.out.println(ad1.isEmpty());
		System.out.println();
		System.out.println(ad1.removeFirst());
		
		ad1.addLast(3);
		ad1.printDeque();
		System.out.println();
		System.out.println(ad1.removeFirst());
	}
	
	public static void ADBasic3() {
		ArrayDeque<Integer> ad1= new ArrayDeque<Integer>();
		ad1.addFirst(0);
         System.out.println(ad1.removeLast());      
         System.out.println(ad1.isEmpty());
         System.out.println(ad1.isEmpty());
         System.out.println(ad1.size());
         System.out.println(ad1.isEmpty());
         System.out.println(ad1.isEmpty());
         ad1.addFirst(7);
         System.out.println(ad1.removeFirst() ) ;  
        System.out.println( ad1.size());
         ad1.addLast(10);
         System.out.println(ad1.removeFirst() ) ;
	}
	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		ADBasic();
		System.out.println("ADBasic2");
		ADBasic2();
		System.out.println("ADBasic3");
		ADBasic3();
	}
} 