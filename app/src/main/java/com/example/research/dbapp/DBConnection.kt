package com.example.research.dbapp

import java.sql.*
import java.util.*

class DBConnection {
    internal var conn: Connection? = null
    internal var username = "dba"
    internal var password = "admin"
    fun executeMySQLQuery() {
        Thread(Runnable {
            var stmt: Statement? = null
            var resultset: ResultSet? = null
            try {
                stmt = conn!!.createStatement()
                resultset = stmt!!.executeQuery("SHOW DATABASES;")
                if (stmt.execute("SHOW DATABASES;")) {
                    resultset = stmt.resultSet
                }
                while (resultset!!.next()) {
                    println(resultset.getString("Database"))
                }
            } catch (ex: SQLException) {
                // handle any errors
                ex.printStackTrace()
            } finally {
                // release resources
                if (resultset != null) {
                    try {
                        resultset.close()
                    } catch (sqlEx: SQLException) {
                    }
                    resultset = null
                }
                if (stmt != null) {
                    try {
                        stmt.close()
                    } catch (sqlEx: SQLException) {
                    }
                    stmt = null
                }
                if (conn != null) {
                    try {
                        conn!!.close()
                    } catch (sqlEx: SQLException) {
                    }
                    conn = null
                }
            }
        }).start()

    }
    fun getConnection() {
        Thread(Runnable {
            val connectionProps = Properties()
            connectionProps.put("user", username)
            connectionProps.put("password", password)
            try {
                Class.forName("cubrid.jdbc.driver.CUBRIDDriver").newInstance()
                conn = DriverManager.getConnection(
                    "jdbc:CUBRID:192.168.0.200:30000:survey_zinna:survey_zinna::?charset=utf8",
                    connectionProps
                )

                conn!!.autoCommit = false

            } catch (ex: SQLException) {
                // handle any errors
                ex.printStackTrace()
            } catch (ex: Exception) {
                // handle any errors
                ex.printStackTrace()
            }
        }).start()
    }
}