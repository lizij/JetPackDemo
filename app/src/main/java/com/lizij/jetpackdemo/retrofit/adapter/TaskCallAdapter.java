package com.lizij.jetpackdemo.retrofit.adapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskCallAdapter<R> implements CallAdapter<R, Task<R>> {

    private Executor executor;
    private final Type responseType;

    TaskCallAdapter(Executor executor, Type responseType){
        this.executor = executor;
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Task<R> adapt(final Call<R> call) {
        final TaskCompletionSource<R> tcs = new TaskCompletionSource<>();

        if (executor != null){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response<R> response = call.execute();
                        setResponseResult(response, tcs);
                    }catch (IOException e) {
                        tcs.setError(e);
                    }
                }
            });
        } else {
            call.enqueue(new Callback<R>() {
                @Override
                public void onResponse(Call<R> call, Response<R> response) {
                    setResponseResult(response, tcs);
                }

                @Override
                public void onFailure(Call<R> call, Throwable t) {
                    tcs.setError(new Exception(t));
                }
            });
        }

        return tcs.getTask();
    }

    private void setResponseResult(Response<R> response, TaskCompletionSource<R> tcs) {
        try {
            if (response.isSuccessful()){
                tcs.setResult(response.body());
            } else {
                tcs.setError(new HttpException(response));
            }
        } catch (CancellationException e){
            tcs.setCancelled();
        } catch (Exception e){
            tcs.setError(e);
        }
    }
}
