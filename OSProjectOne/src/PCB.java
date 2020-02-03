
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 * This is my object that stores all the information about the process.
 */
public class PCB {
    
    ArrayList cpuBurst = new ArrayList();
    ArrayList IOburst = new ArrayList();
    int cpuBurstIndex;
    int IOburstIndex;
    int remainCPUBurstTime;
    int remainIOBurstTime;
    int remainingTicks;
    int pcbNum;

    public PCB(ArrayList cpuBurst, ArrayList IOburst, int cpuBurstIndex, int IOburstIndex, int remainCPUBurstTime, int remainIOBurstTime, int remainingTicks, int pcbNum) {
        this.cpuBurst = cpuBurst;
        this.IOburst = IOburst;
        this.cpuBurstIndex = cpuBurstIndex;
        this.IOburstIndex = IOburstIndex;
        this.remainCPUBurstTime = remainCPUBurstTime;
        this.remainIOBurstTime = remainIOBurstTime;
        this.remainingTicks = remainingTicks;
        this.pcbNum = pcbNum;
    }
   
    public ArrayList getCpuBurst() {
        return cpuBurst;
    }

    public void setCpuBurst(ArrayList cpuBurst) {
        this.cpuBurst = cpuBurst;
    }

    public ArrayList getIOburst() {
        return IOburst;
    }

    public void setIOburst(ArrayList IOburst) {
        this.IOburst = IOburst;
    }

    public int getCpuBurstIndex() {
        return cpuBurstIndex;
    }

    public void setCpuBurstIndex(int cpuBurstIndex) {
        this.cpuBurstIndex = cpuBurstIndex;
    }

    public int getIOburstIndex() {
        return IOburstIndex;
    }

    public void setIOburstIndex(int IOburstIndex) {
        this.IOburstIndex = IOburstIndex;
    }

    public int getRemainCPUBurstTime() {
        return remainCPUBurstTime;
    }

    public void setRemainCPUBurstTime(int remainCPUBurstTime) {
        this.remainCPUBurstTime = remainCPUBurstTime;
    }

    public int getRemainIOBurstTime() {
        return remainIOBurstTime;
    }

    public void setRemainIOBurstTime(int remainIOBurstTime) {
        this.remainIOBurstTime = remainIOBurstTime;
    }

    public int getRemainingTicks() {
        return remainingTicks;
    }

    public void setRemainingTicks(int remainingTicks) {
        this.remainingTicks = remainingTicks;
    }
    
    public int getPcbNum(){
        return pcbNum;
    }
    
    public void setPcbNum(int pcbNum){
        this.pcbNum = pcbNum;
    }

    @Override
    public String toString() {
       
        return "PCB " + pcbNum +  
                "\ncpuBurstIndex=" + cpuBurstIndex +
                "\nIOburstIndex=" + IOburstIndex + 
                "\nremainCPUBurstTime=" + remainCPUBurstTime + 
                "\nremainIOBurstTime=" + remainIOBurstTime + 
                "\nremainingTicks=" + remainingTicks;
    }
    
    
    
}
