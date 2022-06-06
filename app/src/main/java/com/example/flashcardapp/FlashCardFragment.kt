package com.example.flashcardapp

import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentFlashCardBinding
import com.example.flashcardapp.databinding.FragmentStudyCardsBinding


class FlashCardFragment : Fragment() {

    private var _binding: FragmentFlashCardBinding? = null
    private val binding get() = _binding!!
    var soundPool: SoundPool? = null
    var quizMusic = listOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlashCardBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val args = FlashCardFragmentArgs.fromBundle(requireArguments())
        val question = args.questionArg
        val answer = args.answerArg
        binding.flashCardTextView.text = question
        binding.flashCardTextView.setOnClickListener {
            val text = binding.flashCardTextView.text.toString()
            if (text == question){ //change the text from question to answer when clicked (question -> answer)
                binding.flashCardTextView.text = answer
            }
            else if (text == answer){ //change the text from answer to question when clicked (answer -> question)
                binding.flashCardTextView.text = question
            }
            soundPool?.play(quizMusic[(0..1).random()], 1F,1F,1,0, 1F)
        }
        binding.flashCardPhotoCameraButton.setOnClickListener{
            val action = FlashCardFragmentDirections.actionFlashCardFragmentToCameraFragment()
            rootView.findNavController().navigate(action)
        }
        setFragmentResultListener("requestKey"){ requestKey, bundle ->
            val result = bundle.get("bundleKey")
            binding.flashCardPhotoImageView.setImageBitmap(result as Bitmap)
        }
        var audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(6)
            .setAudioAttributes(audioAttributes)
            .build()
        quizMusic = listOf(
            soundPool!!.load(activity,R.raw.quizmusic1,1),
            soundPool!!.load(activity,R.raw.quizmusic2,1)
        )


        return rootView
    }

}