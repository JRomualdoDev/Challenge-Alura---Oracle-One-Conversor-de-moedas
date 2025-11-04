package br.com.challenge.conversor.utils;

import br.com.challenge.conversor.models.PairConversion;
import com.google.gson.Gson;

import java.net.http.HttpResponse;

public final class ConvertOutput {

    private ConvertOutput() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static PairConversion stringToObject(HttpResponse<String> response) {

        Gson gson = new Gson();
        PairConversion pairConvertion = gson.fromJson(response.body(), PairConversion.class);

        return pairConvertion;
    }

}
