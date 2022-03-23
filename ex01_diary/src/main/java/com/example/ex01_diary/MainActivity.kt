package com.example.ex01_diary

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity()
{
    lateinit var edtDiary: EditText
    lateinit var btnWrite: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dp = findViewById<DatePicker>(R.id.datePicker1)
        edtDiary = findViewById<EditText>(R.id.edtDiary)
        btnWrite = findViewById<Button>(R.id.btnWrite)

        var fileName = ""

        //년,월,일 을 가져온다. (오늘 날짜를 캘린더 객체에서)

        var cal = Calendar.getInstance()
        var year = cal.get(Calendar.YEAR)
        var month = cal.get(Calendar.MONTH)
        var day = cal.get(Calendar.DAY_OF_MONTH)
//
//        fileName = ""+ year + "_" + month + "_" + day + ".txt"
        fileName = ""+ year + "_" + (month + 1) + "_" + day + ".txt"
        var str1 : String = readDiary(fileName)
        edtDiary.setText(str1)

//        btnWrite.isEnabled = true

        //쓰기 버튼을 눌렀을때, 1.날짜와 2.내용을 "파일에 쓴다"
        btnWrite.setOnClickListener {
//            var outFs : FileOutputStream = openFileOutput("aaa.txt", Context.MODE_PRIVATE)
            var outFs : FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            var str1 = edtDiary.text.toString()
            outFs.write(str1.toByteArray())
            outFs.close()
            Toast.makeText(this, "저장됨!",Toast.LENGTH_SHORT).show()
        }

        dp.init(year, month, day) { datePicker, year1, month1, day1 ->
            
            fileName = ""+ year1 + "_" + (month1 + 1) + "_" + day1 + ".txt"
            
            //만약에 해당날짜에 일기가 있으면, 불러온다.
             var str1 : String = readDiary(fileName)
            //에디트텍스트에 출력한다.
            edtDiary.setText(str1)
            btnWrite.isEnabled = true
        }
    }

    private fun readDiary(fileName: String): String {
        var str1 = ""//내용 String
        var byteArr = ByteArray(500)//내용 byte배열

        try {
            var inFs = openFileInput(fileName)
            inFs.read(byteArr)
            inFs.close()
            str1 = byteArr.toString(Charsets.UTF_8)
            btnWrite.text = "수정하기"
        }
        catch (e1 : IOException)
        {
            edtDiary.hint = "해당날짜에 일기 없음"
            btnWrite.text = "새로저장"

        }
        return str1
    }
}