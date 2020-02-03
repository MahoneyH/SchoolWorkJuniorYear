import java.util.ArrayList;
import java.util.Scanner;

public class LRU {

  
	private static Scanner console = new Scanner(System.in);
	private static int pointerOne =0;
	private static int pointerTwo =0;
	private static int frameFaults =0;
	
	public void LRU() {
		 int[] list = new int[] {1,2,3,4,2,1,5,6,2,1,2,3,7,6,3,2,1,2,3,6};
		 int size =0;
		
		System.out.println("data is :");
		for( int i =0 ; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		
		System.out.println("How many frames do we have? ");
		size = console.nextInt();
		
		//build the array
		int[] frame = new int[size];
		
		
		//load the frames
		loadFrames(list, frame);
		runLRU(list, frame);
		
		
	}
	
	/**
	 * This methods will load the first use pages in the system, will need to be revamped
	 * to accommodate same page numbers.
	 * @param list
	 * @param frame
	 */
	
	public void loadFrames(int[] list, int[] frame) {
		boolean flag;
		
		while(pointerOne < frame.length ) {
			flag = checkNumbers(list, frame);
			if(flag) {
				System.out.println(pointerOne);
				//pointerOne++;
				pointerTwo++;
			}
			else {
				frame[pointerOne] = list[pointerTwo];
				pointerOne++;
				pointerTwo++;
				frameFaults++;
				output(list, frame);
			}
		}
			
	}
	
	/**
	 * This method needs to go to through the frame array and make sure that the page
	 * number that is being looked for is in it or not.
	 * @param list
	 * @param frame
	 * @return
	 */
	public boolean checkNumbers(int[] list, int[] frame){
		
		for(int i =0; i < frame.length; i++) {
			
			if(frame[i] == list[pointerTwo]) {
				return true;
			}
			
		}
	
		return false;
	}
	
	
	public int findLRU(int[] list, int[] frame) {
		 
		 //Compare the List, to the check frame
		// if the number exists in the check frame, then don't add it into the check frame
		//The last number in the check frame is the number that needs to be replaced in the frame
		
		ArrayList<Integer> check = new ArrayList<Integer>();
		int tPointer = pointerTwo;
		tPointer--;
		while(check.size() < frame.length) {
		if(check.isEmpty()) {
			//add into the check, from the list
			check.add(list[tPointer]);
			tPointer--;
		}
		else {
			
			//check to be sure that the tPointer in list, isn't in the Check
			for(int i =0; i < check.size(); i++ ) {
				//add to the array
				if(check.get(i)==list[tPointer]) {
						tPointer--;
						break;
					}
				//if you reached the end of the loop, and that last number doesnt match then we add it in.
				else if(i == check.size()-1 && check.get(i)!=list[tPointer]) {
					check.add(list[tPointer]);
					tPointer--;
					break;
				}//end else if
					
			}//end for loop
				
		}//end else
		
		}
		String checker ="";
		for(int j =0; j< check.size(); j++) {
			checker = checker + " " + check.get(j); 
		}
		
		//return the last index of check which is the last number.
		System.out.println(checker);
	     return check.get(check.size()-1);
	}
	

	
	public void runLRU(int[] list, int[] frame) {
		
		while(pointerTwo < list.length) {
			boolean flag;
			flag = checkNumbers(list, frame);
		
			if(flag) {
				pointerTwo++;
			}
			else {
				int lru = findLRU(list, frame);
			
				//find lru in 
				for(int i =0; i < frame.length; i++) {
					if(frame[i]== lru) {
						frame[i] = list[pointerTwo];
						pointerTwo++;
						pointerOne = pointerTwo -1;
						frameFaults++;
						output(list, frame);
						break;
					}//end if
				
				}//end for loop
			}//end else
		}
	}//end run lru
	

	public void output(int[] list, int[] frame) {
		String out ="";
		String out2;
		int out3;
		for(int i =0; i <frame.length; i++) {
			if(frame[i] == 0) {
				out2 = "Empty";
			}
			else {
				out3 = frame[i];
				out2 = Integer.toString(out3);
			}
			out = out + i + "        " + out2 + "\n";
			
		}
		
		System.out.println("Frame faults " + frameFaults + "\n" +
						   "Frame" + "    " + "Page" + "\n" +
							out +"\n\n");
		
		
	}
	
}
