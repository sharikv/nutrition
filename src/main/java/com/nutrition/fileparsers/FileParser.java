package com.nutrition.fileparsers;

import java.io.File;
import java.util.List;

import com.nutrition.exception.FileLoadingException;
import com.nutrition.model.Food;

public interface FileParser {
    public List<Food> parse(File file) throws FileLoadingException;    
} 
