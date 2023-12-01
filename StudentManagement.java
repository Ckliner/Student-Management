import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentManagement {

    static class Student implements Comparable<Student> {
        private String name;
        private String address;
        private double GPA;

        public Student(String name, String address, double GPA) {
            this.name = name;
            this.address = address;
            this.GPA = GPA;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Student other) {
            return this.name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Address: " + address + ", GPA: " + GPA;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Student> students = new LinkedList<>();

        while (true) {
            System.out.println("Enter student name (or type 'exit' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter address: ");
            String address = scanner.nextLine();

            double GPA;
            while (true) {
                System.out.println("Enter GPA: ");
                try {
                    GPA = Double.parseDouble(scanner.nextLine());
                    if (GPA < 0.0 || GPA > 4.0) {
                        throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid GPA.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

            }

            students.add(new Student(name, address, GPA));
        }

        Collections.sort(students);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Student data has been written to students.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
