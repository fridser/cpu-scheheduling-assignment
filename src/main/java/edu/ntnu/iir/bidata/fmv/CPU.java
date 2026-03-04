package edu.ntnu.iir.bidata.fmv;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the scheduler classes and assigns processes to process.
 * Tests different kinds of schedulers with the given processes.
 * Prints the details of the processed processes
 *
 */
public class CPU {
  private Scheduler scheduler;
  private int time = 0;
  private ArrayList<Process> processes;
  private int timeFinished = 0;
  private double avgTurnaroundTime;
  private double avgWaitingTime;

  public CPU(Scheduler scheduler) {
    this.scheduler = scheduler;
    this.processes = new ArrayList<>();
  }

  public void addProcess(Process process) {
    this.processes.add(process);
  }


  public void tick() {
    // Add processes that arrive at the current time to the scheduler
    for (Process process : processes) {
      if (process.getArrivalTime() == time) {
        scheduler.addProcess(process);
      }
    }
    scheduler.process(time);
    time++;
  }

  // Run the CPU until all processes are finished
  public void run() {
    while (processes.stream().anyMatch(p -> !p.isFinished())) {
      tick();
    }
    calculateDetails();
  }

  public void printDetails() {
    System.out.printf("%6s %6s %6s %6s %6s %6s\n", "Pid", "AT", "BT", "TT", "WT", "CT");
    for (Process process: processes) {
      System.out.printf("%6s %6s %6s %6s %6s %6s\n",
              process.getId(),
              process.getArrivalTime(),
              process.getBurstTime(),
              process.getTurnaroundTime(),
              process.getWaitingTime(),
              process.getCompletionTime()
          );
    }
    calculateDetails();
    System.out.println("Avg TT: " + avgTurnaroundTime +
        "| Avg WT: " + avgWaitingTime +
        "| CT: " + timeFinished + "|");
  }

  public void calculateDetails() {
    int totTurnaroundTime = 0;
    int totWaitingTime = 0;

    for (Process process: processes) {
      if (process.getCompletionTime() > timeFinished) {
        timeFinished = process.getCompletionTime();
      }
      totTurnaroundTime += process.getTurnaroundTime();
      totWaitingTime += process.getWaitingTime();
    }
    avgTurnaroundTime = (double) totTurnaroundTime / (double)processes.size();
    avgWaitingTime =  ((double) totWaitingTime / (double) processes.size());
  }

  public void addAll(List<Process> list) { this.processes.addAll(list); }
}
