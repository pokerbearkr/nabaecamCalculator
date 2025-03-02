package lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 콘솔로 입력하는 기본 계산기
public class Calculator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // exit 나오기 전까지 무한 반복
        while (true) {
            System.out.println("수식을 입력하세요. 종료는 exit를 입력하세요");
            String input = br.readLine();

            if (input.toLowerCase().equals("exit")) {
                System.out.println("계산기가 종료됩니다.");
                return;
            }

            StringTokenizer st = new StringTokenizer(input);
            double firstNumber = Double.parseDouble(st.nextToken());
            String operator = st.nextToken();
            double secondNumber = Double.parseDouble(st.nextToken());

            double result = 0;

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber == 0) {
                        System.out.println("0으로는 나눌 수 없습니다.");
                        continue;
                    } else {
                        result = firstNumber / secondNumber;
                    }
                    break;
                default:
                    System.out.println("올바른 연산자를 입력하세요 (+, -, *, /).");
                    continue;
            }

            System.out.println(input + " = " + result);
        }
    }
}
