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
   * Does one time unit of work on one process decided by the scheduling algorithm.
   *
   * @param time The CPU time at which the work is being done.
   */
  public void process(int time);

  /**
   * Returns whether the scheduler is empty or not.
   *
   * @return Whether the scheduler is empty or not.
   */
  public boolean isEmpty();


}
