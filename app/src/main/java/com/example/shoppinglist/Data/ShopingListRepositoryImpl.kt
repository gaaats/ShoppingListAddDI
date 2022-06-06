package com.example.shoppinglist.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.Domain.ShopingListRepository
import com.github.javafaker.Faker
import java.lang.RuntimeException
import kotlin.random.Random

object ShopingListRepositoryImpl : ShopingListRepository {

    //        private val shopingList = mutableListOf<BuyItem>()
    private val shopingList = sortedSetOf<BuyItem>({ o1, o2 -> o1.id.compareTo(o2.id) })


    private val shopListMutableLiveData_ = MutableLiveData<List<BuyItem>>()
    val shopListLiveData: LiveData<List<BuyItem>> = shopListMutableLiveData_
    val faker = Faker()
//    private val shopingList = sortedSetOf<BuyItem>({o1, o2 -> o1.id.compareTo(o2.id)})

    private var autoIncrement = 0

    init {
        for (i in 0..10) {
            val element = BuyItem(
                faker.food().fruit().toString(),
                Random.nextInt(1, 20),
                Random.nextBoolean()
            )
            addItemToList(element)
        }
    }

    override fun addItemToList(buyItem: BuyItem) {
        if (buyItem.id == BuyItem.DEFAULT_INDEX) {
            buyItem.id = autoIncrement++
        }
        shopingList.add(buyItem)
        updateShopListLiveData()
    }

    override fun edit(buyItem: BuyItem) {
//        val index = shopingList.indexOf(take(buyItem.id))
//        shopingList[index] = buyItem
//        updateShopListLiveData()
//
        val oldElement = take(buyItem.id)
        shopingList.remove(oldElement)
        addItemToList(buyItem)
    }

    override fun getShopingList(): LiveData<List<BuyItem>> {
        return shopListLiveData
    }

    override fun deleteItem(buyItem: BuyItem) {
        shopingList.remove(buyItem)
        updateShopListLiveData()
    }

    override fun take(idOfItem: Int): BuyItem {
//        return shopingList[idOfItem]
        return shopingList.find { it.id == idOfItem }
            ?: throw RuntimeException("there is no such element in List")
    }

    private fun updateShopListLiveData() {
        shopListMutableLiveData_.value = shopingList.toList()
    }
}