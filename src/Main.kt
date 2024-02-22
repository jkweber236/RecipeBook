//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun displayRecipe(recipe: Recipe) {
    println("Recipe name: ${recipe.name}")
    println("Category: ${recipe.category}")
    println("Ingredients: ${recipe.ingredients}")
    println("Instructions: ${recipe.instructions}")
}
fun main() {

    var running = true
    val recipe1 = Recipe().apply {
        category = "Italian"
        name = "Pasta Carbonara"
        ingredients = "Spaghetti, Bacon, Eggs, Parmesan"
        instructions = "Cook pasta, fry bacon, mix with eggs and Parmesan"
    }


    val recipe2 = Recipe().apply {
        category = "Asian"
        name = "Chicken Stir Fry"
        ingredients = "Chicken, Vegetables, Soy Sauce, Rice"
        instructions = "Stir-fry chicken and vegetables, add soy sauce, serve over rice"
    }

    val recipes = mutableListOf(recipe1, recipe2)

while (running) {
        println("Main menu: ")
        println("1. Add recipe")
        println("2. Search for recipe")
        println("3. Delete recipe")
        println("4. Display all recipes")
        println("5. Quit")
        print("> ")
        val userInput: String? = readLine()
        val userChoice: Int = userInput?.toIntOrNull() ?: 0
        println()

        when (userChoice) {
            1 -> {
                println("Recipe")
                print("Name: ")
                val recipeName: String = readLine() ?: ""
                print("Category: ")
                val recipeCategory: String = readLine() ?: ""
                print("Ingredients: ")
                val recipeIngredients: String = readLine() ?: ""
                print("Instructions: ")
                val recipeInstructions: String = readLine() ?: ""

                val recipe = Recipe().apply {
                    category = recipeCategory
                    name = recipeName
                    ingredients = recipeIngredients
                    instructions = recipeInstructions
                }
                println()
                recipes.add(recipe)
            }

            2 -> {
                println("Search by: ")
                println("1. Name")
                println("2. Category")
                println("3. Ingredient")
                print("> ")

                val searchInput: String? = readLine()
                val searchChoice: Int = searchInput?.toIntOrNull() ?: 0

                println()

                when (searchChoice) {
                    1 -> {
                        print("Enter the name to search: ")
                        val nameToSearch: String? = readLine()
                        println()

                        for (recipe in recipes) {
                            if (recipe.name.contains(nameToSearch.toString(), ignoreCase = true)) {
                                displayRecipe(recipe)
                                println()
                            }
                        }
                    }
                    2 -> {
                        print("Enter the category to search: ")
                        val categoryToSearch: String? = readLine()
                        println()

                        for (recipe in recipes) {
                            if (recipe.category.equals(categoryToSearch, ignoreCase = true)) {
                                displayRecipe(recipe)
                                println()
                            }
                        }
                    }
                    3 -> {
                        print("Enter the ingredient to search: ")
                        val ingredientToSearch: String? = readLine()
                        println()

                        for (recipe in recipes) {
                            if (recipe.ingredients.contains(ingredientToSearch.toString(), ignoreCase = true)) {
                                displayRecipe(recipe)
                                println()
                            }
                        }
                    }
                    else -> {
                        println("Invalid choice. Please enter a number from 1 to 3.")
                    }
                }
            }

            3 -> {
                print("Enter the name of the recipe you would like to delete:  ")
                val recipeToDelete: String? = readLine()

                val recipesToRemove = mutableListOf<Recipe>()

                for (recipe in recipes) {
                    if (recipe.name.contains(recipeToDelete.toString(), ignoreCase = true)) {
                        displayRecipe(recipe)
                        println("")
                        print("Would you like to delete this recipe (yes/no)? ")
                        val confirm: String? = readLine()

                        if (confirm.equals("yes", ignoreCase = true)) {
                            recipesToRemove.add(recipe)
                            println("Deletion successful.")
                            println()
                        } else if (confirm.equals("no", ignoreCase = true)) {
                            println("Deletion cancelled.")
                            println()
                        } else {
                            println("Invalid input.")
                            println()
                        }
                    }
                }
                recipes.removeAll(recipesToRemove)
            }

            4 -> {
                for (recipe in recipes) {
                    println("Category: ${recipe.category}")
                    println("Name: ${recipe.name}")
                    println("Ingredients: ${recipe.ingredients}")
                    println("Instructions: ${recipe.instructions}\n")
                }
            }

            5 -> {
                println("Quitting...")
                running = false
            }

            else -> {
                println("Invalid choice. Please enter a number from 1 to 5.")
            }
        }
    }
}