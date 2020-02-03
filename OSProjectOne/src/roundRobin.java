
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 */
public class roundRobin {
    
    final int MAX = 3;
    
    public void rr (ArrayList<PCB>jobList, ArrayList<PCB> ready ,ArrayList<PCB> io,ArrayList<PCB> disk,PCB[] cpu, SystemTimer timer, int quantum){
        
     
        int i =0;
        ready = loadReady(jobList, ready, io, cpu);
        
        
        do{
            //output(jobList, ready, io, disk, cpu, timer);
       
            cpu = loadCPU(ready, cpu);
              
            output(jobList, ready, io, disk, cpu, timer);
            
            if(cpu[0]==null){
                break;
            }
            
            runCPU(cpu, timer, disk, io, jobList, ready, quantum);

            i++;
            
        }while(i < 50);
    }
    
    public ArrayList<PCB> loadReady(ArrayList<PCB>jobList, ArrayList<PCB> ready,ArrayList<PCB> io, PCB[] cpu){
       
        while(ready.size() < MAX){
            //check io for empty, and cpu for empty
            //if both are empty pull from job
            if(io.isEmpty() && cpu[0] == null && !jobList.isEmpty()){
                ready.add(jobList.get(0));
                jobList.remove(0);
            }
            //check if cpu is not null
            //pull from cpu
            else if(cpu[0] != null){
                ready.add(cpu[0]);
                cpu[0] = null;
                
            }
            //check io is not empty
            //and cpu is null
            //pull from io if ready
            else if(!io.isEmpty() && cpu[0]==null && !ready.isEmpty() ){
                if(io.get(0).getRemainIOBurstTime() == 0){
                    ready.add(io.get(0));
                    io.remove(0);
                }
                else{
                    if(!jobList.isEmpty()){
                        ready.add(jobList.get(0));
                        jobList.remove(0);
                    }
                    else{
                        break;
                    }
                }
            }
            // added
            else if(jobList.isEmpty() && !io.isEmpty()){
                
                    ready.add(io.get(0));
                    io.remove(0);
               
            }
                   
            else{
                break;
            }
        }
        
        return ready;
    }
    
    public PCB[] loadCPU(ArrayList<PCB> ready, PCB[] cpu){
        
        if(!ready.isEmpty()){
            cpu[0] = ready.get(0);
            ready.remove(0);
            if(cpu[0].getRemainCPUBurstTime() >=(int)cpu[0].getCpuBurst().get(cpu[0].getCpuBurstIndex()) || cpu[0].getRemainCPUBurstTime() ==0){
            // cast to an int since techically pulling a interger object
                cpu[0].setRemainCPUBurstTime((int)cpu[0].getCpuBurst().get(cpu[0].getCpuBurstIndex()));
                
            }
        }
        return cpu;
    }
    
    public void runCPU(PCB[] cpu, SystemTimer timer, ArrayList<PCB> disk, ArrayList<PCB> io, ArrayList<PCB> jobList, ArrayList<PCB> ready, int quantum){
        
        
        
        for(int i = 0; i < quantum; i++){
            //increase the timer by one
            timer.setTimer(timer.getTimer()+1);
            
            //set new remaining time
            cpu[0].setRemainCPUBurstTime(cpu[0].getRemainCPUBurstTime()-1);
            
            
            //check to be sure it is not zero
            if(cpu[0].getRemainCPUBurstTime() == 0){
                //update disk
                disk = updateDisk(disk, io);
                //cpu[0].setCpuBurstIndex(cpu[0].getCpuBurstIndex()+1);
               
                if(cpu[0].getIOburstIndex() <= cpu[0].IOburst.size() && cpu[0].getCpuBurstIndex() < cpu[0].cpuBurst.size()){
                    //load disk
                    cpu[0].setCpuBurstIndex(cpu[0].getCpuBurstIndex()+1);
                    disk = loadDisk(cpu, disk);
                }
                //set cpu to null
                cpu[0] = null;
                
                //load ready
                ready = loadReady(jobList, ready,io ,cpu );
                //end the loop
                i = quantum;
            }
            else{
                updateDisk(disk, io);
            }
            
            
        }
        
        ready = loadReady(jobList, ready, io, cpu);
        
    }
    public ArrayList<PCB> loadDisk(PCB[] cpu, ArrayList<PCB> disk){
        
        disk.add(cpu[0]);
        
        if(disk.get(disk.size()-1).getIOburstIndex() < disk.get(disk.size()-1).IOburst.size()){
            disk.get(disk.size()-1).setRemainIOBurstTime((int)disk.get(disk.size()-1).getIOburst().get(disk.get(disk.size()-1).getIOburstIndex()));
            //update index by 1
        }
        disk.get(disk.size()-1).setIOburstIndex(disk.get(disk.size()-1).getIOburstIndex()+1);
        
        
        return disk;
       
    }
    
    public ArrayList<PCB> updateDisk(ArrayList<PCB> disk, ArrayList<PCB> io){
        
        for(int i = 0; i < disk.size(); i++){
            
            disk.get(i).setRemainIOBurstTime(disk.get(i).getRemainIOBurstTime()-1);
           
            if(disk.get(i).getRemainIOBurstTime() <= 0){
                disk.get(i).setIOburstIndex(disk.get(i).getIOburstIndex()+1);
                io.add(disk.get(i));
                disk.remove(i);
            }
        }
        
        return disk;
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
                if(ready.get(i)!=null){
                    readyQ = readyQ + ready.get(i).getPcbNum() + " ";
                }
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
    
    
}
