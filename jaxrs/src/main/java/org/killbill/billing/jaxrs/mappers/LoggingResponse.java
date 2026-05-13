/*
 * Copyright 2016 Groupon, Inc
 * Copyright 2016 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.jaxrs.mappers;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Link.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingResponse extends Response {

    private static final Logger log = LoggerFactory.getLogger(LoggingResponse.class);

    private final Exception e;
    private final Response response;

    public LoggingResponse(final Exception e, final Response response) {
        this.e = e;
        this.response = response;
    }

    @Override
    public Object getEntity() {
        // Delay logging until the entity is retrieved: this is to avoid double logging with TimedResourceInterceptor
        // which needs to access exception mappers to get the response status
        if (response.getStatus() == Status.CONFLICT.getStatusCode()) {
            log.warn("Conflicting request", e);
        } else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
            log.debug("Not found", e);
        } else if (response.getStatus() == Status.BAD_REQUEST.getStatusCode()) {
            log.warn("Bad request", e);
        } else if (response.getStatus() == Status.UNAUTHORIZED.getStatusCode()) {
            log.debug("Authorization error", e);
        } else if (response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            log.warn("Internal error", e);
        }

        return response.getEntity();
    }

    @Override
    public <T> T readEntity(final Class<T> entityType) {
        return response.readEntity(entityType);
    }

    @Override
    public <T> T readEntity(final GenericType<T> entityType) {
        return response.readEntity(entityType);
    }

    @Override
    public <T> T readEntity(final Class<T> entityType, final Annotation[] annotations) {
        return response.readEntity(entityType, annotations);
    }

    @Override
    public <T> T readEntity(final GenericType<T> entityType, final Annotation[] annotations) {
        return response.readEntity(entityType, annotations);
    }

    @Override
    public boolean hasEntity() {
        return response.hasEntity();
    }

    @Override
    public boolean bufferEntity() {
        return response.bufferEntity();
    }

    @Override
    public void close() {
        response.close();
    }

    @Override
    public MediaType getMediaType() {
        return response.getMediaType();
    }

    @Override
    public Locale getLanguage() {
        return response.getLanguage();
    }

    @Override
    public int getLength() {
        return response.getLength();
    }

    @Override
    public Set<String> getAllowedMethods() {
        return response.getAllowedMethods();
    }

    @Override
    public Map<String, NewCookie> getCookies() {
        return response.getCookies();
    }

    @Override
    public EntityTag getEntityTag() {
        return response.getEntityTag();
    }

    @Override
    public Date getDate() {
        return response.getDate();
    }

    @Override
    public Date getLastModified() {
        return response.getLastModified();
    }

    @Override
    public URI getLocation() {
        return response.getLocation();
    }

    @Override
    public Set<Link> getLinks() {
        return response.getLinks();
    }

    @Override
    public boolean hasLink(final String relation) {
        return response.hasLink(relation);
    }

    @Override
    public Link getLink(final String relation) {
        return response.getLink(relation);
    }

    @Override
    public Builder getLinkBuilder(final String relation) {
        return response.getLinkBuilder(relation);
    }

    @Override
    public int getStatus() {
        return response.getStatus();
    }

    @Override
    public StatusType getStatusInfo() {
        return response.getStatusInfo();
    }

    @Override
    public MultivaluedMap<String, Object> getMetadata() {
        return response.getMetadata();
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders() {
        return response.getStringHeaders();
    }

    @Override
    public String getHeaderString(final String name) {
        return response.getHeaderString(name);
    }
}
