package com.company;

import java.util.Random;
import java.util.Scanner;

public class TicTac {
    // settings Game

    private static char[][] map;
    private static int[][] rate; // array of cell rating
    private static int SIZE = 3;

    private static final char DOT_EMPTY = '*';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        initRate();

        while (true) {
            humanTurn();
            if (isEndGame(DOT_X)) {
                break;
            }
            /*
            computerTurn();
            if (isEndGame(DOT_O)) {
                break;
            }
            */
            smartCompTurn();
            if (isEndGame(DOT_O)) {
                break;
            }

        }

        System.out.println("GAME OVER");
    }

    // preparing playing field

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void initRate() {
        rate = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rate[i][j] = 0;
            }
        }
    }

    // printing playing field

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // human turn

    private static void humanTurn() {
        int x, y;

        do {
            System.out.println("Введите координаты ячейки");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        } while(!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    // computer turn

    private static void computerTurn() {
        int x = -1;
        int y = -1;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));

        System.out.println("Компьютер выбрал ячейку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    // smart computer turn

    private static void smartCompTurn() {
        int y = 0;
        int x = 0;
        int maxRate = 0;


        for (int i = 0; i < SIZE; i++) {
            for (int j =0; j < SIZE; j++){
                if (rate[i][j] > maxRate) {
                    maxRate = rate[i][j];
                }
            }
        }

        if (maxRate != 0) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (rate[i][j] == maxRate) {
                        y = i;
                        x = j;
                        break;
                    }
                }
                if (rate[y][x] == maxRate) {
                    break;
                }
            }

            System.out.println("Компьютер выбрал ячейку " + (y + 1) + " " + (x + 1));
            map[y][x] = DOT_O;
        } else {
            computerTurn();
        }

        // recount rating

        rate[y][x] = 0;
        if (y == 0 && x == 0) {
            rate[y + 1][x + 1] = (map[y + 1][x + 1] == DOT_O || map[y + 1][x + 1] == DOT_X) ? 0 : rate[y + 1][x + 1] + 1;
            rate[y + 1][x] = (map[y + 1][x] == DOT_O || map[y + 1][x] == DOT_X) ? 0 : rate[y + 1][x] + 1;
            rate[y][x + 1] = (map[y][x + 1] == DOT_O || map[y][x + 1] == DOT_X) ? 0 : rate[y][x + 1] + 1;
        } else if (y == 0 && x > 0 && x < SIZE - 1) {
            rate[y][x + 1] = (map[y][x + 1] == DOT_O || map[y][x + 1] == DOT_X) ? 0 : rate[y][x + 1] + 1;
            rate[y + 1][x + 1] = (map[y + 1][x + 1] == DOT_O || map[y + 1][x + 1] == DOT_X) ? 0 : rate[y + 1][x + 1] + 1;
            rate[y + 1][x] = (map[y + 1][x] == DOT_O || map[y + 1][x] == DOT_X) ? 0 : rate[y + 1][x] + 1;
            rate[y + 1][x - 1] = (map[y + 1][x - 1] == DOT_O || map[y + 1][x - 1] == DOT_X) ? 0 : rate[y + 1][x - 1] + 1;
            rate[y][x - 1] = (map[y][x - 1] == DOT_O || map[y][x - 1] == DOT_X) ? 0 : rate[y][x - 1] + 1;
        } else if (y == 0 && x == SIZE - 1) {
            rate[y][x - 1] = (map[y][x - 1] == DOT_O || map[y][x - 1] == DOT_X) ? 0 : rate[y][x - 1] + 1;
            rate[y + 1][x - 1] = (map[y + 1][x - 1] == DOT_O || map[y + 1][x - 1] == DOT_X) ? 0 : rate[y + 1][x - 1] + 1;
            rate[y + 1][x] = (map[y + 1][x] == DOT_O || map[y + 1][x] == DOT_X) ? 0 : rate[y + 1][x] + 1;
        } else if (y > 0 && y < SIZE - 1 && x == 0) {
            rate[y - 1][x] = (map[y - 1][x] == DOT_O || map[y - 1][x] == DOT_X) ? 0 : rate[y - 1][x] + 1;
            rate[y - 1][x + 1] = (map[y - 1][x + 1] == DOT_O || map[y - 1][x + 1] == DOT_X) ? 0 : rate[y - 1][x + 1] + 1;
            rate[y][x + 1] = (map[y][x + 1] == DOT_O || map[y][x + 1] == DOT_X) ? 0 : rate[y][x + 1] + 1;
            rate[y + 1][x + 1] = (map[y + 1][x + 1] == DOT_O || map[y + 1][x + 1] == DOT_X) ? 0 : rate[y + 1][x + 1] + 1;
            rate[y + 1][x] = (map[y + 1][x] == DOT_O || map[y + 1][x] == DOT_X) ? 0 : rate[y + 1][x] + 1;
        } else if (y == SIZE - 1 && x == 0) {
            rate[y][x + 1] = (map[y][x + 1] == DOT_O || map[y][x + 1] == DOT_X) ? 0 : rate[y][x + 1] + 1;
            rate[y - 1][x + 1] = (map[y - 1][x + 1] == DOT_O || map[y - 1][x + 1] == DOT_X) ? 0 : rate[y - 1][x + 1] + 1;
            rate[y - 1][x] = (map[y - 1][x] == DOT_O || map[y - 1][x] == DOT_X) ? 0 : rate[y - 1][x] + 1;
        } else if (y == SIZE - 1 && x > 0 && x < SIZE - 1) {
            rate[y][x - 1] = (map[y][x - 1] == DOT_O || map[y][x - 1] == DOT_X) ? 0 : rate[y][x - 1] + 1;
            rate[y - 1][x - 1] = (map[y - 1][x - 1] == DOT_O || map[y - 1][x - 1] == DOT_X) ? 0 : rate[y - 1][x - 1] + 1;
            rate[y - 1][x] = (map[y - 1][x] == DOT_O || map[y - 1][x] == DOT_X) ? 0 : rate[y - 1][x] + 1;
            rate[y - 1][x + 1] = (map[y - 1][x + 1] == DOT_O || map[y - 1][x + 1] == DOT_X) ? 0 : rate[y - 1][x + 1] + 1;
            rate[y][x + 1] = (map[y][x + 1] == DOT_O || map[y][x + 1] == DOT_X) ? 0 : rate[y][x + 1] + 1;
        } else if (y == SIZE - 1 && x == SIZE - 1) {
            rate[y][x - 1] = (map[y][x - 1] == DOT_O || map[y][x - 1] == DOT_X) ? 0 : rate[y][x - 1] + 1;
            rate[y - 1][x - 1] = (map[y - 1][x - 1] == DOT_O || map[y - 1][x - 1] == DOT_X) ? 0 : rate[y - 1][x - 1] + 1;
            rate[y - 1][x] = (map[y - 1][x] == DOT_O || map[y - 1][x] == DOT_X) ? 0 : rate[y - 1][x] + 1;
        } else if (y > 0 && y < SIZE - 1 && x == SIZE - 1) {
            rate[y + 1][x] = (map[y + 1][x] == DOT_O || map[y + 1][x] == DOT_X) ? 0 : rate[y + 1][x] + 1;
            rate[y + 1][x - 1] = (map[y + 1][x - 1] == DOT_O || map[y + 1][x - 1] == DOT_X) ? 0 : rate[y + 1][x - 1] + 1;
            rate[y][x - 1] = (map[y][x - 1] == DOT_O || map[y][x - 1] == DOT_X) ? 0 : rate[y][x - 1] + 1;
            rate[y - 1][x - 1] = (map[y - 1][x - 1] == DOT_O || map[y - 1][x - 1] == DOT_X) ? 0 : rate[y - 1][x - 1] + 1;
            rate[y - 1][x] = (map[y - 1][x] == DOT_O || map[y - 1][x] == DOT_X) ? 0 : rate[y - 1][x] + 1;
        } else {
            rate[y - 1][x - 1] = (map[y - 1][x - 1] == DOT_O || map[y - 1][x - 1] == DOT_X) ? 0 : rate[y - 1][x - 1] + 1;
            rate[y - 1][x] = (map[y - 1][x] == DOT_O || map[y - 1][x] == DOT_X) ? 0 : rate[y - 1][x] + 1;
            rate[y - 1][x + 1] = (map[y - 1][x + 1] == DOT_O || map[y - 1][x + 1] == DOT_X) ? 0 : rate[y - 1][x + 1] + 1;
            rate[y][x + 1] = (map[y][x + 1] == DOT_O || map[y][x + 1] == DOT_X) ? 0 : rate[y][x + 1] + 1;
            rate[y + 1][x + 1] = (map[y + 1][x + 1] == DOT_O || map[y + 1][x + 1] == DOT_X) ? 0 : rate[y + 1][x + 1] + 1;
            rate[y + 1][x] = (map[y + 1][x] == DOT_O || map[y + 1][x] == DOT_X) ? 0 : rate[y + 1][x] + 1;
            rate[y + 1][x - 1] = (map[y + 1][x - 1] == DOT_O || map[y + 1][x - 1] == DOT_X) ? 0 : rate[y + 1][x - 1] + 1;
        }
    }

    // validation

    private static boolean isCellValid(int x, int y) {
        boolean result = true;

        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }

        if (map[y][x] != DOT_EMPTY) {
            result = false;
        }

        return result;
    }

    private static boolean isEndGame(char playerSymbol) {
        boolean result = false;

        printMap();

        if (checkWin(playerSymbol)) {
            System.out.println("Победили " + playerSymbol);
            result = true;
        }

        if (isMapFull()) {
            System.out.println("Ничья");
            result = true;
        }

        return result;
    }

    private static boolean isMapFull() {
        boolean result = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++ ) {
                if (map[i][j] == DOT_EMPTY)
                    result = false;
            }
        }

        return result;
    }

    private static boolean checkWin(char playerSymbol) {
        boolean result = false;

        if (
                (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)
        ) {
            result = true;
        }

        return result;
    }

}
