package lv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 콘솔로 입력하는 기본 계산기 입력의 편의성을 늘림
public class CalculatorMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Calculator calculator = new Calculator(); // Calculator 객체 생성

        while (true) {
            System.out.println("수식을 입력하세요. 종료는 exit를 입력하세요 (삭제하려면 delete 입력)");
            String input = br.readLine().replaceAll("\\s+", "");

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("계산기 종료");
                return;
            }

            if (input.equalsIgnoreCase("delete")) {
                calculator.removeFirstResult();
                System.out.println("현재 저장된 연산 결과: " + calculator.getResults());
                continue;
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

            try {
                double result = calculator.calculate(firstNumber, secondNumber, operator);
                System.out.println(input + " = " + result);

                // 최근 5개 연산 결과 출력
                List<Double> recentResults = calculator.getRecentResults(5);
                System.out.println("최근 5개의 연산 결과: " + recentResults);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
