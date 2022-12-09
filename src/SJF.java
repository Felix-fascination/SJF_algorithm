import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


class SJF extends JFrame implements ActionListener {
    JButton[] buttonControllers = new JButton[3];
    JTextField[] inputTimeText, processSizeText;
    JLabel[] processStrings, firstMenuCols;
    JPanel northLayout, southLayout;
    Container firstMenuContainer;
    int numberOfProcesses;
    String[] stringControllers = {"Расчитать", "Очистить", "Выход"};
    String[] outputCols = {"Process ID", "Время добавления ", "Время обработки ", "Время ожидания ", "Время завершения "};


    public SJF() {
        super("SJF algorithm");
        firstMenuContainer = getContentPane();

        numberOfProcesses = Integer.parseInt(JOptionPane.showInputDialog("Введите количество процессов"));

        firstMenuCols = new JLabel[3];
        firstMenuCols[0] = new JLabel("Процесс");
        firstMenuCols[1] = new JLabel("Время добавления");
        firstMenuCols[2] = new JLabel("Время обработки");

        processStrings = new JLabel[numberOfProcesses];
        inputTimeText = new JTextField[numberOfProcesses];
        processSizeText = new JTextField[numberOfProcesses];


        for (int i = 0; i < numberOfProcesses; i++) {
            processStrings[i] = new JLabel("Процесс " + (i + 1));
            inputTimeText[i] = new JTextField(1);
            processSizeText[i] = new JTextField(1);
        }

        for (int i = 0; i < 3; i++) {
            buttonControllers[i] = new JButton(stringControllers[i]);
        }

        firstMenuContainer.setLayout(new GridLayout(numberOfProcesses + 2, 3));
        firstMenuContainer.add(firstMenuCols[0]);
        firstMenuContainer.add(firstMenuCols[1]);
        firstMenuContainer.add(firstMenuCols[2]);

        for (int i = 0; i < numberOfProcesses; i++) {
            firstMenuContainer.add(processStrings[i]);
            firstMenuContainer.add(inputTimeText[i]);
            firstMenuContainer.add(processSizeText[i]);
        }
        for (int i = 0; i < 3; i++) {
            firstMenuContainer.add(buttonControllers[i]);
            buttonControllers[i].addActionListener(this);
        }
    }//end of constructor

    public void actionPerformed(ActionEvent ae) {
        float sum = 0;
        float avg;
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        northLayout = new JPanel();
        southLayout = new JPanel();
        northLayout.setLayout(new GridLayout(numberOfProcesses + 1, 5));
        southLayout.setLayout(new FlowLayout());


        if (ae.getSource() == buttonControllers[0]) {
            int[][] processesData = new int[numberOfProcesses][];
            SJFObject processes = new SJFObject();
            for(int i = 0; i < numberOfProcesses; i++){
                processes.addElement(Integer.parseInt(inputTimeText[i].getText()),
                        Integer.parseInt(processSizeText[i].getText()));
            }
            for(int i = 0; i < numberOfProcesses; i++){
                // 0 - index, 1 - waiting time, 2 - ending time
                int[] results = processes.nextProcess();
                processesData[results[0]] = Arrays.copyOfRange(results,1, 3);

            }

            //end for loop
            for (int i = 0; i < 5; i++) {
                northLayout.add(new JLabel(outputCols[i]));
            }
            for (int i = 0; i < numberOfProcesses; i++) {
                northLayout.add(new JLabel("процесс " + (i + 1)));
                northLayout.add(new JLabel("   " + Integer.parseInt(inputTimeText[i].getText())));
                northLayout.add(new JLabel("" + Integer.parseInt(processSizeText[i].getText())));
                northLayout.add(new JLabel("" + processesData[i][0]));
                northLayout.add(new JLabel(""+ processesData[i][1]) );
                sum += processesData[i][0];
            }
            avg = sum / numberOfProcesses;
            String str2 = "Среднее время ожидания " + avg;
            southLayout.add(new JLabel(str2));
            main.add(northLayout, BorderLayout.NORTH);
            main.add(southLayout, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(null, main, "Results", JOptionPane.PLAIN_MESSAGE);

        } else if (ae.getSource() == buttonControllers[1]) {
            setVisible(false);
            SJF window = new SJF();
            window.setSize(800, 300);
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } else if (ae.getSource() == buttonControllers[2]) {
            System.exit(0);
        }
    }//END ACTION PERFORMED

    public static void main(String[] args) {
        SJF window = new SJF();
        window.setSize(800, 300);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end main
}//end class