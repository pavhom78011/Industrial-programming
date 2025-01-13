package Calculations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCalculation {
    public static void RegexProcessing(String inputText, StringBuffer resultString) throws Exception {
        inputText = processOperations(inputText, "(\\d+(\\.\\d+)?)\\s*([*/])\\s*(\\d+(\\.\\d+)?)");
        inputText = processOperations(inputText, "(\\d+(\\.\\d+)?)\\s*([+-])\\s*(\\d+(\\.\\d+)?)");
        resultString.append(inputText);
    }

    private static String processOperations(String inputText, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);
        while (matcher.find()) {
            double operand1 = Double.parseDouble(matcher.group(1));
            double operand2 = Double.parseDouble(matcher.group(4));
            char operator = matcher.group(3).charAt(0);
            double result;
            switch (operator) {
                case '*': result = operand1 * operand2;
                break;
                case '/': result = operand1 / operand2;
                break;
                case '+': result = operand1 + operand2;
                break;
                case '-': result = operand1 - operand2;
                break;
                default: throw new IllegalArgumentException("Unsupported operator");
            }
            String replacement = result % 1 == 0 ? String.valueOf((int) result) : String.valueOf(result);
            inputText = matcher.replaceFirst(replacement);
            matcher.reset(inputText);
        }
        return inputText;
    }
}
