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


import org.postgresql.ext.javatutorial.common.data.BikeStation;
import org.postgresql.ext.javatutorial.common.data.BikeTrip;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Data2Csv {
    public static void bikeStationValues(Stream.Builder<String> values, BikeStation station) {
        values
                .add(station.getId() + "")
                .add(station.getName())
                .add(station.getLatitude() + "")
                .add(station.getLongitude() + "");
    }

    public static Stream<String> bikeTripValues(BikeTrip trip) {
        Stream.Builder<String> values = Stream.<String>builder()
                .add(trip.getStartDateString())
                .add(trip.getEndDateString());

        bikeStationValues(values, trip.getStartStation());
        bikeStationValues(values, trip.getEndStation());

        values
                .add(trip.getBikeId() + "")
                .add(trip.getUserType())
                .add(trip.getBirthYear().orElse(null) + "")
                .add(trip.getGender() + "");

        return values.build();
    }

    public static String bikeTripMappedCsv(BikeTrip trip, Function<String,String> fieldMapper) {
        return bikeTripValues(trip)
                .map(fieldMapper)
                .collect(Collectors.joining(","));
    }

    public static String bikeTripQuotedCsv(BikeTrip trip) {
        return bikeTripMappedCsv(trip, s -> "null".equals(s) ? s : "'" + s + "'");
    }

    public static String bikeTripCopyCsv(BikeTrip trip) {
        return bikeTripMappedCsv(trip, s -> "null".equals(s) ? "\\N" : s);
    }
}
