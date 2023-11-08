package com.mindsim.petroapi.csvhandlers;

import com.mindsim.petroapi.shared.InfluxResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CSVOperations {
    public static void writeInfluxToCSV(List<InfluxResponse> influxResponseList){
        Date agora = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\arquivosCSV\\dadosInflux"+simpleDateFormat.format(agora)+".csv"))){
            bufferedWriter.write("id, time, value, field, measurement, mfg, name, plataforma");
            bufferedWriter.newLine();
            for(InfluxResponse i : influxResponseList){
                bufferedWriter.write(i.getId() + "," + i.getTime().format(dateTimeFormatter) + "," + i.getValue() + "," +
                        i.getField() + "," + i.getMeasurement() + "," + i.getMfg() + "," + i.getName() + "," + i.getPlataforma());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
