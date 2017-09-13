package com.payconiq.mohamedassignment.data;


import com.payconiq.mohamedassignment.data.local.LocalRepository;
import com.payconiq.mohamedassignment.data.remote.ApiRepository;
import com.payconiq.mohamedassignment.data.remote.ServiceResponse;

import javax.inject.Inject;

import io.realm.Realm;

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

    public ServiceResponse requestData(String pageNumber, Realm realm) {
        return apiRepository.getData(pageNumber,realm);
    }
}
