package app.alhyatt.center.ui.profile

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.alhyatt.center.R
import app.alhyatt.center.base.BaseFragment
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.databinding.FragmentProfileBinding
import app.alhyatt.center.utilis.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.esafirm.imagepicker.features.ImagePicker
import com.jaiselrahman.filepicker.activity.FilePickerActivity
import com.jaiselrahman.filepicker.config.Configurations
import com.jaiselrahman.filepicker.model.MediaFile
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.util.*


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=getViewDataBinding()
        binding.profile.setOnClickListener {
            uploadImage()
        }
        binding.save.setOnClickListener {
            if (validateInput()){
                val user=SavePrefs<User>(binding.root.context,User::class.java).load()
                val body = HashMap<String?, String?>()
                body["name"] = binding.etName.getText().toString()
                body["id"]=user?.id
                //body["gender"] = gender
                //body["blood_type"] = bloodType
                body["hayat_id"] = binding.hayatId.getText().toString()
                body["mobile"] = binding.etMobile.getText().toString()
                body["location"] = binding.etAddress.getText().toString()
                body["address"] = binding.etAddress.getText().toString()
                body["age"] = binding.etAge.getText().toString()
                binding.save.setOnClickListener {
                    viewModel.updateProfile(body)
                }
            }
        }
        viewModel.userData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            SavePrefs(binding.root.context, User::class.java).save(it)
            Toast.makeText(binding.root.context,getString(R.string.save_success),Toast.LENGTH_LONG).show()
        })
        binding.call.setOnClickListener {
            AppUtils.openDual(binding.root
                .context, ambuklance
            )
        }

        setDataInview()
    }

    private fun setDataInview() {
        val user=SavePrefs<User>(binding.root.context,User::class.java).load()
        user?.let {
            binding.etAddress.setText(user.address)
            binding.etMobile.setText(user.mobile)
            binding.etName.setText(user.name)
            binding.hayatId.setText(user.hayat_id)
            binding.etAge.setText(user.age)
            user.image?.let {
                AppUtils.loadGlideImage(binding.root.context,
                    BASE_URL_IMAGE+it,R.drawable.profile,binding.profile)
            }
        }

    }

    private fun validateInput(): Boolean {
        var isValid = true
        if (binding.etName.text.isBlank()) {
            binding.etName.error = getString(R.string.field_required)
            isValid = false
        }
        if (binding.etMobile.text.isBlank()) {
            binding.etMobile.error = getString(R.string.field_required)
            isValid = false
        }
        if (binding.etAge.text.isBlank()) {
            binding.etAge.error = getString(R.string.field_required)
            isValid = false
        }

        if (binding.hayatId.text.isBlank()) {
            Toast.makeText(binding.root.context,getString(R.string.enterHayatId),Toast.LENGTH_LONG).show()
            isValid = false
        }
        if (binding.etAddress.text.isBlank()) {
            binding.etAddress.setError(getString(R.string.field_required))
            isValid = false
        }
        return isValid
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    private fun uploadImage() {
        val intent = Intent(binding.root.context, FilePickerActivity::class.java)
        intent.putExtra(
            FilePickerActivity.CONFIGS, Configurations.Builder()
                .setCheckPermission(true)
                .setShowImages(true)
                .enableImageCapture(true)
                .setShowVideos(false)
                .setSingleChoiceMode(true)
                .setSingleClickSelection(true)
                .build()
        )
        startActivityForResult(intent,1)
    }

    private fun setMediaFiles(mediaFiles: List<MediaFile>) {
        if (mediaFiles.isNotEmpty()){
            val mediaFile=mediaFiles[0]
            Glide.with(binding.root.context).load(mediaFile.uri).apply(
                RequestOptions()
                    .centerCrop()
                    .dontAnimate()
                    .dontTransform()
                    .placeholder(R.drawable.profile)

            ).into(binding.profile)
            val file = FileUtils.getFile(binding.getRoot().context, mediaFile.uri)!!
            val body =createRequestForFile("parameters[0]",file,mediaFile.mimeType)
            viewModel.uploadImage(body)
        }
    }

    private fun createRequestForFile(
        partName: String,
        file: File,
        fileType: String
    ): MultipartBody.Part {
        // create RequestBody instance from file
        val requestFile = RequestBody.create(
            MediaType.parse(fileType),
            file
        )
        // MultipartBody.Part is used to send also the actual file name

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==1&&     resultCode == RESULT_OK
            && data != null){
            val mediaFiles: List<MediaFile>? =
                data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES)
            if (mediaFiles != null) {
                setMediaFiles(mediaFiles)
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.thereIsNoFileSelected),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}