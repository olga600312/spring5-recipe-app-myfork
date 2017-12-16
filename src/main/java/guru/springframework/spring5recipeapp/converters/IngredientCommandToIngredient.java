package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {
    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;


    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand src) {
        if(src==null)
            return null;
        Ingredient ingredient=new Ingredient();
        ingredient.setId(src.getId());
        ingredient.setAmount(src.getAmount());
        ingredient.setDescription(src.getDescription());
        ingredient.setUnit(uomConverter.convert(src.getUnit()));
        return ingredient;
    }
}
