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


package org.postgresql.ext.javatutorial.solutions._01_selects._03;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ArraysSelect {
    private static final String SELECT_ARRAYS_QUERY =
            "SELECT array_agg(bike_id), array_agg(extract(epoch from stop_time - start_time)),"
                    + "array_agg(start_station_name), array_agg(end_station_name)"
                    + " FROM trips";

    private static final String SELECT_CSVS_QUERY =
            "SELECT string_agg(bike_id::text, ','), string_agg(extract(epoch from stop_time - start_time)::text, ','),"
                    + "string_agg(start_station_name, ','), string_agg(end_station_name, ',')"
                    + " FROM trips";

    @Benchmark
    public void selectLargeArrays(Blackhole blackhole) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            ResultSet resultSet = c.prepareStatement(SELECT_ARRAYS_QUERY).executeQuery();
            if(resultSet.next()) {
                Integer[] bikeIds = (Integer[]) resultSet.getArray(1).getArray();
                blackhole.consume(bikeIds);
                Double[] durations = (Double[]) resultSet.getArray(2).getArray();
                blackhole.consume(durations);
                String[] startNames = (String[]) resultSet.getArray(3).getArray();
                blackhole.consume(startNames);
                String[] endNames = (String[]) resultSet.getArray(4).getArray();
                blackhole.consume(endNames);
            }
        });
    }

    @Benchmark
    public void selectLargeCsv(Blackhole blackhole) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            ResultSet resultSet = c.prepareStatement(SELECT_CSVS_QUERY).executeQuery();
            if(resultSet.next()) {
                blackhole.consume(resultSet.getString(1));
                blackhole.consume(resultSet.getString(2));
                blackhole.consume(resultSet.getString(3));
                blackhole.consume(resultSet.getString(4));
            }
        });
    }
}
