package com.example.catchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.catchthekenny.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var score = 0
    var imageArray = ArrayList<ImageView>()

    var runnable = Runnable {}
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArray.add(binding.imageViewKenny)
        imageArray.add(binding.imageViewKenny2)
        imageArray.add(binding.imageViewKenny3)
        imageArray.add(binding.imageViewKenny4)
        imageArray.add(binding.imageViewKenny5)
        imageArray.add(binding.imageViewKenny6)
        imageArray.add(binding.imageViewKenny7)
        imageArray.add(binding.imageViewKenny8)
        imageArray.add(binding.imageViewKenny9)



        hideImages()

        object : CountDownTimer(30500,1000){
            override fun onTick(p0: Long) {
                binding.textViewTime.text = "Time : ${p0 / 1000}"
            }

            override fun onFinish() {
                binding.textViewTime.text = "Time : 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over :)")
                alert.setMessage("Do you want to play again ?")
                alert.setPositiveButton("Yes"){ dialogInterface, i ->
                    // restart
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                }

                alert.setNegativeButton("No"){ dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"Game Over!",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

        }.start()

    }

    fun artir(view : View){
        score = score + 1
        binding.textViewScore.text = "Score : ${score}"
    }

    fun hideImages(){

        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,400)
            }
        }
        handler.post(runnable)
    }


}