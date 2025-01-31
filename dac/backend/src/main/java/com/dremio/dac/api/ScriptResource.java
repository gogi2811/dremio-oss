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

package com.dremio.dac.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.dremio.dac.annotations.APIResource;
import com.dremio.dac.annotations.Secured;
import com.dremio.dac.model.scripts.PaginatedResponse;
import com.dremio.dac.model.scripts.ScriptData;
import com.dremio.service.script.DuplicateScriptNameException;
import com.dremio.service.script.ScriptNotAccessible;
import com.dremio.service.script.ScriptNotFoundException;
import com.dremio.service.script.ScriptService;
import com.dremio.service.script.proto.ScriptProto;
import com.dremio.service.users.UserNotFoundException;
import com.dremio.service.users.UserService;
import com.dremio.service.users.proto.UID;

@APIResource
@Secured
@RolesAllowed({"user", "admin"})
@Path("/scripts")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ScriptResource {
  private static final org.slf4j.Logger logger =
    org.slf4j.LoggerFactory.getLogger(ScriptResource.class);
  private final ScriptService scriptService;
  private final SecurityContext securityContext;
  private final UserService userService;

  @Inject
  public ScriptResource(ScriptService scriptService,
                        @Context SecurityContext securityContext,
                        UserService userService) {
    this.scriptService = scriptService;
    this.securityContext = securityContext;
    this.userService = userService;
  }

  @GET
  public PaginatedResponse<ScriptData> getScripts(@QueryParam("offset") Integer offset,
                                                  @QueryParam("maxResults") Integer maxResults,
                                                  @QueryParam("search") String search,
                                                  @QueryParam("orderBy") String orderBy,
                                                  @QueryParam("createdBy") String createdBy) {
    // validations and assigning default values
    offset = (offset == null) ? 0 : offset;
    maxResults = (maxResults == null) ? 25 : Math.min(maxResults, 1000);
    search = (search == null) ? "" : search;
    orderBy = (orderBy == null) ? "" : orderBy;

    Long totalScripts = scriptService.getCountOfMatchingScripts(search, "", createdBy);
    List<ScriptData> scripts =
      scriptService.getScripts(offset, maxResults, search, orderBy, "", createdBy)
        .stream()
        .map(this::fromScript)
        .collect(Collectors.toList());
    return new PaginatedResponse<>(totalScripts, scripts);
  }

  @POST
  public ScriptData postScripts(ScriptData scriptData) {
    try {
      return fromScript(scriptService.createScript(ScriptData.toScriptRequest(scriptData)));
    } catch (DuplicateScriptNameException exception) {
      logger.error(exception.getMessage(), exception);
      throw new BadRequestException(exception.getMessage());
    }
  }

  @GET
  @Path("/{id}")
  public ScriptData getScript(@PathParam("id") String scriptId) {
    try {
      return fromScript(scriptService.getScriptById(scriptId));
    } catch (ScriptNotFoundException exception) {
      logger.error(exception.getMessage(), exception);
      throw new NotFoundException(exception.getMessage());
    } catch (ScriptNotAccessible exception) {
      logger.error(exception.getMessage(), exception);
      throw new ForbiddenException(exception.getMessage());
    }
  }

  @PUT
  @Path("/{id}")
  public ScriptData updateScript(@PathParam("id") String scriptId, ScriptData scriptData) {
    // check if script exists with given scriptId
    try {
      // update the script
      return fromScript(scriptService.updateScript(scriptId,
                                                   ScriptData.toScriptRequest(scriptData)));
    } catch (ScriptNotFoundException exception) {
      logger.error(exception.getMessage(), exception);
      throw new NotFoundException(exception.getMessage());
    } catch (DuplicateScriptNameException exception) {
      logger.error(exception.getMessage(), exception);
      throw new BadRequestException(exception.getMessage());
    } catch (ScriptNotAccessible exception) {
      logger.error(exception.getMessage(), exception);
      throw new ForbiddenException(exception.getMessage());
    }
  }

  @DELETE
  @Path(("/{id}"))
  public Response deleteScript(@PathParam("id") String scriptId) {
    try {
      scriptService.deleteScriptById(scriptId);
      return Response.noContent().build();
    } catch (ScriptNotFoundException exception) {
      logger.error(exception.getMessage(), exception);
      throw new NotFoundException(exception.getMessage());
    } catch (ScriptNotAccessible exception) {
      logger.error(exception.getMessage(), exception);
      throw new ForbiddenException(exception.getMessage());
    }
  }

  public User getUserInfoById(String userId) {
    try {
      return User.fromUser(this.userService.getUser(new UID(userId)));
    } catch (UserNotFoundException e) {
      logger.warn("User with id: {} is not found while fetching user info.",
                  userId);
      return new User(userId, null, null, null, null, null, null, null, true);
    }
  }

  private ScriptData fromScript(ScriptProto.Script script) {
    return ScriptData.fromScriptWithUserInfo(script,
                                             getUserInfoById(script.getCreatedBy()),
                                             getUserInfoById(script.getModifiedBy()));
  }

}
