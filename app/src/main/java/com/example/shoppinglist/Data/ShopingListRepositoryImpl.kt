package com.example.shoppinglist.Data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shoppinglist.Data.mapper.Mapper
import com.example.shoppinglist.Domain.model.BuyItem
import com.example.shoppinglist.Domain.repository.ShopingListRepository
import javax.inject.Inject

class ShopingListRepositoryImpl @Inject constructor(
    private val shopListDao: ShopListDAO,
    private val mapper: Mapper
) : ShopingListRepository {

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

    override suspend fun addItemToList(buyItem: BuyItem) {
        shopListDao.addShopItemToDB(mapper.mapEntityToDbItem(buyItem))
    }

    override suspend fun edit(buyItem: BuyItem) {
        shopListDao.addShopItemToDB(mapper.mapEntityToDbItem(buyItem))
    }

    override suspend fun deleteItem(buyItem: BuyItem) {
        shopListDao.deleteShopItem(buyItem.id)
    }

    override suspend fun take(idOfItem: Int): BuyItem {
        shopListDao.getSingleItem(idOfItem).also {
            return mapper.mapDbItemToEntity(it)
        }
    }

    override fun getShopingList(): LiveData<List<BuyItem>> =
        Transformations.map(shopListDao.getShopList()) {
            mapper.mapListOfDbToListOfEntity(it)
        }
}