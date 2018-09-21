import java.util.Scanner;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public static void main(String[] args) {
        Range range = new Range(-7, 0);

        System.out.println("Начало диапазона: " + range.getFrom());
        System.out.println("Конец диапазона: " + range.getTo());
        System.out.println("Длинна диапазона: " + range.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        double number = scanner.nextDouble();
        if (range.isInside(number)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
        System.out.println();

        System.out.print("Изменим границы диапазона. Введите начало диапазона: ");
        range.setFrom(scanner.nextDouble());
        System.out.print("Введите конец диапазона: ");
        range.setTo(scanner.nextDouble());

        System.out.println("Используем функции для нового диапозона: ");

        System.out.println("Начало диапазона: " + range.getFrom());
        System.out.println("Конец диапазона: " + range.getTo());
        System.out.println("Длинна диапазона: " + range.getLength());

        System.out.print("Введите число: ");
        number = scanner.nextDouble();
        if (range.isInside(number)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
    }
}
