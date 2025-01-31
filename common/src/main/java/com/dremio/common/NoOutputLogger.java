/*
 * Copyright (C) 2017-2019 Dremio Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dremio.common;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * A {@code Logger} class which does not produce any output
 */
public final class NoOutputLogger implements Logger {
  public static final Logger INSTANCE = new NoOutputLogger();

  private NoOutputLogger() {}

  @Override
  public String getName() {
    return "";
  }

  @Override
  public boolean isTraceEnabled() {
    return false;
  }

  @Override
  public void trace(String msg) {
  }

  @Override
  public void trace(String format, Object arg) {
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
  }

  @Override
  public void trace(String format, Object... arguments) {
  }

  @Override
  public void trace(String msg, Throwable t) {
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return false;
  }

  @Override
  public void trace(Marker marker, String msg) {
  }

  @Override
  public void trace(Marker marker, String format, Object arg) {
  }

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {
  }

  @Override
  public void trace(Marker marker, String format, Object... argArray) {
  }

  @Override
  public void trace(Marker marker, String msg, Throwable t) {
  }

  @Override
  public boolean isDebugEnabled() {
    return false;
  }

  @Override
  public void debug(String msg) {
  }

  @Override
  public void debug(String format, Object arg) {
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
  }

  @Override
  public void debug(String format, Object... arguments) {
  }

  @Override
  public void debug(String msg, Throwable t) {
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return false;
  }

  @Override
  public void debug(Marker marker, String msg) {
  }

  @Override
  public void debug(Marker marker, String format, Object arg) {
  }

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {
  }

  @Override
  public void debug(Marker marker, String format, Object... arguments) {
  }

  @Override
  public void debug(Marker marker, String msg, Throwable t) {
  }

  @Override
  public boolean isInfoEnabled() {
    return false;
  }

  @Override
  public void info(String msg) {
  }

  @Override
  public void info(String format, Object arg) {
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
  }

  @Override
  public void info(String format, Object... arguments) {
  }

  @Override
  public void info(String msg, Throwable t) {
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return false;
  }

  @Override
  public void info(Marker marker, String msg) {
  }

  @Override
  public void info(Marker marker, String format, Object arg) {
  }

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {
  }

  @Override
  public void info(Marker marker, String format, Object... arguments) {
  }

  @Override
  public void info(Marker marker, String msg, Throwable t) {
  }

  @Override
  public boolean isWarnEnabled() {
    return false;
  }

  @Override
  public void warn(String msg) {
  }

  @Override
  public void warn(String format, Object arg) {
  }

  @Override
  public void warn(String format, Object... arguments) {
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
  }

  @Override
  public void warn(String msg, Throwable t) {
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return false;
  }

  @Override
  public void warn(Marker marker, String msg) {
  }

  @Override
  public void warn(Marker marker, String format, Object arg) {
  }

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {
  }

  @Override
  public void warn(Marker marker, String format, Object... arguments) {
  }

  @Override
  public void warn(Marker marker, String msg, Throwable t) {
  }

  @Override
  public boolean isErrorEnabled() {
    return false;
  }

  @Override
  public void error(String msg) {
  }

  @Override
  public void error(String format, Object arg) {
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
  }

  @Override
  public void error(String format, Object... arguments) {
  }

  @Override
  public void error(String msg, Throwable t) {
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return false;
  }

  @Override
  public void error(Marker marker, String msg) {
  }

  @Override
  public void error(Marker marker, String format, Object arg) {
  }

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {
  }

  @Override
  public void error(Marker marker, String format, Object... arguments) {
  }

  @Override
  public void error(Marker marker, String msg, Throwable t) {
  }
}
