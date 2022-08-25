package com.fortun;

import com.fortun.game.GameLogic;
import com.fortun.httpClient.HttpClient;
import com.fortun.model.dto.DeployShipsDTO;

import java.io.IOException;

public class BattleshipGame {

    public static void main(String[] args) throws IOException {

        System.out.println("\n--- Welcome to the Battleship Game ---");
        String playerName = GameLogic.askPlayerName();
        System.out.println(HttpClient.ping());
        String[][] gameBoard = GameLogic.initializeBoard();
        GameLogic.setupPlayerGameBoard(gameBoard, playerName);
        String gameId = HttpClient.gameInit(playerName);
        HttpClient.deployShips(gameId, new DeployShipsDTO(playerName, GameLogic.shipsToDeploy));
        GameLogic.gameLoop(gameId, gameBoard, playerName);
    }

}
