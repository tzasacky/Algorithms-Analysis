package sched;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FifoScheduler extends Scheduler {

    /**
     * Schedules the task set in the interval [0, scheduleEndTime) using
     * the FIFO scheduling algorithm.
     * @param taskSet - the task set, defining job parameters
     * @param scheduleEndTime - the end of the scheduling interval (exclusive)
     * @return a schedule of which jobs to run for which time intervals
     */
    @Override
    public ArrayList<ScheduleInterval> scheduleTaskSet(ArrayList<Task> taskSet, int scheduleEndTime) {
        ArrayList<ScheduleInterval> schedule = new ArrayList<ScheduleInterval>();

        // Pre-calculate all jobs released in the interval
        HashMap<Integer, ArrayList<Job>> jobReleases = this.calculateJobReleases(taskSet, scheduleEndTime);
        int scheduleTime = 0;
        for(int time = 0; time < scheduleEndTime ; time++) {
        	if(jobReleases.get(time) != null){
        		ArrayList<Job> jobs = jobReleases.get(time);
            	for(int i = 0; i < jobs.size(); i++){
            		schedule.add(new ScheduleInterval(jobs.get(i), scheduleTime, scheduleTime + jobs.get(i).getRemainingTime()));
            		scheduleTime = scheduleTime + jobs.get(i).getRemainingTime();
            	}
        	}else if(scheduleTime <= time) {
        		//Hacky solution to fixing scheduling before releases
        		scheduleTime++;
        	}
        	
        }

        return schedule;
    }
}
