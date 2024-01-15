package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField inputField;
    private String textField = "";
    private  String check = "hexadecimal";
    private JButton[] buttons;

    private char[] buttonContent = {'d','e','f','a','b','c','7','8','9','4','5','6','1','2','3',' ','0','='};
    Main() {
        setTitle("canver namber type");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout());
        inputField = new JTextField(15);
        inputField.setPreferredSize(new Dimension(getWidth(), 40));
        inputPanel.add(inputField);
        add(inputPanel, BorderLayout.NORTH);

        //----------------------------------------------
        JPanel buttonPanel = new JPanel(new GridLayout(6, 3));
        buttons = new JButton[20];

        for (int i = 0; i < buttonContent.length; i++){
            buttons[i] = new JButton("" + buttonContent[i]);
            buttons[i].setPreferredSize(new Dimension(getWidth(),40));
            final int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buttonText = buttons[finalI].getText();
                    if (buttonText.toCharArray()[0] != '='){
                        textField += buttonText != " "? buttonText : "";

                    }else {
                        JFrame newFrame = new JFrame("new window");
                        JLabel label1;
                        JLabel label2;

                        switch (check){
                            case "octal":
                                label1 = new JLabel("binary: "+Integer.toBinaryString(Integer.parseInt(textField,8)));
                                label1.setBounds(50,50,200,30);
                                label2 = new JLabel("hexadecimal: "+Integer.toHexString(Integer.parseInt(textField,8)));
                                label2.setBounds(50,100,200,30);
                                newFrame.add(label1);
                                newFrame.add(label2);
                                break;
                            case "hexadecimal":
                                label1 = new JLabel("binary: "+Integer.toBinaryString(Integer.parseInt(textField, 16)));
                                label1.setBounds(50,50,200,30);
                                label2 = new JLabel("octal: "+Integer.toOctalString(Integer.parseInt(textField,16)));
                                label2.setBounds(50,100,200,30);
                                newFrame.add(label1);
                                newFrame.add(label2);
                                break;
                            case "binary":
                                label1 = new JLabel("hexadecimal: "+Integer.toHexString(Integer.parseInt(textField, 2)));
                                label1.setBounds(50,50,200,30);
                                label2 = new JLabel("octal: "+Integer.toOctalString(Integer.parseInt(textField,2)));
                                label2.setBounds(50,100,200,30);
                                newFrame.add(label1);
                                newFrame.add(label2);
                                break;
                        }

                        newFrame.setSize(300,200);
                        newFrame.setLayout(null);
                        newFrame.setVisible(true);
                        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
                    inputField.setText(textField);
                }
            });
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);

        //--------------------------------
        JPanel checkPanel = new JPanel(new GridLayout(1, 3));
        ButtonGroup group = new ButtonGroup();

        JRadioButton checkBox1 = new JRadioButton("octal");
        checkBox1.setBounds(100, 100, 150, 50);
        group.add(checkBox1);
        checkPanel.add(checkBox1);

        JRadioButton checkBox2 = new JRadioButton("binary");
        checkBox2.setBounds(100, 150, 150, 50);
        group.add(checkBox2);
        checkPanel.add(checkBox2);

        JRadioButton checkBox3 = new JRadioButton("hexadecimal");
        checkBox3.setBounds(100, 200, 150, 50);
        group.add(checkBox3);
        checkPanel.add(checkBox3);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = e.getActionCommand();
                switch (check){
                    case "hexadecimal":
                        inputField.setText("");
                        textField = "";
                        for (int i = 0; i < buttonContent.length; i++){
                            buttons[i].setText(buttonContent[i]+"");
                        }
                        break;
                    case "octal":
                        inputField.setText("");
                        textField = "";
                        for (int i = 0; i < buttonContent.length; i++){
                            buttons[i].setText(buttonContent[i]+"");
                        }
                        for(int i = 0; i < 6; i++){
                            buttons[i].setText(" ");
                        }
                        buttons[7].setText(" ");
                        buttons[8].setText(" ");
                        break;
                    case "binary":
                        inputField.setText("");
                        textField = "";
                        for(int i = 0; i < 12; i++){
                            buttons[i].setText(" ");
                        }
                        buttons[13].setText(" ");
                        buttons[14].setText(" ");
                        break;
                    default:

                }
            }
        };

        checkBox1.addActionListener(listener);
        checkBox2.addActionListener(listener);
        checkBox3.addActionListener(listener);


        add(checkPanel, BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}