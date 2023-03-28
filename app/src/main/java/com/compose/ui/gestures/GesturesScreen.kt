package com.compose.ui.gestures

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import com.compose.R
class GesturesScreen {

    //随意拖动
    @Preview
    @Composable
    fun GesturesScreenDragUi(){
        Box(modifier = Modifier.fillMaxSize()) {
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Box(
                Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .background(Color.Blue)
                    .size(50.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consumeAllChanges()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            )
        }

    }


    /**
     * 基本能用了。
     */
    @OptIn(ExperimentalComposeUiApi::class)
    @Preview
    @Composable
        fun TransformableSample() {
            // set up all transformation states
            var scale by remember { mutableStateOf(1f) }
            var rotation by remember { mutableStateOf(0f) }
            var offset by remember { mutableStateOf(Offset.Zero) }

           var offsetX by remember { mutableStateOf(0f) }
           var offsetY by remember { mutableStateOf(0f) }

            val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
                scale *= zoomChange
                rotation += rotationChange
                offset += offsetChange
            }
          //  Image(painter = painterResource(id = R.drawable.ab1_inversions), contentDescription = null)

        Image(painter = painterResource(id = R.drawable.ab1_inversions), contentDescription = null,
                Modifier
                    // apply other transformations like rotation and zoom
                    // on the pizza slice emoji
                   // .pointerInput(Unit)
                    .pointerInteropFilter {

                        when(it.action){

                           MotionEvent.ACTION_UP->{

                               Log.d("mytest","--------MotionEvent.ACTION_UP-------------")
                               false
                           }
                            else->false
                        }

                        true
                    }
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        rotationZ = 0f,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                   .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {

                                Log.d("mytest", "onDragEnd")
                            },
                            onDragCancel = {
                                Log.d("mytest", "onDragCancel")
                            }

                        ) { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                            Log.d("mytest", "detectDragGestures tap")
                        }



                    }
                    .pointerInput(Unit) {

                        detectTapGestures(onDoubleTap = {


                            println("ImageBrowserItem detectTapGestures onDoubleTap offset: $it")
                            scale = if (scale <= 1f) {
                                2f
                            } else {
                                1f
                            }
                            offset = Offset.Zero

                        })
                    }.pointerInput(Unit){

                        forEachGesture {

                            awaitPointerEventScope {

                                val  downPointer=awaitFirstDown()

                                while (true){

                                    val event=awaitDragOrCancellation(downPointer.id)
                                    if(event==null){
                                        break
                                    }
                                    println("--------------------------------------forEachGesture")
                                    offset+=event.positionChange()

                                }

                            }
                        }

                    }
                    // add transformable to listen to multitouch transformation events
                    // after offset
                    .transformable(state = state)
                    //.background(Color.Blue)
                    .fillMaxSize()
            )
        }

    @Preview
    @Composable
    fun TransformGestureDemo() {
        var boxSize = 100.dp
        var offset by remember { mutableStateOf(Offset.Zero) }
        var ratationAngle by remember { mutableStateOf(0f) }
        var scale by remember { mutableStateOf(1f) }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(Modifier
                .size(boxSize)
                .rotate(ratationAngle) // 需要注意offset与rotate的调用先后顺序
                .scale(scale)
                .offset {
                    IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                }
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true, // 平移或放大时是否可以旋转
                        onGesture = { centroid: Offset, pan: Offset, zoom: Float, rotation: Float ->
                            offset += pan
                            scale *= zoom
                            ratationAngle += rotation
                        }
                    )
                }
            )
        }
    }

}