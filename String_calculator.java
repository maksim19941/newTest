package Птицефабрика;

import java.util.*;

import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

//Калькулятор умеет выполнять операции сложения строк
// вычитания строки из строки,
// умножения строки на число и
// деления строки на число: "a" + "b", "a" - "b", "a" * b, "a" / b.

    public class String_calculator {


        public static void main(String[] args) throws Exception {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите строку не более 10 символов, символ и число от 1 до 10");
            String inputData = scanner.nextLine();
            System.out.println(sort(inputData));

        }


        public static String sort(String inputData) throws Exception {

            try {
                String[] mass = inputData.split("[+\\-*/]");
                String str1 = mass[0].trim();
                String str2 = mass[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new Exception("Вы ввели запрещенный символ");
            }

            String[] mass = inputData.split("[+\\-*/]");
            String str1 = mass[0].trim();
            String str2 = mass[1].trim();
            String symbol = detectSymbol(inputData);

            if (str1.matches("^[0-9]*$")) {
                throw new Exception("Выражение должно начинаться с строки");
            }

            if (str1.length() > 10 || str2.length() > 10) {
                throw new Exception("Длина строки больше 10 символов");
            }


            if (symbol.equals("/")) {
                return MultiplicationOrDivision.division(str1, str2);
            } else if ((symbol.equals("*"))) {
                return MultiplicationOrDivision.multiplication(str1, str2);
            } else if (symbol.equals("+")) {
                return PlusOrMinus.plus(str1, str2);
            } else if (symbol.equals("-")) {
                return PlusOrMinus.minus(str1, str2);
            }
            return null;
        }

        //    Получаю знак
        public static String detectSymbol(String mass) throws Exception {
            if (mass.contains("+")) return "+";
            else if (mass.contains("-")) return "-";
            else if (mass.contains("/")) return "/";
            else if (mass.contains("*")) return "*";
            else {
                throw new Exception("Введен не верный знак");
            }
        }


        //    Возвращает результат сложения + или вычитания -
        class PlusOrMinus {
            public static String plus(String str1, String str2) {

                String re1 = str1.replace("\"", "");
                String re2 = str2.replace("\"", "");
                String result = "\"" + re1.trim() + "" + re2.trim() + "\"";
                if (result.length() > 40) {
                    return result.substring(0, 40) + "..." + "\"";
                } else return result;


            }



            public static String minus(String str1, String str2) throws Exception {

              if (str2.contains("1")
                      || str2.contains("2")
                      || str2.contains("3")
                      || str2.contains("4")
                      || str2.contains("5")
                      || str2.contains("6")
                      || str2.contains("7")
                      || str2.contains("8")
                      || str2.contains("9")
                      || str2.contains("0")) {
                  throw new Exception ("При вычитании должны быть только строки");
              }
                    String re1 = str1.replace("\"", "");
                    String re2 = str2.replace("\"", "");
                    String result = re1.replace(re2, "");
                    return result;

            }
        }

        //    Возвращает результат умножения * или деления/
        class MultiplicationOrDivision {
            public static String multiplication(String str1, String str2) throws Exception {

                String re1 = str1.replace("\"", "");

                int number = Integer.parseInt(str2);
                if (number < 0 || number > 10) {
                    throw new Exception("Число должно быть от 1 до 10");
                }
                String result = re1.repeat(number);
                if (result.length() > 40) {
                    return result.substring(0, 40) + "..." + "\"";
                }
                return "\"" + result + "\"";
            }


        public static String division(String str1, String str2) throws Exception {

            String re1 = str1.replace("\"", "");

            int number = Integer.parseInt(str2);
            if (number <= 0 || number > 10) {
                throw new Exception("Число должно быть от 1 до 10");
            }
            int sumb = re1.length() / number;
            String result = re1.substring(0, sumb);
            return "\"" + result + "\"";
        }
    }
}







