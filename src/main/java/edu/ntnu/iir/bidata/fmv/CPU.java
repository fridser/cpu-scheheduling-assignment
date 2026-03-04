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
  private Scheduler scheduler; //The scheduler that decides the
  // order the processes are processed
  private int time = 0; //The current time of the cpu
  private ArrayList<Process> processes; // All the processes to be processed
  private int timeFinished = 0; //The time the last process is finished
  private double avgTurnaroundTime; //The average turnaround time of the processed processes
  private double avgWaitingTime; //The average waiting time of the processed processes

  /**
   * Creates an instance of the CPU.
   *
   * @param scheduler The scheduler used by the CPU.
   */
  public CPU(Scheduler scheduler) {
    this.scheduler = scheduler;
    this.processes = new ArrayList<>();
  }

  /**
   * Adds a process to the CPU.
   *
   * @param process The process added to the CPU.
   */
  public void addProcess(Process process) {
    this.processes.add(process);
  }

  /**
   * Adds a process to the scheduler if it's its arrival time,
   * tells the scheduler to process one process for one unit of time.
   */
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



  /**
   * Run the CPU until all processes are finished.
   */
  public void run() {
    while (processes.stream().anyMatch(p -> !p.isFinished())) {
      tick();
    }
    calculateDetails();
    printDetails();
  }

  /**
   * Prints the details of every process in the cpu,
   * as well as the average turnaround time and waiting time,
   * and the time the last process finished.
   */
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

  /**
   * Calculates the average turnaround and waiting time of
   * the processes.
   */
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

  /**
   * Adds all the processes in the given list to the CPU.
   *
   * @param list The list of processes to be added to the CPU.
   */
  public void addAll(List<Process> list) { this.processes.addAll(list); }
}
