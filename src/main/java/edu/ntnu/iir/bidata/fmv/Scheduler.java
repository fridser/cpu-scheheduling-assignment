package edu.ntnu.iir.bidata.fmv;

public interface Scheduler {

  public void addProcess(Process process);

  public void process(int time);

  public boolean isEmpty();


}
