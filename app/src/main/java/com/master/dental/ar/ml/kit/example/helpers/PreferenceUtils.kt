package com.master.dental.ar.ml.kit.example.helpers

import android.content.Context
import android.os.Build.VERSION_CODES
import android.preference.PreferenceManager
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.camera.core.CameraSelector
import com.google.common.base.Preconditions
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.master.dental.ar.ml.kit.example.R

import java.lang.Exception


/** Utility class to retrieve shared preferences.  */
object PreferenceUtils {
    private const val POSE_DETECTOR_PERFORMANCE_MODE_FAST = 1
    fun saveString(context: Context, @StringRes prefKeyId: Int, value: String?) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(context.getString(prefKeyId), value)
            .apply()
    }

//    fun getCameraPreviewSizePair(context: Context, cameraId: Int): SizePair? {
//        Preconditions.checkArgument(
//            cameraId == CameraSource.CAMERA_FACING_BACK
//                    || cameraId == CameraSource.CAMERA_FACING_FRONT
//        )
//        val previewSizePrefKey: String
//        val pictureSizePrefKey: String
//        if (cameraId == CameraSource.CAMERA_FACING_BACK) {
//            previewSizePrefKey = context.getString(R.string.pref_key_rear_camera_preview_size)
//            pictureSizePrefKey = context.getString(R.string.pref_key_rear_camera_picture_size)
//        } else {
//            previewSizePrefKey = context.getString(R.string.pref_key_front_camera_preview_size)
//            pictureSizePrefKey = context.getString(R.string.pref_key_front_camera_picture_size)
//        }
//        return try {
//            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//            SizePair(
//                com.google.android.gms.common.images.Size.parseSize(
//                    sharedPreferences.getString(
//                        previewSizePrefKey,
//                        null
//                    )
//                ),
//                com.google.android.gms.common.images.Size.parseSize(
//                    sharedPreferences.getString(
//                        pictureSizePrefKey,
//                        null
//                    )
//                )
//            )
//        } catch (e: Exception) {
//            null
//        }
//    }

    @RequiresApi(VERSION_CODES.LOLLIPOP)
    fun getCameraXTargetResolution(context: Context, lensfacing: Int): Size? {
        Preconditions.checkArgument(
            lensfacing == CameraSelector.LENS_FACING_BACK
                    || lensfacing == CameraSelector.LENS_FACING_FRONT
        )
        val prefKey =
            if (lensfacing == CameraSelector.LENS_FACING_BACK) context.getString(R.string.pref_key_camerax_rear_camera_target_resolution) else context.getString(
                R.string.pref_key_camerax_front_camera_target_resolution
            )
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return try {
            Size.parseSize(sharedPreferences.getString(prefKey, null))
        } catch (e: Exception) {
            null
        }
    }

    fun shouldHideDetectionInfo(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = context.getString(R.string.pref_key_info_hide)
        return sharedPreferences.getBoolean(prefKey, false)
    }

//    fun getObjectDetectorOptionsForStillImage(context: Context): ObjectDetectorOptions {
//        return getObjectDetectorOptions(
//            context,
//            R.string.pref_key_still_image_object_detector_enable_multiple_objects,
//            R.string.pref_key_still_image_object_detector_enable_classification,
//            ObjectDetectorOptions.SINGLE_IMAGE_MODE
//        )
//    }
//
//    fun getObjectDetectorOptionsForLivePreview(context: Context): ObjectDetectorOptions {
//        return getObjectDetectorOptions(
//            context,
//            R.string.pref_key_live_preview_object_detector_enable_multiple_objects,
//            R.string.pref_key_live_preview_object_detector_enable_classification,
//            ObjectDetectorOptions.STREAM_MODE
//        )
//    }

//    private fun getObjectDetectorOptions(
//        context: Context,
//        @StringRes prefKeyForMultipleObjects: Int,
//        @StringRes prefKeyForClassification: Int,
//         mode: Int
//    ): ObjectDetectorOptions {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val enableMultipleObjects =
//            sharedPreferences.getBoolean(context.getString(prefKeyForMultipleObjects), false)
//        val enableClassification =
//            sharedPreferences.getBoolean(context.getString(prefKeyForClassification), true)
//        val builder: ObjectDetectorOptions.Builder = Builder().setDetectorMode(mode)
//        if (enableMultipleObjects) {
//            builder.enableMultipleObjects()
//        }
//        if (enableClassification) {
//            builder.enableClassification()
//        }
//        return builder.build()
//    }

//    fun getCustomObjectDetectorOptionsForStillImage(
//        context: Context, localModel: LocalModel
//    ): CustomObjectDetectorOptions {
//        return getCustomObjectDetectorOptions(
//            context,
//            localModel,
//            R.string.pref_key_still_image_object_detector_enable_multiple_objects,
//            R.string.pref_key_still_image_object_detector_enable_classification,
//            CustomObjectDetectorOptions.SINGLE_IMAGE_MODE
//        )
//    }

//    fun getCustomObjectDetectorOptionsForLivePreview(
//        context: Context, localModel: LocalModel
//    ): CustomObjectDetectorOptions {
//        return getCustomObjectDetectorOptions(
//            context,
//            localModel,
//            R.string.pref_key_live_preview_object_detector_enable_multiple_objects,
//            R.string.pref_key_live_preview_object_detector_enable_classification,
//            CustomObjectDetectorOptions.STREAM_MODE
//        )
//    }
//
//    private fun getCustomObjectDetectorOptions(
//        context: Context,
//        localModel: LocalModel,
//        @StringRes prefKeyForMultipleObjects: Int,
//        @StringRes prefKeyForClassification: Int,
//        @DetectorMode mode: Int
//    ): CustomObjectDetectorOptions {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val enableMultipleObjects =
//            sharedPreferences.getBoolean(context.getString(prefKeyForMultipleObjects), false)
//        val enableClassification =
//            sharedPreferences.getBoolean(context.getString(prefKeyForClassification), true)
//        val builder: CustomObjectDetectorOptions.Builder = Builder(localModel).setDetectorMode(mode)
//        if (enableMultipleObjects) {
//            builder.enableMultipleObjects()
//        }
//        if (enableClassification) {
//            builder.enableClassification().setMaxPerObjectLabelCount(1)
//        }
//        return builder.build()
//    }

    fun getFaceDetectorOptions(context: Context): FaceDetectorOptions {
        val landmarkMode = getModeTypePreferenceValue(
            context,
            R.string.pref_key_live_preview_face_detection_landmark_mode,
            FaceDetectorOptions.LANDMARK_MODE_NONE
        )

        val contourMode = getModeTypePreferenceValue(
            context,
            R.string.pref_key_live_preview_face_detection_contour_mode,
            FaceDetectorOptions.CONTOUR_MODE_ALL
        )
        val classificationMode = getModeTypePreferenceValue(
            context,
            R.string.pref_key_live_preview_face_detection_classification_mode,
            FaceDetectorOptions.CLASSIFICATION_MODE_NONE
        )
        val performanceMode = getModeTypePreferenceValue(
            context,
            R.string.pref_key_live_preview_face_detection_performance_mode,
            FaceDetectorOptions.PERFORMANCE_MODE_FAST
        )
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val enableFaceTracking = sharedPreferences.getBoolean(
            context.getString(R.string.pref_key_live_preview_face_detection_face_tracking), false
        )
        val minFaceSize =
            sharedPreferences.getString(
                context.getString(R.string.pref_key_live_preview_face_detection_min_face_size),
                "0.1"
            )!!.toFloat()
        val optionsBuilder = FaceDetectorOptions.Builder()
//            .setLandmarkMode(landmarkMode)
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
//            .setClassificationMode(classificationMode)
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setMinFaceSize(0.2f)
        if (true) {
            optionsBuilder.enableTracking()
        }
        return optionsBuilder.build()
    }

//    fun getPoseDetectorOptionsForLivePreview(context: Context): PoseDetectorOptionsBase {
//        val performanceMode = getModeTypePreferenceValue(
//            context,
//            R.string.pref_key_live_preview_pose_detection_performance_mode,
//            POSE_DETECTOR_PERFORMANCE_MODE_FAST
//        )
//        return if (performanceMode == POSE_DETECTOR_PERFORMANCE_MODE_FAST) {
//            Builder()
//                .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
//                .build()
//        } else {
//            Builder()
//                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
//                .build()
//        }
//    }

//    fun getPoseDetectorOptionsForStillImage(context: Context): PoseDetectorOptionsBase {
//        val performanceMode = getModeTypePreferenceValue(
//            context,
//            R.string.pref_key_still_image_pose_detection_performance_mode,
//            POSE_DETECTOR_PERFORMANCE_MODE_FAST
//        )
//        return if (performanceMode == POSE_DETECTOR_PERFORMANCE_MODE_FAST) {
//            Builder()
//                .setDetectorMode(PoseDetectorOptions.SINGLE_IMAGE_MODE)
//                .build()
//        } else {
//            Builder()
//                .setDetectorMode(AccuratePoseDetectorOptions.SINGLE_IMAGE_MODE)
//                .build()
//        }
//    }

//    fun shouldGroupRecognizedTextInBlocks(context: Context): Boolean {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val prefKey = context.getString(R.string.pref_key_group_recognized_text_in_blocks)
//        return sharedPreferences.getBoolean(prefKey, true)
//    }
//
//    fun shouldShowPoseDetectionInFrameLikelihoodLivePreview(context: Context): Boolean {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val prefKey =
//            context.getString(R.string.pref_key_live_preview_pose_detector_show_in_frame_likelihood)
//        return sharedPreferences.getBoolean(prefKey, true)
//    }
//
//    fun shouldShowPoseDetectionInFrameLikelihoodStillImage(context: Context): Boolean {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val prefKey =
//            context.getString(R.string.pref_key_still_image_pose_detector_show_in_frame_likelihood)
//        return sharedPreferences.getBoolean(prefKey, true)
//    }
//
//    fun shouldPoseDetectionVisualizeZ(context: Context): Boolean {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val prefKey = context.getString(R.string.pref_key_pose_detector_visualize_z)
//        return sharedPreferences.getBoolean(prefKey, true)
//    }
//
//    fun shouldPoseDetectionRescaleZForVisualization(context: Context): Boolean {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val prefKey = context.getString(R.string.pref_key_pose_detector_rescale_z)
//        return sharedPreferences.getBoolean(prefKey, true)
//    }
//
//    fun shouldPoseDetectionRunClassification(context: Context): Boolean {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val prefKey = context.getString(R.string.pref_key_pose_detector_run_classification)
//        return sharedPreferences.getBoolean(prefKey, false)
//    }
//
//    fun shouldSegmentationEnableRawSizeMask(context: Context): Boolean {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val prefKey = context.getString(R.string.pref_key_segmentation_raw_size_mask)
//        return sharedPreferences.getBoolean(prefKey, false)
//    }

    /**
     * Mode type preference is backed by [android.preference.ListPreference] which only support
     * storing its entry value as string type, so we need to retrieve as string and then convert to
     * integer.
     */
    private fun getModeTypePreferenceValue(
        context: Context, @StringRes prefKeyResId: Int, defaultValue: Int
    ): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = context.getString(prefKeyResId)
        return sharedPreferences.getString(prefKey, defaultValue.toString())!!.toInt()
    }

    fun isCameraLiveViewportEnabled(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = context.getString(R.string.pref_key_camera_live_viewport)
        return sharedPreferences.getBoolean(prefKey, false)
    }
}