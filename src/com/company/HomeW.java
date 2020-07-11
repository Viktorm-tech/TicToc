package com.company;

import java.util.Random;
import java.util.Scanner;

public class HomeW {
    /*
    Р±Р»РѕРє РЅР°СЃС‚СЂРѕРµРє РёРіСЂС‹
    */
    private static char[][] map; // РјР°С‚СЂРёС†Р° РїРѕР»СЏ
    private static int SIZE = 3; // СЂР°Р·РјРµСЂРЅРѕСЃС‚СЊ РїРѕР»СЏ

    private static final char DOT_EMPTY = '*'; // СЃРІРѕР±РѕРґРЅРѕРµ РїРѕР»Рµ
    private static final char DOT_X = 'X'; // РєСЂРµСЃС‚РёРє
    private static final char DOT_O = 'O'; // РЅРѕР»РёРє

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    //private static final boolean SILLY_MODE = false;

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn(); //С…РѕРґ С‡РµР»РѕРІРµРєР°
            if (isEndGame(DOT_X)) {
                break;
            }

            computerTurn(); //С…РѕРґ РєРѕРјРїСЊСЋС‚РµСЂР°
            if (isEndGame(DOT_O)) {
                break;
            }
        }
        System.out.println("Game over");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

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

    /**
     * РҐРѕРґ С‡РµР»РѕРІРµРєР°
     */

    private static void humanTurn() {
        int x, y;

        do {
            System.out.println("Р’РІРµРґРёС‚Рµ РєРѕРѕСЂРґРёРЅР°С‚С‹ С‡РµСЂРµР· РїСЂРѕР±РµР»");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    /**
     * РҐРѕРґ РєРѕРјРїСЊСЋС‚РµСЂР°
     */
    private static void computerTurn() {
        int x = -1;
        int y = -1;

        //if (SILLY_MODE) {
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        /*else {
            for (int i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    // РЅР°РїСЂР°РІР»РµРЅРёРµ
                }*/
        System.out.println();
        System.out.println("РљРѕРјРїСЊСЋС‚РµСЂ РІС‹Р±СЂР°Р» СЏС‡РµР№РєСѓ " + (y + 1) + " " + (x + 1));
        map[y][x] = DOT_O;
    }

    /**
     * РњРµС‚РѕРґ РІР°Р»РёРґР°С†РёРё Р·Р°РїСЂР°С€РёР°РµРјРѕР№ СЏС‡РµР№РєРё РЅР° РєРѕСЂСЂРµРєС‚РЅРѕСЃС‚СЊ
     *
     * @param x - РєРѕРѕСЂРґРёРЅР°С‚Р° РїРѕ РіРѕСЂРёР·РѕРЅС‚Р°Р»Рё
     * @param y - РєРѕРѕСЂРґРёРЅР°С‚Р° РїРѕ РІРµСЂС‚РёРєР°Р»Рё
     * @return boolean  - РїСЂРёР·РЅР°Рє РІР°Р»РёРґРЅРѕСЃС‚Рё
     */

    private static boolean isCellValid(int x, int y) {
        boolean result = true;
        //РїСЂРѕРІРµСЂРєР° РєРѕРѕСЂРґРёРЅР°С‚С‹
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }
        //РїСЂРѕРІРµСЂРєР° Р·Р°РїРѕР»РЅРµРЅРѕСЃС‚Рё СЏС‡РµР№РєРё
        if (map[x][y] != DOT_EMPTY) {
            result = false;
        }

        return result;
    }

    /**
     * РњРµС‚РѕРґ РїСЂРѕРІРµСЂРєРё РёРіСЂС‹ РЅР° Р·Р°РІРµСЂС€РµРЅРёРµ
     *
     * @param playerSymbol СЃРёРјРІРѕР», РєРѕС‚РѕСЂС‹Рј РёРіСЂР°РµС‚ С‚РµРєСѓС‰РёР№ РёРіСЂРѕРє
     * @return boolean - РїСЂРёР·РЅР°Рє Р·Р°РІРµСЂС€РµРЅРёСЏ РёРіСЂС‹
     */
    private static boolean isEndGame(char playerSymbol) {
        boolean result = false;

        printMap();
        // РїСЂРѕРІРµСЂСЏРµРј РЅРµРѕР±С…РѕРґРёРјРѕСЃС‚СЊ СЃР»РµРґСѓСЋС‰РµРіРѕ С…РѕРґР° Рё СЃРїСЂР°С€РёРІР°РµРј РѕР± РѕРєРѕРЅС‡Р°РЅРёРё РёРіСЂС‹
        if (checkWin(playerSymbol)) {
            System.out.println("РїРѕР±РµРґРёР»Рё " + playerSymbol);
            String re;
            System.out.print("РҐРѕС‚РёС‚Рµ РїСЂРѕРґРѕР»Р¶РёС‚СЊ РёРіСЂСѓ? ");
            re = scanner.next();
            if (re.equals("Y")) {
                System.out.println();
                initMap();

            }
            else if (re.equals("N")) {
                result = true;
            }
        }
        if (isMapFull()) {
            System.out.println("РќРёС‡СЊСЏ");
            String re;
            System.out.print("РҐРѕС‚РёС‚Рµ РїСЂРѕРґРѕР»Р¶РёС‚СЊ РёРіСЂСѓ? ");
            re = scanner.next();
            if (re.equals("Y")) {
                System.out.println();
                initMap();

            }
            else if (re.equals("N")) {
                result = true;
            }
        }
        return result;
    }


    /**
     * РџСЂРѕРІРµСЂРєР° РЅР° 100% -СЋ Р·Р°РїРѕР»РЅРµРЅРЅРѕСЃС‚СЊ РїРѕР»СЏ
     *
     * @return boolean РїСЂРёР·РЅР°Рє РѕС‚РїС‚РёРјР°Р»СЊРЅРѕСЃС‚Рё
     */

    private static boolean isMapFull() {
        boolean result = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    result = false;
                }
            }
        }
        return result;

    }


    /**
     * @param playerSymbol - РёСЃРјРІРѕР», РІРІРµРґРµРЅРЅС‹Р№ РїРѕР»СЊР·РѕРІР°С‚РµР»РµРј
     * @return boolean - СЂРµР·СѓР»СЊС‚Р°С‚ РїСЂРѕРІРµСЂРєРё
     */
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
                        (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)) {
            result = true;
        }
        return result;
    }
}