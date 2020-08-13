package ru.nsu.mmf.g16121.ddt.main;

import ru.nsu.mmf.g16121.ddt.util.ConvertingTemperature;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

            SwingUtilities.invokeLater(() -> {
                //задаем окно
                ConvertingTemperature frame = new ConvertingTemperature();
                frame.setVisible(true);
                frame.action();
            });
        } catch (Exception e) {
        }
    }
}
