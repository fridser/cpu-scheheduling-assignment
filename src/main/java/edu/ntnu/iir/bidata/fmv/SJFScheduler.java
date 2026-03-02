package edu.ntnu.iir.bidata.fmv;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SJFScheduler implements Scheduler {
  private int time = 0;
  private PriorityQueue<Process> queue;

  public SJFScheduler() {
    queue = new PriorityQueue<>(Comparator.comparing(Process::getBurstTime));
  }


  @Override
  public void addProcess(Process process) {
    this.queue.add(process);
  }

  @Override
  public void process() {
    if (queue.peek() != null) {
      Process process = queue.peek();
      if (process.doWork(time)) {
        queue.remove(process);
      }
    }
    time++;
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
