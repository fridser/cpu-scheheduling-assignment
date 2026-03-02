package edu.ntnu.iir.bidata.fmv;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SJFScheduler implements Scheduler {
  private int time = 0;
  private PriorityQueue<Process> queue;
  private Process currentProcess;

  public SJFScheduler() {
    queue = new PriorityQueue<>(Comparator.comparing(Process::getBurstTime));
  }


  @Override
  public void addProcess(Process process) {
    this.queue.add(process);
  }

  @Override
  public void process() {
    if (currentProcess == null) {
      if (queue.peek() != null) {
        currentProcess = queue.peek();
        queue.remove(currentProcess);
      }
    }
    if (currentProcess.doWork(time)) {
      currentProcess = null;
    }
    time++;
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
