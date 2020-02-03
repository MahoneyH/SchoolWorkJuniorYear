/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 * This timer will control the I/O burst, and help calculate time.
 */
public class SystemTimer {
    int timer;

    public SystemTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "SystemTimer{" + "timer=" + timer + '}';
    }
    
    
    
    
}
