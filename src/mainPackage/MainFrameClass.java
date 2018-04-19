package mainPackage;

import javax.swing.JApplet;
import javax.swing.JFrame;

import panelsPackage.PanelsManager;

@Deprecated
public class MainFrameClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JApplet app = new MainAppletClass();
        frame.getContentPane().add(app);
        frame.setSize(400, 400);
        app.init();
        app.start();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
}
