package edu.ntnu.iir.bidata.fmv;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SJFScheduler implements Scheduler {
  // Sort processes by burst time, then by arrival time
  private PriorityQueue<Process> queue = new PriorityQueue<>(
          Comparator.comparing(Process::getBurstTime).thenComparing(Process::getArrivalTime)
  );
  private Process currentProcess = null;


  @Override
  public void addProcess(Process process) {
    this.queue.add(process);
  }

  @Override
  public void process(int time) {
    // If the current process is finished or there is no current process, pick the next one from the queue
    if (currentProcess == null || currentProcess.isFinished()) {
      currentProcess = queue.poll();
    }
    if (currentProcess != null) {
      currentProcess.doWork(time);
    }
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty() && currentProcess == null;
  }
}
