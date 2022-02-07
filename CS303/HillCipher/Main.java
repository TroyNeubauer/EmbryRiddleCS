import java.util.*;

class Main {


    private static final int KEY[][] = { { 17, 17, 5 }, { 21, 18, 21 }, { 2, 2, 19 } };
    private static final int INV_KEY[][] = { { 4, 9, 15 }, { 15, 17, 6 }, { 24, 0, 17 } };

    public static void main(String[] args) {
        // Run test cases
        Main.testCase();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your message to encrypt (lowercase letters only): ");
        String line = scanner.nextLine();
        ArrayList<Integer> plaintextList = new ArrayList(line.length());
        for (char c : line.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c > 'z' || c < 'a') {
                System.out.println("Only lowercase letters are allowed!");
                System.exit(1);
            }
            plaintextList.add((int) c - (int) 'a');
        }
        //Create plaintext array, round up length to nearest multiple of 3 for encryption
        int plaintext[] = new int[((plaintextList.size() - 1) / 3 + 1) * 3];
        for (int i = 0; i < plaintextList.size(); i++) {
            plaintext[i] = plaintextList.get(i);
        }
 
        System.out.print("Encrypting: ");
        Main.printMessage(plaintext, false);

        int ciphertext[] = new int[plaintext.length];
        Main.encrypt(plaintext, ciphertext);

        System.out.print("Encrypted message is: ");
        Main.printMessage(ciphertext, true);

        int decrypted[] = new int[ciphertext.length];
        Main.decrypt(ciphertext, decrypted);

        System.out.print("Decrypted message is: ");
        Main.printMessage(decrypted, false);
    }

    static void printMessage(int[] message, boolean upper) {
        for (int m : message) {
            if (upper)
                System.out.print((char) (m + 'A'));
            else
                System.out.print((char) (m + 'a'));
        }
        System.out.println();
    }

    static void encrypt(int[] plaintext, int[] ciphertext) {
        int[] tmpPlain = new int[3];
        int[] tmpCipher = new int[3];
        int index = 0;
        while (index < plaintext.length) {
            //Copy the current numbers into `tmpPlain` to encrypt
            for (int i = 0; i < 3; i++) {
                if (i + index < plaintext.length) {
                    //Fill in dummy data if the user didn't specify enough ciphertext
                    tmpPlain[i] = plaintext[i + index];
                } else {
                    tmpPlain[i] = i;
                }
            }
            multiply(KEY, tmpPlain, tmpCipher);
            //Copy the cipher text out of the temp buffer
            for (int i = 0; i < 3; i++) {
                ciphertext[i + index] = tmpCipher[i];
            }
            index += 3;
        }
    }

    static void decrypt(int[] ciphertext, int[] decrypted) {
        int[] tmpCipher = new int[3];
        int[] tmpDec = new int[3];
        int index = 0;
        while (index < ciphertext.length) {
            for (int i = 0; i < 3; i++) {
                tmpCipher[i] = ciphertext[i + index];
            }
            multiply(INV_KEY, tmpCipher, tmpDec);
            for (int i = 0; i < 3; i++) {
                decrypted[i + index] = tmpDec[i];
            }
            index += 3;
        }
    }
    
    // Performs 3x3 times 3x1 matrix multiplication between `a` and `b`, and stores the result in `dst`
    // `a` must be a 3x3 array
    // `b` must be an array with length 3
    // `dst` must be an array with length 3
    static void multiply(int[][] a, int[] b, int[] dst) {
        for (int row = 0; row < 3; row++) {
            dst[row] = 0;
            for (int col = 0; col < 3; col++) {
                dst[row] += a[row][col] * b[col];
            }
        }
        //Apply modulus
        for (int row = 0; row < 3; row++) {
            dst[row] %= 26;
        }
    }

    static void testCase() {
        //Fixed inputs
        int plaintext[] = { (int) 'i', (int) 'b', (int) 'm' };

        //Put in range 0..26
        for (int row = 0; row < 3; row++) {
            plaintext[row] -= (int) 'a';
        }

        int ciphertext[] = new int[3];

        Main.encrypt(plaintext, ciphertext);

        int decrypted[] = new int[3];
        Main.decrypt(ciphertext, decrypted);

        if (!Arrays.equals(decrypted, plaintext)) {
            System.out.println("Test case failed! Expected " + Arrays.toString(plaintext) + " got " + Arrays.toString(decrypted));
            System.exit(1);
        }
    }
}
