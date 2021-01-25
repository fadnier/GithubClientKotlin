package org.sochidrive.poplib.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import org.sochidrive.poplib.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)

    }
}