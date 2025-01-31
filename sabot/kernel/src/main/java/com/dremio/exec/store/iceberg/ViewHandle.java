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
package com.dremio.exec.store.iceberg;

import java.util.Objects;

import org.apache.iceberg.view.ViewVersionMetadata;

import com.dremio.connector.metadata.EntityPath;
import com.dremio.exec.catalog.DremioTable;
import com.dremio.exec.catalog.VersionedDatasetAdapter;
import com.dremio.exec.catalog.VersionedPlugin;
import com.dremio.exec.store.VersionedDatasetHandle;

public class ViewHandle implements VersionedDatasetHandle {
  private EntityPath viewPath;
  private ViewVersionMetadata viewVersionMetadata;
  private String tag;

  private ViewHandle(final EntityPath viewpath, ViewVersionMetadata viewVersionMetadata, String tag) {
    this.viewPath = viewpath;
    this.viewVersionMetadata = viewVersionMetadata;
    this.tag = tag;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public VersionedPlugin.EntityType getType() {
    return VersionedPlugin.EntityType.ICEBERG_VIEW;
  }

  @Override
  public DremioTable translateToDremioTable(VersionedDatasetAdapter vda, String accessUserName) {
    return vda.translateIcebergView(accessUserName);
  }

  public ViewVersionMetadata getViewVersionMetadata() {
    return viewVersionMetadata;
  }

  public String getTag() {
    return tag;
  }

  @Override
  public EntityPath getDatasetPath() {
    return viewPath;
  }

  public static final class Builder {
    private EntityPath viewPath;
    private ViewVersionMetadata viewVersionMetadata;
    private String tag;

    public Builder datasetpath(EntityPath viewpath) {
      this.viewPath = viewpath;
      return this;
    }

    public Builder viewVersionMetadata(ViewVersionMetadata viewVersionMetadata) {
      this.viewVersionMetadata = viewVersionMetadata;
      return this;
    }

    public Builder tag(String tag) {
      this.tag = tag;
      return this;
    }

    public ViewHandle build() {
      Objects.requireNonNull(viewPath, "dataset path is required");
      return new ViewHandle(viewPath, viewVersionMetadata, tag);
    }
  }
}
