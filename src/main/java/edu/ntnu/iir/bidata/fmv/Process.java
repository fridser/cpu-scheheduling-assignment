package edu.ntnu.iir.bidata.fmv;

public class Process {
  private int id;
  private int arrivalTime;
  private int burstTime;
  private int workRemaining;
  private int completionTime;
  private boolean finished = false;

  public Process(int id, int arrivalTime, int burstTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.workRemaining = burstTime;
  }

  public int getId() {
    return this.id;
  }

  public int getArrivalTime() {
    return this.arrivalTime;
  }

  public int getBurstTime() {
    return this.burstTime;
  }

  public int getCompletionTime() {
    return this.completionTime;
  }

  public boolean isFinished() {
    return finished;
  }

  public int getWorkRemaining() {
    return workRemaining;
  }

  public int getTurnaroundTime() {
    return completionTime - arrivalTime;
  }

  public int getWaitingTime() {
    return getTurnaroundTime() - burstTime;
  }

  public boolean doWork(int time) {
    workRemaining--;
    if (workRemaining <= 0) {
      finished = true;
      completionTime = time;
    }
    return finished;
  }
}
