package com.payconiq.mohamedassignment.data.local;

import com.payconiq.mohamedassignment.App;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class LocalRepository {
    Realm realm;

    @Inject
    public LocalRepository() {
        Realm.init(App.getContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        // Create a new empty instance of Realm
        realm = Realm.getInstance(realmConfiguration);
    }
    public void saveRepos(ArrayList<Repo> repoItems){
        realm.beginTransaction();
        Collection<Repo> storedItems = realm.copyToRealmOrUpdate(repoItems);
        realm.commitTransaction();
    }

    public RealmResults<Repo> loadRepos(){
        // Pull all the repos from the realm
        RealmResults<Repo> reposList = realm.where(Repo.class).findAll();
        return  reposList;
    }
}
