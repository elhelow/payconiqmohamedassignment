package com.payconiq.mohamedassignment.data;


import com.payconiq.mohamedassignment.data.local.LocalRepository;
import com.payconiq.mohamedassignment.data.remote.ApiRepository;
import com.payconiq.mohamedassignment.data.remote.ServiceResponse;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class DataRepository {
    private ApiRepository apiRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(ApiRepository apiRepository, LocalRepository localRepository) {
        this.apiRepository = apiRepository;
        this.localRepository = localRepository;
    }

    public ServiceResponse requestData(String pageNumber) {
        return apiRepository.getData(pageNumber);
    }

    public RealmResults<Repo> requestLocalData() {
        return localRepository.loadRepos();
    }
    public void saveLocalData(ArrayList<Repo> repsList) {
         localRepository.saveRepos(repsList);
    }
}
