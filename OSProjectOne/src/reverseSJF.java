
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 */
public class reverseSJF {
    final int MAX = 3;
    
    public void RevSJF (ArrayList<PCB>jobList, ArrayList<PCB> ready ,ArrayList<PCB> io,ArrayList<PCB> disk,PCB[] cpu, SystemTimer timer){
        
        //organize by job value
        int i =0;
        
        reverse(jobList);
        
        ready = loadReady(jobList, ready, io);
        
        output(jobList, ready, io, disk, cpu, timer);
        do{
            
            if(ready.size() !=MAX){
                loadReady(jobList, ready, io);
            }
            
            cpu = loadCPU(ready, cpu);
            output(jobList, ready, io, disk, cpu, timer);
            runCPU(cpu, timer, disk, io, jobList, ready);
            
            if(cpu[0]!=null){
                loadDisk(cpu, disk);
            
            }
            
            if(cpu[0]==null && ready.isEmpty() && io.isEmpty() && disk.isEmpty()){
                break;
            }
            i++;
            
        }while(i<15); 
        
        output(jobList, ready, io, disk, cpu, timer);
        
        
    }
    
    
    public ArrayList<PCB> loadReady(ArrayList<PCB> jobList, ArrayList<PCB> ready, ArrayList<PCB> io){
        int smallest = 500;
        int index =0;
        while(ready.size() < MAX){
            
            if(io.isEmpty()  && !jobList.isEmpty()){
                for(int i = 0; i < ready.size(); i++){
                
                
                    if((int)ready.get(i).getCpuBurst().get(ready.get(i).getCpuBurstIndex()) < smallest){
                        smallest = (int)ready.get(i).getCpuBurst().get(ready.get(i).getCpuBurstIndex());
                        index = i;
                    }//end if
                
                }
                
                if(index == jobList.size()){
                    index = index -1;
                } 
                ready.add(jobList.get(index));
                jobList.remove(index);
            
            }//end outer if
            else if(!io.isEmpty()){
            
                if(io.get(0).getRemainIOBurstTime() ==0 ){
                    ready.add(io.get(0));
                    io.remove(0);
                }
            
            }
            else if (jobList.isEmpty() && io.isEmpty()){
                break;
            }
        }//end while loop
        return ready;
    }
    
    public PCB[] loadCPU(ArrayList<PCB> ready, PCB[] cpu){
        int smallest = 50;
        int index =0;
        
       
        if(!ready.isEmpty()){
            for(int i = 0; i < ready.size(); i++){
                
                if((int)ready.get(i).getCpuBurst().get(ready.get(i).getCpuBurstIndex()) < smallest){
                    smallest = (int)ready.get(i).getCpuBurst().get(ready.get(i).getCpuBurstIndex());
                    index = i;
                }
                
            }
            cpu[0] = ready.get(index);
            ready.remove(index);
            
            //update burst index
            cpu[0].setRemainCPUBurstTime((int)cpu[0].getCpuBurst().get(cpu[0].getCpuBurstIndex())); //wondering why i took of 89 i dont know why
            cpu[0].setCpuBurstIndex(cpu[0].getCpuBurstIndex()+1);
            
        }
        
        return cpu;
        
    }
    
    public void runCPU(PCB[] cpu, SystemTimer timer, ArrayList<PCB> disk, ArrayList<PCB> io, ArrayList<PCB> jobList, ArrayList<PCB> ready){
        
        if(cpu[0] !=null){
            while(cpu[0].getRemainCPUBurstTime() !=0){
            
                timer.setTimer(timer.getTimer()+1);
            
                cpu[0].setRemainCPUBurstTime(cpu[0].getRemainCPUBurstTime()-1);
            
                //update diskQueue
                updateDisk(disk, io);
                
            }
        }//end if
        
        
    }
    
    public void loadDisk(PCB[] cpu, ArrayList<PCB> disk){
        
        if(cpu[0].getIOburstIndex() >= cpu[0].getIOburst().size()){
            
        }
        else {
            
            cpu[0].setRemainIOBurstTime((int)cpu[0].getIOburst().get(cpu[0].getIOburstIndex()));
            cpu[0].setIOburstIndex(cpu[0].getIOburstIndex()+1);
            disk.add(cpu[0]);
        }//end else
        cpu[0] = null;
    }
    
    public void updateDisk(ArrayList<PCB> disk, ArrayList<PCB> io){
        
        if(!disk.isEmpty()){
            
            for(int i = 0; i < disk.size(); i++){
                
                disk.get(i).setRemainIOBurstTime(disk.get(i).getRemainIOBurstTime()-1);
                
                if(disk.get(i).getRemainIOBurstTime()==0){
                    //move to io
                    io.add(disk.get(i));
                    disk.remove(i);
                    
                }//end if
                
            }//end for loop
        }//end outer if
        
    }
    
    public void output(ArrayList<PCB>jobList, ArrayList<PCB> ready ,ArrayList<PCB> io,ArrayList<PCB> disk,PCB[] cpu, SystemTimer timer){
        
        String job ="";
        String readyQ = "";
        String ioQ ="";
        String diskQ = "";
        int cpuQ = 0;
        
        if(!jobList.isEmpty()){
            for(int i =0; i < jobList.size(); i++){
            
                job = job + jobList.get(i).getPcbNum() + " ";
            }
        }//end if
        
        if(!ready.isEmpty()){
            for(int i =0; i < ready.size(); i++){
            
                readyQ = readyQ + ready.get(i).getPcbNum() + " ";
            }
        }//end if
        if(!io.isEmpty()){
            for(int i =0; i < io.size(); i++){
            
                ioQ = ioQ + io.get(i).getPcbNum() + " ";
            }
        }//end if
        if(!disk.isEmpty()){
            for(int i =0; i < disk.size(); i++){
            
                diskQ =diskQ + disk.get(i).getPcbNum() + " ";
            }
        }//end if
        
        if(cpu[0] != null){
            
            cpuQ = cpu[0].getPcbNum();
        }
        
        
        System.out.printf("Time: " + timer.getTimer() + "%n"
                          +"%10s %s %n"
                          +"%10s %s %n"
                          +"%10s %s %n"
                          +"%10s %s %n"
                          +"%10s %s%n%n", "CPU: ", cpuQ, "Job: ", job, "Ready: ",
                                        readyQ, "Disk: ", diskQ, "IO: ", ioQ);
    }
    
    
     
      public void reverse(ArrayList<PCB> jobList){
        
        Collections.reverse(jobList);
        for(int i=0; i<jobList.size();i++){
            jobList.get(i).setPcbNum(i+1);
        }
        
        
    }
}


