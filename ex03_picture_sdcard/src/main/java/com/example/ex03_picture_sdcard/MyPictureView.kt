package com.example.ex03_picture_sdcard

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import java.lang.Exception

class MyPictureView(context: Context?, attrs: AttributeSet?) : View(context, attrs)
{
    var imgPath : String = ""

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        try {
            //1.renoir01.png -> 2.byte[]배열 -> 3.bitmap 객체로 -> 4.canvas에 붙임
            //         var bitmap : Bitmap = BitmapFactory.decodeFile("renoir01.png")
          if(imgPath != null) {
              var bitmap: Bitmap = BitmapFactory.decodeFile(imgPath)//sd카드의 파일 경로
              canvas!!.scale(2f, 2f, 0f, 0f)
              canvas!!.drawBitmap(bitmap, 0f, 0f, null)

              bitmap!!.recycle()
          }
        }
        catch(e : Exception)
        {
            e.printStackTrace()
        }
    }
}