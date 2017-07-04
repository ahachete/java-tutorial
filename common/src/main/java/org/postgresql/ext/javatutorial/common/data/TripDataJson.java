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


import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;


public class TripDataJson {
    @SerializedName("start_time") private LocalDateTime startTime;
    @SerializedName("stop_time") private LocalDateTime stopTime;
    @SerializedName("start_station_id") private int startStationId;
    @SerializedName("start_station_name") private String startStationName;
    @SerializedName("start_station_latitude") private double startStationLatitude;
    @SerializedName("start_station_longitude") private double startStationLongitude;
    @SerializedName("end_station_id") private int endStationId;
    @SerializedName("end_station_name") private String endStationName;
    @SerializedName("end_station_latitude") private double endStationLatitude;
    @SerializedName("end_station_longitude") private double endStationLongitude;
    @SerializedName("bike_id") private int bikeId;
    @SerializedName("user_type") private String userType;
    @SerializedName("birth_year") private short birthYear;
    private short gender;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public int getStartStationId() {
        return startStationId;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public double getStartStationLatitude() {
        return startStationLatitude;
    }

    public double getStartStationLongitude() {
        return startStationLongitude;
    }

    public int getEndStationId() {
        return endStationId;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public double getEndStationLatitude() {
        return endStationLatitude;
    }

    public double getEndStationLongitude() {
        return endStationLongitude;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getUserType() {
        return userType;
    }

    public short getBirthYear() {
        return birthYear;
    }

    public short getGender() {
        return gender;
    }
}
