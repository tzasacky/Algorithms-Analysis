package sched;

public class ScheduleInterval {

    private final Job job;

    private final int start;
    private final int end;

    public ScheduleInterval(Job job, int start, int end) {
        this.job = job;
        this.start = start;
        this.end = end;
    }

    public Job getJob() {
        return this.job;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
}
