package bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import lists.ExhibitionCheeseList;

@Threads(4)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 2)
public class CheeseBench {

    @Param({ "1", "10", "100", "1000", "10000", "100000", "1000000", "10000000", "100000000" })
    public int pushCounts;

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void init(Blackhole bh) {
        ExhibitionCheeseList cheese = new ExhibitionCheeseList(pushCounts);
        bh.consume(cheese);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void pushPreallocate(Blackhole bh) {
        ExhibitionCheeseList stack = new ExhibitionCheeseList(pushCounts);
        for (int i = 0; i < pushCounts; i++) {
            stack.addToTail(bh.i1);
        }
        bh.consume(stack);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void pushAndPop(Blackhole bh) {
        ExhibitionCheeseList stack = new ExhibitionCheeseList(pushCounts);
        for (int i = 0; i < pushCounts; i++) {
            stack.addToTail(bh.i1);
        }
        for (int i = 0; i < pushCounts; i++) {
            bh.consume(stack.deleteBack());
        }
        bh.consume(stack);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void push(Blackhole bh) {
        ExhibitionCheeseList stack = new ExhibitionCheeseList();
        for (int i = 0; i < pushCounts; i++) {
            stack.addToTail(bh.i1);
        }
        bh.consume(stack);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void AqueueReal(Blackhole bh) {
        ExhibitionCheeseList cheese = new ExhibitionCheeseList();
        int iterations = (int) Math.log10(pushCounts);
        for (int i = 0; i < iterations; i++) {
            int adds = pushCounts * (i + 2) / iterations;
            for (int j = 0; j < adds; j++) {
                cheese.addToTail(bh.i1);
            }
            int removes = adds * 2 / 3;
            for (int j = 0; j < removes; j++) {
                bh.consume(cheese.deleteFront());
            }
        }
        bh.consume(cheese);
    }
}
