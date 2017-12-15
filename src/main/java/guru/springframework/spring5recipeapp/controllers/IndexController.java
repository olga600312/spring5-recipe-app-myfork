package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;
   /* private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }*/

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","/index"})
   //@RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        log.debug("Getting Index page");
        /*Optional<Category>category= categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure>unitOfMeasure= unitOfMeasureRepository.findByDescription("Teaspoon");*/
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
