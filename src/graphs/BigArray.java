package graphs;

public class BigArray {
    public enum Days {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }


    private static int COUNTER = 0;

    public static void main(String[] args) {
        boolean[] values = new boolean[1000000000];
        System.out.println("worked");

        System.out.println(COUNTER++);
        System.out.println(getInt());
    }

    private static int getInt() {
        return 5;
    }
}
