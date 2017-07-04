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


package org.postgresql.ext.javatutorial.solutions._01_selects._02;


import com.google.gson.Gson;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;
import org.postgresql.ext.javatutorial.common.data.TripDataJson;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JsonSelect {
    private static final String ROW_TO_JSON_QUERY = "SELECT row_to_json(trips.*) FROM trips";

    @Benchmark
    public void jsonSelect(Blackhole blackhole) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            ResultSet resultSet = c.prepareStatement(ROW_TO_JSON_QUERY).executeQuery();
            while(resultSet.next()) {
                blackhole.consume(resultSet.getString(1));
            }
        });
    }

    @Benchmark
    public void jsonSelectParse(Blackhole blackhole) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            ResultSet resultSet = c.prepareStatement(ROW_TO_JSON_QUERY).executeQuery();
            Gson gson = new Gson();
            while(resultSet.next()) {
                blackhole.consume(gson.fromJson(resultSet.getString(1), TripDataJson.class));
            }
        });
    }
}
