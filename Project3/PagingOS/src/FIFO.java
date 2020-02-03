import java.util.Scanner;

public class FIFO {
	private static Scanner console = new Scanner(System.in);
	private static int pointerOne =0;
	private static int pointerTwo =0;
	private static int counter =0;
	private static int frameFaults =0;
	
	public void FIFO() {
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
		runFIFO(list, frame);
		
		
	}
	
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
	
public boolean checkNumbers(int[] list, int[] frame){
		
		for(int i =0; i < frame.length; i++) {
			
			if(frame[i] == list[pointerTwo]) {
				return true;
			}
			
		}
	
		return false;
	}
	
	public void runFIFO(int[] list, int[] frame) {
		
		
		while(pointerTwo < list.length) {
		
			for(int i =0 ; i < frame.length; i++) {
			
				if(frame[i] == list[pointerTwo]) {
					pointerTwo++;
					break;
				}
				else if(i == frame.length -1) {
					frame[counter] = list[pointerTwo];
					pointerTwo++;
					counter++;
					System.out.println(counter);
					frameFaults++;
					if(counter > frame.length-1) {
						counter =0;
					}
					output(list, frame);
					break;
				}
			
			}//end for loop
		}//end while loop
	}
	
	
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
