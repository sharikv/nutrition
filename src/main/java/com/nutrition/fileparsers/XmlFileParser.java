package com.nutrition.fileparsers;

import java.io.File;
import java.util.List;

import com.nutrition.exception.XmlParsingException;
import com.nutrition.model.Food;

public class XmlFileParser implements FileParser{
    
    public List<Food> parse(File file) throws XmlParsingException{
        throw new XmlParsingException();
    }
}
