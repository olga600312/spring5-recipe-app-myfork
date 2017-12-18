package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm a service.");
        Set<Recipe> set = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(set::add);
        return set;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optional = recipeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        return optional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe recipe = recipeCommandToRecipe.convert(command);
        assert recipe != null;
        // If the entity is new it will call persist on the entity manager, otherwise it will call merge
        recipe = recipeRepository.save(recipe);
        log.debug("Save recipy " + recipe.getId());
        return recipeToRecipeCommand.convert(recipe);

    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        Recipe recipe=findById(id);
        return recipeToRecipeCommand.convert(recipe);
    }


}
