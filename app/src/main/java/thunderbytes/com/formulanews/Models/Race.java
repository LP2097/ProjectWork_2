package thunderbytes.com.formulanews.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import thunderbytes.com.formulanews.RoomDataConverters.DateConverter;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
    @ForeignKey(entity = Circuit.class, parentColumns = "circuitId", childColumns = "circuitId", onDelete = CASCADE),
    @ForeignKey(entity = Season.class, parentColumns = "seasonYear", childColumns = "season")
})
public class Race implements Serializable {

    @PrimaryKey
    private int Id;

    @ColumnInfo(index = true)
    private int season;

    @ColumnInfo
    private int round;

    @ColumnInfo
    private String url;

    @ColumnInfo
    private String raceName;

    @ColumnInfo(index = true)
    public String circuitId;

    @Ignore
    public Circuit circuit;

    @TypeConverters(DateConverter.class)
    @ColumnInfo
    private Date date;

    @ColumnInfo
    private String time;

    @Ignore
    public ArrayList<Results> Results;

    @Ignore
    public ArrayList<Qualifying> QualifyingResults;

    @Ignore
    public ArrayList<Qualifying> getQualifyingResults() {
        return QualifyingResults;
    }

    public void setQualifyingResults(ArrayList<Qualifying> qualifyingResults) {
        QualifyingResults = qualifyingResults;
    }

    private boolean notify = false;

    public ArrayList<Results> getResults() {
        return Results;
    }

    public void setResults(ArrayList<Results> results) {
        Results = results;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    @JsonGetter("Circuit")
    public Circuit getCircuit() {
        return circuit;
    }

    @JsonSetter("Circuit")
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }


    @Override
    public String toString() {
        return "Race{}";
    }

}
