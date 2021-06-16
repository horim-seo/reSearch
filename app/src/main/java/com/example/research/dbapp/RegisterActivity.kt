package com.example.research.dbapp

import android.os.AsyncTask;
import android.util.Log

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class RegisterActivity : AsyncTask<String, Void, String>() {

    var sendMsg: String? = null
    var receiveMsg:kotlin.String? = null

    override fun doInBackground(vararg strings: String): String? {
        try {
            var str: String?

            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            val url = URL("http://192.168.0.8:8083/Android/researchDB.jsp")
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            conn.setRequestMethod("POST")
            val osw = OutputStreamWriter(conn.getOutputStream())

            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "id=" + strings[0] + "&pw=" + strings[1]
            osw.write(sendMsg)
            osw.flush()

            Log.d("sendMsg:", "sendMsg======================= "+sendMsg)

            //jsp와 통신 성공 시 수행
            //if (conn.getResponseCode() === conn.HTTP_OK) {
            if (conn.getResponseCode() != null ) {
                val tmp = InputStreamReader(conn.getInputStream(), "UTF-8")
                val reader = BufferedReader(tmp)
                val buffer = StringBuffer()

                // jsp에서 보낸 값을 받는 부분
                while (reader.readLine().also { str = it } != null) {
                    buffer.append(str)
                }
                receiveMsg = buffer.toString()
            } else {
                // 통신 실패
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        Log.d("receiveMsg:", "receiveMsg======================= "+receiveMsg)

        //jsp로부터 받은 리턴 값
        return receiveMsg
    }

}