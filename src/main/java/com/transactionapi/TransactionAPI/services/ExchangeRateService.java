package com.transactionapi.TransactionAPI.services;

import com.transactionapi.TransactionAPI.entities.ExchangeRateKzt;
import com.transactionapi.TransactionAPI.entities.ExchangeRateRub;
import com.transactionapi.TransactionAPI.repos.ExchangeRateKzRepo;
import com.transactionapi.TransactionAPI.repos.ExchangeRateRuRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateKzRepo exchangeRateKzRepo;

    @Autowired
    private ExchangeRateRuRepo exchangeRateRuRepo;

    private String data = null;
    private URL url = null;


    public ExchangeRateKzt getExchangeRateKZT() throws IOException {
        ExchangeRateKzt latest = exchangeRateKzRepo.findTopByOrderByIdDesc();
        //86.4M Milliseconds is one day we check if it has been 24 hours since last exchange rate check
        if(latest == null || System.currentTimeMillis() - latest.getDate().getTime() > 86_400_000){
            String urlString = "https://api.twelvedata.com/quote?symbol=USD/KZT&apikey=64d14e2d15374b1aa25506203a906377";
            getData(urlString);
            ExchangeRateKzt exchangeRate = new ExchangeRateKzt();
            exchangeRate.setRate(Double.parseDouble(data));
            exchangeRate.setDate(new Timestamp(System.currentTimeMillis()));
            return exchangeRateKzRepo.save(exchangeRate);
        }
        return latest;
    }

    public ExchangeRateRub getExchangeRateRub() throws IOException {
        ExchangeRateRub latest = exchangeRateRuRepo.findTopByOrderByIdDesc();
        //86.4M Milliseconds is one day we check if it has been 24 hours since last exchange rate check
        if(latest == null || System.currentTimeMillis() - latest.getDate().getTime() > 86_400_000){
            String urlString = "https://api.twelvedata.com/quote?symbol=USD/RUB&apikey=64d14e2d15374b1aa25506203a906377";
            getData(urlString);
            ExchangeRateRub exchangeRate = new ExchangeRateRub();
            exchangeRate.setRate(Double.parseDouble(data));
            exchangeRate.setDate(new Timestamp(System.currentTimeMillis()));
            return exchangeRateRuRepo.save(exchangeRate);
        }
        return latest;
    }

    private void getData(String urlString) throws IOException{
        StringBuilder responseData = new StringBuilder();
        JSONObject jsonObject = null;
        url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        try{ BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                responseData.append(line);
            }
            jsonObject = new JSONObject(responseData.toString());

            data = jsonObject.get("close").toString();
            if(data == null){
                data = jsonObject.get("previous_close").toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(con != null){
                con.disconnect();
            }
        }
    }


}
