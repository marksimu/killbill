/*
 * Copyright 2010-2012 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
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

package com.ning.billing.util.tag.api.user;

import java.util.UUID;

import com.ning.billing.util.tag.TagDefinition;
import com.ning.billing.util.tag.api.ControlTagDefinitionCreationEvent;

public class DefaultControlTagDefinitionCreationEvent implements ControlTagDefinitionCreationEvent {
    private final UUID tagId;
    private final TagDefinition tagDefinition;
    private final UUID userToken;

    public DefaultControlTagDefinitionCreationEvent(final UUID tagId, final TagDefinition tagDefinition, final UUID userToken) {
        this.tagId = tagId;
        this.tagDefinition = tagDefinition;
        this.userToken = userToken;
    }

    @Override
    public UUID getTagDefinitionId() {
        return tagId;
    }

    @Override
    public TagDefinition getTagDefinition() {
        return tagDefinition;
    }

    @Override
    public BusEventType getBusEventType() {
        return BusEventType.CONTROL_TAGDEFINITION_CREATION;
    }

    @Override
    public UUID getUserToken() {
        return userToken;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DefaultControlTagDefinitionCreationEvent");
        sb.append("{tagDefinition=").append(tagDefinition);
        sb.append(", tagId=").append(tagId);
        sb.append(", userToken=").append(userToken);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final DefaultControlTagDefinitionCreationEvent that = (DefaultControlTagDefinitionCreationEvent) o;

        if (tagDefinition != null ? !tagDefinition.equals(that.tagDefinition) : that.tagDefinition != null) {
            return false;
        }
        if (tagId != null ? !tagId.equals(that.tagId) : that.tagId != null) {
            return false;
        }
        if (userToken != null ? !userToken.equals(that.userToken) : that.userToken != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId != null ? tagId.hashCode() : 0;
        result = 31 * result + (tagDefinition != null ? tagDefinition.hashCode() : 0);
        result = 31 * result + (userToken != null ? userToken.hashCode() : 0);
        return result;
    }
}
