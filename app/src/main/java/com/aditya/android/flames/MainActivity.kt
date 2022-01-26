package com.aditya.android.flames

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.aditya.android.flames.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

        var first: String = binding.firstEditText.text.toString().trim()
        var second: String = binding.secondEditText.text.toString().trim()

        var length: Int = findLength(first,second)
        var ans = flames(length)

        binding.resultTextview.text = ans
        }

    }
    private fun flamesStringResponse(ans:Char):String{
        var response: String
        when(ans){
            'f'-> response = "FRIENDS"
            'l'-> response = "LOVERS"
            'a'-> response = "AFFECTIONATE"
            'm'-> response = "MARRIAGE"
            'e'-> response = "ENEMIES"
            else ->response = "SIBLINGS"
        }
        return response
    }

    private fun flames(length: Int): String{
        val flamesList: ArrayList<Char> = convertStringToCharList("flames")
        while(flamesList.size>1){
            var removeIndex: Int = length%(flamesList.size)
            if(removeIndex==0){
                removeIndex= flamesList.size
            }
            removeIndex--
            flamesList.removeAt(removeIndex)
        }
        var ans: String =  flamesStringResponse(flamesList.get(0))
        return ans
    }

    private fun findLength(first: String, second: String): Int {
        val firstLength = first.length
        val secondLength = second.length
        var totalLength = firstLength + secondLength

        var firstName: ArrayList<Char> = convertStringToCharList(first)
        var secondName: ArrayList<Char> = convertStringToCharList(second)

        var i = 0
        while (i < firstName.size) {
            for (j in 0 until secondName.size) {
                if (firstName.get(i) === secondName.get(j)) {
                    secondName.removeAt(j)
                    totalLength -= 2
                    firstName.removeAt(i)
                    i--
                    break
                }
            }
            i++
        }

        return if (secondName.size === 0 && firstName.size === 0) 0 else totalLength
    }
    fun convertStringToCharList(str: String): ArrayList<Char> {
        // Create an empty List of character
        val chars: ArrayList<Char> = ArrayList()
        // For each character in the String add it to the List
        for (ch in str.toCharArray()) {
            chars.add(ch)
        }
        // return the List
        return chars
    }
}