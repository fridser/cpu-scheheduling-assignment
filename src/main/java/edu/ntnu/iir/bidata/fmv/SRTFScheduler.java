package edu.ntnu.iir.bidata.fmv;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Represents a scheduler using the Shortest Remaining Time First algorithm.
 */
public class SRTFScheduler implements Scheduler {
  private PriorityQueue<Process> queue = new PriorityQueue<>(
          Comparator.comparing(Process::getWorkRemaining).thenComparing(Process::getArrivalTime)
  );


  @Override
  public void addProcess(Process process) {
    queue.add(process);
  }

  /**
   * Does one time unit of work on one process decided by the SRTF
   * algorithm.
   *
   * @param time The CPU time at which the work is being done.
   */
  @Override
  public void process(int time) {
    // Always pick the process with the shortest remaining time
    Process currentProcess = queue.poll();
    if (currentProcess != null) {
      currentProcess.doWork(time);
// If the process is not finished, add it back to the queue
      if (!currentProcess.isFinished()) {
        queue.add(currentProcess);
      }
    }
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }

}
