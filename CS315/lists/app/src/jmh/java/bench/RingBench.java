package bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import lists.Stack;
import lists.Queue;

@Threads(4)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 2)
public class RingBench {

    @Param({ "1", "10", "100", "1000", "10000", "100000", "1000000", "10000000", "100000000" })
    public int pushCounts;

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void init(Blackhole bh) {
        Stack<Integer> stack = new Stack<Integer>(pushCounts);
        bh.consume(stack);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void pushPreallocate(Blackhole bh) {
        Stack<Integer> stack = new Stack<Integer>(pushCounts);
        for (int i = 0; i < pushCounts; i++) {
            stack.push(bh.i1);
        }
        bh.consume(stack);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void pushAndPop(Blackhole bh) {
        Stack<Integer> stack = new Stack<Integer>(pushCounts);
        for (int i = 0; i < pushCounts; i++) {
            stack.push(bh.i1);
        }
        for (int i = 0; i < pushCounts; i++) {
            bh.consume(stack.pop());
        }
        bh.consume(stack);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void push(Blackhole bh) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < pushCounts; i++) {
            stack.push(bh.i1);
        }
        bh.consume(stack);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void queueReal(Blackhole bh) {
        Queue<Integer> queue = new Queue<Integer>();
        int iterations = (int) Math.log10(pushCounts);
        for (int i = 0; i < iterations; i++) {
            int adds = pushCounts * (i + 2) / iterations;
            for (int j = 0; j < adds; j++) {
                queue.enqueue(bh.i1);
            }
            int removes = adds * 2 / 3;
            for (int j = 0; j < removes; j++) {
                bh.consume(queue.dequeue());
            }
        }
        bh.consume(queue);
    }
}
