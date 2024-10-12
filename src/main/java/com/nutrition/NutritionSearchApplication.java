package com.nutrition;

import com.nutrition.exception.FileLoadingException;
import com.nutrition.service.FileParserService;
import com.nutrition.service.NutritionSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class NutritionSearchApplication {

    @Value("${nutrition-search.data.file.name}")
    private String nutritionDataFile;

    @Value("${file-reader.file-format}")
    private String fileFormat;

    @Autowired
    private FileParserService fileParserService;

    @Bean
    public NutritionSearchService nutritionSearchService() {
        File file = readFile();
        return new NutritionSearchService(fileParserService.getFileParser(fileFormat).parse(file));
    }

    @Bean
    public File readFile() {
        try {
            var nutritionData = new ClassPathResource(nutritionDataFile);
            return nutritionData.getFile();
        } catch (IOException ex) {
            throw new FileLoadingException(ex);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NutritionSearchApplication.class, args);
    }
}
