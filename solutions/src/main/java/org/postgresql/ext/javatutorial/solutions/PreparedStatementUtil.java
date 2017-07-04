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


package org.postgresql.ext.javatutorial.solutions;


import org.postgresql.ext.javatutorial.common.data.BikeStation;
import org.postgresql.ext.javatutorial.common.data.BikeTrip;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


public class PreparedStatementUtil {
    public static final String INSERT = "INSERT INTO trips VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static void setDataPreparedStatement(PreparedStatement preparedStatement, BikeStation station, int startPos)
    throws SQLException {
        preparedStatement.setInt(startPos + 0, station.getId());
        preparedStatement.setString(startPos + 1, station.getName());
        preparedStatement.setDouble(startPos + 2, station.getLatitude());
        preparedStatement.setDouble(startPos + 3, station.getLongitude());
    }

    public static void setDataPreparedStatement(PreparedStatement preparedStatement, BikeTrip trip) throws SQLException {
        preparedStatement.setTimestamp(1, Timestamp.valueOf(trip.getStartDate()));
        preparedStatement.setTimestamp(2, Timestamp.valueOf(trip.getEndDate()));
        setDataPreparedStatement(preparedStatement, trip.getStartStation(), 3);
        setDataPreparedStatement(preparedStatement, trip.getStartStation(), 7);
        preparedStatement.setInt(11, trip.getBikeId());
        preparedStatement.setString(12, trip.getUserType());
        trip.getBirthYear().ifPresent(v -> {
            try {
                preparedStatement.setShort(13, v);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        preparedStatement.setShort(14, trip.getGender());
    }
}
