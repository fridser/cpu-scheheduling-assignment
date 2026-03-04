package edu.ntnu.iir.bidata.fmv;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU(new FCFSScheduler());
        cpu.addAll(createSampleTestFCF  S());
        cpu.run(100);
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

