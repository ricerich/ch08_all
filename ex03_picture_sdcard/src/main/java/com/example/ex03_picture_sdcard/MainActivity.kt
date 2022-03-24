package com.example.ex03_picture_sdcard

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File

class MainActivity : AppCompatActivity()
{
//    var imgFileArr : Array<File>? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "간단 이미지 뷰어"
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        var btnPrev = findViewById<Button>(R.id.btnPrev)
        var btnNext = findViewById<Button>(R.id.btnNext)
        var tv1 = findViewById<TextView>(R.id.tv1)

        var myPictureView = findViewById<MyPictureView>(R.id.myPictureView1)

        //File() 객체는 1.디렉토리(파일여러개목록 가져올수 있음) 2.파일
        val imgFileArr = File(Environment.getExternalStorageDirectory().absolutePath + "/Pictures").listFiles()
        var imgFileName = imgFileArr[0].toString()
        myPictureView.imgPath = imgFileName

        tv1.text = "1 / " + imgFileArr.size

        var curNum : Int = 0 //현재 위치번호
        btnPrev.setOnClickListener {
            if(curNum == 0)
            {
//                Toast.makeText(this, "첫번째그림임!", Toast.LENGTH_SHORT).show()
                curNum = imgFileArr.size-1
            }
            else {
                curNum--
            }
            var imgFileName = imgFileArr[curNum].toString()
            myPictureView.imgPath = imgFileName
            myPictureView.invalidate()

            tv1.text = ""+(curNum+1) +" / "+ imgFileArr.size
        }
        btnNext.setOnClickListener {
            if(curNum == imgFileArr.size-1)
            {
//                Toast.makeText(this, "마지막그림임!", Toast.LENGTH_SHORT).show()
                curNum = 0
            }
            else {
                curNum++
            }
            var imgFileName = imgFileArr[curNum].toString()
            myPictureView.imgPath = imgFileName
            myPictureView.invalidate()

            tv1.text = ""+(curNum+1) + " / " + imgFileArr.size
        }


    }
}
