package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainMenu {
    static JFrame jFrame = new JFrame("App");
    static JLabel label1 = new JLabel("Input file name:");
    static JLabel label2 = new JLabel("Output file name:");
    static JLabel InputArchivedOrNot = new JLabel("Input file archived");
    static JLabel OutputArchivedOrNot = new JLabel("Output file archived");
    static JLabel OutputEncryption = new JLabel("Output file encrypted");
    static JLabel InvalidNames = new JLabel("invalid names");
    static JLabel AvailableFormats = new JLabel("available formats: txt, json, xml, yaml");
    static JLabel ChooseVariantOfCalculation = new JLabel("Choose variant of calculation");
    static JTextField inputfilename = new JTextField(15);
    static JTextField outputfilename = new JTextField(15);
    static JCheckBox InputNotArchived = new JCheckBox("No");
    static JCheckBox InputArchived = new JCheckBox("Yes");
    static JCheckBox OutputNotArchived = new JCheckBox("No");
    static JCheckBox OutputArchived = new JCheckBox("Yes");
    static JCheckBox OutputNotEncrypted = new JCheckBox("No");
    static JCheckBox OutputEncrypted = new JCheckBox("Yes");
    static JCheckBox ByRegex = new JCheckBox("Regex");
    static JCheckBox ByLibrary = new JCheckBox("Library");
    static JButton FinishInput = new JButton("finish");
    static boolean once = true;

    public static void CreateMainMenu() {
        jFrame.setSize(500, 700);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());
        inputfilename.setMinimumSize(new Dimension(100, 30));
        outputfilename.setMinimumSize(new Dimension(100, 30));
        InvalidNames.setForeground(Color.RED);
        InvalidNames.setVisible(false);
        ByRegex.setSelected(true);
        InputNotArchived.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    InputArchived.setSelected(false);
                }
            }
        });
        InputArchived.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    InputNotArchived.setSelected(false);
                }
            }
        });
        OutputNotArchived.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    OutputArchived.setSelected(false);
                }
            }
        });
        OutputArchived.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    OutputNotArchived.setSelected(false);
                }
            }
        });
        OutputNotEncrypted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    OutputEncrypted.setSelected(false);
                }
            }
        });
        OutputEncrypted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    OutputNotEncrypted.setSelected(false);
                }
            }
        });
        ByRegex.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ByLibrary.setSelected(false);
                }
                else {
                    ByLibrary.setSelected(true);
                }
            }
        });
        ByLibrary.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ByRegex.setSelected(false);
                }
                else {
                    ByRegex.setSelected(true);
                }
            }
        });
        FinishInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (inputfilename.getText().matches(".*\\b\\w+\\.\\w+\\b.*") && outputfilename.getText().matches(".*\\b\\w+\\.\\w+\\b.*")) {
                        InvalidNames.setVisible(false);
                        DataProcessing.Working();
                        MainMenu.CloseMainMenu();
                        if (once) {
                            ProcessingResultMenu.CreateResultMenu();
                            once = false;
                        }
                        ProcessingResultMenu.ViewResultMenu(DataProcessing.GetInputText(), DataProcessing.GetOutputText());
                    }
                    else {
                        InvalidNames.setVisible(true);
                        if (!inputfilename.getText().matches(".*\\b\\w+\\.\\w+\\b.*")) {
                            inputfilename.setText("");
                        }
                        if (!outputfilename.getText().matches(".*\\b\\w+\\.\\w+\\b.*")) {
                            outputfilename.setText("");
                        }
                    }
                } catch (Exception ex) {
                    DataProcessing.SetMatches(false);
                    MainMenu.CloseMainMenu();
                    if (once) {
                        ProcessingResultMenu.CreateResultMenu();
                        once = false;
                    }
                    ProcessingResultMenu.ViewResultMenu("", "");
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        jFrame.add(label1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(label2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        jFrame.add(inputfilename, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(outputfilename, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(AvailableFormats, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        jFrame.add(InputArchivedOrNot, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(InputNotArchived, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(InputArchived, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        jFrame.add(OutputArchivedOrNot, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(OutputNotArchived, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(OutputArchived, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        jFrame.add(OutputEncryption, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(OutputNotEncrypted, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(OutputEncrypted, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        jFrame.add(ChooseVariantOfCalculation, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(ByRegex, gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(ByLibrary, gbc);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(FinishInput, gbc);
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        jFrame.add(InvalidNames, gbc);
    }

    public static void ViewMainMenu() {
        jFrame.setVisible(true);
    }

    public static void CloseMainMenu() {
        jFrame.setVisible(false);
    }

    public static boolean InputNotArchivedStatus() {
        return InputNotArchived.isSelected();
    }

    public static boolean InputArchivedStatus() {
        return InputArchived.isSelected();
    }

    public static boolean OutputNotArchivedStatus() {
        return OutputNotArchived.isSelected();
    }

    public static boolean OutputArchivedStatus() {
        return OutputArchived.isSelected();
    }

    public static boolean OutputNotEncryptedStatus() {
        return OutputNotEncrypted.isSelected();
    }

    public static boolean OutputEncryptedStatus() {
        return OutputEncrypted.isSelected();
    }

    public static boolean RegexStatus() { return ByRegex.isSelected(); }

    public static boolean LibraryStatus() { return ByLibrary.isSelected(); }

    public static String GetInputFileName() {
        return inputfilename.getText();
    }

    public static String GetOutputFileName() {
        return outputfilename.getText();
    }

    public static void SetVisibleInvalidNames() {
        InvalidNames.setVisible(true);
    }
}