// Copyright (C) 2015 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface CommonConstants extends Constants {
  public static final CommonConstants C = GWT.create(CommonConstants.class);

  String inTheFuture();
  String month();
  String months();
  String year();
  String years();

  String oneSecondAgo();
  String oneMinuteAgo();
  String oneHourAgo();
  String oneDayAgo();
  String oneWeekAgo();
  String oneMonthAgo();
  String oneYearAgo();
}
