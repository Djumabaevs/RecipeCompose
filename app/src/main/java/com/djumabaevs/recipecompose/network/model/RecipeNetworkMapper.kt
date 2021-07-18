package com.djumabaevs.recipecompose.network.model

import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.domain.util.EntityMapper

class RecipeNetworkMapper: EntityMapper<RecipeNetworkEntity, Recipe> {

    override fun mapFromEntity(entity: RecipeNetworkEntity): Recipe {
        return Recipe(
            id = entity.pk,
            title = entity.title,
            featuredImage = entity.featuredImage,
            rating = entity.rating,
            publisher = entity.publisher,
            sourceUrl = entity.sourceUrl,
            description = entity.description,
            cookingInstructions = entity.cookingInstructions,
            ingredients = entity.ingredients,
            dateAdded = entity.dateAdded,
            dateUpdated = entity.dateUpdated

        )
    }

    override fun mapToEntity(domainModel: Recipe): RecipeNetworkEntity {

    }


}