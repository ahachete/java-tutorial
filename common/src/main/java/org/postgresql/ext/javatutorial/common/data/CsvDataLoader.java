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


package org.postgresql.ext.javatutorial.common.data;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;


public class CsvDataLoader {
    private static final String CSV_FILE_DATA = "201701-citibike-tripdata.csv.gz";


    public static Collection<BikeTrip> loadCsvData() throws IOException {
        return loadCsvData(CSV_FILE_DATA);
    }

    public static Collection<BikeTrip> loadCsvData(String resourceFileName) throws IOException {
        URL resourceUrl = CsvDataLoader.class.getClassLoader().getResource(resourceFileName);
        if(null == resourceUrl) {
            throw new RuntimeException("Data not found in the package!");
        }

        try(Reader reader = new InputStreamReader(new GZIPInputStream(resourceUrl.openStream()))) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

            return parser.getRecords().stream()
                    .skip(1)    // CSV header
                    .map(
                            r -> new BikeTrip(
                                    r.get(1),
                                    r.get(2),
                                    r.get(11),
                                    r.get(12),
                                    r.get(13),
                                    r.get(14),
                                    new BikeStation(r.get(3), r.get(4), r.get(5), r.get(6)),
                                    new BikeStation(r.get(7), r.get(8), r.get(9), r.get(10))
                            )
                    ).collect(Collectors.toList());
        }
    }
}
