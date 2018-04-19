package tests;

import java.awt.Component;
import java.util.concurrent.Executors;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.ComponentMatcher;
import org.fest.swing.core.Robot;
import org.junit.Assert;

import dataPackage.Lecturer;
import mainPackage.MainAppletClass;

public enum ProgrammClicker {
    INSTANCE;

    private Robot robot;
    private ComponentFinder finder;

    private ProgrammClicker() {
        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        finder = robot.finder();
    }

    public void pressLectorerPanelButton() throws Exception {
        String btnText = MainAppletClass.panelsManager.mainPanel.lecturerButton.getText();
        CaptionMatcher matcher = new CaptionMatcher(btnText);
        click(matcher);
    }

    public void pressStudentPanelButton() throws Exception {
        String btnText = MainAppletClass.panelsManager.mainPanel.studentButton.getText();
        CaptionMatcher matcher = new CaptionMatcher(btnText);
        click(matcher);
    }

    public void pressInfoListPanelButton() throws Exception {
        String btnText = MainAppletClass.panelsManager.mainPanel.btnInfoList.getText();
        CaptionMatcher matcher = new CaptionMatcher(btnText);
        click(matcher);
    }

    private void click(CaptionMatcher matcher) throws Exception {
        Component tmp = null;
        long start = System.currentTimeMillis();

        while (tmp == null) {
            try {
                tmp = finder.find(matcher);
                final Component btn = tmp;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        ((JButton) btn).doClick();
                    }
                });
            } catch (Exception e) {
                // e.printStackTrace();
            }
            Thread.yield();
            if (System.currentTimeMillis() - start > 1000) {
                throw new Exception("text to be thrown");
            }
        }
        Thread.sleep(100);
    }

    private class CaptionMatcher implements ComponentMatcher {
        private String caption;

        public CaptionMatcher(String caption) {
            this.setCaption(caption);
        }

        @Override
        public boolean matches(Component comp) {
            if (comp != null && comp instanceof JButton) {
                if (caption.equals(((JButton) comp).getText())) {
                    return true;
                }
            }
            return false;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }
    }
}
