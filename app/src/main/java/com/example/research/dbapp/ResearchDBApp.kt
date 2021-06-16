package com.example.research.dbapp

import android.R
import android.R.id.button1
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import cubrid.jdbc.driver.CUBRIDDriver
import java.sql.*


class ResearchDBApp {

    var conn: Connection? = null
    var stmt: Statement? = null
    var rs: ResultSet? = null

    /*
    override fun onCreate() {
        super.onCreate()
        c()
        s()
    }
     */


    // 커넥트 부분

    // 커넥트 부분
    fun c() {
        try {

            Log.d("c try", "1==============1")

            Class.forName("cubrid.jdbc.driver.CUBRIDDriver").newInstance()

            Log.d("c try", "2==============2")

            // ("jdbc:CUBRID:서버아이피:포트:디비명:::?케릭터셋",아이디,패스워드)
            conn = DriverManager
                .getConnection(
                    "jdbc:cubrid:192.168.0.200:30000:survey_zinna:::?charset=UTF-8",
                    "dba", "admin"
                )

            Log.d("c try", "3==============3")

            conn!!.autoCommit = false

            Log.d("c try", "4==============4")

            Log.d("c:", "c==============c  " + conn)

        } catch (e: Exception) {
            Log.e("c", e.message.toString())
        } catch ( e: SQLException ){
            Log.e("c sql", e.message.toString())
        }
    }


    fun s() {
        try {
            //val sql = "select f_name from code"
            val sql = "select code_nm from commcode"
            stmt = conn!!.createStatement()
            rs = stmt!!.executeQuery(sql)

            // 제가 여기서 고생을 좀 했습니다. 큐브리드는 컬럼이 0이 아닌 1부터 시작하더군요..
            while (rs!!.next()) {
                Log.e("rs.getString(1)", "" + rs!!.getString(1))
            }
        } catch (e: Exception) {
            Log.e("s", e.message.toString())
        }
    }

}