package lv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField textField;
    private DefaultListModel<String> recentResultsModel;
    private JList<String> recentResultsList;
    private String currentInput = "";
    private Calculator calculator;

    public CalculatorGUI() {
        calculator = new Calculator(); // Calculator 객체 생성

        frame = new JFrame("계산기");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 텍스트 필드 (수식 입력창)
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(textField, BorderLayout.NORTH);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // 버튼 배열
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        // 최근 연산 결과 패널 생성
        JPanel recentPanel = new JPanel();
        recentPanel.setLayout(new BorderLayout());
        recentPanel.setPreferredSize(new Dimension(150, frame.getHeight()));

        // "최근 연산 결과" 제목 추가
        JLabel recentLabel = new JLabel("Resent Calculated", SwingConstants.CENTER);
        recentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recentPanel.add(recentLabel, BorderLayout.NORTH);

        // 최근 연산 결과 리스트
        recentResultsModel = new DefaultListModel<>();
        recentResultsList = new JList<>(recentResultsModel);
        recentResultsList.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(recentResultsList);
        recentPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(recentPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    // 버튼 이벤트 처리
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("=")) {
                try {
                    String[] parts = currentInput.split("(?<=[+\\-*/])|(?=[+\\-*/])");
                    if (parts.length != 3) {
                        textField.setText("Error");
                        currentInput = "";
                        return;
                    }

                    double num1 = Double.parseDouble(parts[0]);
                    OperatorType operator = OperatorType.fromSymbol(parts[1]);
                    double num2 = Double.parseDouble(parts[2]);

                    double result = calculator.calculate(num1, num2, operator);
                    textField.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);

                    updateRecentResults(); // 최근 연산 결과 갱신
                } catch (Exception ex) {
                    textField.setText("Error");
                    currentInput = "";
                }
            } else if (command.equals("C")) {
                currentInput = "";
                textField.setText("");
            } else {
                currentInput += command;
                textField.setText(currentInput);
            }
        }
    }

    // 최근 연산 결과 갱신
    private void updateRecentResults() {
        recentResultsModel.clear();
        List<Double> recentResults = calculator.getRecentResults(5);

        for (int i = 0; i < recentResults.size(); i++) {
            recentResultsModel.addElement(" " + recentResults.get(i));
            if (i < recentResults.size() - 1) {
                recentResultsModel.addElement("----------------"); // 구분선 추가
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
