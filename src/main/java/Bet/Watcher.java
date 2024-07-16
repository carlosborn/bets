package Bet;

import com.bets.App.Controllers.CrashBetController;
import com.bets.App.Controllers.CrashRoundController;
import com.bets.App.Controllers.UserController;
import com.bets.App.DTOs.crashBets.CreateCrashBetDTO;
import com.bets.App.DTOs.crashRounds.CreateCrashRoundDTO;
import com.bets.App.DTOs.users.CreateUserDTO;
import com.bets.App.Models.CrashBetModel;
import com.bets.App.Models.CrashRoundModel;
import com.bets.App.Models.UserModel;
import com.bets.App.Repositories.CrashRoundRepository;
import org.json.JSONArray;
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

            // Update the CrashRoundModel
            if (crashRoundModel != null) {
                crashRoundModel.setUpdatedAt(new Date());
                crashRoundModel.setStatus(status);
                crashRoundModel.setTotalEurWon(totalEurWonDouble);
                crashRoundModel.setTotalEurBet(totalEurBetDouble);
                crashRoundModel.setTotalBetsPlaced(totalBetsPlacedDouble);
                crashRoundModel.setCrashPoint(crashPointDouble);
                crashRoundController.updateCrashRound(crashRoundModel);
            } else {
                // Create new if does not exists
                CreateCrashRoundDTO dto = new CreateCrashRoundDTO(id, status, crashPointDouble, totalEurBetDouble, totalBetsPlacedDouble, totalEurWonDouble);
                crashRoundModel = crashRoundController.createCrashRound(dto);
            }

            saveBets(crashRoundModel, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveBets(CrashRoundModel crashRoundModel, JSONObject json) {
        try {
            CrashBetController crashBetController = applicationContext.getBean(CrashBetController.class);
            JSONArray bets = json.getJSONArray("bets");
            for (Object bet : bets) {
                JSONObject jsonBet = (JSONObject) bet;


                String id = jsonBet.getString("id");
                Double cashedAt = !jsonBet.get("cashed_out_at").toString().equals("null") ? jsonBet.getDouble("cashed_out_at") : null;
                Double amount = jsonBet.getDouble("amount");
                String currencyType = jsonBet.getString("currency_type");
                Double autoCashoutAt = !jsonBet.get("auto_cashout_at").toString().equals("null") ? jsonBet.getDouble("auto_cashout_at") : null;
                Double winAmount = !jsonBet.get("win_amount").toString().equals("null") ? jsonBet.getDouble("win_amount") : null;
                String status = jsonBet.getString("status");

                CrashBetModel crashBetModel = crashBetController.findCrashBetByHash(id);
                // Update CrashBetModel
                if (crashBetModel != null) {
                    crashBetModel.setCashedOutAt(cashedAt);
                    crashBetModel.setWinAmount(winAmount);

                    crashBetController.update(crashBetModel);
                    continue;
                }

                // Create or update user
                UserModel userModel = createOrUpdateUser(jsonBet);

                // Create new bet
                CreateCrashBetDTO createCrashBetDTO = new CreateCrashBetDTO(
                        id, cashedAt, amount, currencyType, autoCashoutAt, winAmount, status, userModel, crashRoundModel
                );

                crashBetController.createCrashBet(createCrashBetDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static UserModel createOrUpdateUser(JSONObject json) {
        JSONObject jsonUser = json.getJSONObject("user");

        Long id = jsonUser.getLong("id");
        String idStr = jsonUser.getString("id_str");
        String username = jsonUser.getString("username");
        String rank = jsonUser.getString("rank");

        UserController userController = applicationContext.getBean(UserController.class);

        UserModel userModel = userController.findUserByIdStr(idStr);
        if(userModel != null){
            userModel.setRank(rank);
            return userController.update(userModel);
        }

        CreateUserDTO createUserDTO = new CreateUserDTO(id, idStr, username, rank);
        return userController.createUser(createUserDTO);
    }

}
