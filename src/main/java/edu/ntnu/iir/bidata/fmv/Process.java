package edu.ntnu.iir.bidata.fmv;

/**
 * Represents one process to be processed by the cpu.
 */
public class Process {
  private int id; //The id of the process.
  private int arrivalTime; //The time the process arrives to the schedule.
  private int burstTime; //The amount of time the processes needs to complete.
  private int workRemaining;//The amount of time left for the process to complete.
  private int completionTime;//The time at which the process completed.
  private boolean finished = false; //Whether the process is finished or not.

  /**
   * Creates an instance of the process.
   *
   * @param id The id of the process.
   * @param arrivalTime The time the process arrives to the schedule.
   * @param burstTime The amount of time the processes needs to complete.
   */
  public Process(int id, int arrivalTime, int burstTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.workRemaining = burstTime;
  }

  /**
   * Returns the id of the process.
   *
   * @return The id of the process.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns the arrival time of the process.
   *
   * @return The arrival time of the process.
   */
  public int getArrivalTime() {
    return this.arrivalTime;
  }

  /**
   * Returns the burst time of the process.
   *
   * @return The burst time of the process.
   */
  public int getBurstTime() {
    return this.burstTime;
  }

  /**
   * Returns the time the process completed.
   * @return The time the process completed.
   */
  public int getCompletionTime() {
    return this.completionTime;
  }

  /**
   * Returns whether the process is completed.
   *
   * @return Whether the process is completed.
   */
  public boolean isFinished() {
    return finished;
  }

  /**
   * Returns the amount of time left for the process to complete.
   *
   * @return The amount of time left for the process to complete.
   */
  public int getWorkRemaining() {
    return workRemaining;
  }

  /**
   * Calculates and returns the turnaround time of the process.
   *
   * @return The turnaround time of the process.
   */
  public int getTurnaroundTime() {
    return completionTime - arrivalTime;
  }

  /**
   * Calculates and returns the waiting time of the process.
   *
   * @return The waiting time of the process.
   */
  public int getWaitingTime() {
    return getTurnaroundTime() - burstTime;
  }

  /**
   * Does one unit of times worth of work on the process.
   *
   * @param time The time at which the work done on the process was done.
   * @return Whether the process is finished or not.
   */
  public boolean doWork(int time) {
    workRemaining--;
    if (workRemaining <= 0) {
      finished = true;
      completionTime = time + 1;
    }
    return finished;
  }
}
