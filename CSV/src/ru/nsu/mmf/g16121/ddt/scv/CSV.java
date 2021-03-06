package ru.nsu.mmf.g16121.ddt.scv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV {

    public static String translateCell(String cell) {
        int length = cell.length();
        StringBuilder translateCell = new StringBuilder();
        for (int i = 0; i < length - 1; i++) {
            switch (cell.charAt(i)) {
                case '\n':
                    translateCell.append("<br/>");
                    break;
                case '<':
                    translateCell.append("&lt");
                    break;
                case '>':
                    translateCell.append("&gt");
                    break;
                case '&':
                    translateCell.append("&amp");
                    break;
                case '"': {
                    char nextChar = cell.charAt(i + 1);
                    if (nextChar == '"') {
                        translateCell.append('"');
                        i++;
                    } else {
                        throw new IllegalArgumentException("Illegal symbol \"");
                    }
                    break;
                }
                default:
                    translateCell.append(cell.charAt(i));
            }
        }
        char lastChar = cell.charAt(length - 1);
        if (lastChar != '"') {
            translateCell.append(lastChar);
        }
        return translateCell.toString();
    }

    public static ArrayList<String> splitIntoCells(Scanner scanner) {
        ArrayList<String> cells = new ArrayList<>();
        String line = scanner.nextLine();
        row:
        for (; ; ) {
            if (line.indexOf('"') != 0) {
                int commaIndex = line.indexOf(',');
                if (line.indexOf('"') < commaIndex) {
                    throw new IllegalArgumentException("Illegal symbol \" in cell without quotes");
                }
                if (commaIndex != -1) {
                    cells.add(line.substring(0, commaIndex));
                    line = line.substring(commaIndex + 1);
                } else {
                    cells.add(line);
                    break;
                }
            } else {
                line = line.substring(1);
                StringBuilder newCell = new StringBuilder();
                while (true) {
                    //если есть перенос строки в ячейке или ошибка
                    if (line.isEmpty() && scanner.hasNext()) {
                        newCell.append("\n");
                        line = scanner.nextLine();
                    } else if (line.isEmpty()) {
                        throw new IllegalArgumentException("Illegal symbol \": not close cell");
                    }
                    int quotIndex = line.indexOf('"');
                    //если нет знака ковычки (о завершении ячейки) - значит есть перенос строки или ошибка
                    if (quotIndex == -1) {
                        newCell.append(line);
                        line = "";
                        //если знак ковычки (о заверении ячейки) в строке есть
                    } else {
                        newCell.append(line, 0, quotIndex);
                        line = line.substring(quotIndex);
                        if (line.length() == 1) {
                            cells.add(newCell.toString());
                            break row;
                        } else if (line.charAt(1) == ',') {
                            line = line.substring(2);
                            cells.add(newCell.toString());
                            break;
                        } else if (line.charAt(1) == '"') {
                            newCell.append("\"\"");
                            line = line.substring(2);
                        } else {
                            throw new IllegalArgumentException("Illegal symbol \": not close and not double");
                        }
                    }
                }
            }
        }
        return cells;
    }

    public static void printTd(PrintWriter writer, ArrayList<String> cells) {
        for (String cell : cells) {
            writer.println("<td>");
            if (!cell.isEmpty()) {
                writer.println(translateCell(cell));
            }
            writer.println("</td>");
        }
    }

    public static void convertToHTML(String writeFile, String readFile) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(writeFile);
             Scanner scanner = new Scanner(new FileInputStream(readFile))) {
            writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
                    "\"http://www.w3.org/TR/html4/loose.dtd\">");
            writer.println("<html>");

            writer.println("<head>");
            writer.println("<title>");
            writer.println(readFile);
            writer.println("</title>");
            writer.println("</head>");

            writer.println("<body>");
            writer.println("<table>");
            while (scanner.hasNextLine()) {
                writer.println("<tr>");
                ArrayList<String> cells = splitIntoCells(scanner);
                printTd(writer, cells);
                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
