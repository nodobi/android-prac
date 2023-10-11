package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * @author Dohyeok Kim
 * https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?hl=ko#0
 */
class GameViewModel: ViewModel() {

    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord: String

    val score: Int
        get() = _score

    val currentWordCount: Int
        get() = _currentWordCount

    val currentScrambledWord: String
        get() = _currentScrambledWord

    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel: Created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "onCleared: GameViewModel Destroyed!")
    }

    fun nextWord(): Boolean {
        return if(currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if(playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()

        while(String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if(wordList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordList.add(currentWord)
        }
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }
}