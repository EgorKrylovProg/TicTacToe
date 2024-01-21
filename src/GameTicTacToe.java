
import java.util.Scanner;

public class GameTicTacToe {
    static final char O = 'O';
    static final char X = 'X';
    static int numCell = -1;
    static int draw = 0;
    static char[][] gameField = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static void printField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + gameField[i][j] + "]");
            }
            System.out.println();
        }
    }

    static void move(char symbol) {
        int i = 0, j = numCell - 1;
        switch (numCell) {
            case 4, 5, 6 -> {
                i = 1;
                j = numCell - 4;
            }
            case 7, 8, 9 -> {
                i = 2;
                j = numCell - 7;
            }
            default -> {
                System.out.println("Такой клетки нет!");
                numCell = -1;
                return;
            }
        }

        if (gameField[i][j] == ' ') {
            gameField[i][j] = symbol;
            draw++;
        }
        else {
            System.out.println("Эта клетка уже занята! Выберите другую.");
            numCell = -1;
        }
    }

    static boolean checkWin(){
        char currentSymbol;
        int numSymbol = 1;
        for (int i = 0; i < 3; i++) { // проверка комбинации слева направо
            currentSymbol = gameField[i][0];
            for (int j = 1; j < 3; j++) {
                if (currentSymbol == gameField[i][j] && gameField[i][j] != ' '){
                    numSymbol++;
                }
                else {
                    numSymbol = 1;
                    break;
                }
            }

            if (numSymbol == 3) {
                printField();
                System.out.printf("Побеждает игрок, который играл за %s!", currentSymbol == X ? "крестик" : "нолик");
                draw = -1;
                return false;
            }
        }

        for (int i = 1; i < 3; i++) { // проверка комбинации по ДИАГОНАЛИ слева на права
            currentSymbol = gameField[0][0];
            if (currentSymbol == gameField[i][i] && gameField[i][i] != ' '){
                numSymbol++;
            }
            else {
                numSymbol = 1;
                break;
            }

            if (numSymbol == 3) {
                printField();
                System.out.printf("Побеждает игрок, который играл за %s!", currentSymbol == X ? "крестик" : "нолик");
                draw = -1;
                return false;
            }
        }

        currentSymbol = gameField[0][2];
        int j = 2;
        for (int i = 1; i < 3; i++) {  // проверка комбинации по ДИАГОНАЛИ справа налево
            j--;
            while (j >= 0) {
                if (currentSymbol == gameField[i][j] && gameField[i][j] != ' '){
                    numSymbol++;
                }
                else {
                    numSymbol = 1;
                    break;
                }

                if (numSymbol == 3) {
                    printField();
                    System.out.printf("Побеждает игрок, который играл за %s!", currentSymbol == X ? "крестик" : "нолик");
                    draw = -1;
                    return false;
                }
                break;
            }
        }

        for (int i = 0; i < 3; i++) { // проверка комбинации по вертикали
            currentSymbol = gameField[0][i];
            for (j = 1; j < 3; j++) {
                if (currentSymbol == gameField[j][i] && gameField[j][i] != ' '){
                    numSymbol++;
                }
                else {
                    numSymbol = 1;
                    break;
                }

                if (numSymbol == 3) {
                    printField();
                    System.out.printf("Побеждает игрок, который играл за %s!", currentSymbol == X ? "крестик" : "нолик");
                    draw = -1;
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру крестики-нолики!\n");
        System.out.println("""
                Вот как взаимодействовать с игрой:
                Для того чтобы поставть O или X надо написать номер ячейки.
                """);
        System.out.println("""
                Как пронумерованы ячейки:
                [1][2][3]
                [4][5][6]
                [7][8][9]
                """);
        System.out.println("По умолчанию игра начинается с крестика, если хотите начать с нолика введите 'O'.");
        char symbol = X;
        try {
            String enter = scanner.nextLine().toUpperCase();
            if (enter.charAt(0) == O) {
                symbol = enter.charAt(0);

                System.out.println("Отлично, нолик ходит первым!");
                System.out.println("Начнем игру!\n");
            }
            else {
                System.out.println("Крестик будет первым!");
                System.out.println("Начнем игру!");
                System.out.println();
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("Крестик будет первым!");
            System.out.println("Начнем игру!");
            System.out.println();
        }

        boolean flag = true;
        while (flag && draw < 9) {
            System.out.printf("Сейчас ходит: %s\n", symbol == O ? "Нолик" : "Крестик");
            printField();

            System.out.print("Ваш выбор: ");
            try {
                numCell = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Это что-то не то:)");
                continue;
            }
            System.out.println();
            move(symbol);

            if (numCell == -1) {
                continue;
            }

            flag = checkWin();
            numCell = -1;

            symbol = symbol == O ? X : O;
        }
        if (draw == 9) {
            printField();
            System.out.println("Ничья! Стоит сыграть еще раз!)");
        }
    }
}
