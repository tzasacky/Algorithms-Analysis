package sched;

public class Job implements Comparable<Job> {

    private final Task task;

    private final int jobNum;

    private final int release;
    private final int deadline;

    private int remainingTime;

    public Job(Task task, int jobNum) {
        this.task = task;
        this.jobNum = jobNum;

        // Calculate the job's release and deadline
        this.release = (jobNum - 1) * task.getPeriod();
        this.deadline = this.release + task.getPeriod();

        // Initially, all computation time is still remaining
        this.remainingTime = task.getComputationTime();
    }

    public void execute(int time) {
        this.remainingTime = Math.max(0, this.remainingTime - time);
    }

    public int getTaskId() {
        return this.task.getTaskId();
    }

    public int getJobNum() {
        return this.jobNum;
    }

    public int getRelease() {
        return this.release;
    }

    public int getDeadline() {
        return this.deadline;
    }

    public int getRemainingTime() {
        return this.remainingTime;
    }

    public boolean isComplete() {
        return this.remainingTime == 0;
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(this.deadline, other.getDeadline());
    }
}
