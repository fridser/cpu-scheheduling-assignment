package edu.ntnu.iir.bidata.fmv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CPUTest {

  static private List<Process> createSampleTestSJF() {
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

  @Test
    public void testCpuWithFCFS() {
    CPU cpu = new CPU(new FCFSScheduler());
    List<Process> sample = createSampleTestFCFS();
    cpu.addAll(sample);
    cpu.run();
    assertEquals(5, sample.get(0).getTurnaroundTime());
    assertEquals(11, sample.get(1).getTurnaroundTime());
    assertEquals(3, sample.get(2).getTurnaroundTime());
    assertEquals(13, sample.get(3).getTurnaroundTime());
    assertEquals(8, sample.get(4).getTurnaroundTime());

    assertEquals(0, sample.get(0).getWaitingTime());
    assertEquals(7, sample.get(1).getWaitingTime());
    assertEquals(0, sample.get(2).getWaitingTime());
    assertEquals(11, sample.get(3).getWaitingTime());
    assertEquals(4, sample.get(4).getWaitingTime());
  }

  @Test
    public void testCpuWithSJF() {
    CPU cpu = new CPU(new SJFScheduler());
    List<Process> sample = createSampleTestSJF();
    cpu.addAll(sample);
    cpu.run();
    assertEquals( 8, sample.get(0).getTurnaroundTime());
    assertEquals(  11, sample.get(1).getTurnaroundTime());
    assertEquals(24, sample.get(2).getTurnaroundTime());
    assertEquals( 14, sample.get(3).getTurnaroundTime());

    assertEquals(0, sample.get(0).getWaitingTime());
    assertEquals(7, sample.get(1).getWaitingTime());
    assertEquals(15, sample.get(2).getWaitingTime());
    assertEquals(9, sample.get(3).getWaitingTime());
  }
}