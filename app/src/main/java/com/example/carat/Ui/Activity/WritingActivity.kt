package com.example.carat.Ui.Activity

import android.content.ClipData
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.carat.Model.UserObject
import com.example.carat.Presenter.Writing.WritingContract
import com.example.carat.Presenter.Writing.WritingPresenter
import com.example.carat.R
import kotlinx.android.synthetic.main.activity_writing.*
import kotlinx.android.synthetic.main.widget_appbar.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class WritingActivity : AppCompatActivity() {
    private val PICTURE_REQUEST_CODE = 100
    private val writingPresenter: WritingContract.Presenter = WritingPresenter()
    private val appbar: Toolbar by lazy {
        writing_appbar_include.widget_toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)

        settingActionBar()
        setClickEvents()
        setProfileImage()
        selectImages()
    }

    private fun settingActionBar() {
        appbar.apply {
            setSupportActionBar(this)
            appbar_backKey_imageView.setImageResource(R.drawable.icon_close_button)
            appbar_title_textView.visibility = View.GONE
            appbar_save_textView.text = "캐링하기"
        }
    }

    private fun setClickEvents() {
        appbar.apply {
            appbar_backKey_imageView.setOnClickListener {
                finish()
            }
            appbar_save_textView.setOnClickListener {
                val content: String = writing_content_editText.text.toString()
                if (content != "") writingPresenter.saveContent(content)
                finish()
            }
        }
    }

    private fun setProfileImage() {
        Glide.with(this)
            .load(UserObject.getInstance().profile)
            .circleCrop()
            .into(writing_profile_imageView)
    }

    private fun selectImages() {
        linearLayout_writing.setOnLongClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICTURE_REQUEST_CODE
            )

            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            linearLayout_showImages.removeAllViews()
            val clipData: ClipData? = data?.clipData

            if (clipData != null) {
                for (i in 0 until clipData.itemCount) {
                    val uri: Uri = clipData.getItemAt(i).uri
                    val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1f
                    )

                    layoutParams.setMargins(0, 10, 0, 0)
                    writingPresenter.addImage(getImageFromUri(uri))

                    val imageView = ImageView(this)
                    imageView.setImageURI(uri)
                    imageView.layoutParams = layoutParams
                    linearLayout_showImages.addView(imageView)
                }
            }
        }
    }

    private fun getImageFromUri(uri: Uri): MultipartBody.Part {
        val file = File(convertImageToPart(uri)!!)
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        return MultipartBody.Part.createFormData("file", file.name, requestBody)
    }

    private fun convertImageToPart(uri: Uri): String? {
        val cursor: Cursor? = this.contentResolver.query(uri, null, null, null, null)
        val path = if (cursor == null) {
            uri.path
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }

        cursor?.close()
        return path
    }
}