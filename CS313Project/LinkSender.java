// LinkSender sends a message to LinkReceiver and receives a reply.
// LinkReceiver needs to be started before LinkSender.
import java.util.*;
import java.util.Random; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LinkSender {

   static int senderPort = 3200;   // port number used by sender
   static int receiverPort = 3300; // port number used by receiver
   static Scanner console = new Scanner(System.in);
   
   public static void main (String args[]) throws Exception 
   {	
   
      int lengthMessageToSend; 
      int lengthMessageReceived = 0;
      String messageToSend;
      String messageReceived;
      byte[] sendingBuffer = new byte[16]; //was 512
      byte[] receivingBuffer = new byte[512]; //was 512
      byte[] frame = new byte[19];
      byte[] damageFrame = new byte[19]; //this iwll be used to send a damaged frame
      int packages =0;  //Number of sends it should take
      int framesSent=0; //number of frames sent
      double thoFrames = 0.0; 
      double errorRate =0; //giving error rate
      int framesDamaged =0;   //keep track of number of frames that are damaged
      int maxResends =0;   //Tracks max number of resends of 1 frame
      int count =0;        //tracks current resends, and sets it to maxResend if greater than
      boolean damageFlag = true; //flag control
      int randNum=0;          //Rand number for checking if we should damage frame.
      double damageRate=0;       //Rate of which frames should be damaged.
      int check=0;            //cheap track of the size of the bytes added from the file, up to 16
      boolean trace = false;
      int seq =0;
      String fileName;
      /*
      *  Trace on vs off
      */
      //ask for filename
      System.out.println("Enter file name(add .txt to end): ");
      fileName = console.nextLine();
      //ask for trace on or off
      System.out.println("Trace on?(y/n): ");
      String traceOn = console.nextLine();
      if(traceOn.equals("y")){
         trace = true;
      }
      //Ask for error rate
      System.out.println("Would you like to add an Error rate?(y/n): ");
      String choice = console.nextLine();
      
      //check to see if they want error rate to be
      if(choice.equals("y")){
         System.out.println("Enter an error rate between 0 and 100:");
         damageRate = console.nextInt(); 
      
      }
      
   	// Set up a link with source and destination ports
      Link myLink = new SimpleLink(senderPort, receiverPort);
      
      //Read a file
      FileInputStream infile = new FileInputStream(new File(fileName));
      while (( check = infile.read(sendingBuffer)) != -1) { //read the file into the sending buffer
       
      //Start method
      //create a loop for multiple sends
         
         boolean flag =true;
         do{
            for(int i=0; i<sendingBuffer.length; i++){
            
               if(i%16 == 0){//this should insert into frame, 0 to 15 in the frame.
               
                  if(sendingBuffer.length -i < 16){
                     check = sendingBuffer.length%16;
                  }
               //empties out the frame
                  for(int j =0; j<frame.length; j++){
                     frame[j] = 0;
                  } 
               //set first index to seq
                  frame[0] =(byte)seq;
               //set second index to the size of the payload, 1 - 16
                  frame[1] = (byte)check;          //note was check
               //insert payload into the frame
                  System.arraycopy(sendingBuffer, i, frame,2, check);
               //calculate CRC to the last index in the frame
                  frame[18]=CRC8.checksum(frame);
               
               //Damage frame as needed using random number generator
                  while(damageFlag=true){
                     Random rand = new Random();
                     randNum = rand.nextInt(101);
                     //System.out.println(randNum);
                     if(damageRate ==0){
                        break;
                     }
                     else{ //will damage the frame if nessecary
                        if(randNum <=damageRate){ 
                           
                                 
                              framesDamaged++;
                              randNum = rand.nextInt(18);
                              int randValue = rand.nextInt(256);
                              frame[randNum] =(byte)randValue;
                              
                                                           
                           break;
                        
                        }//end retrasmission
                        
                        else{
                        
                           break;
                        }
                     }// end else
                  
                  }//End while loop
               
               //Send the message
                  myLink.sendFrame(frame,frame.length);
               //increment frames and package sent
                  packages++;
                  framesSent++;
               
               //partial message sent
                  String partial = new String(frame, 2, check);
                  if(randNum<= damageRate){ 
                     System.out.println("Error");
                  }
                  else{
                     System.out.println("Data to be sent [ " + partial + " ]"+"sequence number: " + seq);
                  }
               
               //wait and receive message
                  lengthMessageReceived = myLink.receiveFrame(receivingBuffer);
               //Display the message
               
                  messageReceived = new String(receivingBuffer, 0, lengthMessageReceived);
                  if(trace){
                     System.out.println( messageReceived);
                  }
               //resend the previous frame
                  if(messageReceived.compareTo("OK!") !=0){
                     if(i <16){
                        i =0;
                     }
                     else{
                        i= i -check;
                     //note this should resend the previous frome
                     }
                     packages--;
                     count ++;
                     if(count > maxResends){
                        maxResends = count;
                     }
                  }
                  else{
                     flag = false;  //break out of the Do While loop if the sending
                                    //frame was good
                     count =0;
                  //if good seq ++, count reset
                     if(seq == 1){
                        seq =0;
                     }
                     else{
                        seq = 1;
                     }
                  //else resend
                  }
               }
                  
            
            }//end for loop
         }while (flag); //controls and issue where the resend, wasnt not
                        //sending the previous frame, it would skip that frame instead.
      
      }//end while loop
      
      //send empty frame of seq 2 that tells receiver to end
      frame[0] =2;
      myLink.sendFrame(frame,frame.length);
      
      //Close the connection
      infile.close();         //close the infile port	
      myLink.disconnect();    //disconnect the link
      
      //Print the statistics
      System.out.println("Statistics");
      System.out.println("Packages Sent: " + packages + "\n" +
                        "Frames Sent: " + framesSent + "\n" +
                        "Theoretical total number of transmission: " +packages/(1-(damageRate/100)) + "\n"+
                        "Total Number Of Frames Damaged: " + framesDamaged + "\n" +
                        "Maximum Number of frame resends: " + maxResends);
      
      
   }
   
   
}
