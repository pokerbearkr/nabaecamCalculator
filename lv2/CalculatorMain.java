package lv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 콘솔로 입력하는 기본 계산기 입력의 편의성을 늘림
public class CalculatorMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("수식을 입력하세요. 종료는 exit를 입력하세요");
            String input = br.readLine().replaceAll("\\s+", ""); // 공백 제거

            if (input.toLowerCase().equals("exit")) {
                return;
            }


            Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)([+\\-*/])(-?\\d+\\.?\\d*)");
            Matcher matcher = pattern.matcher(input);

            if (!matcher.matches()) {
                System.out.println("올바른 수식을 입력하세요.");
                continue;
            }

            double firstNumber = Double.parseDouble(matcher.group(1));
            String operator = matcher.group(2);
            double secondNumber = Double.parseDouble(matcher.group(3));

            double result = 0;

            result = Calculator.calculate(firstNumber,secondNumber,operator);

            System.out.println(input + " = " + result);
        }
    }
}
