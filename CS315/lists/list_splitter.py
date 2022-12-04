import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import math

#RingBench.init                    16  avgt    2      26.552          ns/op
#RingBench.init                    64  avgt    2      67.184          ns/op

benches = {}
sizes = []

for line in open("app/build/results/jmh/results.txt").read().split("\n"):
    arr = line.split()
    if len(arr) == 0:
        break
    bench = arr[0]
    size = arr[1]
    time = arr[4]
    unit = arr[5]

    sizes.append(size)

    #print(bench + ", " + size + ", " + time + ", " + unit)
    clas, bench = bench.split(".")
    if bench not in benches:
        benches[bench] = {}

    x = benches[bench]
    if clas not in x:
        x[clas] = []
    x[clas].append(float(time))


sizes = list(dict.fromkeys(sizes))
x = list(range(0, len(sizes)))
offset_delta = 1.0 / (len(benches) - 2)

width = 40

for bench, d in benches.items():
    print()
    print(bench)
    data = []
    for i, (clas, times) in enumerate(d.items()):
        offset = -0.5 + offset_delta * i
        inner_data = [clas]
        for time in times:
            inner_data.append(time)
        data.append(inner_data)

    colums = ['sizes']
    colums.extend(sizes)
    df = pd.DataFrame(data, columns=colums)
    # view data
    print(df)
      
    # plot grouped bar chart
    df.plot(x='sizes',
            kind='bar',
            stacked=False,
            title='Data structure `' + bench + '` operation vs elements') 
    plt.show()

