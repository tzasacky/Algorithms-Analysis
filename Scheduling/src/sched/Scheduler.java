package sched;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Scheduler {

    /**
     * Schedules the task set in the interval [0, scheduleEndTime).
     * @param taskSet - the task set, defining job parameters
     * @param scheduleEndTime - the end of the scheduling interval (exclusive)
     * @return a schedule of which jobs to run for which time intervals
     */
    public abstract ArrayList<ScheduleInterval> scheduleTaskSet(ArrayList<Task> taskSet, int scheduleEndTime);

    /**
     * Initializes all jobs released in the interval [0, endTime), and groups
     * them in a HashMap by release time.
     * @param taskSet - the task set, defining job parameters
     * @param endTime - the end of the interval (exclusive)
     * @return a map of release time to job instances
     */
    protected HashMap<Integer, ArrayList<Job>> calculateJobReleases(ArrayList<Task> taskSet, int endTime) {
        // Pre-calculate all job release times within this interval (and initialize
        // those jobs)
        HashMap<Integer, ArrayList<Job>> jobReleases = new HashMap<Integer, ArrayList<Job>>();
        for (Task task : taskSet) {
            int numJobs = (int)Math.ceil(endTime / (float)task.getPeriod());
            for (int j = 1; j <= numJobs; j++) {
                // Initialize the jth job of the task
                Job job = new Job(task, j);

                // Add the job to the map of job releases
                if (!jobReleases.containsKey(job.getRelease())) {
                    jobReleases.put(job.getRelease(), new ArrayList<Job>());
                }

                jobReleases.get(job.getRelease()).add(job);
            }
        }

        return jobReleases;
    }
}
