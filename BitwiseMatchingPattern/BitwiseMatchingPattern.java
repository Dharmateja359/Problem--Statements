public class BitwiseMatchingPattern {

    public static int nextLargerWithSameOnes(int n) {
        int c = n;
        int c0 = 0; // count trailing zeros
        int c1 = 0; // count ones to the right of rightmost non-trailing zero

        // count trailing zeros
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // count ones after trailing zeros
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // if no bigger number with same ones
        if (c0 + c1 == 31 || c0 + c1 == 0) return -1;

        int p = c0 + c1; // position of rightmost non-trailing zero

        // Flip rightmost non-trailing zero
        n |= (1 << p);

        // Clear all bits to right of p
        n &= ~((1 << p) - 1);

        // Insert (c1-1) ones on right
        n |= (1 << (c1 - 1)) - 1;

        return n;
    }

    public static void main(String[] args) {
        int[] tests = {6, 7, 8, 15, 21};
        for (int test : tests) {
            System.out.printf("Next larger with same ones for %d (%s) is %d (%s)%n",
                              test, Integer.toBinaryString(test),
                              nextLargerWithSameOnes(test),
                              Integer.toBinaryString(nextLargerWithSameOnes(test)));
        }
    }
}