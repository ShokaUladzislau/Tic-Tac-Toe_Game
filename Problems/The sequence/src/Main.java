import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int array [] = new int[scanner.nextInt()];
        int max = 0;

        for (int i =0; i < array.length; i ++) {
            array[i] = scanner.nextInt();
        }

        for (int i = 0; i < array.length; i++) {
            max = max < array[i] && array[i] % 4 == 0 ? array[i] : max;
        }

        System.out.println(max);
    }
}