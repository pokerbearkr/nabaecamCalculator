package lv3;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<Double> results;

    public Calculator() {
        this.results = new ArrayList<>();
    }

    public double calculate(double num1, double num2, OperatorType operator) {
        double result = operation(num1, num2, operator);
        results.add(result);
        return result;
    }

    private double operation(double num1, double num2, OperatorType operatorType) {
        switch (operatorType) {
            case ADD: return num1 + num2;
            case SUBTRACT: return num1 - num2;
            case MULTIPLY: return num1 * num2;
            case DIVIDE:
                if (num2 == 0) {
                    throw new ArithmeticException("0으로 나눌수 없습니다.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("올바른 연산자를 입력하세요.");
        }
    }

    public List<Double> getResults() {
        return results;
    }

    public List<Double> getRecentResults(int count) {
        int size = results.size();
        return (size == 0) ? new ArrayList<>() : new ArrayList<>(results.subList(Math.max(0, size - count), size));
    }


    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0);
            System.out.println("먼저 연산된 연산 결과가 삭제되었습니다.");

        } else {
            System.out.println("삭제할 결과가 없습니다.");
        }
    }

}
