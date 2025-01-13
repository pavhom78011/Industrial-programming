package Calculations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class LibraryCalculation {
    public static void ProcessExpressions(String inputText, StringBuffer resultString) throws Exception {
        Pattern pattern = Pattern.compile("\\d+(\\s*[+\\-*/]\\s*\\d+)+");
        Matcher matcher = pattern.matcher(inputText);
        while (matcher.find()) {
            String expression = matcher.group();
            Expression exp = new ExpressionBuilder(expression).build();
            double result = exp.evaluate();
            matcher.appendReplacement(resultString, String.valueOf(result));
        }
        matcher.appendTail(resultString);
    }
}
