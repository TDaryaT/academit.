package ru.nsu.mmf.g16121.ddt.main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            SwingUtilities.invokeLater(() -> {
                //задаем окно
                JFrame frame = new JFrame("Convert Temperature");
                int width = 350;
                int height = 200;
                frame.setSize(width, height);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //задаем иконку
                Image img = Toolkit.getDefaultToolkit().getImage("//home//dasha//IdeaProjects//academit.//ConvertingTemperature//iconConvTemp.jpg");
                frame.setIconImage(img);

                //положение окна относительно размера экрана
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int ScreenWidth = screenSize.width;
                int ScreenHeight = screenSize.height;
                frame.setLocation(ScreenWidth / 2 - width / 2, ScreenHeight / 2 - height / 2);

                //подключение менеджера компоновки
                JPanel panel = new JPanel();
                GroupLayout layout = new GroupLayout(panel);
                panel.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                //Создание компонент
                JButton find = new JButton("Find");
                JButton cancel = new JButton("Cancel");
                JTextField textField = new JTextField("Enter temperature", 11);


                frame.setVisible(true);
            /*
            String text = textField.getText();
            int number = Integer.parseInt(text);
             */
            });
        } catch (Exception e) {
        }
    }
}
