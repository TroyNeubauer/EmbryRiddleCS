
class Main {
    public static void main(String[] args) {
        long p = 13;
        long q = 19;
        long e = 17;

        long phi = (p - 1) * (q - 1);
        System.out.println("P: " + p + ", Q: " + q + ", E: " + e + ", PHI: " + phi);
        long d = Main.calculate_d(e, phi);
        System.out.println("D: " + d);
    } 

    public static long calculate_d(long e, long phi) {
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            long inner = (1 + i * phi);
            if (inner % e == 0) {
                return inner / e;
            }
        }
        throw new RuntimeException("Failed to find d!");
    }
}
