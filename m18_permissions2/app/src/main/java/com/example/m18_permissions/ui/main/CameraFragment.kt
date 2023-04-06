package com.example.m18_permissions.ui.main

import android.Manifest
import android.app.PendingIntent

import android.content.ContentValues

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.m18_permissions.App
import com.example.m18_permissions.MainActivity
import com.example.m18_permissions.R
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.databinding.FragmentCameraBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm-ss"

@AndroidEntryPoint
class CameraFragment : Fragment() {

    private val cameraFragmentViewModel: CameraFragmentViewModel by viewModels()

    val contentResolver get() = requireActivity().contentResolver

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!


    private val name =
        SimpleDateFormat(FILE_NAME_FORMAT, Locale.US).format(System.currentTimeMillis())


    private var imageCapture: ImageCapture? = null
    private lateinit var executor: Executor

    private val launch =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            if (map.values.all { it }) {
                startCamera()
            } else {
                Toast.makeText(
                    this.requireContext(),
                    "permission is not Granted",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }


        }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    private fun checkPermission() {
        val isAllGranted = REQUEST_PERMISSIONS.all { permisions ->
            ContextCompat.checkSelfPermission(
                requireContext(),
                permisions
            ) == PackageManager.PERMISSION_GRANTED
        }
        if (isAllGranted) {
            startCamera()
            Toast.makeText(context, "granted", Toast.LENGTH_SHORT).show()
        } else {
            launch.launch(REQUEST_PERMISSIONS)
        }
    }

    private fun takePhoto() {
        createNotification()

        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")

        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
            ).build()

        imageCapture.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(
                        requireContext(), "Photo ${outputFileResults.savedUri}", Toast.LENGTH_SHORT
                    ).show()
                    Log.d("TAG", "${outputFileResults.savedUri}")


                    Glide.with(this@CameraFragment)
                        .load(outputFileResults.savedUri)
                        .into(binding.imageView3)

                    lifecycleScope.launchWhenStarted {
                        val uri = outputFileResults.savedUri.toString()
                        val photo = Photo(uri)
                        cameraFragmentViewModel.insert(photo)
                    }

                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        requireContext(),
                        "Photo error ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    exception.printStackTrace()
                }
            }
        )
    }

    fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
            imageCapture = ImageCapture.Builder().build()

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageCapture
            )
        }, executor)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        executor = ContextCompat.getMainExecutor(this.requireContext())
        checkPermission()
        startCamera()
        binding.takePhotoButton.setOnClickListener {
            takePhoto()
            FirebaseCrashlytics.getInstance().log("Log message")
            try {
                throw RuntimeException("Test Crash record EXCEPTION")
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)

            }

        }


        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            Log.d("reg token", it.result)
        }

        return binding.root


    }


    fun createNotification() {

        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.getActivity(requireContext(), O, intent, PendingIntent.FLAG_IMMUTABLE)
        else
            PendingIntent.getActivity(
                requireContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val notification = NotificationCompat.Builder(requireContext(), App.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setContentText("my Notification")
            .setContentText("текст моего уведомления")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Manifest.permission.POST_NOTIFICATIONS

            //return
        }
        NotificationManagerCompat.from(requireContext()).notify(NOTIFICATION_ID, notification)
    }


    companion object {


        private const val NOTIFICATION_ID = 1000


        private val REQUEST_PERMISSIONS: Array<String> = buildList {
            add(Manifest.permission.CAMERA)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()

    }
}