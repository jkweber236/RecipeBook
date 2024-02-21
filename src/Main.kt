//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

    val recipes = listOf(recipe1, recipe2)

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

        when (userChoice) {
            1 -> {
                println("You selected 1")
            }

            2 -> {
                println("Search by: ")
                println("1. Name")
                println("2. Category")
                println("3. Ingredient")

                val searchInput: String? = readLine()
                val searchChoice: Int = searchInput?.toIntOrNull() ?: 0

                when (searchChoice) {
                    1 -> {
                        println("You selected 1")
                    }
                    2 -> {
                        println("You selected 2")
                    }
                    3 -> {
                        println("You selected 3")
                    }
                }
                println("You selected 2")
            }

            3 -> {
                println("You selected 3")
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