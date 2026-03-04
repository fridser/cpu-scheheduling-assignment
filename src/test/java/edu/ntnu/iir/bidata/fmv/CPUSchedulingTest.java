package edu.ntnu.iir.bidata.fmv;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CPUSchedulingTest {

  private List<Process> createCase1() {
    return Arrays.asList(
            new Process(1, 0, 8),
            new Process(2, 0, 4),
            new Process(3, 0, 2),
            new Process(4, 0, 6),
            new Process(5, 0, 3)
    );
  }

  @Test
  public void testFCFSScheduler_Case1() {
    CPU cpu = new CPU(new FCFSScheduler());
    cpu.addAll(createCase1());
    cpu.run();

    cpu.calculateDetails();

    // Completion times for FCFS with all processes arriving at time 0 and burst times 8, 4, 2, 6, 3:
    // Order of execution: P1(8), P2(4), P3(2), P4(6), P5(3)
    assertEquals(8, getProcessById(cpu, 1).getCompletionTime());
    assertEquals(12, getProcessById(cpu, 2).getCompletionTime());
    assertEquals(14, getProcessById(cpu, 3).getCompletionTime());
    assertEquals(20, getProcessById(cpu, 4).getCompletionTime());
    assertEquals(23, getProcessById(cpu, 5).getCompletionTime());
  }

  @Test
  public void testSJFScheduler_Case1() {
    CPU cpu = new CPU(new SJFScheduler());
    cpu.addAll(createCase1());
    cpu.run();

    // In SJF with all processes arriving at time 0, the order of execution will be based on burst time:
    // Order of execution: P3(2), P5(3), P2(4), P4(6), P1(8)
    assertEquals(2, getProcessById(cpu, 3).getCompletionTime());
    assertEquals(5, getProcessById(cpu, 5).getCompletionTime());
    assertEquals(9, getProcessById(cpu, 2).getCompletionTime());
    assertEquals(15, getProcessById(cpu, 4).getCompletionTime());
    assertEquals(23, getProcessById(cpu, 1).getCompletionTime());
  }

  @Test
  public void testSRTFScheduler_Case1() {
    CPU cpu = new CPU(new SRTFScheduler());
    cpu.addAll(createCase1());
    cpu.run();

    // For Case 1 (all arrive at 0), SRTF behaves the same as SJF since there are no new arrivals to preempt the current process.
    assertEquals(2, getProcessById(cpu, 3).getCompletionTime());
    assertEquals(5, getProcessById(cpu, 5).getCompletionTime());
    assertEquals(9, getProcessById(cpu, 2).getCompletionTime());
    assertEquals(15, getProcessById(cpu, 4).getCompletionTime());
    assertEquals(23, getProcessById(cpu, 1).getCompletionTime());
  }

  // Helper method to access private processes list in CPU for assertions
  private Process getProcessById(CPU cpu, int id) {
    try {
      java.lang.reflect.Field field = CPU.class.getDeclaredField("processes");
      field.setAccessible(true);
      List<Process> list = (List<Process>) field.get(cpu);
      return list.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
