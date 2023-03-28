package com.compose.ui.image

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.swipeable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlin.math.absoluteValue
import com.compose.R
/**
 * 大图预览
 */

fun ImageBrowserScreen() {


}
@Preview
@Composable
fun ImageBrowserItem() {
    /**
     * 缩放比例
     */
    var scale by remember { mutableStateOf(1f) }

    /**
     * 偏移量
     */
    var offset  by remember { mutableStateOf(Offset.Zero) }

    /**
     * 监听手势状态变换
     */
    var state =
        rememberTransformableState(onTransformation = { zoomChange, panChange, rotationChange ->
            scale = (zoomChange * scale).coerceAtLeast(1f)
            scale = if (scale > 5f) {
                5f
            } else {
                scale
            }
            println("ImageBrowserItem detectTapGestures rememberTransformableState scale: $scale")
        })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ab1_inversions),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .transformable(state = state)
                .graphicsLayer {  //布局缩放、旋转、移动变换
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y

               /*     val pageOffset = pagerScope.calculateCurrentOffsetForPage(page = page).absoluteValue
                    if (pageOffset == 1.0f) {
                        scale = 1.0f
                    }
                    println("ImageBrowserItem pagerScope calculateCurrentOffsetForPage pageOffset: $pageOffset")*/
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            println("ImageBrowserItem detectTapGestures onDoubleTap offset: $it")
                            scale = if (scale <= 1f) {
                                2f
                            } else {
                                1f
                            }
                            offset = Offset.Zero
                        },
                        onTap = {

                        }
                    )
                }
        )
    }
}

