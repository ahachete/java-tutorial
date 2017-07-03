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


package org.postgresql.ext.javatutorial.solutions.block1._01;


import org.openjdk.jmh.annotations.*;
import org.postgresql.ext.javatutorial.common.data.BikeStation;
import org.postgresql.ext.javatutorial.common.data.BikeTrip;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;
import org.postgresql.ext.javatutorial.solutions.block1.TripsBenchmarkState;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StatementInserts {
    private static void bikeStationCsv(Stream.Builder<String> values, BikeStation station) {
        values
                .add(station.getId() + "")
                .add(station.getName())
                .add(station.getLatitude() + "")
                .add(station.getLongitude() + "");
    }

    private static String bikeTripCsv(BikeTrip trip) {
        Stream.Builder<String> values = Stream.<String>builder()
                .add(trip.getStartDateString())
                .add(trip.getEndDateString());

        bikeStationCsv(values, trip.getStartStation());
        bikeStationCsv(values, trip.getEndStation());

        values
                .add(trip.getBikeId() + "")
                .add(trip.getUserType())
                .add(trip.getBirthYear().orElse(null) + "")
                .add(trip.getGender() + "");

        return values.build()
                .map(s -> ("null".equals(s)) ? s : "'" + s + "'")
                .collect(Collectors.joining(","));
    }

    private static void executeStatements(Connection connection, Stream<String> statements) {
        statements.forEach(i -> {
            try {
                try (Statement statement = connection.createStatement()) {
                    statement.execute(i);
                }
            } catch(SQLException e) {
                throw new RuntimeException("Error inserting", e);
            }
        });
    }

    @Benchmark
    public void statementInserts(TripsBenchmarkState state) throws IOException, SQLException {
        SqlUtil.connection(c ->
                executeStatements(
                        c,
                        state.getBikeTrips().stream().map(t ->
                                new StringBuilder()
                                        .append("INSERT INTO trips VALUES (")
                                        .append(bikeTripCsv(t))
                                        .append(")")
                                        .toString()
                                )
                )
        );
    }

    private static Stream<String> generateMultiInsertStatements(Collection<BikeTrip> trips, int batchSize) {
        Stream.Builder<String> builder = Stream.builder();

        Iterator<BikeTrip> iterator = trips.iterator();
        while(iterator.hasNext()) {
            StringBuilder sb = new StringBuilder()
                    .append("INSERT INTO trips VALUES ");
            for (int i = 0; i < batchSize; i++) {
                sb.append("(").append(bikeTripCsv(iterator.next())).append(")");
                if(iterator.hasNext() && i < batchSize - 1) {
                    sb.append(",");
                } else {
                    break;
                }
            }

            builder.add(sb.toString());
        }

        return builder.build();
    }

    @State(Scope.Benchmark)
    public static class MultiInsertState {
        @Param({"3", "10", "100"})
        public int multiInsertBatchSize;
    }

    @Benchmark
    public void statementMultiInserts(TripsBenchmarkState tripsState, MultiInsertState batchState)
    throws IOException, SQLException {
        SqlUtil.connection(c ->
                executeStatements(
                        c,
                        generateMultiInsertStatements(tripsState.getBikeTrips(), batchState.multiInsertBatchSize)
                )
        );
    }
}
