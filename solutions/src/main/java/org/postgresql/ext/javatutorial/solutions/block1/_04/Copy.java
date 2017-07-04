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

package org.postgresql.ext.javatutorial.solutions.block1._04;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.postgresql.ext.javatutorial.common.data.BikeTrip;
import org.postgresql.ext.javatutorial.common.data.Data2Csv;
import org.postgresql.ext.javatutorial.common.jmh.InsertsState;
import org.postgresql.ext.javatutorial.common.sql.SqlUtil;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.Iterator;


public class Copy {
    private static final String COPY_SQL = "COPY trips FROM STDIN WITH DELIMITER ','";

    @State(Scope.Benchmark)
    public static class BatchInsertState {
        @Param({"3", "10", "100"})
        public int batchSize;
    }

    @Benchmark
    public void batchedInserts(InsertsState state, BatchInsertState batchState) throws IOException, SQLException {
        SqlUtil.connection(c -> {
            CopyManager copyManager = new CopyManager((BaseConnection) c);
            Iterator<BikeTrip> iterator = state.getBikeTrips().iterator();

            while(iterator.hasNext()) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < batchState.batchSize; i++) {
                    if(iterator.hasNext()) {
                        sb.append(Data2Csv.bikeTripCopyCsv(iterator.next())).append("\n");
                    }
                }
                try {
                    copyManager.copyIn(COPY_SQL, new StringReader(sb.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
