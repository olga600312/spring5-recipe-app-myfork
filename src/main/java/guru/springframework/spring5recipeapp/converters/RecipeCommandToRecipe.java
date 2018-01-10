package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    @Autowired
    private IngredientCommandToIngredient ingredientConverter;
    @Autowired
    private NotesCommandToNotes notesConverter;
    @Autowired
    private CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Nullable
    @Override
    public Recipe convert(RecipeCommand src) {
        if (src == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(src.getId());
        recipe.setDescription(src.getDescription());
        recipe.setCookTime(src.getCookTime());
        recipe.setDifficulty(src.getDifficulty());
        recipe.setDirections(src.getDirections());
        recipe.setServings(src.getServings());
        recipe.setPrepTime(src.getPrepTime());
        recipe.setSource(src.getSource());
        recipe.setUrl(src.getUrl());
        recipe.setImage(src.getImage());
        recipe.setNotes(notesConverter.convert(src.getNotes()));

        Set<Category> categorySet = new HashSet<>();
        if (src.getCategories() != null && src.getCategories().size() > 0) {
            src.getCategories().forEach(categoryCommand -> categorySet.add(categoryConverter.convert(categoryCommand)));
        }
        recipe.setCategories(categorySet);
        if (src.getIngredients() != null && src.getIngredients().size() > 0) {
            src.getIngredients()
                    .forEach(ingredientCommand -> recipe.addIngredient(ingredientConverter.convert(ingredientCommand)));
        }
        return recipe;
    }
}
