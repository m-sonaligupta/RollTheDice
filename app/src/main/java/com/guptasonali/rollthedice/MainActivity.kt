package com.guptasonali.rollthedice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var TAG:String = "MainActivity"
    var random_number:Int = 0
    var SCORE_PLAYER_A:Int = 0
    var SCORE_PLAYER_B:Int = 0
    var ACTIVE_PLAYER_A:Boolean = true
    var ACTIVE_PLAYER_B:Boolean = false
    var GAME_POINT:Int = 100
    var GAME_END_MSG:String? = null
    var GAME_END_MSG_DEFAULT:String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //disable dice image
        dice_image.isEnabled = false

//        val button = findViewById<Button>(R.id.btnGamePlay)
//        button.setOnClickListener()
//        {
//            Toast.makeText(this,"Button is clicked",
//                Toast.LENGTH_LONG).show() }
//
        btnGamePlay.setOnClickListener {
            //enable dice image
            Toast.makeText(this,"Button is clicked",Toast.LENGTH_LONG).show()

            dice_image.isEnabled = true

            //reset the score for the player
            SCORE_PLAYER_A = 0
            SCORE_PLAYER_B = 0

            //clear the text view and set the default value
            tvGameOverMsg.text =" "
            tvGamePointA.text = "0"
            tvGamePointB.text = "0"

            //enable the player A
            ACTIVE_PLAYER_A = true
            ACTIVE_PLAYER_B = false

            //reset the image with dice1
            dice_image.setImageResource(R.drawable.diceone)

        }

        dice_image.setOnClickListener {
            //generate random number
            random_number = (1..6).random()
            //when expression
            when (random_number) {
                1 -> {
                    dice_image.setImageResource(R.drawable.diceone)
                }
                2 -> {
                    dice_image.setImageResource(R.drawable.dicetwo)
                }
                3 -> {
                    dice_image.setImageResource(R.drawable.dicethree)
                }
                4 ->{
                    dice_image.setImageResource(R.drawable.dicefour)
                }
                5 ->{
                    dice_image.setImageResource(R.drawable.dicefive)
                }
                6 ->{
                    dice_image.setImageResource(R.drawable.dicesix)
                }
            }

            if (ACTIVE_PLAYER_A) {
                //add the score to player a
                SCORE_PLAYER_A += random_number
                //set the text of the textView
                tvGamePointA.text = SCORE_PLAYER_A.toString()
                //enable player b and disable player a
                ACTIVE_PLAYER_A = false
                ACTIVE_PLAYER_B = true

                //check the game point
                if(SCORE_PLAYER_A >= 100){
                    GAME_END_MSG_DEFAULT = resources.getText(R.string.game_over_msg).toString()
                    GAME_END_MSG = GAME_END_MSG_DEFAULT + "Player A"
                    tvGameOverMsg.text = GAME_END_MSG
                    //disable the image view
                    dice_image.isEnabled = false
                }
            }
            else{
                //add the score to player b
                SCORE_PLAYER_B += random_number
                //set the text of the textView
                tvGamePointB.text = SCORE_PLAYER_B.toString()
                //enable player a and disable player b
                ACTIVE_PLAYER_A = true
                ACTIVE_PLAYER_B = false

                //check the game point
                if (SCORE_PLAYER_B >= 100) {
                    GAME_END_MSG_DEFAULT = resources.getText(R.string.game_over_msg).toString()
                    GAME_END_MSG = GAME_END_MSG_DEFAULT + "Player B"
                    tvGameOverMsg.text = GAME_END_MSG
                    //disable the image view
                    dice_image.isEnabled = false
                }
            }



            //show the random number in our logcat
            Log.e(TAG, random_number.toString())
        }
    }
}


