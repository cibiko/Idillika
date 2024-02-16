import android.content.Context
import android.util.Log
import androidx.core.text.isDigitsOnly
import com.example.idillika.App
import com.example.idillika.Catalog.Data.dto.CatalogDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object FavoritesManager {


    private const val FAVORITES_PREFS_NAME = "Favorites"

    private fun getSharedPreferences(context: Context) =
        context.getSharedPreferences(FAVORITES_PREFS_NAME, Context.MODE_PRIVATE)

    fun addFavorite(context: Context, id: Int) {
        val sharedPrefsEditor = getSharedPreferences(context).edit()
        sharedPrefsEditor.putBoolean(id.toString(), true)
        sharedPrefsEditor.apply()
    }

    fun removeFavorite(context: Context, id: Int) {
        val sharedPrefsEditor = getSharedPreferences(context).edit()
        sharedPrefsEditor.remove(id.toString())
        sharedPrefsEditor.apply()
    }

    suspend fun getAllFavoriteProducts(context: Context): List<CatalogDto> {
        return withContext(Dispatchers.IO) {
            Log.d("FavoritesManager", "Fetching favorite products...")
            val favoriteIds = getSharedPreferences(context).all.keys
                .filter { it != null && it.isDigitsOnly() }
                .map { it.toInt() }

            val favoriteProducts = App.restApi.getProductsList()
                .filter { it.available && it.id in favoriteIds }

            Log.d("FavoritesManager", "Favorite products fetched: $favoriteProducts")
            favoriteProducts
        }
    }


    fun isHeartEnabled(context: Context, id: String): Boolean {
        return getSharedPreferences(context).getBoolean("heart_$id", false)
    }

    fun setHeartEnabled(context: Context, id: String, enabled: Boolean) {
        val sharedPrefsEditor = getSharedPreferences(context).edit()
        sharedPrefsEditor.putBoolean("heart_$id", enabled)
        sharedPrefsEditor.apply()
    }
}