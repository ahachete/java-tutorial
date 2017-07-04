/*
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */


package org.postgresql.ext.javatutorial.common.jmh;


import org.openjdk.jmh.infra.Blackhole;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TripDataBlackholeConsumer {
    public static void consumeStationData(ResultSet resultSet, Blackhole blackhole, int startColumn) throws SQLException {
        blackhole.consume(resultSet.getInt(startColumn));
        blackhole.consume(resultSet.getString(startColumn + 1));
        blackhole.consume(resultSet.getDouble(startColumn + 2));
        blackhole.consume(resultSet.getDouble(startColumn + 3));
    }

    public static void consumeTripData(ResultSet resultSet, Blackhole blackhole) throws SQLException {
        blackhole.consume(resultSet.getTimestamp(1));
        blackhole.consume(resultSet.getTimestamp(2));
        consumeStationData(resultSet, blackhole, 3);
        consumeStationData(resultSet, blackhole, 7);
        blackhole.consume(resultSet.getInt(11));
        blackhole.consume(resultSet.getString(12));
        blackhole.consume(resultSet.getShort(13));
        blackhole.consume(resultSet.getShort(14));
    }
}
