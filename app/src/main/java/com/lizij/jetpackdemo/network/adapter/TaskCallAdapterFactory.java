/*
 * Copyright (C) 2016 zeng1990java.
 *     https://github.com/zeng1990java
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lizij.jetpackdemo.network.adapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import bolts.Task;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created by lizij on 2018/08/28
 */
public final class TaskCallAdapterFactory extends CallAdapter.Factory {

    public static TaskCallAdapterFactory create(){
        return new TaskCallAdapterFactory(null);
    }

    public static TaskCallAdapterFactory create(Executor executor){
        if (executor == null){
            throw new NullPointerException("executor == null");
        }
        return new TaskCallAdapterFactory(executor);
    }

    private Executor executor;

    private TaskCallAdapterFactory(Executor executor){
        this.executor = executor;
    }

    /**
     * Return TaskCallAdapter if returnType is like Task<T>
     * @param returnType should be like Task<T>
     * @param annotations
     * @param retrofit
     * @return return {@link TaskCallAdapter}
     */
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        // returnType should be like Task<T>
        if (getRawType(returnType) != Task.class){
            return null;
        }

        if (!(returnType instanceof ParameterizedType)){
            throw new IllegalArgumentException("Task return type must be parameterized"
                    + " as Task<Foo> or Task<? extends Foo>");
        }

        // innerType is T in Task<T>
        Type innerType = getParameterUpperBound(0, (ParameterizedType) returnType);

        return new TaskCallAdapter(executor, innerType);
    }
}
