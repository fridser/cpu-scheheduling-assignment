package edu.ntnu.bidata;

public class Process {
  private int id;
  private int arrivalTime;
  private int burstTime;

  public Process(int id, int arrivalTime, int burstTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
  }

  public int getId() {
    return this.id;
  }

  public int getArrivalTime() {
    return this.arrivalTime;
  }

  public int getBurstTime() {
    return this.burstTime;
  }
}
