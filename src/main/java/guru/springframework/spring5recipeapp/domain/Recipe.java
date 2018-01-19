package guru.springframework.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    /*to ensure that when the recipe is persisted, it deletes the Notes entity mapped to it if it is notes property is null. Note that this can have side effects such that if you only want to update the category, and accidentally leave the notes as null, it will be deleted.

        - remember the notes id in the recipeform.html by adding the following line inside the form tag
        <input type="hidden" th:field="*{notes.id}"/>

    Technically, you can use only one of the following but I used both so that 1. I don't create a Notes entity every time I update. and 2. I ensure that there is a true one to one relationship between the Recipe and Notes entities*/
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    @OrderBy("id")
    private Set<Ingredient> ingredients = new HashSet<>();
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }
}
