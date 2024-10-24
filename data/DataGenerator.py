from RecipeProvider import generateRecipes
from ItemModelProvider import generateItemModels
from Core.Output import Output

output = Output('../src/generated/assets/', 'dragonmounts')
generateRecipes(output)
generateItemModels(output)