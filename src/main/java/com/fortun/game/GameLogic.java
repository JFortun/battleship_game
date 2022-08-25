package com.fortun.game;

import com.fortun.httpClient.HttpClient;
import com.fortun.model.dto.FireIDTO;
import com.fortun.model.dto.FireODTO;
import com.fortun.model.dto.ShipDTO;
import com.fortun.model.enums.Cell;
import com.fortun.model.enums.CoordinateX;
import com.fortun.model.enums.Ship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLogic {

    public static final int ROW_SIZE = 11;
    public static final int COLUMN_SIZE = 11;

    public static List<ShipDTO> shipsToDeploy = new ArrayList<ShipDTO>();

    public static String askPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player name: \n");
        return scanner.nextLine();
    }

    public static void gameLoop(String gameId, String[][] playerGameBoard, String playerName) throws IOException {
        String[][] computerGameBoard = initializeBoard();
        printGameBoard(computerGameBoard, playerName);
        printGameBoard(computerGameBoard, "Computer");

        boolean isGameFinished = false;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter a cell to fire: ");
            String cell = scanner.nextLine();
            int[] coordinatesXY = getCoordinatesFromCell(cell);
            FireODTO fireODTO = HttpClient.fire(gameId, new FireIDTO(playerName, cell));
            if (Cell.HIT.getState().equals(fireODTO.getFireOutcome())) {
                computerGameBoard[coordinatesXY[0]][coordinatesXY[1]] = Cell.SHIP.getSymbol();
                System.out.print("Your shot hit the target!\n");
            } else if (Cell.MISSED.getState().equals(fireODTO.getFireOutcome())) {
                computerGameBoard[coordinatesXY[0]][coordinatesXY[1]] = Cell.MISSED.getSymbol();
                System.out.print("Your shot has missed...\n");
            } else if (Cell.SUNKEN_SHIP.getState().equals(fireODTO.getFireOutcome())) {
                computerGameBoard[coordinatesXY[0]][coordinatesXY[1]] = Cell.SUNKEN_SHIP.getSymbol();
                System.out.print("Your shot has sunk a ship!\n");
            }
            printGameBoard(computerGameBoard, "Computer");
        } while (!isGameFinished);
        System.out.println("\n--- You won! Thanks for playing the Battleship Game ---");
    }

    public static String[][] setupPlayerGameBoard(String[][] gameBoard, String playerName) {
        deployShip(gameBoard, Ship.Destroyer);
        printGameBoard(gameBoard, playerName);
        deployShip(gameBoard, Ship.Submarine);
        printGameBoard(gameBoard, playerName);
        deployShip(gameBoard, Ship.Cruiser);
        printGameBoard(gameBoard, playerName);
        deployShip(gameBoard, Ship.Battleship);
        printGameBoard(gameBoard, playerName);
        deployShip(gameBoard, Ship.AircraftCarrier);
        printGameBoard(gameBoard, playerName);

        return gameBoard;
    }

    public static String[][] deployShip(String[][] gameBoard, Ship ship) {
        Scanner scanner = new Scanner(System.in);
        List<int[]> coordinatesList = new ArrayList<int[]>();
        String[] cellList = new String[ship.getSize()];
        int count = 1;
        do {
            System.out.print("Enter the " + count + "º cell for your " + ship.getName() + ": ");
            String cell = scanner.nextLine();
            int[] coordinates = getCoordinatesFromCell(cell);
            coordinatesList.add(coordinates);
            cellList[count - 1] = cell;
            count++;
        } while (count < (ship.getSize() + 1));

        for (int[] coordinate : coordinatesList) {
            gameBoard[coordinate[0]][coordinate[1]] = Cell.SHIP.getSymbol();
        }

        shipsToDeploy.add(new ShipDTO(ship.getName(), cellList));

        return gameBoard;
    }

    public static String[][] initializeBoard() {
        String[][] gameBoard = new String[ROW_SIZE][COLUMN_SIZE];

        gameBoard[0][0] = "[ º ]";
        gameBoard[1][0] = " [1]  ";
        gameBoard[2][0] = " [2]  ";
        gameBoard[3][0] = " [3]  ";
        gameBoard[4][0] = " [4]  ";
        gameBoard[5][0] = " [5]  ";
        gameBoard[6][0] = " [6]  ";
        gameBoard[7][0] = " [7]  ";
        gameBoard[8][0] = " [8]  ";
        gameBoard[9][0] = " [9]  ";
        gameBoard[10][0] = " [10] ";
        gameBoard[0][1] = "[A]";
        gameBoard[0][2] = "[B]";
        gameBoard[0][3] = "[C]";
        gameBoard[0][4] = "[D]";
        gameBoard[0][5] = "[E]";
        gameBoard[0][6] = "[F]";
        gameBoard[0][7] = "[G]";
        gameBoard[0][8] = "[H]";
        gameBoard[0][9] = "[I]";
        gameBoard[0][10] = "[J]";

        for (int i = 1; i < ROW_SIZE; ++i) {
            for (int j = 1; j < COLUMN_SIZE; ++j) {
                gameBoard[i][j] = Cell.UNKNOWN.getSymbol();
            }
        }
        return gameBoard;
    }

    public static void printGameBoard(String[][] gameBoard, String playerName) {
        System.out.println("_____________________________________________");
        System.out.println(playerName + "'s game board:");
        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < ROW_SIZE; ++j) {
                if (j != 10) {
                    System.out.print(gameBoard[i][j] + " ");
                } else {
                    System.out.println(gameBoard[i][j]);
                }
            }
        }
    }

    public static int[] getCoordinatesFromCell(String cell) {
        int[] coordinatesXY = new int[2];
        int coordinateX = CoordinateX.getPositionFromXCoordinate(String.valueOf(cell.charAt(0)));
        int coordinateY = Integer.parseInt(String.valueOf(cell.charAt(1)));
        coordinatesXY[0] = coordinateY;
        coordinatesXY[1] = coordinateX;

        return coordinatesXY;
    }

    public static void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
