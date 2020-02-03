import java.util.Scanner;

public class Main {
	private static int choice=0;
	private static Scanner console = new Scanner(System.in);
	private static LRU lru = new LRU();
	private static FIFO fifo = new FIFO();
	
	public static void main(String[] args) throws InterruptedException {
		
		
		System.out.println("Welcome to the Paging simulation");
		//Thread.sleep(2000);
		System.out.println("We are currently setting things up for you");
		//Thread.sleep(2000);
		System.out.println("Please sit tight....");
		//Thread.sleep(2000);
		System.out.println("Thank you for waiting");
		//Thread.sleep(2000);
		
		System.out.println("Would you like to Run your simulation in LRU or FIFO?");
		//Thread.sleep(1000);
		System.out.println("1 - LRU (Least Recently Used)");
		//Thread.sleep(1000);
		System.out.println("2 - FIFO (First in First Out)");
		//Thread.sleep(1000);
		System.out.print("Enter your choice: ");
		choice = console.nextInt();
		
		switch(choice){
			case 1:
				System.out.println("LRU good Choice!");
				lru.LRU();
				
				break;
			case 2:
				System.out.println("FIFO good Choice!");
				fifo.FIFO();
				break;
			default:
				System.out.println("Invalid input");
				break;
		}
		
		
		
	}
}
