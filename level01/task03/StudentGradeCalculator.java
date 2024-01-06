package task03;

import java.util.Scanner;

public class StudentGradeCalculator {
    static Scanner scanner = new Scanner(System.in);
    public int totalSubjects;
    public StudentGradeCalculator(int totalSubjects){
        this.totalSubjects = totalSubjects;
    }

    public float averageGrade(float[] markList){
        float total = 0;

        for(int i = 0; i < this.totalSubjects; i++){
            total += markList[i];
        }
        return total / this.totalSubjects;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Kartik's Grade Calculator!");

        System.out.println("\nEnter the total number of subjects: ");
        int totalSubjects = scanner.nextInt();
        StudentGradeCalculator calculate = new StudentGradeCalculator(totalSubjects);

        float[] markList = new float[totalSubjects];
        for(int i = 0; i < totalSubjects; i++){
            System.out.println("Enter the marks scored in subject " + (i+1) + ": ");
            markList[i] = scanner.nextFloat();
        }
        float averageMarks = calculate.averageGrade(markList);
        System.out.println("\nThe average marks obtained is: " + Math.round(averageMarks * 100.0) / 100.0);
        System.out.println("\nThank you.");
        scanner.close();
    }
}
