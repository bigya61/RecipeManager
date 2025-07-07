package app.ui;

import java.util.List;

public class RecipeData {
    public String title;
    public String description;
    public String imageUrl;
    public List<String> ingredients;
    public String cookTime;
    public List<String> instructions;

    public RecipeData(String title, String description, String imageUrl, List<String> ingredients, String cookTime,List<String> instructions) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.cookTime = cookTime;
        this.instructions = instructions;
    }
}
