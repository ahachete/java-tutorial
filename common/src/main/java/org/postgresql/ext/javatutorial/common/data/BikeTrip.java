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


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;


public class BikeTrip {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH
    );

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final int bikeId;
    private final String userType;
    private final Optional<Short> birthYear;
    private final short gender;
    private final BikeStation startStation;
    private final BikeStation endStation;

    public BikeTrip(
            LocalDateTime startDate, LocalDateTime endDate, int bikeId, String userType, Optional<Short> birthYear,
            short gender, BikeStation startStation, BikeStation endStation
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.bikeId = bikeId;
        this.userType = userType;
        this.birthYear = birthYear;
        this.gender = gender;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public BikeTrip(
            String startDate, String endDate, String bikeId, String userType, String birthYear, String gender,
            BikeStation startStation, BikeStation endStation
    ) {
        this(
                LocalDateTime.parse(startDate, TIMESTAMP_FORMATTER),
                LocalDateTime.parse(endDate, TIMESTAMP_FORMATTER),
                Integer.parseInt(bikeId),
                userType,
                Optional.ofNullable(
                null == birthYear || birthYear.isEmpty() ? null : new Short((short) Integer.parseInt(birthYear))),
                (short) Integer.parseInt(gender),
                startStation,
                endStation
        );
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getUserType() {
        return userType;
    }

    public Optional<Short> getBirthYear() {
        return birthYear;
    }

    public short getGender() {
        return gender;
    }

    public BikeStation getStartStation() {
        return startStation;
    }

    public BikeStation getEndStation() {
        return endStation;
    }

    @Override
    public String toString() {
        return "BikeTrip{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", bikeId=" + bikeId +
                ", userType='" + userType + '\'' +
                ", birthYear=" + birthYear +
                ", gender=" + gender +
                ", startStation=" + startStation +
                ", endStation=" + endStation +
                '}';
    }
}
