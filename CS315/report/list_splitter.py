import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import math

benches = {}
sizes = []

first = True
for line in open("app/build/results/jmh/results.txt").read().split("\n"):
    if first:
        first = False
        continue
    arr = line.split()
    if len(arr) == 0:
        break
    bench = arr[0]
    sizePow = arr[1]
    time = arr[4]
    unit = arr[5]
    print(bench, sizePow, time, unit)
    size = 4**int(sizePow)

    sizes.append(size)

    bench = bench.replace("Bench.", "")
    print(bench)
    sortingAlg, bench = bench.split("Sort", 1)
    if bench not in benches:
        benches[bench] = {}

    x = benches[bench]
    if sortingAlg not in x:
        x[sortingAlg] = []
    x[sortingAlg].append(float(time))


sizes = list(dict.fromkeys(sizes))
x = list(range(0, len(sizes)))
offset_delta = 1.0 / (len(benches) - 2)

width = 40

for bench, d in benches.items():
    print()
    print(bench)
    data = []
    bigSizes = []
    algorithms = []
    bigTimes = []
    for i, (sortingAlg, times) in enumerate(d.items()):
        algToAdd = [sortingAlg] * len(times)
        print(algToAdd)
        print(times)
        print(sizes)
        assert(len(times) == len(sizes))

        bigSizes.extend(sizes)
        algorithms.extend(algToAdd)
        bigTimes.extend(times)

    print()
    print(bigSizes)
    print(algorithms)
    print(bigTimes)

    df = pd.DataFrame({"size": bigSizes, "algorithm": algorithms, "time": bigTimes})
    print(df)

    df.set_index('algorithm', inplace=True)

    sizeGroup = df.groupby('size')
    xPlots = 2
    yPlots = math.ceil(float(len(sizeGroup)) / float(xPlots))
    print(yPlots)
    sizeGroup = list(sizeGroup)

    fig, axs = plt.subplots(xPlots, yPlots)
    i = 0
    for x in range(0, xPlots):
        for y in range(0, yPlots):
            if i == len(sizeGroup):
                break
            size, inner = sizeGroup[i]
            print()
            print(size)
            print(inner)

            plot = axs[x, y];
            #plot.plot(inner["algorithm"], inner["time"])
            plot.set_title('Time to sort ' + str(size) + "element array")
            i += 1

    #pd.pivot_table(df,index = 'size',
    #           columns = 'algorithm',aggfunc ='count').plot.bar() 

    plt.show()

