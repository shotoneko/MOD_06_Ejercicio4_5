package modulo_05.sprint.retrofitnews.navigation

import android.os.Bundle
import androidx.navigation.NavType
import android.os.Build
import android.os.Parcelable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Provides a NavType mapper for Parcelable objects.
 *
 * This function generates a NavType for a specified Parcelable type, enabling
 * the navigation component to handle Parcelable objects in a type-safe manner.
 *
 * @return A NavType instance for the specified Parcelable type.
 */
inline fun <reified T : Parcelable> NavType.Companion.mapper(): NavType<T> {
    return object : NavType<T>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }

        override fun get(bundle: Bundle, key: String): T? {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                @Suppress("DEPRECATION")
                bundle.getParcelable(key)
            } else {
                bundle.getParcelable(key, T::class.java)
            }
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString<T>(value)
        }

        override fun serializeAsValue(value: T): String {
            return Json.encodeToString(value)
        }

        override val name = T::class.java.name
    }
}