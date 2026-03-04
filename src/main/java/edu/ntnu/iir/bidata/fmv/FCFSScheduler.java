package edu.ntnu.iir.bidata.fmv;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a scheduler using the First Come First Serve algorithm.
 */
public class FCFSScheduler implements Scheduler {
  private Queue<Process> queue;
  private int time = 0;

  /**
   * Creates an instance of the FCFSScheduler.
   */
  public FCFSScheduler(){
    queue = new LinkedList<>();
  }

  /**
   * Adds a process to the FCFSScheduler.
   *
   * @param process The process added to the scheduler.
   */
  @Override
  public void addProcess(Process process) {
    queue.add(process);
  }

  @Override
  public void process() {
    if (queue.peek() != null) {
      if (queue.peek().doWork(time)) {
        queue.remove();
      }
      time++;
    }
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }

}
