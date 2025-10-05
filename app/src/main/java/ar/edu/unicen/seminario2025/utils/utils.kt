package ar.edu.unicen.seminario2025.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream

fun getImageUriFromUrl(context: Context, imageUrl: String): Uri {
    val bitmap = Glide.with(context)
        .asBitmap()
        .load(imageUrl)
        .submit()
        .get()

    val cachePath = File(context.cacheDir, "images")
    cachePath.mkdirs()
    val file = File(cachePath, "shared_image.png")
    FileOutputStream(file).use { out -> bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) }

    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
}
