package mainPackage;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JFrame;

import panelsPackage.PanelsManager;

public class MainAppletClass extends JApplet {
    public static JFrame frame;
    public static PanelsManager panelsManager;

    public MainAppletClass() {
        System.out.println("MainAppletClass::MainAppletClass(); -- ");
    }

    @Override
    public void init() {
        System.out.println("MainAppletClass::init(); -- Start! thread:" + Thread.currentThread().toString());
        super.init();
        try {
//            new Thread() {
//                public void run() {
//                    initComponents();
//                };
//            }.start();
            
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    System.out.println("MainAppletClass::init()::run(); -1- thread:" + Thread.currentThread().toString());
                    initComponents();
                    invalidate();
                    System.out.println("MainAppletClass::init()::run(); -2- thread:" + Thread.currentThread().toString());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("MainAppletClass::init(); -- End! thread:" + Thread.currentThread().toString());
    }

    public void initComponents() {
        System.out.println("MainAppletClass::initComponents(); -- Start! thread:" + Thread.currentThread().toString());
        this.panelsManager = new PanelsManager(this, getContentPane());
    }

    @Override
    public void start() {
        System.out.println("MainAppletClass::start(); -- ");
    }

    @Override
    public void stop() {
        System.out.println("MainAppletClass::stop(); -- ");
    }

    @Override
    public void destroy() {
        System.out.println("MainAppletClass::destroy(); -- ");
    }

    public static void main(String[] args) throws InterruptedException {
        JApplet applet = new MainAppletClass();
        applet.init();

        frame = new JFrame("Applet in Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( applet );
        frame.pack();
//        frame.setLocationRelativeTo( null );
        frame.setVisible( true );

        applet.start();
    }

    public void resizeApplet() {
//        System.out.println("MainAppletClass::resizeApplet(); -- this.getPreferredSize():" + this.getPreferredSize());
        this.setSize(this.getPreferredSize());
//        System.out.println("MainAppletClass::resizeApplet(); -- frame:" + (frame!=null));
        if (frame != null) {
//            System.out.println("MainAppletClass::resizeApplet(); -- frame.getPreferredSize():" + frame.getPreferredSize());
            frame.setSize(frame.getPreferredSize());
        }
    }

//    @Override
//    public void paint(Graphics g) {
////       g.drawString("Hello, world!",70,50);
//       System.out.println("MainAppletClass::paint(); -- frame:" + frame + " getContentPane().getPreferredSize():" + getContentPane().getPreferredSize());
//       if(frame != null) {
//           frame.setSize(getContentPane().getPreferredSize());
//           update(g);
//           repaint();
//       }
//    }
}
