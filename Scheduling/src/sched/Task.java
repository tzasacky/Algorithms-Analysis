package sched;

public class Task {

    private final int taskId;

    private final int period;

    private final int computationTime;

    public Task(int taskId, int computationTime, int period) {
        this.taskId = taskId;
        this.period = period;
        this.computationTime = computationTime;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public int getPeriod() {
        return this.period;
    }

    public int getComputationTime() {
        return this.computationTime;
    }
}
