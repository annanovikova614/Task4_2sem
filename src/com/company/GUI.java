package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame {
    private JButton buttonLoadFile;
    private JPanel panelGUI;
    private JTextArea textAreaInput;
    private JButton buttonSolve;
    private JTextArea textAreaOutput;


    public GUI() {
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panelGUI);
        setVisible(true);
        buttonLoadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser =new JFileChooser();
                fileChooser.setCurrentDirectory(new File("src/com/company/Tests"));
                fileChooser.showOpenDialog(panelGUI);
                Scanner scanner;
                try {
                    scanner = new Scanner(new File(fileChooser.getSelectedFile().getPath()));
                } catch (FileNotFoundException ex) {
                    JOptionPane.showInputDialog("No file");
                    return;
                }
                readFromScanner(scanner);
            }
        });
        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer[] sorted = readFromAreaAndSort();
                textAreaOutput.setText("");
                for (Integer i: sorted) {
                    textAreaOutput.append(i + " ");
                }
            }
        });
    }

    private void readFromScanner(Scanner scanner) {
        textAreaInput.setText("");
        while (scanner.hasNextLine()) {
            textAreaInput.append(scanner.nextLine() + "\n");
        }
    }

    private Integer[] readFromAreaAndSort() {
        Scanner scanner = new Scanner(textAreaInput.getText());
        Scanner lineScanner = new Scanner(scanner.nextLine());
        List<Integer> list = new LinkedList<>();
        while (lineScanner.hasNextInt()) {
            list.add(lineScanner.nextInt());
        }
        lineScanner = new Scanner(scanner.nextLine());
        boolean[] allowList = new boolean[list.size()];
        for (int i = 0; i < allowList.length; i++) {
            allowList[i] = lineScanner.nextInt() == 1;
        }
        Integer[] sort = MyInsertionSort.sort(list.toArray(new Integer[list.size()]),allowList);
        return sort;
    }
}