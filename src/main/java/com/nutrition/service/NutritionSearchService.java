package com.nutrition.service;

import com.nutrition.dto.NutritionSearchRequest;
import com.nutrition.dto.Sort;
import com.nutrition.dto.SortField;
import com.nutrition.dto.SortOrder;
import com.nutrition.model.Food;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;

public final class NutritionSearchService {

    private static final EnumMap<SortField, Comparator<Food>> FIELD_COMPARATORS = new EnumMap<>(SortField.class);

    static {
        FIELD_COMPARATORS.put(SortField.CALORIES, Comparator.comparing(
            Food::calories, Comparator.naturalOrder()));
        FIELD_COMPARATORS.put(SortField.NAME, Comparator.comparing(
            Food::name, String.CASE_INSENSITIVE_ORDER));
    }

    private final List<Food> items;

    public NutritionSearchService(List<Food> files) {
        this.items = files;
    }

    public List<Food> searchNutrition(NutritionSearchRequest request) {
        return items.stream()
            .filter(item -> { return filterItem(item, request);})
            .sorted(buildComparator(request))
            .limit(request.limit())
            .toList();
    }

    private Boolean filterItem(Food item, NutritionSearchRequest request) {
        return filterFatRating(item, request) && filterMaxCalories(item, request) 
            && filterMinCalories(item, request);
    }

    private Boolean filterFatRating(Food item, NutritionSearchRequest request) {
        return (request.fatRating() == null || item.fatRating().equals(request.fatRating()));
    }

    private Boolean filterMaxCalories(Food item, NutritionSearchRequest request) {
        return (request.maxCalories() == null || item.calories()<=request.maxCalories());
    }

    private Boolean filterMinCalories(Food item, NutritionSearchRequest request) {
        return (request.minCalories() == null || item.calories()>=request.minCalories());
    }

    private Comparator<Food> buildComparator(NutritionSearchRequest request) {
        return request.sorts().stream()
            .map(this::getComparator)
            .reduce(Comparator::thenComparing)
            .orElse((h1, h2) -> 0);
    }

    private Comparator<Food> getComparator(Sort sort) {
        var comparator = FIELD_COMPARATORS.get(sort.field());
        return sort.order() == SortOrder.ASC ? comparator : comparator.reversed();
    }
}
