package com.nutrition.fileparsers;

import java.io.File;
import java.util.List;

import com.nutrition.exception.JsonParsingException;
import com.nutrition.model.Food;

public class JsonFileParser implements FileParser {
    
    public List<Food> parse(File file) throws JsonParsingException{
        throw new JsonParsingException();
    }
}
