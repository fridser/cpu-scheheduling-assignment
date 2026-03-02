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
    if (processes.stream().anyMatch(process -> (process.getArrivalTime() == time))) {
      for (Process process :
          processes.stream().filter(process ->
              (process.getArrivalTime() == time)).toList()) {
        scheduler.addProcess(process);

      }
    }
    scheduler.process();
    time++;
  }

  public void run(int iterations) {
    for (int i = 0; i < iterations; i++) {
      tick();
    }
    printDetails();
  }

  public void printDetails() {

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
    avgTurnaroundTime = totTurnaroundTime / processes.size();
    avgWaitingTime = totWaitingTime / processes.size();
  }

  public void addAll(List<Process> list) {
    for (Process process: list) {
      addProcess(process);
    }
  }
}
