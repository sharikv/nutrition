package com.nutrition.service;

import org.springframework.stereotype.Service;

import com.nutrition.fileparsers.CsvFileParser;
import com.nutrition.fileparsers.FileFormat;
import com.nutrition.fileparsers.FileParser;
import com.nutrition.fileparsers.JsonFileParser;
import com.nutrition.fileparsers.XmlFileParser;

@Service
public class FileParserService {
    
    public FileParser getFileParser(String fileType) {
        FileFormat fileFormat = FileFormat.valueOf(fileType.toUpperCase());
        switch (fileFormat) {
            case CSV:
                return new CsvFileParser();
            case XML:
                return new XmlFileParser();
            case JSON:
                return new JsonFileParser();
            default:
                throw new IllegalArgumentException("Unsupported file format: " + fileFormat);
        }
    }
}
