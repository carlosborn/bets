package Bet;

import com.bets.App.Controllers.CrashRoundController;
import com.bets.App.DTOs.crashRounds.CreateCrashRoundDTO;
import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Repositories.CrashRoundRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

@Controller
public class Watcher {

    private static final String URL = "https://blaze1.space/api/crash_games/current";
    private static ApplicationContext applicationContext = null;


    public static void watch(ApplicationContext applicationContext) {
        JSONObject json = sendGetRequest();
        if (json == null) {
            return;
        }

        Watcher.applicationContext = applicationContext;

        processResponse(json);
    }

    private static JSONObject sendGetRequest() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(URL))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonObject = new JSONObject(response.body());
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void processResponse(JSONObject json) {
        try {
            String id = json.getString("id");
            String status = json.getString("status");
            String crashPoint = json.get("crash_point").toString();
            String totalEurBet = json.get("total_eur_bet").toString();
            String totalBetsPlaced = json.get("total_bets_placed").toString();
            String totalEurWon = json.get("total_eur_won").toString();

            Double crashPointDouble = !crashPoint.equals("null") ? Double.valueOf(crashPoint) : null;
            Double totalEurBetDouble = !totalEurBet.equals("null") ? Double.valueOf(totalEurBet) : null;
            Double totalBetsPlacedDouble = !totalBetsPlaced.equals("null") ? Double.valueOf(totalBetsPlaced) : null;
            Double totalEurWonDouble = !totalEurWon.equals("null") ? Double.valueOf(totalEurWon) : null;

            CrashRoundController crashRoundController = applicationContext.getBean(CrashRoundController.class);
            CrashRoundModel crashRoundModel = crashRoundController.getCrashRoundByHash(id);
            if (crashRoundModel != null) {
                crashRoundModel.setUpdatedAt(new Date());
                crashRoundModel.setStatus(status);
                crashRoundModel.setTotalEurWon(totalEurWonDouble);
                crashRoundModel.setTotalEurBet(totalEurBetDouble);
                crashRoundModel.setTotalBetsPlaced(totalBetsPlacedDouble);
                crashRoundModel.setCrashPoint(crashPointDouble);
                crashRoundController.updateCrashRound(crashRoundModel);
                return;
            }

            CreateCrashRoundDTO dto = new CreateCrashRoundDTO(id, status, crashPointDouble, totalEurBetDouble, totalBetsPlacedDouble, totalEurWonDouble);
            crashRoundController.createCrashRound(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
