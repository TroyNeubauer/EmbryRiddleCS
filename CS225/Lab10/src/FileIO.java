import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {

    private File labFile, outFile;

    public FileIO() {
        // constructor
        labFile = new File("Lab_Answers.csv");
        outFile = new File("Stats_Results.txt");
    }

    public void parseFile() {
        // this will be to for the requirements
        try {
            FileReader fr = new FileReader(labFile);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(outFile);
            BufferedWriter bw = new BufferedWriter(fw);

            String[] correctColors = new String[] { "Red", "Blue", "Yellow" };
            // make a String called line
            String line;

            // read the header line in the file
            line = br.readLine();
            // print out header line
            System.out.println(line);

            // while line is equal to the next line of the bufferedreader is not equal to
            // null
            // this means read the next line in the file until there are not more line to
            // read
            int validNumbers = 0;
            int invalidNumbers = 0;

            int vampires = 0;
            int werewolves = 0;

            int blackEntries = 0;

            int validColors = 0;
            while ((line = br.readLine()) != null) {

                // make an array to hold the columns
                String[] lineColumns;
                // break the line up to columns. break on the comma and delete the comma
                lineColumns = line.split(",");
                try {
                    String section = lineColumns[0];
                    String color = lineColumns[1];
                    String vampire = lineColumns[2];
                    String number = lineColumns[3];
                    String lab = lineColumns[4];

                    try {
                        double d = Double.parseDouble(number);
                        validNumbers++;
                    } catch (NumberFormatException e) {
                        invalidNumbers++;
                    }

                    if (vampire.equalsIgnoreCase("vampire")) {
                        vampires++;
                    } else if (vampire.equalsIgnoreCase("werewolf")) {
                        werewolves++;
                    }

                    if (vampire.length() == 0) {
                        blackEntries++;
                    }
                    if (number.length() == 0) {
                        blackEntries++;
                    }
                    if (lab.length() == 0) {
                        blackEntries++;
                    }
                    if (section.length() == 0) {
                        blackEntries++;
                    }
                    if (color.length() == 0) {
                        blackEntries++;
                    }

                    for (String officialColor : correctColors) {
                        if (color.equals(officialColor))
                            validColors++;
                    }

                } catch (Exception a) {
                    System.out.println("Ignoring bad value");
                }

                // print every line

            }

            bw.write("Valid numbers: ");
            bw.write(String.valueOf(validNumbers));
            bw.newLine();

            bw.write("Invalid numbers: ");
            bw.write(String.valueOf(invalidNumbers));
            bw.newLine();

            if (vampires == werewolves) {
                bw.write("Vampires and werewolves tie");

            } else if (vampires >= werewolves) {
                bw.write("Vampires win");
            } else {
                bw.write("Werewolves win");
            }

            bw.write(" by: ");
            bw.write(String.valueOf(Math.abs(vampires - werewolves)));
            bw.write(" votes");
            bw.newLine();

            bw.write("Blank lines: ");
            bw.write(String.valueOf(blackEntries));
            bw.newLine();

            bw.write("Red, Blue, and Yellow color total: ");
            bw.write(String.valueOf(validColors));
            bw.newLine();

            // when completely done with reading close the Reader
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        FileIO lab10 = new FileIO();
        lab10.parseFile();

    }

}

