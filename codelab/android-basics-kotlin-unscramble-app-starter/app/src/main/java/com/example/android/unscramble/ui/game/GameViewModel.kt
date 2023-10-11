package com.example.android.unscramble.ui.game

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TtsSpan
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * @author Dohyeok Kim
 * https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?hl=ko#0
 * https://developer.android.com/codelabs/basic-android-kotlin-training-livedata?hl=ko#0
 */
class GameViewModel : ViewModel() {

    private val _score = MutableLiveData(0)
    private val _currentWordCount = MutableLiveData(0)
    private val _currentScrambledWord = MutableLiveData<String>()

    val score: LiveData<Int>
        get() = _score

    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    val currentScrambledWord: LiveData<Spannable> = Transformations.map(_currentScrambledWord) {
        if (it == null) {
            SpannableString("")
        } else {
            val scrumbledWord = it.toString()
            val spannable: Spannable = SpannableString(scrumbledWord)
            spannable.setSpan(
                TtsSpan.VerbatimBuilder(scrumbledWord).build(),
                0,
                scrumbledWord.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannable
        }
    }

    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        getNextWord()
    }

    fun nextWord(): Boolean {
        return if (currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordList.clear()
        getNextWord()
    }

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if (wordList.contains(currentWord)) {
            getNextWord()
        } else {
            Log.d("GameFragment", "getNextWord: answer: $currentWord")
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordList.add(currentWord)
        }
    }

    private fun increaseScore() {
        _score.value = (score.value)?.plus(SCORE_INCREASE)
    }
}