package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticDTO {

    private String url;

    private int total;

}
