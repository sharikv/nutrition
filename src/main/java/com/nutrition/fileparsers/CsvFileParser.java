package com.nutrition.fileparsers;

import com.nutrition.exception.CsvParsingException;
import com.nutrition.model.Food;
import com.nutrition.util.FoodUtility;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CsvFileParser implements FileParser {
    public List<Food> parse(File file) throws CsvParsingException {
        return loadFromCsvFile(file);
    }

    private List<Food> loadFromCsvFile(File file) {
        try (var csvReader = new CSVReaderHeaderAware(new FileReader(file, UTF_8))) {

            Map<String, String> rowData;
            var foods = new ArrayList<Food>();

            while ((rowData = csvReader.readMap()) != null) {
                if (FoodUtility.isValidFood(rowData)) {
                    foods.add(FoodUtility.createFood(rowData));
                }
            }

            return foods;
        } catch (IOException | CsvValidationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
