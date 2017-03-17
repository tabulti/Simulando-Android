package com.simulando.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.simulando.API.Answer.AnswerService;
import com.simulando.Database.AnswerRepository;
import com.simulando.Interfaces.Callback;
import com.simulando.Models.Answer;
import com.simulando.Models.BulkAnswers;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Luciano José on 16/03/2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    private AnswerService mAnswerService;
    private AnswerRepository mAnswerRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean hasConnection = NetworkUtils.isNetworkAvailable(context);
        String connectionType = NetworkUtils.getConnectionType(context);

        if (hasConnection) {
            if (connectionType.equals("WIFI")) {
                mAnswerService = AnswerService.getInstance(context);
                mAnswerRepository = AnswerRepository.getInstance();

                List<Answer> answers = mAnswerRepository.findAll();
                if (answers.size() > 0) {
                    BulkAnswers bulkAnswers = new BulkAnswers(answers);
                    mAnswerService.registerBulkAnswers(bulkAnswers, new Callback() {
                        @Override
                        public void onSuccess(Object response) {
                            mAnswerRepository.deleteAll();
                        }

                        @Override
                        public void onError(String message) {
                        }
                    });
                }
            }
        }
    }
}
