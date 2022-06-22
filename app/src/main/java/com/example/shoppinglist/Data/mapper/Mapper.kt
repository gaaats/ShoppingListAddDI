package com.example.shoppinglist.Data.mapper

import com.example.shoppinglist.Data.ShopItemDBModel
import com.example.shoppinglist.Domain.model.BuyItem

class Mapper {

    fun mapEntityToDbItem (buyItem: BuyItem)= ShopItemDBModel(
            id = buyItem.id,
            name = buyItem.name,
            total = buyItem.total,
            isBuyed = buyItem.isBuyed
        )
    fun mapDbItemToEntity (shopItemDBModel: ShopItemDBModel) = BuyItem(
        id = shopItemDBModel.id,
        name = shopItemDBModel.name,
        total = shopItemDBModel.total,
        isBuyed = shopItemDBModel.isBuyed
    )

    fun mapListOfDbToListOfEntity (list: List<ShopItemDBModel>) = list.map {
        mapDbItemToEntity(it)
    }
}