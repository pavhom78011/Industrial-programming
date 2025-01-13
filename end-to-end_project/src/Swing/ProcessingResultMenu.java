package Swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

public class ProcessingResultMenu {
    static JFrame jFrame = new JFrame("Result");
    static JLabel SuccessfulResult = new JLabel("action completed successfully");
    static JLabel inputinfo = new JLabel("input information:");
    static JLabel outputinfo = new JLabel("output information:");
    static JTextArea input = new JTextArea();
    static JTextArea output = new JTextArea();
    static JButton exit = new JButton("exit app");
    static JButton retry = new JButton("retry");

    public static void CreateResultMenu () {
        jFrame.setSize(300, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new MigLayout("", "[grow][grow]", "[][]"));
        panel.add(SuccessfulResult, "cell 1 0");
        input.setEditable(false);
        output.setEditable(false);
        panel.add(inputinfo, "cell 0 1");
        panel.add(outputinfo, "cell 2 1");
        panel.add(input, "cell 0 2");
        panel.add(output, "cell 2 2");
        panel.add(exit, "cell 0 3");
        panel.add(retry, "cell 2 3");
        jFrame.add(panel);
        jFrame.pack();
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        retry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcessingResultMenu.CloseResultMenu();
                MainMenu.ViewMainMenu();
            }
        });
    }

    public static void ViewResultMenu (String inputtxt, String outputtxt) {
        jFrame.setVisible(true);
        if (DataProcessing.GetMatches()) {
            SuccessfulResult.setText("action completed successfully");
            input.setText(inputtxt);
            output.setText(outputtxt);
        }
        else {
            SuccessfulResult.setText("files not found");
            input.setText("");
            output.setText("");
        }
    }

    public static void CloseResultMenu () {
        jFrame.setVisible(false);
    }
}
