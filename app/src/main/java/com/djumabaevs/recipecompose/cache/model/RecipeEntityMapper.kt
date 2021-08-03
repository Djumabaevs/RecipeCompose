package com.djumabaevs.recipecompose.cache.model


import com.djumabaevs.recipecompose.domain.model.Recipe
import com.djumabaevs.recipecompose.domain.util.DomainMapper

class RecipeEntityMapper: DomainMapper<RecipeEntity, Recipe> {

    override fun mapToDomainModel(model: RecipeEntity): Recipe {
        return Recipe(
            id = model.id,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = convertIngredientsToList(model.ingredients),
            dateAdded = DateUtils.longToDate(model.dateAdded),
            dateUpdated = DateUtils.longToDate(model.dateUpdated),
        )

    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeEntity {

    }

}