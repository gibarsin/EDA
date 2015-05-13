package tp_1.ej9;

public class Main {
    private static String[] numAlphabet = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public static void main(String[] args) {
        char[] number = {'2','5','6'};

        runCombinations(number);
    }

    private static void runCombinations(char[] number) {
        char[] vec = new char[number.length];

        runCombinationsRec(number, 0, vec);
    }

    public static void runCombinationsRec(char[] number, int indexNumber, char[] vec) {
        if(indexNumber == number.length) {
            System.out.println(vec);
        } else {
            String alphabet = numAlphabet[number[indexNumber]-'0'];
            for(int i=0; i < alphabet.length(); i++) {
                vec[indexNumber] = alphabet.charAt(i);
                runCombinationsRec(number, indexNumber + 1, vec);
            }
        }
    }
}
