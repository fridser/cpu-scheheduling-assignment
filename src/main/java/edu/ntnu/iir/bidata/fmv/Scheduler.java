package edu.ntnu.iir.bidata.fmv;

/**
 * Represents a scheduler that decides when work on each process should be done
 * in the cpu.
 */
public interface Scheduler {

  /**
   * Adds a process to the scheduler.
   *
   * @param process The process added to the scheduler.
   */
  public void addProcess(Process process);

  /**
   * Processes one unit of time on the process first in the scheduler.
   */
  public void process();

  /**
   * Returns whether the scheduler is empty or not.
   *
   * @return Whether the scheduler is empty or not.
   */
  public boolean isEmpty();


}
