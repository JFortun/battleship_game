package com.fortun.httpClient;

import com.fortun.model.dto.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static final String PROTOCOL = "http://";
    public static final String HOST_PING = "localhost:8081/";
    public static final String HOST_BS = "localhost:8080/";
    public static final String SERVICE_PING = "battleship-computer-service/";
    public static final String SERVICE_BS = "battleship-service/";

    static Gson gson = new GsonBuilder().create();

    public static String ping() throws IOException {
        String uri = PROTOCOL + HOST_PING + SERVICE_PING + "api/engineering/ping";
        URL url = new URL(uri);
        StringBuilder text = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
        BufferedReader buff = new BufferedReader(in);
        String st = buff.readLine();
        while (st != null) {
            text.append(st);
            st = buff.readLine();
        }
        String data = text.toString();
        return data;
    }

    public static String gameInit(String name) throws IOException {
        GameInitDTO gameInitDTO = new GameInitDTO();
        gameInitDTO.setPlayerId(name);
        gameInitDTO.setVsComputer(true);

        String inputJson = gson.toJson(gameInitDTO);
        String uri = PROTOCOL + HOST_BS + SERVICE_BS + "api/games/new";

        String outputJson = doPostRequest(uri, inputJson);

        GameIdDTO gameIdDTO = gson.fromJson(outputJson, GameIdDTO.class);

        return gameIdDTO.getId();
    }

    public static void deployShips(String gameId, DeployShipsDTO deployShipsDTO) throws IOException {
        String inputJson = gson.toJson(deployShipsDTO, DeployShipsDTO.class);
        String uri = PROTOCOL + HOST_BS + SERVICE_BS + "api/games/" + gameId + "/fields/ships/deploy";

        doPostRequest(uri, inputJson);
    }

    public static FireODTO fire(String gameId, FireIDTO fireIDTO) throws IOException {
        String inputJson = gson.toJson(fireIDTO, FireIDTO.class);
        String uri = PROTOCOL + HOST_BS + SERVICE_BS + "api/games/" + gameId + "/fields/fire";

        String outputJson = doPostRequest(uri, inputJson);

        FireODTO fireODTO = gson.fromJson(outputJson, FireODTO.class);

        return fireODTO;
    }

    private static String doPostRequest(String uri, String inputJson) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        OutputStream os = conn.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(inputJson);
        osw.close();
        os.close();
        conn.connect();

        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int st = bis.read();
        while (st != -1) {
            buf.write((byte) st);
            st = bis.read();
        }
        String result = buf.toString();
        return result;
    }
}
