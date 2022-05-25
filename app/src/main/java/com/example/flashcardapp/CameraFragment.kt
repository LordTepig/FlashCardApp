package com.example.flashcardapp

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentCameraBinding
import com.example.flashcardapp.databinding.FragmentCreateTrueFalseBinding
import com.example.flashcardapp.databinding.FragmentFlashCardBinding


class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val rootView = binding.root
        // Inflate the layout for this fragment
        launchCamera()
        binding.addImageToQuestionButton.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        return rootView
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.cameraImageView.setImageBitmap(imageBitmap)
        }
    }

    fun launchCamera(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }
}