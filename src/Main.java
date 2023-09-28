import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isFirstNegative = false, isSecondNegative = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first number: ");
        String first = scanner.nextLine();
        String[] firstNum = first.split("");
        if (firstNum[0].equals("-")){
            isFirstNegative = true;
            firstNum[0] = "0";
        }

        System.out.println("Enter operator: ");
        String operator = scanner.nextLine();

        System.out.println("Enter second number: ");
        String second = scanner.nextLine();
        String[] secondNum = second.split("");
        if (secondNum[0].equals("-")){
            isSecondNegative = true;
            secondNum[0] = "0";
        }

        int[] firstNumber = new int[firstNum.length];
        int[] secondNumber = new int[secondNum.length];
        try {
            for (int i = 0; i < firstNum.length; i++) firstNumber[i] = Integer.parseInt(firstNum[i]);
            for (int i = 0; i < secondNum.length; i++) secondNumber[i] = Integer.parseInt(secondNum[i]);
        }
        catch (NumberFormatException e){
            System.out.println("Error: Incorrect number!!!");
            return;
        }

        switch (operator) {

            case ("+"):
                countSum(firstNumber, secondNumber, isFirstNegative, isSecondNegative);
                break;

            case ("-"):
                countDiff(firstNumber, secondNumber, isFirstNegative, isSecondNegative);
                break;

            case ("*"):
                countProd(firstNumber, secondNumber, isFirstNegative, isSecondNegative);
                break;
            default:
                System.out.printf("Error, unknown operator: \"%s\"", operator);
        }
    }
    static void countSum(int[] firstNumber, int[] secondNumber, boolean isFirstNegative, boolean isSecondNegative){
        if (!isFirstNegative && !isSecondNegative) Sum(firstNumber, secondNumber, ' ');
        if (isFirstNegative && isSecondNegative) Sum(firstNumber, secondNumber, '-');
        if (!isFirstNegative && isSecondNegative) {
            if (isSecondNumberBigger(firstNumber, secondNumber))
                Diff(secondNumber, firstNumber, '-');
            else Diff(firstNumber, secondNumber, ' ');
        }
        if (isFirstNegative && !isSecondNegative){
            if (isSecondNumberBigger(firstNumber, secondNumber))
                Diff(secondNumber, firstNumber, ' ');
            else Diff(firstNumber, secondNumber, '-');
        }
    }
    static void countDiff(int[] firstNumber, int[] secondNumber, boolean isFirstNegative, boolean isSecondNegative){
        if (isFirstNegative && !isSecondNegative) Sum(firstNumber, secondNumber, '-');
        if (!isFirstNegative && isSecondNegative) Sum(firstNumber, secondNumber, ' ');
        if (!isFirstNegative && !isSecondNegative) {
            if (isSecondNumberBigger(firstNumber, secondNumber))
                Diff(secondNumber, firstNumber, '-');
            else Diff(firstNumber, secondNumber, ' ');
        }
        if (isFirstNegative && isSecondNegative){
            if (isSecondNumberBigger(firstNumber, secondNumber))
                Diff(secondNumber, firstNumber, ' ');
            else Diff(firstNumber, secondNumber, '-');
        }
    }
    static void countProd(int[] firstNumber, int[] secondNumber, boolean isFirstNegative, boolean isSecondNegative){
        if (isFirstNegative == isSecondNegative) Prod(firstNumber, secondNumber, ' ');
        else Prod(firstNumber, secondNumber, '-');
    }
    public static boolean isSecondNumberBigger(int[] firstNumber, int[] secondNumber){
        if (secondNumber.length > firstNumber.length) return true;
        if (secondNumber.length == firstNumber.length){
            for (int i = 0; i < firstNumber.length; i++) {
                if (secondNumber[i] > firstNumber[i]) return true;
                if (firstNumber[i] > secondNumber[i]) return false;
            }
        }
        return false;
    }

    static void Sum(int[] firstNumber, int[] secondNumber, char sign){
        int[] result = new int[Math.max(firstNumber.length, secondNumber.length) + 1];
        System.arraycopy(firstNumber, 0, result, result.length - firstNumber.length, firstNumber.length);
        for (int i = result.length - 1; i >= 0; i--){
            if (i - result.length + secondNumber.length >= 0) result[i] += secondNumber[i - result.length + secondNumber.length];
            if (result[i] > 9){
                result[i] = result[i] % 10;
                result[i - 1]++;
            }
        }
        printResult(result, sign);
    }
    static void Diff(int[] firstNumber, int[] secondNumber, char sign){
        for (int i = firstNumber.length - 1; i >= 0; i--){
            if (i - firstNumber.length + secondNumber.length >= 0) firstNumber[i] -= secondNumber[i - firstNumber.length + secondNumber.length];
            if (firstNumber[i] < 0){
                firstNumber[i] += 10;
                firstNumber[i - 1]--;
            }
        }
        printResult(firstNumber, sign);
    }

    static void Prod (int[] firstNumber, int[] secondNumber, char sign){
        int  num;
        int[] result = new int[firstNumber.length + secondNumber.length];
        for (int i = firstNumber.length - 1; i >= 0; i--){
            for (int j = secondNumber.length - 1; j >= 0; j--){
                num = result.length - secondNumber.length - firstNumber.length + j + i + 1;
                result[num] += firstNumber[i] * secondNumber[j];
                if (result[num] > 9){
                    result[num - 1] += result[num] / 10;
                    result[num] %= 10;
                }
            }
        }
        printResult(result, sign);
    }
    static void printResult(int[] result, char sign) {
        System.out.print("Answer is: ");
        int j = 0;
        while (result[j] == 0) {
            j++;
            if (j == result.length) {
                System.out.println(0);
                return;
            }
        }
        System.out.print(sign);
        for (int i = j; i < result.length; i++) System.out.print(result[i]);
    }
}
