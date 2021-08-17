/*
 * Copyright (c) 2004-2021, University of Oslo
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
package org.hisp.dhis.webapi.controller;

import static org.hisp.dhis.webapi.WebClient.Body;
import static org.hisp.dhis.webapi.WebClient.ContentType;
import static org.hisp.dhis.webapi.utils.WebClientUtils.assertStatus;

import org.hisp.dhis.webapi.DhisControllerConvenienceTest;
import org.hisp.dhis.webapi.utils.ContextUtils;
import org.junit.Test;
import org.springframework.http.HttpStatus;

/**
 * Tests the {@link SystemSettingController} using (mocked) REST requests.
 *
 * @author Jan Bernitt
 */
public class SystemSettingControllerTest extends DhisControllerConvenienceTest
{
    @Test
    public void testSetSystemSettingOrTranslation_NoSuchObject()
    {
        assertWebMessage( "Conflict", 409, "ERROR", "Key is not supported: xyz",
            POST( "/systemSettings/xyz?value=abc" ).content( HttpStatus.CONFLICT ) );
    }

    @Test
    public void testSetSystemSettingOrTranslation_NoValue()
    {
        assertWebMessage( "Conflict", 409, "ERROR", "Value must be specified as query param or as payload",
            POST( "/systemSettings/xyz" ).content( HttpStatus.CONFLICT ) );
    }

    @Test
    public void testSetSystemSettingOrTranslation_Setting()
    {
        assertWebMessage( "OK", 200, "OK",
            "System setting 'keyUiLocale' set to value 'en'.",
            POST( "/systemSettings/keyUiLocale?", Body( "en" ), ContentType( ContextUtils.CONTENT_TYPE_TEXT ) )
                .content( HttpStatus.OK ) );
    }

    @Test
    public void testSetSystemSettingOrTranslation_Translation()
    {
        assertStatus( HttpStatus.OK, POST( "/systemSettings/keyUiLocale?value=de" ) );

        assertWebMessage( "OK", 200, "OK",
            "Translation for system setting 'keyUiLocale' and locale: 'de' set to: 'Sprache'",
            POST( "/systemSettings/keyUiLocale?locale=de&value=Sprache" )
                .content( HttpStatus.OK ) );
    }

    @Test
    public void testSetSystemSettingOrTranslation_TranslationNoSetting()
    {
        assertWebMessage( "Conflict", 409, "ERROR",
            "No entry found for key: keyUiLocale",
            POST( "/systemSettings/keyUiLocale?locale=de&value=Sprache" )
                .content( HttpStatus.CONFLICT ) );
    }

    @Test
    public void testSetSystemSettingV29()
    {
        assertWebMessage( "OK", 200, "OK", "System settings imported",
            POST( "/systemSettings", "{'keyUiLocale':'en'}" ).content( HttpStatus.OK ) );
    }

    @Test
    public void testSetSystemSettingV29_Empty()
    {
        assertWebMessage( "OK", 200, "OK", "System settings imported",
            POST( "/systemSettings", "{}" ).content( HttpStatus.OK ) );
    }

    @Test
    public void testSetSystemSettingV29_NoSuchObject()
    {
        assertWebMessage( "Conflict", 409, "ERROR", "Key(s) is not supported: xyz, abc",
            POST( "/systemSettings", "{'xyz':'en','abc':'foo'}" ).content( HttpStatus.CONFLICT ) );
    }
}