package lv2;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<Double> results; // 연산 결과를 저장하는 리스트

    public Calculator() {
        this.results = new ArrayList<>(); // ArrayList 초기화
    }

    // 사칙연산을 수행하고 결과를 반환
    public double calculate(double num1, double num2, String operator) {
        double result;

        switch (operator) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("❌ 0으로 나눌 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("❌ 올바른 연산자를 입력하세요 (+, -, *, /).");
        }

        results.add(result); // 연산 결과 저장
        return result;
    }

    // 연산 결과 목록을 가져오는 메서드 = Getter
    public List<Double> getResults() {
        return new ArrayList<>(results); // 캡슐화를 위해 복사본 반환
    }

    // 최근 n개의 연산 결과를 반환
    public List<Double> getRecentResults(int count) {
        int size = results.size();
        if (size == 0) {
            return new ArrayList<>(); // 저장된 결과가 없으면 빈 리스트 반환
        }
        int start = Math.max(0, size - count);
        return new ArrayList<>(results.subList(start, size));
    }

    // 가장 먼저 저장된 연산 결과를 삭제
    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0);
            System.out.println("가장 먼저 저장된 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("삭제할 결과가 없습니다.");
        }
    }
}
