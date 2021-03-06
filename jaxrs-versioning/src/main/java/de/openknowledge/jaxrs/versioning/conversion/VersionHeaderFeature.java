/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.openknowledge.jaxrs.versioning.conversion;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import de.openknowledge.jaxrs.versioning.VersionHeader;

/**
 * @author Arne Limburg - open knowledge GmbH
 */
@Provider
public class VersionHeaderFeature implements DynamicFeature {

  @Override
  public void configure(ResourceInfo resource, FeatureContext context) {
    VersionHeader versionHeader = resource.getResourceMethod().getAnnotation(VersionHeader.class);
    if (versionHeader == null) {
      versionHeader = resource.getResourceClass().getAnnotation(VersionHeader.class);
    }
    
    if (versionHeader != null) {
      context.register(new VersionHeaderFilter(versionHeader.value()));
    }
  }
}
