// Function to display a recipe with formatted output.
fun displayRecipe(recipe: Recipe) {
    // ANSI escape code for bold text
    val boldStart = "\u001B[1m"
    val boldEnd = "\u001B[0m"

    // Printing section names and the information associated.
    println("${boldStart}Recipe name:${boldEnd} ${recipe.name}")
    println("${boldStart}Category:${boldEnd} ${recipe.category}")
    println("${boldStart}Ingredients:${boldEnd}")

    // Splitting the ingredients section to be printed in list format.
    recipe.ingredients.split(", ").forEachIndexed { index, ingredient ->
        println("  ${index + 1}. $ingredient")
    }
    // Splitting the instructions section to be printed in list format.
    println("${boldStart}Instructions:${boldEnd}")
    recipe.instructions.split(", ", ". ").forEachIndexed { index, step ->
        println("  ${index + 1}. $step")
    }
    println()
}

fun main() {
    // Call and store the file handler class in a variable.
    val fileHandler = RecipeFileHandler()
    // Create a mutable list with the data loaded in from the file.
    val recipes: MutableList<Recipe> = fileHandler.loadFromFile().toMutableList()

    // Continues running until the user quits the program.
    var running = true
    while (running) {
        // Display the menu items
        println("Main menu: ")
        println("1. Add recipe")
        println("2. Search for recipe")
        println("3. Delete recipe")
        println("4. Display all recipes")
        println("5. Quit")
        print("> ")
        // Retrieve user input
        val userInput: String? = readlnOrNull()
        val userChoice: Int = userInput?.toIntOrNull() ?: 0
        println()

        when (userChoice) {
            // Add a recipe
            1 -> {
                println("Recipe")
                // Input for recipe name
                print("Name: ")
                val recipeName: String = readlnOrNull() ?: ""
                // Input for category
                print("Category: ")
                val recipeCategory: String = readlnOrNull() ?: ""

                // Input for ingredients
                // Put ingredients in a list to use for displaying
                val ingredientsList = mutableListOf<String>()
                print("Ingredients (enter each ingredient separated by commas): ")
                val recipeIngredients: String = readlnOrNull() ?: ""
                ingredientsList.addAll(recipeIngredients.split(", "))

                // Input for instructions
                // Put instructions in a list to use for displaying
                val instructionsList = mutableListOf<String>()
                print("Instructions (enter each step separated by commas): ")
                val recipeInstructions: String = readlnOrNull() ?: ""
                instructionsList.addAll(recipeInstructions.split(", "))

                // Creating a new recipe instance using apply block for property initialization
                val recipe = Recipe().apply {
                    // Set the values in each section based on the values provided
                    category = recipeCategory
                    name = recipeName
                    ingredients = ingredientsList.joinToString(", ")
                    instructions = instructionsList.joinToString(", ")
                }
                println()
                // Add the recipes to the recipes list
                recipes.add(recipe)
            }

            // Search for a recipe
            2 -> {
                // Display the sections the user can search by
                println("Search by: ")
                println("1. Name")
                println("2. Category")
                println("3. Ingredient")
                print("> ")

                // Get user's search choice 
                val searchInput: String? = readlnOrNull()
                val searchChoice: Int = searchInput?.toIntOrNull() ?: 0

                println()

                // Handle user choices based on the selected menu option
                when (searchChoice) {
                    // Search by name
                    1 -> {
                        print("Enter the name to search: ")
                        val nameToSearch = readlnOrNull()

                        println()
                        // Create a variable to track if a recipe was found with the given name
                        var inRecipe = false

                        // Iterate through recipes to find ones that match the name
                        for (recipe in recipes) {
                            if (recipe.name.contains(nameToSearch.toString(), ignoreCase = true)) {
                                // Display matching recipes
                                displayRecipe(recipe)
                                inRecipe = true
                            }
                        }
                        // Display a message if no matches were found
                        if (!inRecipe) {
                            println("No recipes were found with the name '$nameToSearch'")
                            println()
                        }
                    }

                    // Search by category
                    2 -> {
                        print("Enter the category to search: ")
                        val categoryToSearch = readlnOrNull()

                        println()
                        // Create a variable to track if a recipe was found in the given category
                        var inRecipe = false

                        // Iterate through recipes to find ones that match the category
                        for (recipe in recipes) {
                            if (recipe.category.equals(categoryToSearch, ignoreCase = true)) {
                                // Display matching recipes
                                displayRecipe(recipe)
                                inRecipe = true
                            }
                        }
                        // Display a message if no matches were found
                        if (!inRecipe) {
                            println("There are no recipes in the '$categoryToSearch' category.")
                            println()
                        }
                    }

                    // Search by ingredient
                    3 -> {
                        print("Enter the ingredient to search: ")
                        val ingredientToSearch = readlnOrNull()

                        println()
                        // Create a variable to track if a recipe was found with the given ingredient
                        var inRecipe = false

                        // Iterate through recipes to find ones with a matching ingredient
                        for (recipe in recipes) {
                            if (recipe.ingredients.contains(ingredientToSearch.toString(), ignoreCase = true)) {
                                // Display matching recipes
                                displayRecipe(recipe)
                                inRecipe = true
                            }
                        }
                        // Display a message if no matches were found
                        if (!inRecipe) {
                            println("There are no recipes that contain '$ingredientToSearch'")
                            println()
                        }
                    }

                    // Handle invalid search choices
                    else -> {
                        println("Invalid choice. Please enter a number from 1 to 3.")
                    }
                }
            }

            // Delete a recipe
            3 -> {
                print("Enter the name of the recipe you would like to delete:  ")
                val recipeToDelete: String? = readlnOrNull()

                // Create a mutable list for recipes that are to be removed
                val recipesToRemove = mutableListOf<Recipe>()
                var exists = false

                // Iterate through recipes to find matching names for deleting
                for (recipe in recipes) {
                    if (recipe.name.contains(recipeToDelete.toString(), ignoreCase = true)) {
                        // Display the recipe to the user (to make sure it's the correct one)
                        displayRecipe(recipe)
                        exists = true
                        println("")
                        // Ask for confirmation
                        print("Would you like to delete this recipe (yes/no)? ")
                        val confirm: String? = readlnOrNull()

                        // Confirm deletion based on user input
                        if (confirm.equals("yes", ignoreCase = true)) {
                            // Add to the list of recipes to be removed
                            recipesToRemove.add(recipe)
                            println("Deletion successful.")
                            println()
                        // Confirm cancellation based on user input
                        } else if (confirm.equals("no", ignoreCase = true)) {
                            println("Deletion cancelled.")
                            println()
                        // Handles invalid input
                        } else {
                            println("Invalid input.")
                            println()
                        }
                    }
                }
                // Display message if no matching recipes were found for deletion
                if (!exists) {
                    println("No recipes were found with the name '$recipeToDelete'")
                }
                recipes.removeAll(recipesToRemove)
            }

            // Display all recipes
            4 -> {
                // Iterate through recipes and display each one
                for (recipe in recipes) {
                    displayRecipe(recipe)
                }
            }

            // Quit the program
            5 -> {
                // Save recipes to file before quitting
                fileHandler.saveToFile(recipes)
                println("Quitting...")
                running = false
            }

            // Handle invalid menu choice
            else -> {
                println("Invalid choice. Please enter a number from 1 to 5.")
            }
        }
    }
}