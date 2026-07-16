import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int[] rollNumbers = new int[100];
        float[] midtermMarks = new float[100];
        float[] finalMarks = new float[100];
        int[] classes = new int[100];
        char[] grades = new char[100];
        int count = 50;

        for (int i = 0; i < count; i++) {
            int r;
            boolean unique;
            do {
                r = rand.nextInt(1000) + 1;
                unique = true;
                for (int j = 0; j < i; j++) {
                    if (rollNumbers[j] == r) {
                        unique = false;
                        break;
                    }
                }
            } while (!unique);

            rollNumbers[i] = r;
            midtermMarks[i] = rand.nextFloat() * 50;
            finalMarks[i] = rand.nextFloat() * 100;
            classes[i] = rand.nextInt(10) + 1;

            if (finalMarks[i] < 50) grades[i] = 'F';
            else if (finalMarks[i] <= 59) grades[i] = 'D';
            else if (finalMarks[i] <= 72) grades[i] = 'C';
            else if (finalMarks[i] <= 85) grades[i] = 'B';
            else grades[i] = 'A';
        }

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1-2. Sort by Roll No (Asc/Desc)");
            System.out.println("3-4. Sort by Midterm (Asc/Desc)");
            System.out.println("5-6. Sort by Final (Asc/Desc)");
            System.out.println("7-8. Sort by Grade (Asc/Desc)");
            System.out.println("9. Add Entry | 10. Delete Entry");
            System.out.println("11-12. Final Marks > X (Desc/Asc)");
            System.out.println("13-14. Final Marks <= X (Desc/Asc)");
            System.out.println("15-16. Grade > X (Desc/Asc)");
            System.out.println("17-18. Grade <= X (Desc/Asc)");
            System.out.print("Enter choice (1-18) or 'e' to exit: ");
            String input = sc.next();

            if (input.equalsIgnoreCase("e")) break;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid Input! Enter a number or 'e'.");
                continue;
            }
            if (choice >= 1 && choice <= 8) {
                for (int i = 0; i < count - 1; i++) {
                    for (int j = 0; j < count - i - 1; j++) {
                        boolean swap = false;
                        if (choice == 1 && rollNumbers[j] > rollNumbers[j + 1]) swap = true;
                        if (choice == 2 && rollNumbers[j] < rollNumbers[j + 1]) swap = true;
                        if (choice == 3 && midtermMarks[j] > midtermMarks[j + 1]) swap = true;
                        if (choice == 4 && midtermMarks[j] < midtermMarks[j + 1]) swap = true;
                        if (choice == 5 && finalMarks[j] > finalMarks[j + 1]) swap = true;
                        if (choice == 6 && finalMarks[j] < finalMarks[j + 1]) swap = true;
                        if (choice == 7 && grades[j] < grades[j + 1]) swap = true;
                        if (choice == 8 && grades[j] > grades[j + 1]) swap = true;

                        if (swap) {
                            int tr = rollNumbers[j]; rollNumbers[j] = rollNumbers[j + 1]; rollNumbers[j + 1] = tr;
                            float tm = midtermMarks[j]; midtermMarks[j] = midtermMarks[j + 1]; midtermMarks[j + 1] = tm;
                            float tf = finalMarks[j]; finalMarks[j] = finalMarks[j + 1]; finalMarks[j + 1] = tf;
                            int tc = classes[j]; classes[j] = classes[j + 1]; classes[j + 1] = tc;
                            char tg = grades[j]; grades[j] = grades[j + 1]; grades[j + 1] = tg;
                        }
                    }
                }
                for (int i = 0; i < count; i++) {
                    System.out.println("Roll No: " + rollNumbers[i] + " Midterm: " + midtermMarks[i] + " Final: " + finalMarks[i] + " Class: " + classes[i] + " Grade: " + grades[i]);
                }
            }
            else if (choice == 9) {
                if (count >= 100) { System.out.println("Array Full!"); }
                else {
                    System.out.print("Enter Unique Roll Number: ");
                    int r = sc.nextInt();
                    rollNumbers[count] = r;
                    System.out.print("Midterm: "); midtermMarks[count] = sc.nextFloat();
                    System.out.print("Final: "); finalMarks[count] = sc.nextFloat();
                    System.out.print("Class: "); classes[count] = sc.nextInt();
                    if (finalMarks[count] < 50) grades[count] = 'F';
                    else if (finalMarks[count] <= 85) grades[count] = 'B';
                    else grades[count] = 'A';
                    count++;
                }
            }
            else if (choice == 10) {
                System.out.print("Enter Roll to Delete: ");
                int del = sc.nextInt();
                for (int i = 0; i < count; i++) {
                    if (rollNumbers[i] == del) {
                        for (int j = i; j < count - 1; j++) {
                            rollNumbers[j] = rollNumbers[j + 1];
                            midtermMarks[j] = midtermMarks[j + 1];
                            finalMarks[j] = finalMarks[j + 1];
                            classes[j] = classes[j + 1];
                            grades[j] = grades[j + 1];
                        }
                        count--;
                        break;
                    }
                }
            }
            else if (choice >= 11 && choice <= 18) {
                System.out.print("Enter value X: ");
                float X = 0; char charX = ' ';
                if (choice >= 15) charX = sc.next().charAt(0); else X = sc.nextFloat();

                for (int i = 0; i < count - 1; i++) {
                    for (int j = 0; j < count - i - 1; j++) {
                        boolean swap = false;
                        if ((choice == 11 || choice == 13) && finalMarks[j] < finalMarks[j + 1]) swap = true;
                        if ((choice == 12 || choice == 14) && finalMarks[j] > finalMarks[j + 1]) swap = true;
                        if ((choice == 15 || choice == 17) && grades[j] > grades[j + 1]) swap = true;
                        if ((choice == 16 || choice == 18) && grades[j] < grades[j + 1]) swap = true;

                        if (swap) {
                            int tr = rollNumbers[j]; rollNumbers[j] = rollNumbers[j + 1]; rollNumbers[j + 1] = tr;
                            float tf = finalMarks[j]; finalMarks[j] = finalMarks[j + 1]; finalMarks[j + 1] = tf;
                            char tg = grades[j]; grades[j] = grades[j + 1]; grades[j + 1] = tg;
                        }
                    }
                }
                for (int i = 0; i < count; i++) {
                    boolean print = false;
                    if (choice == 11 || choice == 12) { if (finalMarks[i] > X) print = true; }
                    if (choice == 13 || choice == 14) { if (finalMarks[i] <= X) print = true; }
                    if (choice == 15 || choice == 16) { if (grades[i] < charX) print = true; }
                    if (choice == 17 || choice == 18) { if (grades[i] >= charX) print = true; }

                    if (print) System.out.println("Roll: " + rollNumbers[i] + " Final: " + finalMarks[i] + " Grade: " + grades[i]);
                }
            }
        }
    }
}








