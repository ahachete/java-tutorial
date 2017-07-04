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


package org.postgresql.ext.javatutorial.exercises._01_selects._02;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;

import java.io.IOException;
import java.sql.SQLException;


public class JsonSelect {
    @Benchmark
    public void jsonSelect(Blackhole blackhole) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            /**
             * TODO:
             *
             * Execute a query to return all the rows of the trips table.
             * Transform in the query all the fields to a json document.
             * For every row, get that single field of every row as a String and consume it in the blackhole.
             */
        });
    }

    @Benchmark
    public void jsonSelectParse(Blackhole blackhole) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            /**
             * TODO:
             *
             * Execute a query to return all the rows of the trips table.
             * Transform in the query all the fields to a json document.
             * For every row, parse the returned json using Google's Gson library.
             * Consume the parsed object in a blackhole.
             * You may want to use the TripDataJson class, and then GsonUtil to obtain a proper Gson instance.
             *
             */
        });
    }
}
