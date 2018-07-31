package com.kiyosuke.androidoptional

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kiyosuke.optional.utils.Optional

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sample = "sample"

        // 結果 -> SAMPLE6
        Optional.ofNullable(sample)
                .map { it.toUpperCase() }
                .map { it + it.length }
                .ifPresent { println("sample= $it") }


        val sampleNull:String? = null

        // 結果 -> nullのデータは処理されないため何も表示されない
        Optional.ofNullable(sampleNull)
                .map { it.toUpperCase() }
                .map { it + it.length }
                .ifPresent { println("sample= $it") }

    }
}
