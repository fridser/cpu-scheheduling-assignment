package edu.ntnu.iir.bidata.fmv;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SRTFScheduler implements Scheduler {
  private int timeQuantum;
  private int time = 0;
  private PriorityQueue<Process> queue;
  private Process currentProcess;
  private int counter = 0;

  public SRTFScheduler(int timeQuantum) {
    this.timeQuantum = timeQuantum;
    queue = new PriorityQueue<>(Comparator.comparing(Process::getWorkRemaining));
  }


  @Override
  public void addProcess(Process process) {
    queue.add(process);
  }

  @Override
  public void process() {
    if (counter%timeQuantum == 0) {
      if (currentProcess != null) {
        queue.add(currentProcess);
      }
      if (queue.peek() != null) {
        currentProcess = queue.peek();
        queue.remove(currentProcess);
      }
      counter = 0;
    }
    if (currentProcess != null) {
      currentProcess.doWork(time);
      counter++;
    }
    time++;
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }

}
