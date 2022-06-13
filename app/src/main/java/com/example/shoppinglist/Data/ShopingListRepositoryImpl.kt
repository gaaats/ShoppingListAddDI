package com.example.shoppinglist.Data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.Domain.ShopingListRepository
import com.github.javafaker.Faker
import java.lang.RuntimeException
import kotlin.random.Random

class ShopingListRepositoryImpl(application: Application) : ShopingListRepository {

    private val shopListDao = ShopListDataBase.getDataBase(application).getShopListDAO()
    private val mapper = Mapper()
    /*
    OLD, VITHOUT DATABASE
//    //        private val shopingList = mutableListOf<BuyItem>()
//    private val shopingList = sortedSetOf<BuyItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
//
//
//    private val shopListMutableLiveData_ = MutableLiveData<List<BuyItem>>()
//    val shopListLiveData: LiveData<List<BuyItem>> = shopListMutableLiveData_
//    val faker = Faker()
////    private val shopingList = sortedSetOf<BuyItem>({o1, o2 -> o1.id.compareTo(o2.id)})
//
//    private var autoIncrement = 0
//
//    init {
//        for (i in 0..10) {
//            val element = BuyItem(
//                faker.food().fruit().toString(),
//                Random.nextInt(1, 20),
//                Random.nextBoolean()
//            )
//            addItemToList(element)
//        }
//    }

*/

    override fun addItemToList(buyItem: BuyItem) {
        shopListDao.addShopItemToDB(mapper.mapEntityToDbItem(buyItem))
    }

    override fun edit(buyItem: BuyItem) {
        shopListDao.addShopItemToDB(mapper.mapEntityToDbItem(buyItem))
    }

    override fun deleteItem(buyItem: BuyItem) {
        shopListDao.deleteShopItem(buyItem.id)
    }

    override fun take(idOfItem: Int): BuyItem {
        shopListDao.getSingleItem(idOfItem).also {
            return mapper.mapDbItemToEntity(it)
        }
    }

    override fun getShopingList(): LiveData<List<BuyItem>> =
        Transformations.map(shopListDao.getShopList()) {
            mapper.mapListOfDbToListOfEntity(it)
        }
}