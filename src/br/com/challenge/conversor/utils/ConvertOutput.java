package br.com.challenge.conversor.utils;

import br.com.challenge.conversor.models.PairConvertion;
import com.google.gson.Gson;

import java.net.http.HttpResponse;

public final class ConvertOutput {

    private ConvertOutput() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static PairConvertion stringToObject(HttpResponse<String> response) {

        Gson gson = new Gson();
        PairConvertion pairConvertion = gson.fromJson(response.body(), PairConvertion.class);

        return pairConvertion;
    }

}
