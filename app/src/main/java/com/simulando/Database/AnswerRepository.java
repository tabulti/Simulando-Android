package com.simulando.Database;

import com.simulando.Models.Answer;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Luciano José on 16/03/2017.
 */

public class AnswerRepository {

    private Realm mRealm;
    private static AnswerRepository mInstance;

    public AnswerRepository() {
        mRealm = Realm.getDefaultInstance();
    }

    public static AnswerRepository getInstance() {
        if (mInstance == null) {
            mInstance = new AnswerRepository();
        }
        return mInstance;
    }

    public void addAnswer(Answer answer) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(answer);
        mRealm.commitTransaction();
    }

    public List<Answer> findAll() {
        RealmResults<Answer> answers = mRealm.where(Answer.class).findAll();
        return mRealm.copyFromRealm(answers);
    }

    public void deleteAll() {
        RealmResults<Answer> answers = mRealm.where(Answer.class).findAll();
        mRealm.beginTransaction();
        answers.deleteAllFromRealm();
        mRealm.commitTransaction();
    }


}
