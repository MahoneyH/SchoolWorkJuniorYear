/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 */
import java.util.*;
import java.io.*;


public class SystemConsole {
    Scanner console = new Scanner(System.in);
    public static void main(String args[]) throws IOException{
        //variables
        
        int choice, quantum; //used for the switch
        boolean flag = true; //used to controll the loops
        ArrayList<PCB> job = new ArrayList(); //used as a temp holder for jobqueue
        ArrayList<PCB> JobList = new ArrayList();  //create a jobQueue
        ArrayList<PCB> readyUp = new ArrayList(); 
        ArrayList<PCB> ioList = new ArrayList(); //Create and empty IOQueue.
        ArrayList<PCB> diskList = new ArrayList(); //Create diskList
        PCB[] cpuList = new PCB[1];
        SystemTimer timer = new SystemTimer(0); 
        
        ShortestJob runSJF = new ShortestJob();
        roundRobin runRR = new roundRobin();
        reverseSJF rSJF = new reverseSJF();
       
        //start while loop, for user input
        //on gettng the inout compare to be sure it is a valid option.
        job =readFile();
        JobList = job;
        
        Scanner console = new Scanner(System.in);
        while(flag){    
            try{
               // while(flag){
                    
                    choice = getPick();
                    
                    if(choice != 1 && choice !=2 && choice !=3){
                        System.out.print("Wrong input please re-enter selection");
                        choice = getPick();
                    }
                    else{
                        flag = false;
                    }
                    
                    switch(choice){
                        case 1:
                             //call the cpu to begin the process
                            runSJF.SJF(JobList, readyUp, ioList, diskList, cpuList, timer);
                            
                             break;
                        case 2:
                            
                            System.out.println("Enter a quantum time: ");
                            quantum = console.nextInt(); 
                            
                            //Call methods for round robins
                            runRR.rr(JobList, readyUp, ioList, diskList, cpuList, timer, quantum);
                            break;
                        case 3:
                            rSJF.RevSJF(JobList, readyUp, ioList, diskList, cpuList, timer);
                            break;
                    }
            
               // }
            }
            //catch input errors
            catch(InputMismatchException e){
               System.out.print("Please enter either 1 or 2\n");
               
            }
            //Switch case for choice the algorthmn 
            
        } //end main
    }//end class
    
    
    
    //Start methonds --------------------------------------------------------------------
    //simple method to grab user input.
    //Which will be used to pick either SJF or RR
    public static int getPick(){
        int pick = 0;
        Scanner console = new Scanner(System.in);
        try{
             System.out.print("\nWould you like to use SJF or Round Robin or Reverse SJF?(1 = SJF, 2= RR, 3 = RSJF): ");
             pick= console.nextInt();
             //console.next();
        }
        catch(InputMismatchException e){
            
        }
        
        return pick;
    }
    
    public static ArrayList<PCB> readFile() throws FileNotFoundException, IOException{
        ArrayList<PCB> Jobs = new ArrayList<>();
        Scanner myFile = new Scanner(new FileReader(new File("src/testData")));
       
        
        int pcbNum =1;
        String nextJob;
        while ((myFile.hasNextLine())){ 
             int i = 0;
             int j = 0;
             int k =0;
             int cpuNum= 0;
             int burstNum =0;
             ArrayList cpu = new ArrayList();
             ArrayList burst = new ArrayList();
             
               //System.out.print(myFile.nextLine() +"\n");
               nextJob = myFile.nextLine();
               System.out.print(nextJob + "\n");
                //it is converting the number to its acii value, subtracting -48 to fix that issues, but it is ugly.....
                while(i < nextJob.length()){
                    //check for even number
                    if(j==k && i%2 ==0){
                        cpuNum= nextJob.charAt(i);
                        cpu.add(cpuNum -48); 
                        j++;
                        i++;
                    }
                    else if(j>k && i%2 ==0){
                        burstNum = nextJob.charAt(i); //a sad work around, -48 to fix ascii issues
                        burst.add(burstNum-48);
                        k++;
                        i++;
                        
                    }
                    else {
                        i++;
                    }
                    
                }//end while loop
               
               PCB newJob = new PCB(cpu, burst, 0, 0, 0, 0, cpu.size(), pcbNum);
               Jobs.add(newJob);
               pcbNum++;
               System.out.println(newJob.toString()+ "\n");
            
           
            }
        return Jobs;
        }
    
    
    
       
}//end class

