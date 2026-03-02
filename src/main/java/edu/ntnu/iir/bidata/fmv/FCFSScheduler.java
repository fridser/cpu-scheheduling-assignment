package edu.ntnu.iir.bidata.fmv;

import java.util.LinkedList;
import java.util.Queue;

public class FCFSScheduler implements Scheduler {
  private Queue<Process> queue;
  private int time = 0;

  public FCFSScheduler(){
    queue = new LinkedList<>();
  }

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
