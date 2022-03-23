package com.example.ex02_raw

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        var btn1 = findViewById<Button>(R.id.btn1)
        var btn2 = findViewById<Button>(R.id.btn2)
        var btn3 = findViewById<Button>(R.id.btn3)
        var btn4 = findViewById<Button>(R.id.btn4)
        var btn5 = findViewById<Button>(R.id.btn5)

        var edt1 = findViewById<EditText>(R.id.edtRaw)

        btn1.setOnClickListener {
            var inputStream1 : InputStream = resources.openRawResource(R.raw.raw_test)

            //1.InputStream -> ByteArray("1.5"int) -> 2.Byte[]배열로 바꿔야함 -> 3.String
            var byteArray : ByteArray = ByteArray(inputStream1.available())
            inputStream1.read(byteArray)
            var str1 : String = byteArray.toString(Charsets.UTF_8)

            edt1.setText(str1)
            inputStream1.close()
        }

        btn2.setOnClickListener {

            var filePath = Environment.getExternalStorageDirectory().absolutePath
            var inputStream1 : InputStream = FileInputStream(filePath + "/Download/sd_test.txt")

            //1.InputStream -> ByteArray("1.5"int) -> 2.Byte[]배열로 바꿔야함 -> 3.String
            var byteArray : ByteArray = ByteArray(inputStream1.available())
            inputStream1.read(byteArray)
            var str1 : String = byteArray.toString(Charsets.UTF_8)

            edt1.setText(str1)
            inputStream1.close()
        }

        btn3.setOnClickListener {
            var filePath = Environment.getExternalStorageDirectory().absolutePath
            var myDir = File( filePath +"/myDirectory")
            myDir.mkdir()
        }

        btn4.setOnClickListener {
            var filePath = Environment.getExternalStorageDirectory().absolutePath
            var myDir = File(filePath+ "/myDirectory")
            myDir.delete()
        }

        btn5.setOnClickListener {
            var sysPath = Environment.getRootDirectory().absolutePath
            var fileArr = File(sysPath).listFiles()

            var str1 : String = ""
            for(i in fileArr.indices)
            {

                if(fileArr[i].isDirectory)//폴더이면
                {
                    str1 = "<폴더임!!>"+fileArr[i].toString()
                }
                else //파일이면
                {
                    str1 = "<파일임!!>"+fileArr[i].toString()
                }

                edt1.setText(edt1.text.toString() + "\n" + str1)

            }
        }
    }
}





