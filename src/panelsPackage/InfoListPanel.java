package panelsPackage;

import javax.swing.JPanel;

import dataPackage.DataCollector;
import dataPackage.Lecturer;
import dataPackage.Student;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class InfoListPanel extends JPanel {
    private PanelsManager panelsManager;
    private DataCollector dataCollector;
    private DefaultListModel defaultListModelLecturers;
    private DefaultListModel defaultListModelStudents;
    private JList studentsList;

    public InfoListPanel(PanelsManager panelsManager, DataCollector dataCollector) {
        this.panelsManager = panelsManager;
        this.dataCollector = dataCollector;

        System.out.println("InfoListPanel::InfoListPanel(); -- this.getClass().getName():" + this.getClass().getName());
        setName(this.getClass().getName());

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
        setLayout(flowLayout);

        Box verticalBox_2 = Box.createVerticalBox();
        add(verticalBox_2);

        Box horizontalBox = Box.createHorizontalBox();
        verticalBox_2.add(horizontalBox);

        Box verticalBox = Box.createVerticalBox();
        horizontalBox.add(verticalBox);

        JLabel lblNewLabel = new JLabel("Преподаватель");
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox.add(lblNewLabel);

        defaultListModelLecturers = new DefaultListModel();
        JList lecturersList = new JList();
        lecturersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lecturersList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                JList sourceList = (JList)arg0.getSource();
                System.out.println("InfoListPanel::lecturersList::valueChanged(); -- " + sourceList.getSelectedIndex());
                defaultListModelStudents.removeAllElements();
                if(!sourceList.isSelectionEmpty()) {
                    Lecturer lecturer = dataCollector.lecturers.get(sourceList.getSelectedIndex());
                    for(Student student : lecturer.students) {
                        defaultListModelStudents.addElement(student.getFIO());
                    }
                    System.out.println("InfoListPanel::lecturersList::valueChanged(); -- defaultListModelStudents" + defaultListModelStudents);
                }
                panelsManager.mainAppletClass.resizeApplet();
            }
        });
        lecturersList.setModel(defaultListModelLecturers);
        verticalBox.add(lecturersList);

        Box verticalBox_1 = Box.createVerticalBox();
        horizontalBox.add(verticalBox_1);

        JLabel lblNewLabel_1 = new JLabel("Студент");
        lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox_1.add(lblNewLabel_1);

        defaultListModelStudents = new DefaultListModel();
        studentsList = new JList();
        studentsList.setModel(defaultListModelStudents);
        verticalBox_1.add(studentsList);

        JButton btnBack = new JButton("Назад");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelsManager.setMainPanel();
            }
        });
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox_2.add(btnBack);

        JButton btnDelAll = new JButton("Удалить всех");
        btnDelAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Вы уверены что хотите всех удалить?", "Warning", dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    dataCollector.removeAll();
                    updateInfoList();
                    panelsManager.mainAppletClass.resizeApplet();
                }
            }
        });
        btnDelAll.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox_2.add(btnDelAll);
        System.out.println("InfoListPanel::InfoListPanel(); -- dataCollector:" + dataCollector);
//        updateInfoList();
    }

    public void updateInfoList() {
        System.out.println("InfoListPanel::updateInfoList(); -- dataCollector:" + dataCollector);
        defaultListModelLecturers.removeAllElements();
        for (Lecturer lecturer : dataCollector.lecturers) {
            defaultListModelLecturers.addElement(lecturer.getFIO());
        }
//        validate();
    }
}
