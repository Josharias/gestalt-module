/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.naming;

import org.junit.Test;
import org.terasology.naming.exception.VersionParseException;

import static org.junit.Assert.assertEquals;

/**
 * @author Immortius
 */
public class VersionTest {

    @Test
    public void parseVersion() {
        Version version = new Version("1.2.3");
        assertEquals(1, version.getMajor());
        assertEquals(2, version.getMinor());
        assertEquals(3, version.getPatch());
    }

    @Test
    public void getNextMajorVersion() {
        Version version = new Version(1, 2, 3);
        assertEquals(new Version(2, 0, 0), version.getNextMajorVersion());
    }

    @Test
    public void getNextMinorVersion() {
        Version version = new Version(1, 2, 3);
        assertEquals(new Version(1, 3, 0), version.getNextMinorVersion());
    }

    @Test
    public void getNextPatchVersion() {
        Version version = new Version(1, 2, 3);
        assertEquals(new Version(1, 2, 4), version.getNextPatchVersion());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionCreatingWithNegativeMajorVersion() {
        new Version(-1, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionCreatingWithNegativeMinorVersion() {
        new Version(0, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionCreatingWithNegativePatchVersion() {
        new Version(0, 0, -1);
    }

    @Test(expected = VersionParseException.class)
    public void exceptionParsingMalformedString() {
        new Version("hello");
    }

    @Test(expected = VersionParseException.class)
    public void exceptionParsingInvalidNumbers() {
        new Version("1.1.6a");
    }

    @Test
    public void compareVersions() {
        assertEquals(0, new Version(1, 0, 0).compareTo(new Version(1, 0, 0)));
        assertEquals(-1, new Version(1, 0, 0).compareTo(new Version(2, 0, 0)));
        assertEquals(1, new Version(2, 0, 0).compareTo(new Version(1, 0, 0)));
        assertEquals(1, new Version(1, 1, 0).compareTo(new Version(1, 0, 0)));
        assertEquals(-1, new Version(1, 0, 0).compareTo(new Version(1, 1, 0)));
        assertEquals(1, new Version(1, 0, 1).compareTo(new Version(1, 0, 0)));
        assertEquals(-1, new Version(1, 0, 0).compareTo(new Version(1, 0, 1)));
    }

    @Test
    public void versionToString() {
        assertEquals("1.2.3", new Version(1, 2, 3).toString());
    }

}
