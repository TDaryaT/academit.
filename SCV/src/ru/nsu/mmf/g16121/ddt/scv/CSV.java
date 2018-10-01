package ru.nsu.mmf.g16121.ddt.scv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {

    public static void convertToHTML(String writeFile,String readFile) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(writeFile);
             Scanner scanner = new Scanner(new FileInputStream(readFile))) {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<table>");

            boolean isOpenQuotes = false;
            while (scanner.hasNextLine()) {
                if (!isOpenQuotes) {
                    writer.println("<tr>");
                }

                String line = scanner.nextLine();
                int lineLength = line.length();
                for (int i = 0; i < lineLength; ) {
                    if (!isOpenQuotes) {
                        writer.println("<td>");
                    }

                    //начальные данные
                    char symbol = line.charAt(i);

                    //если не заключена в кавычки текущая ячейка
                    if ('\"' != symbol && !isOpenQuotes) {
                        int comma = line.indexOf(",", i);
                        if (comma != -1) {
                            writer.println(line.substring(i, comma));
                            i = comma + 1;
                        } else {
                            writer.println(line.substring(i));
                            i = lineLength;
                        }
                    } else {
                        //если ячейка в кавычках
                        if (!isOpenQuotes) {
                            i++;
                            isOpenQuotes = true;
                        }
                        while (isOpenQuotes && i < lineLength - 1) {
                            symbol = line.charAt(i);

                            //проверка на символ "кавычка" в тексте
                            if (symbol == '"' && line.charAt(i + 1) == '\"') {
                                i++;
                                symbol = line.charAt(i);
                            }

                            writer.print(symbol);
                            i++;

                            //проверка на конец ячейки
                            if (i < lineLength - 1) {
                                isOpenQuotes = line.charAt(i) != '"' || line.charAt(i + 1) != ',';
                            } else {
                                isOpenQuotes = line.charAt(i) != '"';
                            }
                        }
                        if (isOpenQuotes) {
                            writer.print(line.charAt(i));
                            writer.println("<br/>");
                            break;
                        } else {
                            i += 2;
                        }
                        writer.println();
                    }
                    writer.println("</td>");
                }
                if (!isOpenQuotes) {
                    writer.println("</tr>");
                }
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}
