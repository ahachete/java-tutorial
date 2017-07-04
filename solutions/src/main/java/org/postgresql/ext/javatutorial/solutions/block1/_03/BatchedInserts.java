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

package org.postgresql.ext.javatutorial.solutions.block1._03;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.postgresql.ext.javatutorial.common.data.BikeTrip;
import org.postgresql.ext.javatutorial.common.jmh.TripsBenchmarkState;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;
import org.postgresql.ext.javatutorial.solutions.PreparedStatementUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BatchedInserts {
    @State(Scope.Benchmark)
    public static class BatchInsertState {
        @Param({"3", "10", "100"})
        public int batchSize;
    }

    @Benchmark
    public void batchedInserts(TripsBenchmarkState state, BatchInsertState batchState) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            PreparedStatement preparedStatement = c.prepareStatement(PreparedStatementUtil.INSERT);

            int i = 0;
            for (BikeTrip trip : state.getBikeTrips()) {
                try {
                    PreparedStatementUtil.setDataPreparedStatement(preparedStatement, trip);
                    preparedStatement.addBatch();
                    if (i++ == batchState.batchSize) {
                        i = 0;
                        preparedStatement.executeBatch();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
