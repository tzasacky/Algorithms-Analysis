package sched;

import java.util.ArrayList;

public class Main {
    
    public static final int SCHEDULE_END_TIME = 12;

    public static void main(String[] args) {
        // Build the task set
//        ArrayList<Task> tasks = buildTaskSet1();
//        ArrayList<Task> tasks = buildTaskSet2();
        ArrayList<Task> tasks = buildTaskSet3();

        // Try to schedule the task set
        ArrayList<ScheduleInterval> schedule = new FifoScheduler().scheduleTaskSet(tasks, SCHEDULE_END_TIME);
//        ArrayList<ScheduleInterval> schedule = new EdfScheduler().scheduleTaskSet(tasks, SCHEDULE_END_TIME);

        for (ScheduleInterval interval : schedule) {
//        	System.out.println(interval.getJob().getRelease() + "," + interval.getJob().getDeadline() );
            System.out.println("Job " + interval.getJob().getTaskId() + ":" + interval.getJob().getJobNum() + " [" + interval.getStart() + "," + interval.getEnd() + "]" );
        }
    }

    private static ArrayList<Task> buildTaskSet1() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1, 1, 2)); // tau_1: c1=1, p1=2
        tasks.add(new Task(2, 3, 6)); // tau_2: c2=3, p2=6
        return tasks;
    }

    private static ArrayList<Task> buildTaskSet2() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1, 1, 4)); // tau_1: c1=1, p1=4
        tasks.add(new Task(2, 1, 2)); // tau_2: c2=1, p2=2
        tasks.add(new Task(3, 2, 6)); // tau_3: c3=2, p3=6
        return tasks;
    }

    private static ArrayList<Task> buildTaskSet3() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1, 1, 6));  // tau_1: c1=1, p1=6
        tasks.add(new Task(2, 2, 12)); // tau_2: c2=2, p2=12
        tasks.add(new Task(3, 1, 4));  // tau_3: c3=1, p3=4
        return tasks;
    }
}
