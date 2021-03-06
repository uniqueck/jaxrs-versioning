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

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 * @author Arne Limburg - open knowledge GmbH
 */
@Provider
public class VersionPathFilter implements ContainerRequestFilter {

  private static final String VERSION_PATH_PARAMETER_NAME = "version";

  @Context
  private UriInfo uriInfo;

  @Override
  public void filter(ContainerRequestContext context) throws IOException {
    List<String> versionPathParameter = uriInfo.getPathParameters().get(VERSION_PATH_PARAMETER_NAME);
    if (versionPathParameter != null) {
      Version.set(context, versionPathParameter.iterator().next());
    }
  }
}
