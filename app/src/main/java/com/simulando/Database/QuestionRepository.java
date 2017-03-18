package com.simulando.Database;

import com.simulando.Models.Question;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Luciano José on 16/03/2017.
 */

public class QuestionRepository {

    private Random mRandom;
    private Realm mRealm;
    private static QuestionRepository mInstance;

    public QuestionRepository() {
        mRandom = new Random();
        mRealm = Realm.getDefaultInstance();
    }

    public static QuestionRepository getInstance() {
        if (mInstance == null) {
            mInstance = new QuestionRepository();
        }
        return mInstance;
    }


    public void addQuestion(Question question) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(question);
        mRealm.commitTransaction();
    }

    public Question findRandomQuestion() {
        RealmResults<Question> questions = mRealm.where(Question.class).findAll();
        Question question = questions.get(mRandom.nextInt(questions.size()));
        return question;
    }

}
