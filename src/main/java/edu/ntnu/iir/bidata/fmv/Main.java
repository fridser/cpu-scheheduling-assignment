package edu.ntnu.iir.bidata.fmv;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      runAllAlgorithmsOnDataset("Case 1", createSample1());
      runAllAlgorithmsOnDataset("Case 2", createSample2());
      runAllAlgorithmsOnDataset("Case 3", createSample3());
    }

  private static void runAllAlgorithmsOnDataset(String caseName, List<Process> dataset) {
    System.out.println("====== " + caseName + " ======");

    // FCFS
    CPU cpuFCFS = new CPU(new FCFSScheduler());
    cpuFCFS.addAll(copyDataset(dataset));
    cpuFCFS.run();
    cpuFCFS.printDetails();

    // SJF
    CPU cpuSJF = new CPU(new SJFScheduler());
    cpuSJF.addAll(copyDataset(dataset));
    cpuSJF.run();
    cpuSJF.printDetails();

    // SRTF
    CPU cpuSRTF = new CPU(new SRTFScheduler());
    cpuSRTF.addAll(copyDataset(dataset));
    cpuSRTF.run();
    cpuSRTF.printDetails();
  }

  // Helper method to create a deep copy of the dataset for each algorithm
  private static List<Process> copyDataset(List<Process> original) {
    List<Process> copy = new ArrayList<>();
    for (Process p : original) {
      copy.add(new Process(p.getId(), p.getArrivalTime(), p.getBurstTime()));
    }
    return copy;
  }

   static private List<Process> createSample1() {
        ArrayList<Process> list = new ArrayList<>();
        list.add(new Process(1,0,8));
        list.add(new Process(2,0,4));
        list.add(new Process(3,0,2));
        list.add(new Process(4,0,6));
        list.add(new Process(5,0,3));
        return list;
    }

    static private List<Process> createSample2() {
        ArrayList<Process> list = new ArrayList<>();
        list.add(new Process(1, 0, 20));
        list.add(new Process(2,1,2));
        list.add(new Process(3,2,2));
        list.add(new Process(4,3,1));
        list.add(new Process(5,4,3));
        return list;
    }
    static private List<Process> createSample3() {
        ArrayList<Process> list = new ArrayList<>();
        list.add(new Process(1,0,20));
        list.add(new Process(2,1,2));
        list.add(new Process(3,2,2));
        list.add(new Process(4,3,2));
        list.add(new Process(5,4,2));
        list.add(new Process(6,5,2));
        return list;
    }

  static private List<Process> createSampleTestSRTF() {
    ArrayList<Process> list = new ArrayList<>();
    list.add(new Process(1, 0, 8));
    list.add(new Process(2,1,4));
    list.add(new Process(3,2,9));
    list.add(new Process(4,3,5));
    return list;
  }

  static private List<Process> createSampleTestFCFS() {
      ArrayList<Process> list = new ArrayList<>();
      list.add(new Process(1, 4, 5));
      list.add(new Process(2,6,4));
      list.add(new Process(3,0,3));
      list.add(new Process(4,6,2));
      list.add(new Process(5,5,4));
      return list;
  }
}

