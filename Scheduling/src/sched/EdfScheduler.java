package sched;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class EdfScheduler extends Scheduler {

    /**
     * Schedules the task set in the interval [0, scheduleEndTime) using
     * the EDF scheduling algorithm.
     * @param taskSet - the task set, defining job parameters
     * @param scheduleEndTime - the end of the scheduling interval (exclusive)
     * @return a schedule of which jobs to run for which time intervals
     */
    @Override
    public ArrayList<ScheduleInterval> scheduleTaskSet(ArrayList<Task> taskSet, int scheduleEndTime) {
        ArrayList<ScheduleInterval> schedule = new ArrayList<ScheduleInterval>();
        PriorityQueue<Job> queue = new PriorityQueue<Job>(); // Queue compares deadlines
        int time = 0;
        
        // Pre-calculate all jobs released in the interval
        HashMap<Integer, ArrayList<Job>> jobReleases = this.calculateJobReleases(taskSet, scheduleEndTime);
        
        // Build Task Schedule
        while(time < scheduleEndTime || !queue.isEmpty()){
        	//Advance until tasks found if queue empty
        	if(queue.isEmpty()){
        		if(jobReleases.get(time) != null){
            		ArrayList<Job> jobs = jobReleases.get(time);
            		queue.addAll(jobs);
        		}else{time++;}
        	}else {
	        	Job currentJob = queue.remove();
	        	int startTime = time;
	        	while(!currentJob.isComplete()){
	        		//Move to next timestep
	        		currentJob.execute(1);
	        		time++;
	        		
	        		//If jobs are released, add to queue
	        		if(jobReleases.get(time) != null){
	            		ArrayList<Job> jobs = jobReleases.get(time);
	            		queue.addAll(jobs);
	            		//Handle preemptions
	            		if(queue.peek().getDeadline() < currentJob.getDeadline()){
	            			schedule.add(new ScheduleInterval(currentJob, startTime, time));
	            			if(!currentJob.isComplete()) { 
	            				queue.add(currentJob); 
	            			}
	            			currentJob = queue.remove();
	            			startTime = time;
	            		}
	        		}            		
	        	}
	        	schedule.add(new ScheduleInterval(currentJob, startTime, time));
	        }
        }
        
        return schedule;
    }
}
