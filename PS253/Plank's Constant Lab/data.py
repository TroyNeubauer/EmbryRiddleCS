import csv
import os
import matplotlib.pyplot as plt
import numpy
from scipy import stats
import math

if not os.path.exists("out"):
    os.mkdir("out")

all_data = {}

h_data = []

color_number = 1
for filename in os.listdir("."):
    if ".txt" in filename or ".csv" in filename:
        print("Processing " + filename + "...")
        wavelength = int(filename[:3])
        frequency = 2.99792458E+8 / (wavelength / 1000000000)
        print(f"Wanelength {wavelength}, freq {frequency}")

        with open(filename) as csv_file:
            csv_reader = csv.reader(csv_file, delimiter='\t')
            in_header = True
            voltage_col = -1
            current_col = -1
            all_points = []

            for row in csv_reader:
                if in_header:
                    for i, name in enumerate(row):
                        if name == "Voltage, Ch A (V) Run #2":
                            voltage_col = i
                        if name == "Current, Ch B (A) Run #2":
                            current_col = i
                    in_header = False

                else:
                    try:
                        voltage = float(row[voltage_col])
                        current = float(row[current_col])
                        all_points.append((voltage, current))

                    except:
                        pass
                        #print(f"Skipping error {row[current_col]}: {row[voltage_col]}")

            all_points.sort(key=lambda tup: tup[1])
            last_r = -1

            #Find all the good points that form a nice line by starting at the end
            #and walking backwards until the regression R^2 value says this is a bad line
            good_x = []
            good_y = []
            r_values = []
            while len(good_x) < len(all_points):
                #Start from the end
                next_good = all_points[len(all_points) - len(good_x) - 1]
                good_x.append(next_good[0])
                good_y.append(next_good[1])
                if len(good_x) < 2:
                    continue

                gradient, intercept, r_value, p_value, std_err = stats.linregress(good_x, good_y)

                r_values.append((len(good_x), r_value))

            best_r_index = -1
            #Find the best R value so we know which points to use. Go in reverse and skip the first 1% or 20 of the array of the array
            for i in range(len(r_values) - 1, min(int(len(r_values) / 100.0), 20), -1):
                if best_r_index == -1 or r_values[i][1] > r_values[best_r_index][1]:
                    best_r_index = i

            good_count = r_values[best_r_index][0]

            #Do final regression
            gradient, y_intercept, r_value, p_value, std_err = stats.linregress(good_x[:good_count], good_y[:good_count])
            print(f"y={gradient}x+{y_intercept}")
            x_intercept = -y_intercept / gradient
            print(f"x intercept={x_intercept}")

            photon_energy = x_intercept * 1.60217646E-19

            plt.scatter(*zip(*all_points))
            plt.xlabel("Voltage (mV)")
            plt.ylabel("Current (mA)")
            plt.title(str(wavelength) + "nm Current/Voltage")
            plt.savefig("out/" + filename + ".png")
            plt.clf()

            plt.scatter(*zip(*r_values))
            plt.xlabel("Included Point Count")
            plt.title(str(wavelength) + "nm Regression Success vs Point Count")
            plt.ylabel("R^2")
            plt.savefig("out/" + filename + "-regression.png")
            plt.clf()

            plt.scatter(good_x[:good_count], good_y[:good_count])
            plt.xlabel("Voltage (mV)")
            plt.title(str(wavelength) + "nm Current/Voltage (Good Points Only)")
            plt.ylabel("Current (mA)")
            plt.savefig("out/" + filename + "-good.png")
            plt.clf()

            data = {}
            data["color"] = color_number
            color_number += 1
            data["wavelength"] = wavelength
            data["frequency"] = frequency
            data["V_LED"] = x_intercept
            data["V_LED_uncertainty"] = std_err

            data["photon_energy"] = photon_energy
            data["photon_energy_uncertainty"] = std_err * 1.60217646E-19

            all_data[str(wavelength)] = data
            h_data.append((frequency, photon_energy))

h_x = []
h_y = []

for point in h_data:
    h_x.append(point[0])
    h_y.append(point[1])

gradient, y_intercept, r_value, p_value, std_err = stats.linregress(h_x, h_y)

print(f"H: {gradient} +- {std_err}")

plt.clf()
plt.scatter(h_x, h_y)
plt.xlabel("Frequency (Hz)")
plt.ylabel("Photon Energy (J)")
plt.savefig("out/h.png")


print("FINAL TABLE:\n")

for tmp in all_data.values():
    for col in tmp.keys():
        print("{:<19} ".format(col), end='')
    break

for tmp in all_data.values():
    print("")
    for col in tmp.values():
        print("{:<19} ".format(col), end='')
