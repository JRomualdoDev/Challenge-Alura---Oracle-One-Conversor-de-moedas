package br.com.challenge.conversor.services;

import br.com.challenge.conversor.client.HttpClientService;
import br.com.challenge.conversor.utils.ConfigUtil;

import java.net.URI;
import java.net.http.HttpResponse;


public class ApiConversion {


    public HttpResponse<String> convertPair(String coinBase, String coinTarget)  {

        try {
            // get the api key from config.properties
            String apiKey = ConfigUtil.getApiKey();

            URI url = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + coinBase + "/" + coinTarget);


            HttpResponse<String> httpClientService = HttpClientService.httpConnection(url);

            return httpClientService;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
