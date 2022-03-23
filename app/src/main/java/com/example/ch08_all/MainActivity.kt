package com.example.ch08_all

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1 = findViewById<Button>(R.id.btnWrite)
        var btn2 = findViewById<Button>(R.id.btnRead)

        btn1.setOnClickListener {
            //1.내 앱이이, [파일]아웃풋스트림 객체를 만들어, 파일에다가 쓴다
            //String 형태를 Byte[]배열 형태 보낸다.(쓰기)
            //Byte[]배열 형태를 String으로 가져온다.(읽기)

            //1.
            var outFs :  FileOutputStream = openFileOutput("file1.txt", Context.MODE_PRIVATE)
            var str1 = "안녕하세요~!!"
            outFs.write(str1.toByteArray())//스트링을 바이트배열로 해서 저장해야만 함~!
            outFs.close()

            Toast.makeText(this, "파일 생성후! 저장됨~!", Toast.LENGTH_SHORT).show()
        }

        btn2.setOnClickListener {
            var inFs : FileInputStream = openFileInput("file1.txt")

            var byteArr1 = ByteArray(30)//스트림, 즉 바이트배열임[], 읽을라믄 어쩔수 없음

            inFs.read(byteArr1)//아직까지 바이트배열임, 스트링으로 바꿔줘야함!
            var str1 = byteArr1.toString(Charsets.UTF_8)
            Toast.makeText(this, str1, Toast.LENGTH_SHORT).show()
            inFs.close()
        }

    }
}
