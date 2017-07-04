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

package org.postgresql.ext.javatutorial.exercises._02_inserts._04;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.postgresql.ext.javatutorial.common.jmh.InsertsState;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;

import java.io.IOException;
import java.sql.SQLException;


public class Copy {
    @State(Scope.Benchmark)
    public static class BatchInsertState {
        @Param({"3", "10", "100"})
        public int batchSize;
    }

    @Benchmark
    public void batchedInserts(InsertsState state, BatchInsertState batchState) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            /**
             * TODO:
             *
             * Get a CopyManager from the connection.
             * Use copyIn(...) to copy data into PostgreSQL, from state variable.
             * Avoid materializing into a String or other intermediary format the whole dataset. To do so,
             * batch every batchState trips into a copyIn() call.
             */
        });
    }
}
