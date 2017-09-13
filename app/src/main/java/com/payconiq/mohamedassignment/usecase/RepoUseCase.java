package com.payconiq.mohamedassignment.usecase;

import android.os.Handler;
import android.os.Looper;

import com.payconiq.mohamedassignment.data.DataRepository;
import com.payconiq.mohamedassignment.data.remote.ServiceResponse;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.payconiq.mohamedassignment.data.remote.ServiceError.isSuccess;
import static com.payconiq.mohamedassignment.utils.ObjectUtil.isNull;


/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class RepoUseCase {
    DataRepository dataRepository;

    @Inject
    public RepoUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void getData(final Callback callback, String pageNumber) {
        new Thread(() -> {
            ServiceResponse serviceResponse = dataRepository.requestData(pageNumber);
            new Handler(Looper.getMainLooper()).post(() -> {
                if (!isNull(serviceResponse) && isSuccess(serviceResponse.getCode())) {
                    ArrayList<Repo> reposModelList = (ArrayList<Repo>) serviceResponse.getData();
                    callback.onSuccess(reposModelList);
                } else {
                    callback.onFail();
                }
            });
        }).start();
    }
    public interface Callback {
        void onSuccess(ArrayList<Repo> repoModel);

        void onFail();
    }
}
