package com.payconiq.mohamedassignment.data.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.payconiq.mohamedassignment.App;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.data.remote.service.DataService;
import com.payconiq.mohamedassignment.utils.Constants;
import com.payconiq.mohamedassignment.utils.L;
import com.payconiq.mohamedassignment.utils.NetworkUtils;

import java.io.IOException;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;

import static com.payconiq.mohamedassignment.data.remote.ServiceError.NETWORK_ERROR;
import static com.payconiq.mohamedassignment.utils.Constants.ERROR_UNDEFINED;
import static com.payconiq.mohamedassignment.utils.NetworkUtils.isConnected;
import static com.payconiq.mohamedassignment.utils.ObjectUtil.isNull;


/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class ApiRepository {

    private ServiceGenerator serviceGenerator;

    @Inject
    public ApiRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    public ServiceResponse getData(String pageNumber) {
        if (!NetworkUtils.isConnected(App.getContext())) {

            return null;
        } else {
            DataService repoService = serviceGenerator.createService(DataService.class, Constants.BASE_URL);
            ServiceResponse serviceResponse = processCall(repoService.fetchRepos(pageNumber, Constants.PAGESIZE),
                    false);
            return serviceResponse;
        }
    }

    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid) {
        if (!isConnected(App.getContext())) {
            return new ServiceResponse(new ServiceError());
        }
        try {
            retrofit2.Response response = call.execute();
            Gson gson = new Gson();
            L.json(Repo.class.getName(), gson.toJson(response.body()));
            if (isNull(response)) {
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }
}
