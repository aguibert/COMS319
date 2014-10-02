/**
 * 
 */
package iastate.cs319.portfolio1;

import java.util.Date;

/**
 * @author aguibert
 * 
 */
class Job extends Thread {

    public enum JOB_STATE {
        WAITING, // Red
        RUNNING, // Yellow
        COMPLETED // Green
    }

    public final String jobName;
    public final int jobDurSeconds;
    private JOB_STATE state = JOB_STATE.WAITING;

    public Job(String jobName, int jobDuration) {
        this.jobName = jobName;
        this.jobDurSeconds = jobDuration;
    }

    @Override
    public void start() {
        if (getJobState() == JOB_STATE.WAITING)
            super.start();
        else
            System.out.println("This job has already been started.");
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        setState(JOB_STATE.RUNNING);
        System.out.println("Job " + jobName + " started at " + new Date(startTime));
        try {
            // Simulate some job that takes a long time by sleeping the thread
            Thread.sleep(jobDurSeconds * 1000);
        } catch (InterruptedException e) {
        }
        setState(JOB_STATE.COMPLETED);
        System.out.println("Job " + jobName + " completed in " +
                           (System.currentTimeMillis() - startTime) + "ms.");
    }

    public JOB_STATE getJobState() {
        return state;
    }

    public void setState(JOB_STATE newState) {
        if (newState == state)
            return; // If no state change occurred, no-op
        state = newState;
        DataModel.updateList(); // If state change occurred, notify the UI
    }

    @Override
    public String toString() {
        return "Job " + jobName + " duration=" + jobDurSeconds + "s";
    }
}
