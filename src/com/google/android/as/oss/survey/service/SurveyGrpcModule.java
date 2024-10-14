/*
 * Copyright 2024 Google LLC
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

package com.google.android.as.oss.survey.service;

import com.google.android.apps.miphone.astrea.grpc.Annotations.GrpcService;
import com.google.android.apps.miphone.astrea.grpc.Annotations.GrpcServiceName;
import com.google.android.as.oss.survey.api.proto.SurveyServiceGrpc;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoSet;
import io.grpc.BindableService;
import javax.inject.Singleton;

/** Module providing {@link SurveyGrpcBindableService} for survey requests. */
@Module
@InstallIn(SingletonComponent.class)
abstract class SurveyGrpcModule {

  @Binds
  @Singleton
  @GrpcService
  abstract SurveyGrpcBindableService bindsSurveyGrpcBindableService(
      SurveyGrpcBindableService surveyGrpcBindableService);

  @Binds
  @IntoSet
  @GrpcService
  abstract BindableService bindBindableService(
      @GrpcService SurveyGrpcBindableService surveyGrpcBindableService);

  @Provides
  @IntoSet
  @GrpcServiceName
  static String provideServiceName() {
    return SurveyServiceGrpc.SERVICE_NAME;
  }
}
