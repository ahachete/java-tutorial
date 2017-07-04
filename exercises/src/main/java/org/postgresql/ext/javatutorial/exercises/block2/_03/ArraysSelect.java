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


package org.postgresql.ext.javatutorial.exercises.block2._03;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;

import java.io.IOException;
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
            /**
             * TODO:
             *
             * Execute the query SELECT_ARRAYS_QUERY.
             * Get the returned row and get the (SQL) Array from each column.
             * Consume within the blackhole each Array.
             */
        });
    }

    @Benchmark
    public void selectLargeCsv(Blackhole blackhole) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            /**
             * TODO:
             *
             * Execute the query SELECT_CSVS_QUERY.
             * Get the returned row, and consume each column (as what it is, a String)
             */
        });
    }
}
