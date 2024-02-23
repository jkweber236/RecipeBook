import java.io.File

// Class responsible for handling saving and loading of recipes to/from a file
class RecipeFileHandler {

    // Function to save a list of recipes to a file
    fun saveToFile(recipes: List<Recipe>) {
        // Defining the file name
        val fileName = "RecipeBook.txt"
        // Creating a file object to represent the file
        val file = File(fileName)

        try {
            // Build a string containing the content to be written to the file
            val content = buildString {
                // Iterate through each recipe in the list
                for (recipe in recipes) {
                    // Append recipe information to the content string
                    append("Recipe name: ${recipe.name}\n")
                    append("Category: ${recipe.category}\n")
                    append("Ingredients: ${recipe.ingredients}\n")
                    append("Instructions: ${recipe.instructions}\n")
                    // Add an empty line between recipes.
                    append("\n")
                }
            }

            // Write the content string to the file
            file.writeText(content)

        } catch (e: Exception) {
            // Handle any exceptions that may occur during the file writing process
            println("Error saving to file.")
        }
    }

    // Function to load a list of recipes from a file
    fun loadFromFile(): List<Recipe> {
        // Define the file name
        val fileName = "RecipeBook.txt"
        // Create a File object to represent the file
        val file = File(fileName)

        // Create a mutable list to store the loaded recipes
        val recipes = mutableListOf<Recipe>()

        try {
            // Variable o keep track of the current recipe being retrieved
            var currentRecipe: Recipe? = null

            // iterate through each line in the file
            file.forEachLine { line ->
                // Check if the line is blank, indicating the end of a recipe
                if (line.isBlank()) {
                    // Add the current retrieved recipe to the list
                    currentRecipe?.let {
                        recipes.add(it)
                        currentRecipe = null
                    }
                } else {
                    // Split each line into key-value pairs using ": " as the delimiter
                    val (key, value) = line.split (": ", limit = 2)
                    // Process each key-value pair and split the recipe information into sections
                    when(key.trim()) {
                        // If the key is "Recipe name", create a new Recipe instance and set its name
                        "Recipe name" -> currentRecipe = Recipe().apply { name = value }
                        // Set all other sections of the current recipe
                        "Category" -> currentRecipe?.category = value
                        "Ingredients" -> currentRecipe?.ingredients = value
                        "Instructions" -> currentRecipe?.instructions = value
                    }
                }
            }
        } catch (e: Exception) {
            // Handle any exceptions that may occur during the file reading process
            println("Error loading from file: ${e.message}")
        }

        // Return the list of loaded recipes
        return recipes
    }
}