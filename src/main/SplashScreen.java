package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SplashScreen extends JFrame {

    SplashScreen() throws InterruptedException {
        URL iconURL = getClass().getResource("/Resources/titleIcon.png");
        ImageIcon titleIcon = new ImageIcon(iconURL);

        JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/Resources/Logo.gif")));
        logo.setVerticalAlignment(JLabel.CENTER);
        logo.setHorizontalAlignment(JLabel.CENTER);

        this.setTitle("Let OS Play");
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(titleIcon.getImage());
        this.getContentPane().setBackground(new Color(6, 19, 44));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.getContentPane().add(logo);

        Thread.sleep(3000);
        new MainGameFrame();
        this.dispose();
    }
}