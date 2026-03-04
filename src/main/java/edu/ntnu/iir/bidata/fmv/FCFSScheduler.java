package edu.ntnu.iir.bidata.fmv;

import java.util.LinkedList;
import java.util.Queue;

public class FCFSScheduler implements Scheduler {
  private Queue<Process> queue = new LinkedList<>();
  private Process currentProcess = null;

  public FCFSScheduler(){
    queue = new LinkedList<>();
  }

  @Override
  public void addProcess(Process process) {
    queue.add(process);
  }

  @Override
  public void process(int time) {
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
