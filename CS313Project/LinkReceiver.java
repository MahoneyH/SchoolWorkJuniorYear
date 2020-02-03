// LinkReceiver receives a message from LinkSender and replies.
// LinkReceiver needs to be started before LinkSender.
import java.util.Arrays;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class LinkReceiver {

   static int senderPort = 3200;   // port number used by sender
   static int receiverPort = 3300; // port number used by receiver
	static Scanner console = new Scanner(System.in);
   public static void main (String args[]) throws Exception 
   {	
      byte checkError =0;
      int lengthMessageToSend; 
      int lengthMessageReceived = 0;
      String messageToSend;
      String messageReceived="";
      byte[] sendingBuffer = new byte[16];
      byte[] receivingBuffer = new byte[19];
      byte[] checksumTest = new byte[17];
      boolean flag =true;
      int seqNum =0;
      String outputFile;
      boolean traceOn = false;
   	// Set up a link with source and destination ports
   	// Any 4-digit number greater than 3000 should be fine. 
      Link myLink = new SimpleLink(receiverPort, senderPort);
      
      System.out.println("Enter output destination(outfile.txt): ");
      outputFile = console.nextLine();
      
      System.out.println("Trace on?(y/n): ");
      String trace = console.nextLine();
      
      if(trace.equals("y")){
         traceOn = true;
      }
      //create a file to output the sent data that it receives
      FileOutputStream outfile = new FileOutputStream(new File(outputFile));

      while(flag){
      // Receive a message
         lengthMessageReceived = myLink.receiveFrame(receivingBuffer);
         lengthMessageReceived = receivingBuffer[1]; 
         System.out.println("Data Length is: " + lengthMessageReceived);
      
      //check sequence number and close if 2
         if(receivingBuffer[0] ==2){
            System.out.println("Goodbye");
            outfile.close();
            myLink.disconnect();
            System.exit(0);
         }
         
         if(seqNum == receivingBuffer[0]){
            //System.out.print("Correct Sequence");
            if(seqNum == 0){
               seqNum =1;
            }
            else{
               seqNum = 0;
            }
         }
         //Check CRC and prepare to ask for next, or ask for resend.
         checkError = CRC8.checksum(receivingBuffer);
         //handle and error
         if(lengthMessageReceived > 16){
            lengthMessageReceived =16;
         }
         if(traceOn){   
            messageReceived = new String(receivingBuffer, 0, lengthMessageReceived+2);  
            System.out.println("Message received is: [" + messageReceived + "]");
         }
      
         if(checkError == 0){
            System.out.println("Check Sum is: " + checkError);
            
         // Display the message
            messageReceived = new String(receivingBuffer, 0, lengthMessageReceived+2);
            outfile.write(receivingBuffer, 0, lengthMessageReceived+2);
            if(!traceOn){
               System.out.println("Message received is: [" + messageReceived + "]");
            }	
            //messageReceived = null;
         
         // Prepare a message		
            messageToSend = "OK!";
            lengthMessageToSend = messageToSend.length(); 
            sendingBuffer = messageToSend.getBytes();
         
         // Send the message
            myLink.sendFrame(sendingBuffer, lengthMessageToSend);
         
         // Print out the message sent
            System.out.println("Message sent is:   [" + messageToSend + "]");
            
         }
         //Else as for retransmission
         else{
         // Prepare a message		
            messageToSend = "ERROR! request retransmission";
            lengthMessageToSend = messageToSend.length(); 
            sendingBuffer = messageToSend.getBytes();
         
         // Send the message
            myLink.sendFrame(sendingBuffer, lengthMessageToSend);
         
         // Print out the message sent
            System.out.println("Message sent is:   [" + messageToSend + "]");
         
         
         }//end else
      
      
      
      
      }//end while loop
      
   	// Close the connection
      
   }
}
