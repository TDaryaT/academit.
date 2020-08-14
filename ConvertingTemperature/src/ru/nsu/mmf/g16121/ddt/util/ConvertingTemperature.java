package ru.nsu.mmf.g16121.ddt.util;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * Class for Converting Temperature. Swing forms
 *
 * Функциональность:
 *
 * Ввод температуры в поле ввода
 * Кнопка, которая переводит температуру из одной шкалы в другую
 * Результат перевода выводится на форму, не редактируемый
 * Можно задать из какой шкалы и в какую переводить
 * Доступные шкалы: цельсия, фаренгейта, кельвина
 * Если ввели не число, выводит ошибку
 * Использованы layout manager’ы
 */
public class ConvertingTemperature extends JFrame {
    //Создание компонент
    private final JButton find;
    private final JButton cancel;
    private final JComboBox<String> dimensionFrom;
    private final JComboBox<String> dimensionTo;
    private final JTextField textField;
    private final JLabel result;

    public ConvertingTemperature() {
        super();
        this.setTitle("Convert temperature");

        int width = 350;
        int height = 200;
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //положение окна относительно размера экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ScreenWidth = screenSize.width;
        int ScreenHeight = screenSize.height;
        this.setLocation(ScreenWidth / 2 - width / 2, ScreenHeight / 2 - height / 2);

        //задаем иконку
        String path = "//home//dasha//IdeaProjects//academit.//ConvertingTemperature//iconConvTemp2.jpg";
        Image img = Toolkit.getDefaultToolkit().getImage(path);
        this.setIconImage(img);

        //Создание компонент
        result = new JLabel("Result: ");
        JLabel enterTemp = new JLabel("Enter temperature");
        textField = new JTextField("", 11);
        String[] dimensions = {"", "Celsius", "Fahrenheit", "Kelvin"};
        dimensionFrom = new JComboBox<>(dimensions);
        dimensionTo = new JComboBox<>(dimensions);
        find = new JButton("Find");
        cancel = new JButton("Cancel");

        //подключение менеджера компоновки
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        this.add(panel);

        //подключение компонент
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(enterTemp)
                                .addComponent(dimensionFrom)
                                .addComponent(result))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(textField)
                                .addComponent(dimensionTo))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(find)
                                .addComponent(cancel))
        );
        layout.linkSize(SwingConstants.HORIZONTAL, find, cancel);
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(enterTemp)
                                .addComponent(textField)
                                .addComponent(find))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dimensionFrom)
                                .addComponent(dimensionTo)
                                .addComponent(cancel))
                        .addComponent(result)
        );
        pack();
    }

    private double convertTemp(double temp, char from, char to) {
        if (from == to) {
            return temp;
        }
        switch (from) {
            case 'F':
                switch (to) {
                    case 'C':
                        return 5.0 * (temp - 32.0) / 9.0;
                    case 'K':
                        return 273.15 + 5.0 * (temp - 32.0) / 9.0;
                    default:
                        throw new IllegalArgumentException("You can use only F, C or K");
                }
            case 'C':
                switch (to) {
                    case 'F':
                        return temp * 1.8 + 32.0;
                    case 'K':
                        return temp + 273.15;
                    default:
                        throw new IllegalArgumentException("You can use only F, C or K");
                }
            case 'K':
                switch (to) {
                    case 'F':
                        return 1.8 * (temp - 273.15) + 32.0;
                    case 'C':
                        return temp - 273.15;
                    default:
                        throw new IllegalArgumentException("You can use only F, C or K");
                }
            default:
                throw new IllegalArgumentException("You can use only F, C or K");
        }
    }

    public void action() {
            final String[] from = {""};
            final String[] to = {""};
            dimensionFrom.addActionListener(e -> from[0] = (String) dimensionFrom.getSelectedItem());
            dimensionTo.addActionListener(e -> to[0] = (String) dimensionTo.getSelectedItem());
            find.addActionListener(e -> {
                try {
                    String text = textField.getText();
                    double temp = Double.parseDouble(text);
                    double newTemp = convertTemp(temp, from[0].charAt(0), to[0].charAt(0));
                    result.setText("Result: " + String.format(Locale.US, "%.2f", newTemp));
                } catch (NumberFormatException | NullPointerException nfe) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                            "Enter a number!");
                } catch (IndexOutOfBoundsException err){
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                            "Select a dimension!");
                }
            });
            cancel.addActionListener(e -> {
                textField.setText("");
                result.setText("Result: ");
                dimensionFrom.setSelectedIndex(0);
                dimensionTo.setSelectedIndex(0);
            });

    }
}
