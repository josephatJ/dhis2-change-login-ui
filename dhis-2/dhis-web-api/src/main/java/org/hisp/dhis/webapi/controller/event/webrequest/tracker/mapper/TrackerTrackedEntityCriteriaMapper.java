/*
 * Copyright (c) 2004-2022, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.webapi.controller.event.webrequest.tracker.mapper;

import org.hisp.dhis.webapi.controller.event.webrequest.TrackedEntityInstanceCriteria;
import org.hisp.dhis.webapi.controller.event.webrequest.tracker.TrackerTrackedEntityCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: It should be removed when we will implement new services.
 *
 * Mapper to convert new tracker criteria to old one, to be used until we have
 * new services for new Tracker.
 */
@Mapper
public interface TrackerTrackedEntityCriteriaMapper
{
    @Mapping( source = "orgUnit", target = "ou" )
    @Mapping( source = "updatedAfter", target = "lastUpdatedStartDate" )
    @Mapping( source = "updatedBefore", target = "lastUpdatedEndDate" )
    @Mapping( source = "updatedWithin", target = "lastUpdatedDuration" )
    @Mapping( source = "enrollmentEnrolledAfter", target = "programStartDate" )
    @Mapping( source = "enrollmentEnrolledBefore", target = "programEndDate" )
    @Mapping( source = "enrollmentOccurredAfter", target = "programIncidentStartDate" )
    @Mapping( source = "enrollmentOccurredBefore", target = "programIncidentEndDate" )
    @Mapping( source = "trackedEntity", target = "trackedEntityInstance" )
    @Mapping( source = "eventOccurredAfter", target = "eventStartDate" )
    @Mapping( source = "eventOccurredBefore", target = "eventEndDate" )
    TrackedEntityInstanceCriteria toTrackedEntityInstanceCriteria( TrackerTrackedEntityCriteria from );
}
