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


package org.postgresql.ext.javatutorial.exercises.block1._01;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.postgresql.ext.javatutorial.common.jmh.InsertsState;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;

import java.io.IOException;
import java.sql.SQLException;


public class StatementInserts {
    @Benchmark
    public void statementInserts(InsertsState state) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            /*
             * TODO:
             *
             * Using Statement (not PreparedStatement!), generate the String representing an INSERT command
             * from the data obtained from the InsertsState, and execute the statements.
             */
        });
    }

    @State(Scope.Benchmark)
    public static class MultiInsertState {
        @Param({"3", "10", "100"})
        public int multiInsertBatchSize;
    }

    @Benchmark
    public void statementMultiInserts(InsertsState tripsState, MultiInsertState batchState)
    throws IOException, SQLException {
        SqlUtil.connection(c -> {
            /*
             * TODO:
             *
             * Using Statement (not PreparedStatement!), generate the String representing a multi-valued INSERT command
             * from the data obtained from the InsertsState, and execute the statements.
             * Use the bachState to define the batch size.
             */
        });
    }
}
